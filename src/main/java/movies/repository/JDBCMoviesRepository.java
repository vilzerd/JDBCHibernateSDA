package movies.repository;

import movies.exceptions.MovieServiceException;
import movies.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDBCMoviesRepository implements MovieRepository {

    private Connection connection;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "eskuelowiec";
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
    private static final String SQL_PROBLEM_MESSAGE = "SQL problem with JDBC";

    public JDBCMoviesRepository() throws MovieServiceException {
        try {
            initConnection();
            initTable();
        } catch (SQLException e) {
            throw new MovieServiceException(SQL_PROBLEM_MESSAGE);
        }
    }

    private void initConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    private void initTable() throws SQLException {
        PreparedStatement statement = connection.prepareStatement(CREATE_MOVIES_TABLE_SQL);
        statement.execute();
    }

    public void save(Movie movie) throws MovieServiceException {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_MOVIE_SQL);
            statement.setString(1, movie.getTitle());
            statement.setInt(2, movie.getPremiereYear());
            statement.setString(3, movie.getGenre());
            statement.setInt(4, movie.getRate());
            statement.execute();
        } catch (SQLException e) {
            throw new MovieServiceException(SQL_PROBLEM_MESSAGE);
        }
    }

    public List<Movie> findAllMovies() throws MovieServiceException {
        try {
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
            return movies;
        }
        } catch (SQLException e) {
            throw new MovieServiceException(SQL_PROBLEM_MESSAGE);
        }
       return Collections.emptyList();
    }

    public void closeAllResources() throws MovieServiceException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new MovieServiceException(SQL_PROBLEM_MESSAGE);

        }
    }

}
