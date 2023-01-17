package movies;

//This class allows you to interact with the application (entering data, presenting results).

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Controller {

    private boolean running = true;
//    only one service on controller (one private field!)
    private MovieService movieService = new MovieService();

    public void startMenu() {
        do {
            menuAction();
        } while (running);
    }

    private void menuAction() {
        showOptions();
        int input = readDecision();
        handleOption(input);
    }

    private void showOptions() {
        System.out.println("""
                Choose one from the options:
                1. Add new movie
                2. Display movies
                3. Finish""");
    }

    private int readDecision() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void handleOption(int input) {
        try {
            executeOption(input);
        } catch (SQLException e) {
            System.out.println("Database query error");
        }
    }

    private void executeOption(int input) throws SQLException { //TODO dekompozycja!
        switch (input) {
            case 1:
                Movie movie = readMovieData();
                movieService.save(movie);
                break;
            case 2:
                List<Movie> movies = movieService.findAllMovies();
                displayMovies(movies);
                break;
            case 3:
                finish();
                break;
        }
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
//      scanner = new Scanner(System.in) can be use instead of line below as well
        scanner.nextLine();
        System.out.print("Enter the genre of movie:");
        String genre = scanner.nextLine();
        System.out.print("Enter the movie rating (1-10):");
        int rate = scanner.nextInt();
        return new Movie(title, premiereYear, genre, rate);
    }

    private void displayMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    private void finish() throws SQLException {
        System.out.println("End");
        running = false;
        movieService.closeAllResources();
    }
}
