package movies;

import movies.repository.HibernateMoviesRepository;

public class AppHibernateDemo {
    public static void main(String[] args) {
        HibernateMoviesRepository repository = new HibernateMoviesRepository();
    }
}
