package movies;

import movies.model.Movie;
import movies.repository.HibernateMoviesRepository;

public class AppHibernateDemo {
    public static void main(String[] args) {
        HibernateMoviesRepository repository = new HibernateMoviesRepository();
        Movie movie = new Movie("Kot w butach 2", 2022, "animowany", 5);
        repository.save(movie);
//        repository.update(1, 2022);
        System.out.println(repository.findAllMovies());
    }
}
