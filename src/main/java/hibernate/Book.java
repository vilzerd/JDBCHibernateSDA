package hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")  //poprawa nazwy tabeli przez Hibernate,
                        //inaczej używa 'book'
public class Book {
    @Id
    @Column(name = "id")  //@Column jest opcjonalne. Używamy gdy mielibyśmy niezgodności tzn. inaczej chcielibyśmy
    private int id;       //nazwać w Javie, a inaczej w nazwie kolumn w DB.
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "pages")
    private int pages;

    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public Book(int id, String title, String author, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    Book() {  //wymagany przez hibernate bezparametrowy konstruktor

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                '}';
    }
}
