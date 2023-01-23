package movies.service;

import movies.exceptions.MovieServiceException;
import movies.model.Movie;
import movies.repository.MovieRepository;

import java.sql.SQLException;
import java.util.List;

public class MovieService {
    private MovieRepository repository;

    public MovieService(MovieRepository repository) { //dependency injection
        this.repository = repository;
    }

    public void save(Movie movie) throws MovieServiceException {
        if (movie.getPremiereYear() < 1895 || movie.getPremiereYear() > 2025) {
//          Wyjątek może zastępować return
            throw new MovieServiceException("Unreal release date given. Should be a range: 1895 - 2025.");
        }
        repository.save(movie);
    }

    public List<Movie> findAllMovies() throws MovieServiceException {
        return repository.findAllMovies();
    }

    public void closeAllResources() throws MovieServiceException {
        repository.closeAllResources();
    }
}
