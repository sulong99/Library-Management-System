-- phpMyAdmin SQL Dump
-- version 4.6.6deb5ubuntu0.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Czas generowania: 26 Maj 2021, 09:59
-- Wersja serwera: 5.7.34-0ubuntu0.18.04.1
-- Wersja PHP: 7.2.24-0ubuntu0.18.04.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `freedbtech_Biblioteka`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Authors`
--

CREATE TABLE `Authors` (
  `Author_id` int(11) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `SecondName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `Authors`
--

INSERT INTO `Authors` (`Author_id`, `FirstName`, `SecondName`) VALUES
(1, 'Andrzej', 'Sapkowski'),
(2, 'Juliusz', 'Słowacki'),
(3, 'Adam', 'Mickiewicz'),
(4, 'Ignacy', 'Krasicki'),
(5, 'Bolesław', 'Prus'),
(6, 'Homer', ''),
(7, 'Stefan', 'Żeromski'),
(8, 'Aleksander', 'Fredro'),
(9, 'Henryk', 'Sienkiewicz'),
(10, 'Stanisław', 'Wyspiański'),
(11, 'test11', 'test1'),
(12, 'Remigiusz', 'Mróz'),
(13, 'Aleksander', 'Kamiński'),
(14, 'Władysław', 'Reymont'),
(15, 'Karol', 'Dickens'),
(16, 'Bruno', 'Schulz'),
(17, 'Tadeusz', 'Słobodzianek'),
(18, 'Jacek', 'Dukaj'),
(19, 'Olga', 'Tokarczuk'),
(20, 'Stanisław', 'Lem'),
(21, 'Tadeusz', 'Różewicz');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Authors_Books`
--

CREATE TABLE `Authors_Books` (
  `Author_id` int(11) NOT NULL,
  `Book_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `Authors_Books`
--

