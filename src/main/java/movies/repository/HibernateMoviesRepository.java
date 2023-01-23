package movies.repository;

import movies.model.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class HibernateMoviesRepository implements MovieRepository {

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
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Movie movie = session.get(Movie.class, id);
            movie.setYear(correctYear);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
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
            return Collections.emptyList();
        }
    }


    private <T> T executeOperation(Function<Session,T> operation) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
           T result = operation.apply(session);
            transaction.commit();
            return result;
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }

    public void saveMovie2(Movie movie) {
        executeOperation( session -> session.save(movie) );
    }

    public void update2(int id, int year) {
        executeOperation( session -> {
            Movie movie = session.get(Movie.class, id);
            movie.setYear(year);
            return null;
        });
    }

    public List<Movie> findAllMovies2() {
       return executeOperation(session -> session.createQuery("FROM Movie", Movie.class).getResultList());
    }

    public void closeAllResources() {
        sessionFactory.close();
    }
}
