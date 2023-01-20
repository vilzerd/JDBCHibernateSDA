package movies.repository;

import movies.model.Movie;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateMoviesRepository {

    private SessionFactory sessionFactory;

    public HibernateMoviesRepository() {
        initConnection();
    }

    private void initConnection() {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Movie.class)
                .buildSessionFactory();
    }

    public void save(Movie movie) {

    }

    public List<Movie> findAllMovies() {
        return null;
    }

    public void closeAllResources() {

    }
}
