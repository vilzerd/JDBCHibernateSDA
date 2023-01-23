package movies.repository;

import movies.exceptions.MovieServiceException;
import movies.model.Movie;

import java.util.List;

public interface MovieRepository {
    void save(Movie movie) throws MovieServiceException;

    List<Movie> findAllMovies() throws MovieServiceException;

    void closeAllResources() throws MovieServiceException;
}
