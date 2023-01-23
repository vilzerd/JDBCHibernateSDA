package movies.repository;

import movies.model.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) { //try-with-resources - we put "lockable" resources
//            here - implementing "Closeable"
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<Movie> findAllMovies() {
        return null;
    }

    public void closeAllResources() {

    }
}
