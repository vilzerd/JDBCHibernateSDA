package movies;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//menu
public class Menu {
    private boolean running = true;
    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "PASSWORD";
    private static final String CREATE_MOVIES_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS movies (
            id int AUTO_INCREMENT PRIMARY KEY,
            title varchar(255) NOT NULL,
            premiere_year int NOT NULL,
            genre varchar(255) NOT NULL,
            rate int
            );""";

    private static final String INSERT_MOVIE_SQL = "INSERT INTO movies VALUES (0,?,?,?,?);";
    private static final String SELECT_ALL_MOVIES_SQL = "SELECT * FROM movies;";

    public Menu() {
        try {
            initConnection();
            initTable();
        } catch (SQLException e) {
            System.out.println("Database failed");
        }
    }
    private void initConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }
    private void initTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_MOVIES_TABLE_SQL);
        statement.execute();
    }
    public void startMenu() {
        do {
            menuAction();
        } while (running);
    }
    private void menuAction() {
        showOptions();
        int input = readDecision();
        executeOption(input);
    }
    private void showOptions() {
        System.out.println("""
                Choose one from the options:
                1. Add new movie
                2. Display movies
                3. Finish""");
    }
    private int readDecision() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void executeOption(int input) { //TODO ROZDZIELIĆ NA 2 METODY
        try {
            switch (input) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    displayMovies();
                    break;
                case 3:
                    finish();
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Database query error");
        }

    }

    private void addMovie() throws SQLException{
        Movie movie = readMovieData();
        save(movie);
    }

    private Movie readMovieData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of movie:");
        String title = scanner.nextLine();
        System.out.print("Enter release year of movie:");
        int premiereYear = scanner.nextInt();
        if (premiereYear < 1895 || premiereYear > 2025) {
            System.out.println("Unreal release date given. Should be a range: 1895 - 2025.");
            return readMovieData();
        }
//        scanner = new Scanner(System.in) can be use instead of line 57. as well
        scanner.nextLine();
        System.out.print("Enter the genre of movie:");
        String genre = scanner.nextLine();
        System.out.print("Enter the movie rating (1-10):");
        int rate = scanner.nextInt();
        return new Movie(title, premiereYear, genre, rate);
    }

    private void displayMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MOVIES_SQL);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String title = resultSet.getString(2);
            int year = resultSet.getInt(3);
            String genre = resultSet.getString(4);
            int rate = resultSet.getInt(5);
            Movie movie = new Movie(id, title, year, genre, rate);
            movies.add(movie);
        }

        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private void save(Movie movie) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_MOVIE_SQL);
        statement.setString(1, movie.getTitle());
        statement.setInt(2, movie.getPremiereYear());
        statement.setString(3, movie.getGenre());
        statement.setInt(4, movie.getRate());
        statement.execute();
    }


    private void finish() {
        System.out.println("End");
        running = false;
    }
}
