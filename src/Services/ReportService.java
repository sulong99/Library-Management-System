package Services;

import Data.DAO.BookDAO;
import Data.DAO.BookRentalViewDAO;
import Models.BookModel;
import Models.BookRentalViewModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Klasa Raportów
 */
public class ReportService {

    private static AlertService alertService = new AlertService();

    /**
     * Metoda odpowiadająca za generowanie raportu zbioru bibliotecznego.
     * Metoda zwraca plik .pdf w formacie "Zbior-biblioteczny-" + dataWygenerowania + ".pdf".
     * W pliku znajduje się wygenerowana tabela zawierająca informację na temat książek oraz ilości dostępnych i niedostępnych.
     * Po wykonaniu operacji wyświetli się stosowny komunikat.
     * @see BookModel
     * @throws Exception
     */
    public static void generateLibraryCollectionReport(){
        try {
            List<BookModel> books = BookDAO.getList("SELECT DISTINCT Books.Books_id, Books.ISBN, Books.Title, Books.Description, CONCAT(Authors.FirstName, ' ', Authors.SecondName) AS 'Authors', Books.Category, Books.Publisher, Books.Year, Books.`is_taken` FROM Books LEFT JOIN Authors_Books ON Authors_Books.Book_id = Books.Books_id LEFT JOIN Authors ON Authors.Author_id = Authors_Books.Author_id");

            int noInaccessibles = getInaccessiblesNumber(books);

            LocalDateTime reportGenTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
            String formattedDate = reportGenTime.format(formatter);

            String path = "C:\\Zbior-biblioteczny-" + formattedDate + ".pdf";

            Document doc = new Document();
            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);


            PdfWriter.getInstance(doc, new FileOutputStream(path));

            doc.open();

            Paragraph title = new Paragraph("Raport zawartości zbioru bibliotecznego", new Font(helvetica,18));
            Paragraph stats = new Paragraph("Wszystkie: "+books.size()+" w tym dostępne: "+(books.size() - noInaccessibles)+" niedostępne: "+noInaccessibles+"", new Font(helvetica,16));
            Paragraph br = new Paragraph("\n");
            doc.add(title);
            doc.add(stats);
            doc.add(br);
            doc.add(createLibraryCollectionTable(books));
            doc.close();
            alertService.displaySuccesDialog("Skutecznie wygenerowano raport!");

        }catch (Exception ex){
            alertService.displayExceptionAlertDialog("operacja zakończona niepowodzeniem!");
            ex.printStackTrace();
        }
    }

    /**
     * Metoda odpowiadająca za generowanie raportu dotyczącego wypożyczeń.
     * Metoda zwraca plik .pdf w formacie "Wypozyczone-" + dataWygenerowania + ".pdf".
     * W pliku znajduje się wygenerowana tabela zawierająca informację na temat wypożyczonych książek oraz ilości dokonanych wypożyczeń w określonym przedziale czasowym.
     * Po wykonaniu operacji wyświetli się stosowny komunikat.
     * @see BookRentalViewModel
     * @param from określa datę od której liczymy wypożyczenia
     * @param to określa datę do której liczymy wypożyczenia
     */
    public static void generateBorrowedBooksReport(String from, String to){
        try {
            if (from == null || from.isEmpty()){
                from = "0000-00-00";
            }
            if (to == null || to.isEmpty()){
                DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
                to = formatter2.format(new Date());
            }

            List<BookRentalViewModel> rentals = BookRentalViewDAO.getList("SELECT DISTINCT br.BookRentals_id, b.Title, b.`is_taken`, CONCAT(a.FirstName,' ',a.SecondName) AS 'Author', CONCAT(u.FirstName,' ', u.LastName) AS 'User', b.Category, b.Publisher, b.ISBN, br.DateOfBorrow, br.DateOfReturn " +
                    "FROM BookRentals_Users bru " +
                    "INNER JOIN Users u ON u.Users_id=bru.User_id " +
                    "INNER JOIN BookRentals br ON br.BookRentals_id=bru.BookRentals_id " +
                    "INNER JOIN Books b ON b.Books_id=br.Book_id " +
                    "INNER JOIN Authors_Books ab ON ab.Book_id=b.Books_id " +
                    "INNER JOIN Authors a ON a.Author_id=ab.Author_id " +
                    "WHERE br.DateOfBorrow BETWEEN '"+from+"' AND '"+to+"'");

            LocalDateTime reportGenTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
            String formattedDate = reportGenTime.format(formatter);

            String path = "C:\\Wypozyczone-" + formattedDate + ".pdf";
            Document doc = new Document();
            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);

            PdfWriter.getInstance(doc, new FileOutputStream(path));

            doc.open();

            Paragraph title = new Paragraph("Raport wypożyczeń",  new Font(helvetica,18));
            Paragraph stats = new Paragraph("Wszystkie książki wypożyczone w tym okresie: "+rentals.size()+"",  new Font(helvetica,16));
            Paragraph br = new Paragraph("\n");

            doc.add(title);
            doc.add(stats);
            doc.add(br);

            doc.add(createBorrowedTable(rentals));

            doc.close();
            alertService.displaySuccesDialog("Skutecznie wygenerowano raport!");

        }catch (Exception ex){
            alertService.displayExceptionAlertDialog("operacja zakończona niepowodzeniem!");
            ex.printStackTrace();
        }
    }

    /**
     * Metoda odpowiadająca za wyświetlanie tabeli zawierającej dane wypożyczonych książek.
     * @param rentals Lista wypożyczeń
     * @see BookRentalViewModel
     * @return table tabela zawierająca dane o wypożyczeniach
     * @throws IOException
     * @throws DocumentException
     */
    private static PdfPTable createBorrowedTable(List<BookRentalViewModel> rentals) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Tytuł", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Autor", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Kategoria", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Wydawnictwo", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("ISBN", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Data wyp.", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Czytelnik", new Font(helvetica,8)));
        table.addCell(cell);

        for (BookRentalViewModel b : rentals){
            cell = new PdfPCell(new Phrase(b.getTitle(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getAuthor(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getCategory(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getPublisher(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getISBN(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getFormattedDateOfBorrow(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getUser(), new Font(helvetica,8)));
            table.addCell(cell);
        }

        return table;
    }

    /**
     * Metoda odpowiadająca za generowanie raportu dotyczącego zwrotów.
     * Metoda zwraca plik .pdf w formacie "Zwroty-" + dataWygenerowania + ".pdf".
     * W pliku znajduje się wygenerowana tabela zawierająca informację na temat zwróconych książek oraz ilości dokonanych zwrotów w określonym przedziale czasowym.
     * Po wykonaniu operacji wyświetli się stosowny komunikat.
     * @see BookRentalViewModel
     * @param from określa datę od której liczymy zwroty
     * @param to określa datę do której liczymy zwroty
     */
    public static void generateReturnedBooksReport(String from, String to){
        try {
            if (from == null || from.isEmpty()){
                from = "0000-00-00";
            }
            if (to == null || to.isEmpty()){
                DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
                to = formatter2.format(new Date());
            }

            List<BookRentalViewModel> rentals = BookRentalViewDAO.getList("SELECT DISTINCT br.BookRentals_id, b.Title, b.`is_taken`, CONCAT(a.FirstName,' ',a.SecondName) AS 'Author', CONCAT(u.FirstName,' ', u.LastName) AS 'User', b.Category, b.Publisher, b.ISBN, br.DateOfBorrow, br.DateOfReturn " +
                    "FROM BookRentals_Users bru " +
                    "INNER JOIN Users u ON u.Users_id=bru.User_id " +
                    "INNER JOIN BookRentals br ON br.BookRentals_id=bru.BookRentals_id " +
                    "INNER JOIN Books b ON b.Books_id=br.Book_id " +
                    "INNER JOIN Authors_Books ab ON ab.Book_id=b.Books_id " +
                    "INNER JOIN Authors a ON a.Author_id=ab.Author_id " +
                    "WHERE br.DateOfReturn BETWEEN '"+from+"' AND '"+to+"'");

            LocalDateTime reportGenTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
            String formattedDate = reportGenTime.format(formatter);

            String path = "C:\\Zwroty-" + formattedDate + ".pdf";
            Document doc = new Document();
            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);

            PdfWriter.getInstance(doc, new FileOutputStream(path));

            doc.open();

            Paragraph title = new Paragraph("Raport zwrotow",  new Font(helvetica,18));
            Paragraph stats = new Paragraph("Wszystkie książki zwrócone w tym okresie: "+rentals.size()+"", new Font(helvetica,16));
            Paragraph br = new Paragraph("\n");

            doc.add(title);
            doc.add(stats);
            doc.add(br);

            doc.add(createReturnTable(rentals));

            doc.close();
            alertService.displaySuccesDialog("Skutecznie wygenerowano raport!");

        }catch (Exception ex){
            alertService.displayExceptionAlertDialog("operacja zakończona niepowodzeniem!");
            ex.printStackTrace();
        }
    }

    /**
     * Metoda odpowiadająca za utworzenie tabeli zawierającej informacje na temat zwrotów książek i ich ilości.
     * @param rentals Lista wypożyczeń
     * @return table Tabela zawierająca inforamcję na temat książek, które zostały zwrócone
     * @see BookRentalViewModel
     * @throws IOException
     * @throws DocumentException
     */
    private static PdfPTable createReturnTable(List<BookRentalViewModel> rentals) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100);
        BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Tytul", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Autor", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Kategoria", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Wydawnictwo", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("ISBN", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Data zwr.", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Czytelnik", new Font(helvetica,8)));
        table.addCell(cell);

        for (BookRentalViewModel b : rentals){
            cell = new PdfPCell(new Phrase(b.getTitle(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getAuthor(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getCategory(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getPublisher(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getISBN(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getFormattedDateOfReturn(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getUser(), new Font(helvetica,8)));
            table.addCell(cell);
        }

        return table;
    }

    /**
     * Metoda odpowiadająca za stworzenie tabeli zawierającej informacje na temat stanu bibliotecznego.
     * @param books Lista książek
     * @return table Tabela zawierająca informacje na temat książek będących w zbiorze bibliotecznym.
     * @see BookModel
     * @throws IOException
     * @throws DocumentException
     */
    private static PdfPTable createLibraryCollectionTable(List<BookModel> books) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Tytuł", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Autor", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Kategoria", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Wydawnictwo", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("ISBN", new Font(helvetica,8)));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Dostępność", new Font(helvetica,8)));
        table.addCell(cell);

        for (BookModel b : books){
            cell = new PdfPCell(new Phrase(b.getTitle(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getAuthor(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getCategory(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getPublisher(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getISBN(), new Font(helvetica,8)));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(b.getIsAvailableStr(), new Font(helvetica,8)));
            table.addCell(cell);
        }

        return table;
    }

    /**
     * Metoda odpowiadająca za zliczenie książek, mających status niedostępne.
     * @param books Lista książek
     * @return result - Ilość niedostępnych książek
     */
    private static int getInaccessiblesNumber(List<BookModel> books){
        int result = 0;

        for (BookModel b : books){
            if (b.getIsUnavailable()){
                result++;
            }
        }

        return result;
    }
}
