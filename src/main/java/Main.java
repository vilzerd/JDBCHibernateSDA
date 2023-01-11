import books.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException {
//      You should only connect to the database once
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "eskuelowiec");

//      Create books table in MySQL
        String createTableSql = """
                CREATE TABLE books (
                id int AUTO_INCREMENT PRIMARY KEY,
                title varchar(255) NOT NULL,
                author varchar(255) NOT NULL,
                pages int NOT NULL
                );
                """;

//        connection.createStatement().execute(createTableSql);

        Book book = new Book("Ostatnie życzenie", "Andrzej Sapkowski", 330);
        Statement statement = connection.createStatement();

        String insertBookSql = String.format("INSERT INTO books VALUES (0,'%s','%s',%d)",
//      if there is no need to read ID (first value), it could be also replaced by:
//      "INSERT INTO books(title, author, pages) VALUES ('%s','%s,'%d)".
        book.getTitle(), book.getAuthor(), book.getPages());

        System.out.println(insertBookSql);
//        statement.execute(insertBookSql);

        //update:
        String updateBook = """
                UPDATE books
                SET pages = 332
                WHERE title = 'Ostatnie życzenie';
                """;

        statement.execute(updateBook);

    }
}
