package movies.service;

import movies.exceptions.MovieServiceException;
import movies.model.Movie;
import movies.repository.JDBCMoviesRepository;

import java.sql.SQLException;
import java.util.List;

public class MovieService {
    private JDBCMoviesRepository repository = new JDBCMoviesRepository();

    public void save(Movie movie) throws SQLException, MovieServiceException {
        if (movie.getPremiereYear() < 1895 || movie.getPremiereYear() > 2025) {
//          Wyjątek może zastępować return
            throw new MovieServiceException("Unreal release date given. Should be a range: 1895 - 2025.");
        }
        repository.save(movie);
    }

    public List<Movie> findAllMovies() throws SQLException {
        return repository.findAllMovies();
    }

    public void closeAllResources() throws SQLException {
        repository.closeAllResources();
    }
}
