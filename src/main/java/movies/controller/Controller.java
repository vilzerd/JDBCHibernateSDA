package movies.controller;

import movies.model.Movie;
import movies.service.MovieService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public abstract class Controller {

    private boolean running = true;
    //    only one service on controller (one private field!)
    private MovieService movieService = new MovieService();
    private static final String OPTIONS = """
            Choose one from the options:
            1. Add new movie
            2. Display movies
            3. Finish""";

    public void startMenu() {
        do {
            menuAction();
        } while (running);
    }

    private void menuAction() {
        int input = readInt(OPTIONS);
        handleOption(input);
    }

    private void handleOption(int input) {
        try {
            executeOption(input);
        } catch (SQLException e) {
            showMessage("Database query error");
        }
    }

    private void executeOption(int input) throws SQLException { //TODO dekompozycja!
        switch (input) {
            case 1:
                addMovie();
                break;
            case 2:
                showMovies();
                break;
            case 3:
                finish();
                break;
        }
    }

    private void addMovie() throws SQLException {
        Movie movie = readMovieData();
        movieService.save(movie);
    }

    private void showMovies() throws SQLException {
        List<Movie> movies = movieService.findAllMovies();
        displayMovies(movies);
    }

    private Movie readMovieData() {
        String title = readString("Enter the title of movie:");
        int premiereYear = readInt("Enter release year of movie:");
        if (premiereYear < 1895 || premiereYear > 2025) {
           showMessage("Unreal release date given. Should be a range: 1895 - 2025.");
            return readMovieData();
        }
        String genre = readString("Enter the genre of movie:");
        int rate = readInt("Enter the movie rating (1-10):");
        return new Movie(title, premiereYear, genre, rate);
    }

    private void displayMovies(List<Movie> movies) {
        String allMovies = "";
        for (Movie movie : movies) {
            allMovies += movie + "\n";
        }
        showMessage(allMovies);
    }

    private void finish() throws SQLException {
        showMessage("End");
        running = false;
        movieService.closeAllResources();
    }

    abstract void showMessage(String message);

    abstract int readInt(String message);

    abstract String readString(String message);
}

//WARSTWY:
//CONTROLLER -> SERVICE -> REPOSITORY
