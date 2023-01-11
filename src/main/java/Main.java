import books.Book;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
//      You should only connect to the database once
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "PASSWORD");

//      Create books table in MySQL
        String createTableSql = """
                CREATE TABLE books (
                id int AUTO_INCREMENT PRIMARY KEY,
                title varchar(255) NOT NULL,
                author varchar(255) NOT NULL,
                pages int NOT NULL
                );
                """;

//      connection.createStatement().execute(createTableSql);

        Book book = new Book("Ostatnie życzenie", "Andrzej Sapkowski", 330);
        //example of SQL injection - hacker attack
//      Book book = new Book("'a','a',1); drop database books; --", "Andrzej Sapkowski", 330);
        Statement statement = connection.createStatement();

        String insertBookSql = String.format("INSERT INTO books VALUES (0,'%s','%s',%d)",
//      if there is no need to read ID (first value), it could be also replaced by:
//      "INSERT INTO books(title, author, pages) VALUES ('%s','%s,'%d)".
                book.getTitle(), book.getAuthor(), book.getPages());

//        System.out.println(insertBookSql);
        statement.execute(insertBookSql);

//      update:
        String updateBookSql = """
                UPDATE books
                SET pages = 332
                WHERE title = 'Ostatnie życzenie';
                """;

//      statement.execute(updateBookSql);

//      read:
        String selectAllSql = "SELECT * FROM books WHERE pages < 275";

        ResultSet resultSet = statement.executeQuery(selectAllSql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
//          System.out.println(id);
            String title = resultSet.getString("title");
//          System.out.println(name);
            String author = resultSet.getString("author");
//          System.out.println(author);
            int pages = resultSet.getInt("pages");
//          System.out.println(pages);
            Book dbBook = new Book(id, title, author, pages);
            System.out.println(dbBook);
        }

//      delete:
        String deleteSql = "DELETE FROM books WHERE id = 2";
//      statement.execute(deleteSql);

//      to avoid SQL injection attack, it is mandatory to use PreparedStatement instead of Statement
        String insertSql = "INSERT INTO books VALUES(0,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
        preparedStatement.setString(1, "Czysta Architektura");
        preparedStatement.setString(2, "Robert Martin");
        preparedStatement.setInt(3, 350);
        preparedStatement.execute();

    }
}
