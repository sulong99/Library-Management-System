--Utworzenie kwerendy zliczającej ilość wypożyczonych książęk przez usera o danym id

SELECT CONCAT(u.FirstName," ",u.LastName) AS "Czytelnik"
      ,COUNT(b.User_id)                   AS "Ilość wypożyczeń"
FROM Users u
INNER JOIN BookRentals_Users b
ON u.Users_id=b.User_id
GROUP BY b.User_id
ORDER BY 'Ilość wypożyczeń' DESC

--Utworzenie kwerendy dodającej ksiązki do koszyka

INSERT INTO Cart`(Book_id`, User_id)
VALUES (
    (SELECT Books_id FROM Books WHERE Books.Title='Wesele'),
    (SELECT Users_id FROM Users WHERE Users_id = '1')
    );