--Utworzenie kwerendy wybieraj�cej ksi��ki z datami ich zwrotu oraz dane u�ytkownika kt�ry dokona� zwrotu

SELECT Books.Title, BookRentals.DateOfReturn, Users.FirstName, Users.LastName FROM Books
INNER JOIN BookRentals ON BookRentals.Book_id = Books.Books_id
LEFT OUTER JOIN BookRentals_Users ON BookRentals_Users.BookRentals_id = BookRentals.BookRentals_id
LEFT OUTER JOIN Users ON Users.Users_id = BookRentals_Users.User_id
WHERE BookRentals.DateOfReturn IS NOT NULL AND Users.FirstName IS NOT NULL AND Users.LastName IS NOT NULL

--Utworzenie kwerendy wybieraj�cej ksi��ki z datami ich wypo�yczenia oraz dane u�ytkownika kt�ry dokona� wypo�yczenia

SELECT Books.Title, BookRentals.DateOfBorrow, Users.FirstName, Users.LastName FROM Books
INNER JOIN BookRentals ON BookRentals.Book_id = Books.Books_id
LEFT OUTER JOIN BookRentals_Users ON BookRentals_Users.BookRentals_id = BookRentals.BookRentals_id
LEFT OUTER JOIN Users ON Users.Users_id = BookRentals_Users.User_id
WHERE BookRentals.DateOfBorrow IS NOT NULL AND Users.FirstName IS NOT NULL AND Users.LastName IS NOT NULL

--Utworzenie kwerendy wybieraj�cej ksi��ki wraz z ich autorem
SELECT DISTINCT Books.Books_id, Books.ISBN, Books.Title, Books.Description, CONCAT(Authors.FirstName, ' ', Authors.SecondName) AS "Authors", Books.Category, Books.Publisher, Books.Year
FROM Books LEFT JOIN Authors_Books ON Authors_Books.Book_id = Books.Books_id
LEFT JOIN Authors ON Authors.Author_id = Authors_Books.Author_id