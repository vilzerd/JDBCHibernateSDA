package movies.repository;

import movies.model.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
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
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void update(int id, int correctYear) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Movie movie = currentSession.get(Movie.class, id);
        movie.setYear(correctYear);
        transaction.commit();
        currentSession.close();
    }

    public List<Movie> findAllMovies() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            List<Movie> movies = session.createQuery("FROM Movie", Movie.class).getResultList();
            transaction.commit();
            return movies;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return new ArrayList<>();
    }

    public void closeAllResources() {

    }
}
