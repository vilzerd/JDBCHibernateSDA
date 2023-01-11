import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","PASSWORD");
    }
}
