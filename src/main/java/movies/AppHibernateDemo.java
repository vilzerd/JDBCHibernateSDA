package movies;

import movies.model.Movie;
import movies.repository.HibernateMoviesRepository;

public class AppHibernateDemo {
    public static void main(String[] args) {
        HibernateMoviesRepository repository = new HibernateMoviesRepository();
        Movie movie = new Movie("Avatar 2", 2023, "przygodowy/fantasy", 5);
        repository.save(movie);
    }
}
