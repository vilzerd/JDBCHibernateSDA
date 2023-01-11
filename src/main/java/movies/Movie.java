package movies;

public class Movie {
    private String title;
    private int premiereYear;
    private String genre;
    private int rate;

    public Movie(String title, int premiereYear, String genre, int rate) {
        this.title = title;
        this.premiereYear = premiereYear;
        this.genre = genre;
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public int getPremiereYear() {
        return premiereYear;
    }

    public String getGenre() {
        return genre;
    }

    public int getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", premiereYear=" + premiereYear +
                ", genre='" + genre + '\'' +
                ", rate=" + rate +
                '}';
    }
}