INSERT INTO `Authors_Books` (`Author_id`, `Book_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(9, 8),
(10, 9),
(8, 10),
(12, 13),
(12, 14),
(12, 15),
(12, 16),
(12, 17),
(2, 18),
(2, 19),
(2, 20),
(3, 21),
(9, 22),
(14, 23),
(13, 24),
(15, 24),
(13, 25),
(9, 26),
(9, 27),
(9, 28),
(9, 29),
(9, 30),
(9, 31),
(7, 32),
(7, 33),
(3, 34),
(16, 35),
(17, 36),
(18, 37),
(19, 38),
(19, 39),
(19, 40),
(19, 41),
(19, 42),
(19, 43),
(21, 49),
(21, 50),
(20, 44),
(20, 45),
(20, 46),
(20, 47),
(20, 48);


-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `BookRentals`
--

CREATE TABLE `BookRentals` (
  `BookRentals_id` int(11) NOT NULL,
  `Book_id` int(11) NOT NULL,
  `DateOfBorrow` date NOT NULL,
  `DateOfReturn` date DEFAULT NULL,
  `Settings_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `BookRentals`
--

INSERT INTO `BookRentals` (`BookRentals_id`, `Book_id`, `DateOfBorrow`, `DateOfReturn`, `Settings_id`) VALUES
(1, 8, '2021-04-12', '2021-05-08', 1),
(2, 9, '2021-04-11', '2021-05-18', 2),
(3, 3, '2021-04-07', '2021-04-11', 3),
(4, 1, '2021-04-04', '2021-04-11', 2),
(5, 1, '2015-02-02', NULL, 1),
(6, 1, '2021-05-08', '2021-05-08', 7),
(7, 10, '2021-05-11', '2021-05-11', 8),
(8, 7, '2021-05-11', '2021-05-11', 8),
(9, 1, '2021-05-12', '2021-05-18', 9),
(10, 3, '2021-05-18', '2021-05-18', 9),
(11, 5, '2021-05-18', '2021-05-18', 9),
(12, 3, '2021-05-18', NULL, 9),
(13, 8, '2021-05-18', NULL, 9),
(14, 9, '2021-05-18', NULL, 9);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `BookRentals_Users`
--

CREATE TABLE `BookRentals_Users` (
  `BookRentals_id` int(11) NOT NULL,
  `User_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `BookRentals_Users`
--

INSERT INTO `BookRentals_Users` (`BookRentals_id`, `User_id`) VALUES
(1, 3),
(2, 3),
(9, 3),
(10, 3),
(11, 3),
(12, 3),
(13, 3),
(14, 10);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Books`
--

CREATE TABLE `Books` (
  `Books_id` int(11) NOT NULL,
  `ISBN` varchar(13) NOT NULL,
  `Title` varchar(255) NOT NULL,
  `Category` varchar(50) NOT NULL,
  `Publisher` varchar(50) NOT NULL,
  `Year` int(4) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `is_taken` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `Books`
--

INSERT INTO `Books` (`Books_id`, `ISBN`, `Title`, `Category`, `Publisher`, `Year`, `Description`, `is_taken`) VALUES
(1, '9788375780635', 'Ostatnie Życzenie. Wiedźmin, Tom 1', 'Fantasy', 'Supernowa', 2014, 'Sięgając po sagę wiedzmin, masz szansę wkroczyć w fantastyczny świat wykreowany przez Andrzeja Sapkowskiego. W pierwszym tomie sagi pod tytułem „Ostatnie Życzenie” poznasz początek historii Geralta z Rivii i wraz z nim wyruszysz w świat przygód.', 1),
(2, '9788366251182', 'Balladyna', 'Dramat', 'Siedmioróg', 2019, 'Przedstawiamy ponadczasowy dramat Juliusza Słowackiego, który od pokoleń zaciekawia i intryguje widzów i czytelników. Historia zaczyna się jak baśń, a kończy jak tragedia.', 1),
(3, '9788366251199', 'Pan Tadeusz', 'Lektury', 'Siedmioróg', 2019, 'Historia szlachecka z okresu poprzedzającego wyprawą Napoleona na Moskwą. W utworze tym można wskazać elementy eposu, pokazuje on bowiem bohaterskie czyny wielkich postaci na tle prze?omowych dla narodu wydarzeń. Stał się on epopeją narodową.', 1),
(4, '9788372723260', 'Satyry', 'Komedia', 'G&P Oficyna Wydawnicza', 2017, 'W satyrach Ignacy Krasicki ośmiesza wady społeczeństwa polskiego: pijaństwo, zacofanie, ciemnotę szlachty, skłonność do hazardu, brak krytycyzmu. ', 1),
(5, '9788375176889', 'Lalka', 'Powieść', 'Greg', 2021, '\"Lalka\" Bolesława Prusa to utwór wyjątkowy. Zawikłana, pełna niedopowiedzeń historia źle ulokowanych uczuć, straconych złudzeń i zaprzepaszczonych możliwości. Jedyna w swoim rodzaju konfrontacja romantycznego i pozytywistycznego idealizmu z realizmem.', 0),
(6, '9788377916841', 'Iliada', 'Epika', 'Siedmioróg', 2017, '\"Iliada\" jest, obok Biblii, utworem, który zainspirował i nadal inspiruje największą liczbę badaczy literatury, filologów, filozofów, historyków, a także artystów tworzących dzieła sztuki w malarstwie, muzyce, literaturze czy kinematografii.', 0),
(7, '9788380598393', 'Przedwiośnie', 'Powieść', 'SBM', 2019, '\"Przedwiośnie\" to wybitna powieść Stefana Żeromskiego wydana w 1924 (z datą 1925) w Warszawie. Opowiada o życiu Cezarego Baryki, urodzonego i wychowanego w Baku, przyjeżdżającego do odrodzonej po I wojnie światowej Polski.', 0),
(8, '9788366382985', 'Potop', 'Epika', 'Astrum', 2020, 'Rok 1655, w granice Rzeczpospolitej wkracza olbrzymia armia szwedzka. Polacy zapamiętają ten najazd jako \"potop szwedzki”. Ostoją Polaków staje się Częstochowa, w której obronie niebagatelną rolę odegra Andrzej Kmicic.', 1),
(9, '9788361189916', 'Wesele', 'Dramat', 'SPES', 2019, 'Koniec XIX wieku - w galicyjskim zaciszu Polacy powoli przywykają do tego, że ich kraju nie ma na mapach Europy. Nie wszyscy się z tym pogodzili, każdy z uczestników bronowickiego wesela nosi w sobie marzenie o niepodległości Polski.', 1),
(10, '9788375178494', 'Zemsta', 'Komedia', 'Greg', 2018, 'Ponadczasowa i niezwykle zabawna opowieść mistrza polskiej literatury, Aleksandra Fredry. Historia nieustającego sporu między Cześnikiem Raptusiewiczem i Rejentem Milczkiem, ukazuj?ca z przymrużeniem oka typowo polskie przywary i słabostki.', 1),
(13, '9788381954785', 'Ekstremista', 'Kryminał', 'Filia', 2021, 'W Opolu znika maturzystka, a jedynym pozostawionym przez nią śladem jest karta tarota z symbolem sprawiedliwości. Rodzina nie zamierza iść na policję, twierdzi, że dziewczyna zmagała się z problemami i deklarowała, że któregoś dnia ucieknie z domu.', 0),
(14, '9788366736238', 'Afekt', 'Kryminał', 'Czwarta Strona', 2021, 'W nowej kancelarii prawnej Joannie Chyłce zostaje przydzielona sprawa będąca jej zawodowym koszmarem: ma bronić pedofila. Życie zawodowe Chyłki komplikuje się coraz bardziej. Prawdziwe problemy dopiero się zaczną.', 0),
(15, '9788381726917', 'Wieża Milczenia', 'Kryminał', 'Dragon', 2020, 'Wyrzucony z pracy wykładowca uniwersytecki Scott Winton postanawia wyjaśnić zagadkowe morderstwo swojej ukochanej, która zginęła, wracając w nocy do domu.  Niedługo potem dochodzi do kolejnych zabójstw oraz zamachu na życie znanego polityka.', 0),
(16, '9788366553811', 'Precedens', 'Kryminał', 'Czwarta Strona', 2020, 'Do Joanny Chyłki z nietypową sprawą zgłasza się jedna z polskich aktorek. Twierdzi, że za moment popełni przestępstwo, i chce, by prawniczka ją broniła. Kobieta brutalnie morduje swoją ofiarę, a wszystko to transmituje w mediach społecznościowych.', 0),
(17, '9788311160323', 'Turkusowe Szale', 'Kryminał', 'Bellona', 2020, 'Fascynująca powieść sensacyjno-przygodowa najpopularniejszego polskiego pisarza młodego pokolenia łączy w sobie szpiegowską fabułę z wiernością historycznym źródłom. Wspaniała przygoda dla zainteresowanych II wojną światową i lotnictwem.', 0),
(18, '9788377916001', 'Kordian', 'Dramat', 'Siedmioróg', 2017, 'Polska pod jarzmem cara - zbrodniarza. Patrioci planują na niego zamach. Jeden z nich, tajemniczy młodzieniec w masce, podejmuje się zabić znienawidzonego tyrana. Wpisuje się tym samym w krąg działań, które zaplanował szatan u progu kolejnego stulecia.', 0),
(19, '9788304050181', 'Horsztyński', 'Dramat', 'Ossolineum', 2009, 'Horsztyński jest arcydziełem. To dramat fascynujący i porażający, szczytowe arcydzieło pesymizmu i daleka zapowiedź mistycznej fazy twórczości, dzieło bluźniercze i zawierające głębokie intuicje, tekst o utracie wiary i poszukiwaniu własnej tożsamości.', 0),
(20, '9788375179835', 'Lilla Weneda', 'Dramat', 'Greg', 2021, 'Trwa śmiertelny bój między plemionami Wenedów a najeźdźcami - Lechitami. W ręce okrutnej władczyni Lechitów, Gwinony, dostaje się król Wenedów, Derwid, i jego magiczna harfa, która ma moc ocalenia skazanego na zagładę narodu', 0),
(21, '9788375178302', 'Dziady', 'Dramat', 'Greg', 2019, 'Trwa proces studentów - filomatów. Wileńskie więzienie jest pełne młodych ludzi, którzy otwarcie przyznają się do tego, że są Polakami. Jeden z nich, Konrad, staje się wcieleniem niepodległości własnej ojczyzny. ', 0),
(22, '9788375178883', 'Quo Vadis', 'Historia', 'Greg', 2020, 'Od ponad stu lat historia miłości młodego patrycjusza rzymskiego i pięknej zakładniczki chrześcijanki wzrusza i fascynuje kolejne pokolenia czytelników. Trzymająca w napięciu akcja oraz wspaniały obraz Rzymu za czasów Nerona.', 0),
(23, '9788375179132', 'Chłopi', 'Powieść', 'Greg', 2019, 'Akcja powieści rozgrywa się pod koniec XIX wieku. We wsi Lipce trwają jesienne prace polowe. Nikt nie spodziewa się mających wkrótce nadejść wypadków, które zaciążą na całej wiejskiej społeczności. ', 0),
(24, '9788375175783', 'Opowieść Wigilijna', 'Opowiadania', 'Greg', 2019, 'Ebenezer Scrooge jest bezduszny, skąpy i samolubny. Całe dnie spędza w swoim kantorze – z nosem w opasłych księgach rachunkowych. Dobrych uczynków unika jak ognia, nigdy nie bywa miły i nienawidzi Bożego Narodzenia.', 0),
(25, '9788310126542', 'Kamienie na Szaniec', 'Historia', 'Nasza Księgarnia', 2014, 'Opowieść o Bohaterach Szarych Szeregów - Rudym, Alku, Zośce, zilustrowana ponad osiemdziesięcioma zdjęciami ze wszystkich epok Ich życia - od gniazda rodzinnego, poprzez czas harcerstwa, miłości, walki, po dni ostatnie!', 0),
(26, '9788375178340', 'Latarnik', 'Powieść', 'Greg', 2019, 'Skawiński to starszy człowiek, wieloletni tułacz, który marzy o miejscu, w którym znajdzie przystań, spokój i odpoczynek po trudach życia. Kiedy dostaje pracę w stojącej na uboczu latarni morskiej, wydaje mu się, że znalazł wreszcie to, czego szukał.', 0),
(27, '9788366382268', 'Krzyżacy', 'Historia', 'Astrum', 2021, 'W tynieckiej gospodzie \"Pod Lutym Turem\" dwaj rycerze z Bogdańca, Maćko i jego synowiec, młodziutki Zbyszko, spotykają się z dworem księżnej Anny Danuty. Nie spodziewa się, jak bardzo dziewczyna zostanie skrzywdzona przez Krzyżaków.', 0),
(28, '9788366382978', 'Ogniem i Mieczem', 'Historia', 'Astrum', 2020, '\"Ogniem i mieczem\" to pierwsza z trzech powieści słynnej Trylogii Henryka Sienkiewicza. Pisana ku pokrzepieniu serc, wzbudzała czytelnicze emocje zaraz po ukazaniu się i niewiele mniejszą popularność dzisiaj jako romans historyczny.', 0),
(29, '9788366325630', 'Potop', 'Historia', 'SBM', 2020, 'Rok 1655, w granice Rzeczpospolitej wkracza olbrzymia armia szwedzka. Ostoją Polaków staje się Częstochowa, w której obronie niebagatelną rolę odegra Andrzej Kmicic. Bohaterskimi czynami za wszelką cenę będzie chciał odzyskać dobre imię.', 0),
(30, '9788364786679', 'Pan Wołodyjowski', 'Historia', 'Astrum', 2019, 'Pan Michał Jerzy Wołodyjowski rozpacza po śmierci narzeczonej, Anusi Borzobohatej Krasieńskiej. Nie wierzy, by mógł być kiedykolwiek szczęśliwy w miłości. Tymczasem wkrótce pozna Basię Jeziorkowską, swą przyszłą towarzyszkę życia. ', 0),
(31, '9788375178678', 'Janko Muzykant', 'Opowiadania', 'Greg', 2019, 'Bohater tej nowelki, Janko, to biedny wiejski chłopiec, który ma ogromny dar - talent muzyczny. Dziecko słyszy muzykę wszędzie, nawet w szumie wiatru i szeleście liści, a jego największym marzeniem jest posiadanie własnych skrzypiec.', 0),
(32, '9788377916209', 'Ludzie Bezdomni', 'Powieść', 'Siedmioróg', 2017, 'Doktor Tomasz Judym po zakończonej praktyce lekarskiej w Paryżu powraca do Warszawy. Ze smutkiem stwierdza, że nie ma szans na poprawienie losu ludzi z nizin społecznych, ludzi, z których i on pochodzi.', 0),
(33, '9788375178838', 'Syzyfowe Prace', 'Powieść', 'Greg', 2020, 'Do czego dorastali chłopcy przymierający głodem na ohydnych stancjach, pozbawieni pomocy i wsparcia rodziców - pokolenia zmiażdzonego klęską powstania styczniowego? Kto do podłości? Kto do niepodległości?', 0),
(34, '9788365875662', 'Ballady i Romanse', 'Dramat', 'BOOKS', 2017, '\"Ballady i romanse\" Adama Mickiewicza zostały wydane w 1822 roku w Wilnie i wyznaczyły początek polskiego romantyzmu. W skład tomu wchodzą m.in. Romantyczność, Świtezianka, To lubię oraz Pani Twardowska.', 0),
(35, '9788377791219', 'Sklepy Cynamonowe', 'Opowiadania', 'Wydawnictwo MG', 2013, '9788377791219', 0),
(36, '9788374539562', 'Nasza Klasa', 'Dramat', 'Greg', 2021, '\"Nasza klasa\" to dramat przedstawiający historię dziesięciu uczniów z jednej klasy - Polaków i Żydów - opowiedzianą od roku 1925 aż po współczesność', 0),
(37, '9788308073179', 'Lód', 'Opowiadania', 'Astrum', 2020, 'Jacek Dukaj przenosi czytelnika w alternatywną rzeczywistość, gdzie I wojna światowa nigdy nie wybuchła, jest rok 1924, a Królestwo Polskie wciąż zamrożone jest pod władzą cara i w Belle Epoque.', 0),
(38, '9788308073247', 'Prawiek i inne czasy', 'Powieść', 'Wydawnictwo Literackie', 2020, 'Powieść opowiada o niszczącej sile czasu zobrazowanej w formie historii mitycznej podkieleckiej wsi i jej mieszkańców. To właśnie tu codzienność splata się z niezwykłością, rzeczywistość z mitem, a powszednie życie jest ważniejsze od wielkich wydarzeń.', 0),
(39, '9788308073261', 'Lalka i Perła', 'Powieść', 'Wydawnictwo Literackie', 2021, 'Oświetlając dzieło Prusa lekturą klasyków filozofii i poezji Olga Tokarczuk odkrywa \"Lalkę\" na nowo. W opowieści o Stanisławie Wokulskim dostrzega ponadczasową historię człowieka czasu przełomu - jego dążeń, pragnień i obsesji.', 0),
(40, '9788308073032', 'Szafa', 'Powieść', 'Wydawnictwo Literackie', 2020, 'Porywająca, niepokojąca wyobraźnia Olgi Tokarczuk. Opowiadania, w których wyobraźnia sąsiaduje z mitami, a przedmioty znaczą więcej, niż można się spodziewać.', 0),
(41, '9788308073070', 'Ostatnie Historie', 'Powieść', 'Wydawnictwo Literackie', 2020, 'Trzy kobiety, babka, córka i wnuczka, spotykają na swojej drodze śmierć. Stają przed koniecznością spojrzenia na swoje życie od nowa. Każda z nich szuka dla siebie właściwego miejsca. Choć są przedstawicielkami różnych pokoleń tej samej rodziny.', 0),
(42, '9788308073056', 'Czuły Narrator', 'Powieść', 'Wydawnictwo Literackie', 2020, 'Czuły narrator jest książką napisaną już po otrzymaniu prestiżowej nagrody. Zawiera ona dwanaście starannie wyselekcjonowanych wykładów, pozwalających zrozumieć twórczość i warsztat autorki. Zobacz, jak pisze, zapoznaj się z wygłoszoną przez nią mową.', 0),
(43, '9788361488743', 'Zgubiona Dusza', 'Powieść', 'Format', 2017, 'Pierwsza książka Olgi Tokarczuk, która mówi nie tylko słowami, ale i obrazami. To dwugłos – idąc za słowami pisarki, Joanna Concejo stworzyła równoległą opowieść, ukrytą w obrazach. Poruszająca rzecz o czekaniu, cierpliwości i uważności.', 0),
(44, '9788308061756', 'Powrót z gwiazd', 'Powieść', 'Wydawnictwo Literackie', 2016, '\"Powrót z gwiazd\" to historia astronauty, który na skutek paradoksu czasowego Einsteina powrócił z wyprawy w Kosmos na Ziemię, gdzie tymczasem minęło półtora stulecia. ', 0),
(45, '9788308073995', 'Podróż siódma', 'Fantastyka', 'Wydawnictwo Literackie', 2021, 'Podróż siódma to komiks, picture book i kawał dobrej literatury w jednym. Kolekcjonerski rarytas dla miłośników twórczości autora \"Dzienników gwiazdowych\" i jednocześnie doskonały wstęp dla młodych czytelników do flirtu z pisarstwem Lema.', 0),
(46, '9788308073872', 'Bajki robotów', 'Fantastyka', 'Wydawnictwo Literackie', 2021, 'To zabawne skrzyżowanie literatury science fiction i tradycyjnej baśni. Ich wyjątkowość polega na baśniowej fabule rozgrywającej się w kosmicznej scenerii, bohaterami są istoty mechaniczne, natomiast rolę czarowników i wróżek pełnią uczeni konstruktorzy.', 0),
(47, '9788375525823', 'Maska', 'Opowiadania', 'Agora', 2020, '„Maska” zawiera te spośród opowiadań Lema, które nie należą do żadnych uporządkowanych cykli. Są to jednak utwory szalenie istotne, niektóre z nich — jak „Maska” czy „Pamiętnik” — należą wręcz do najważniejszych, jakie Lem napisał. ', 0),
(48, '9788308070161', 'Wysoki zamek', 'Autobiografia', 'Wydawnictwo Literackie', 2020, 'Najbardziej osobista książka Stanisława Lema. Autoportret z okresu dzieciństwa i dorastania.', 0),
(49, '9788366487406', 'Ostatnia wolność', 'Dramat', 'Biuro Literackie', 2020, 'Ostatnie, niepublikowane wcześniej w książkach utwory, które miały znaleźć się w kolejnym zbiorze autora. Wiersze pisane z perspektywy końca, wiersze o  sprawach ostatecznych, o tym, co ważne i najważniejsze.', 0),
(50, '9788366487048', 'Credo', 'Biografia', 'Biuro Literackie', 2020, 'Poruszający „portret wielokrotny\" ludzkiej twarzy. Intymny album z pamiątkami po najbliższych. Świadectwo załamania się wiary w człowieka i rozpadu sensu w obliczu wojny.', 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Cart`
--

CREATE TABLE `Cart` (
  `Book_id` int(11) NOT NULL,
  `User_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Requests`
--

CREATE TABLE `Requests` (
  `Request_id` int(11) NOT NULL,
  `BookTitle` varchar(255) NOT NULL,
  `BookAuthor` varchar(50) NOT NULL,
  `Category` varchar(50) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `isAddRequest` tinyint(1) NOT NULL,
  `UserId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `Requests`
--

INSERT INTO `Requests` (`Request_id`, `BookTitle`, `BookAuthor`, `Category`, `Description`, `isAddRequest`, `UserId`) VALUES
(1, 'Rydzyk i przyjaciele. Kręte ścieżki', 'Tomasz Piątek', 'Komedia', 'Tomasz Piątek odkrywa nieznaną przeszłość księdza Tadeusza Rydzyka - jednego z najbardziej wpływowych graczy tzw. dobrej zmiany, założyciela ultraprawicowego imperium medialnego i „edukacyjnego”. Autor przedstawia relacje Rydzyka z Rosją i PRL-owską SB.', 1, 3),
(2, 'Pan Tadeusz', 'Adam Mickiewicz', 'Lektury', '3', 0, 2),
(3, 'Bez skrupułów ', 'Harlan Coben', 'Thriller', 'Thriller', 1, 3),
(4, 'Bez skrupułów ', 'Harlan Coben', 'Thriller', 'Thriller', 1, 3),
(5, 'Świt, który nie nadejdzie ', 'Remigiusz Mróz', 'Thriller', 'Thriller', 1, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Settings`
--

CREATE TABLE `Settings` (
  `Settings_id` int(11) NOT NULL,
  `days_for_return` int(11) NOT NULL,
  `penalty` varchar(255) NOT NULL,
  `penalty_for_day_of_delay` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `Settings`
--

INSERT INTO `Settings` (`Settings_id`, `days_for_return`, `penalty`, `penalty_for_day_of_delay`) VALUES
(1, 30, '10', '0.50'),
(2, 60, '10', '0.75'),
(3, 30, '5', '0.50'),
(4, 60, '11', '0.55'),
(5, 3, '22.0', '10.00'),
(6, 30, '12.0', '0.50'),
(7, 15, '10', '2.00'),
(8, 3, '10.0', '0.50'),
(9, 5, '5.0', '5.00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `Users`
--

CREATE TABLE `Users` (
  `Users_id` int(11) NOT NULL,
  `Login` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Permission` enum('User','Worker','Administrator') NOT NULL DEFAULT 'User'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `Users`
--

INSERT INTO `Users` (`Users_id`, `Login`, `Password`, `FirstName`, `LastName`, `Address`, `Permission`) VALUES
(1, 'admin', '201b5ed129be6724d54afe8e54151e542a451af8', 'Jan', 'Kowalski', '35-309 Rzeszów ul. Rejtana 10', 'Administrator'),
(2, 'worker', '4c24b1791aa981db606ff1354f9197a0d2468739', 'Andrzej', 'Rydzyk', '21-370 Toru? ul. ?wirki i Wigury 80', 'Worker'),
(3, 'user', 'b9db9807ac7ce46152effc7e0ab4f4f9cbc4117f', 'Michał', 'Bajor', '35-410 Brzozów ul. Kwiatowa 10', 'User'),
(4, 'test', 'test', 'test', 'test', 'test', 'User'),
(6, 'www', '11', 'ww', 'eee', '212', 'User'),
(8, 'xxx', 'a5cec6dbf3b77eae2c6f2439448c8c1e16e4a22c', 'worker', 'worker', 'worker worker worker', 'User'),
(9, 'admin1', '201b5ed129be6724d54afe8e54151e542a451af8', 'admin', 'admin', 'admin admin admin', 'User'),
(10, 'user1', 'b9db9807ac7ce46152effc7e0ab4f4f9cbc4117f', 'user', 'user', 'user user user', 'User'),
(11, 'user123', 'b9db9807ac7ce46152effc7e0ab4f4f9cbc4117f', 'user', 'user', 'user user user', 'User'),
(12, 'test1', 'bf42abaf66333ff77c55e4b84c8ec88020241631', 'workertestowy', 'workertestowy', '36-065 - Brzozów ul.. kwiatowa 34', 'Worker'),
(13, 'test1213', 'ba6bbea711d082121bf24a6ec74d8ccd041244e3', 'testzmiana', 'test', 'test1', 'Worker'),
(14, 'test1111', 'bf42abaf66333ff77c55e4b84c8ec88020241631', 'workertestowy1111', 'workertestowy111', '36-065 - Brzozów ul.. kwiatowa 34', 'Worker'),
(15, 'testetest', 'a4656d4f56386dcaa399579cc172364d2a7c1340', 'testtesttest', 'test', 'test', 'Worker'),
(16, 'TEST123', 'ba6bbea711d082121bf24a6ec74d8ccd041244e3', 'test', 'test', 'test', 'Worker'),
(17, 'do1', '1e53090d5195a06dafac24bc528a3d73a8b9676d', 'ddd123', 'ddd123', 'ddd', 'Administrator'),
(18, 'worker123', '499df763b7323f0a804965e89290f9d831a6b0f2', 'Adam', 'Adamczyk', 'Rzeszów ul.Kwiatowa 1', 'Worker');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `Authors`
--
ALTER TABLE `Authors`
  ADD PRIMARY KEY (`Author_id`);

--
-- Indexes for table `Authors_Books`
--
ALTER TABLE `Authors_Books`
  ADD KEY `Author_id` (`Author_id`),
  ADD KEY `Book_id` (`Book_id`);

--
-- Indexes for table `BookRentals`
--
ALTER TABLE `BookRentals`
  ADD PRIMARY KEY (`BookRentals_id`),
  ADD KEY `Settings_id` (`Settings_id`),
  ADD KEY `Book_id` (`Book_id`);

--
-- Indexes for table `BookRentals_Users`
--
ALTER TABLE `BookRentals_Users`
  ADD KEY `User_id` (`User_id`),
  ADD KEY `BookRentals_id` (`BookRentals_id`);

--
-- Indexes for table `Books`
--
ALTER TABLE `Books`
  ADD PRIMARY KEY (`Books_id`);

--
-- Indexes for table `Cart`
--
ALTER TABLE `Cart`
  ADD KEY `Book_id` (`Book_id`),
  ADD KEY `User_id` (`User_id`);

--
-- Indexes for table `Requests`
--
ALTER TABLE `Requests`
  ADD PRIMARY KEY (`Request_id`),
  ADD KEY `UserId` (`UserId`);

--
-- Indexes for table `Settings`
--
ALTER TABLE `Settings`
  ADD PRIMARY KEY (`Settings_id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`Users_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `Authors`
--
ALTER TABLE `Authors`
  MODIFY `Author_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT dla tabeli `BookRentals`
--
ALTER TABLE `BookRentals`
  MODIFY `BookRentals_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT dla tabeli `Books`
--
ALTER TABLE `Books`
  MODIFY `Books_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
--
-- AUTO_INCREMENT dla tabeli `Requests`
--
ALTER TABLE `Requests`
  MODIFY `Request_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT dla tabeli `Settings`
--
ALTER TABLE `Settings`
  MODIFY `Settings_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT dla tabeli `Users`
--
ALTER TABLE `Users`
  MODIFY `Users_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `Authors_Books`
--
ALTER TABLE `Authors_Books`
  ADD CONSTRAINT `Authors_Books_ibfk_1` FOREIGN KEY (`Author_id`) REFERENCES `Authors` (`Author_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Authors_Books_ibfk_2` FOREIGN KEY (`Book_id`) REFERENCES `Books` (`Books_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `BookRentals`
--
ALTER TABLE `BookRentals`
  ADD CONSTRAINT `BookRentals_ibfk_1` FOREIGN KEY (`Settings_id`) REFERENCES `Settings` (`Settings_id`),
  ADD CONSTRAINT `BookRentals_ibfk_2` FOREIGN KEY (`Book_id`) REFERENCES `Books` (`Books_id`);

--
-- Ograniczenia dla tabeli `BookRentals_Users`
--
ALTER TABLE `BookRentals_Users`
  ADD CONSTRAINT `BookRentals_Users_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `Users` (`Users_id`),
  ADD CONSTRAINT `BookRentals_Users_ibfk_2` FOREIGN KEY (`BookRentals_id`) REFERENCES `BookRentals` (`BookRentals_id`);

--
-- Ograniczenia dla tabeli `Cart`
--
ALTER TABLE `Cart`
  ADD CONSTRAINT `Cart_ibfk_1` FOREIGN KEY (`Book_id`) REFERENCES `Books` (`Books_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Cart_ibfk_2` FOREIGN KEY (`User_id`) REFERENCES `Users` (`Users_id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Ograniczenia dla tabeli `Requests`
--
ALTER TABLE `Requests`
  ADD CONSTRAINT `Requests_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `Users` (`Users_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
