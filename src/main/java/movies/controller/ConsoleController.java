package movies.controller;

//This class allows you to interact with the application (entering data, presenting results).

import movies.service.MovieService;

import javax.swing.*;
import java.util.Scanner;

public class ConsoleController extends Controller {
    public ConsoleController(MovieService movieService) {
        super(movieService);
    }

    void showMessage(String message) {
        System.out.println(message);
    }

    int readInt(String message) {
        try {
            String input = readString(message);
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showMessage("Number should be given");
            return readInt(message);
        }
    }

    String readString(String message) {
        Scanner scanner = new Scanner(System.in);
        showMessage(message);
        return scanner.nextLine();
    }
}
