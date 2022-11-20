package Models;

import com.sun.istack.internal.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookRentalViewModel {
    private int bookRentalsId;
    private String title;
    private String author;
    private String user;
    private String category;
    private String publisher;
    private String ISBN;
    private Date dateOfBorrow;
    @Nullable
    private Date dateOfReturn;
    private String formattedDateOfReturn;
    private String formattedDateOfBorrow;

    public BookRentalViewModel() {
    }

    public BookRentalViewModel(int bookRentalsId, String title, String author, String category, String publisher, String ISBN, Date dateOfBorrow, Date dateOfReturn) {
        this.bookRentalsId = bookRentalsId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.dateOfBorrow = dateOfBorrow;
        this.dateOfReturn = dateOfReturn;
    }

    public int getBookRentalsId() {
        return bookRentalsId;
    }

    public void setBookRentalsId(int bookRentalsId) {
        this.bookRentalsId = bookRentalsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getDateOfBorrow() {
        return dateOfBorrow;
    }

    public void setDateOfBorrow(Date dateOfBorrow) {
        this.dateOfBorrow = dateOfBorrow;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFormattedDateOfReturn() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(dateOfReturn);
        return date;
    }

    public String getFormattedDateOfBorrow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(dateOfBorrow);
        return date;
    }
}
