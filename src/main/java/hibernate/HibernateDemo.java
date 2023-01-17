package hibernate;

import books.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateDemo {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(hibernate.Book.class);
        configuration.configure("hibernate.cfg.xml");
        //SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(); //SessionFactory to odpowiednik Connection z JDBC
        //SessionFactory - ciężki obiekt, potrzebny tylko jeden na całą aplikację

        //ORM - Object-Relation Mapper
        Book book = new Book("DDD Kompendium wiedzy", "Vaughn Vernon", 130);

//      Aby klasa była encją bazodanową, rozumianą przez hibernate musi:
//      być oznaczona adnotacją @Entity
//      musi być wskazana przy konfiguracji (addAnnotatedClass)
//      musi być wskazane id adnotacją @Id
//      musi mieć bezparametrowy konstruktor
//      pamiętaj o otwarciu i zamknięciu tranzakcji oraz zamknięciu sesji

//      Crud:
//      przygotowanie interakcji z bazą
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//      nasze interakcje
        session.save(book);
//      zakończenie interakcji
        transaction.commit();
        session.close();

//      cRud:




        sessionFactory.close();
//      należy zamknąć SessionFactory na koniec
    }
}
