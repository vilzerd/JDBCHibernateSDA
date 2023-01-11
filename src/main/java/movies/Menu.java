package movies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    //  method should operate on one level of abstraction

    private boolean running = true;
    private List<Movie> movies = new ArrayList<>();
    private Connection connection;

    public Menu() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "PASSWORD");
        } catch (SQLException e) {
            System.out.println("Database failed");
        }
    }

    public void startMenu() {
        do {
            menuAction();
        } while (running);
    }

    private void menuAction() {
        showOptions();
        int input = readOption();
        executeOption(input);
    }

    private void showOptions() {
        System.out.println("""
                Choose one from the options:
                1. Add new movie
                2. Display movies
                3. Finish""");
    }

    private int readOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void executeOption(int input) {
        switch (input) {
            case 1:
                addMovie();
                break;
            case 2:
                displayMovies();
                break;
            case 3:
                finish();
                break;
        }
    }

    private void addMovie() {
        Movie movie = readMovieData();
        save(movie);
    }

    private Movie readMovieData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of movie:");
        String title = scanner.nextLine();
        System.out.print("Enter release year of movie:");
        int premiereYear = scanner.nextInt();
        if (premiereYear < 1895 || premiereYear > 2025) {
            System.out.println("Unreal release date given. Should be a range: 1895 - 2025.");
            return readMovieData();
        }
//        scanner = new Scanner(System.in) can be use instead of line 57. as well
        scanner.nextLine();
        System.out.print("Enter the genre of movie:");
        String genre = scanner.nextLine();
        System.out.print("Enter the movie rating (1-10):");
        int rate = scanner.nextInt();

        return new Movie(title, premiereYear, genre, rate);
    }

    private void displayMovies() {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private void save(Movie movie) {
        movies.add(movie);
    }

    private void finish() {
        System.out.println("End");
        running = false;
    }

}
