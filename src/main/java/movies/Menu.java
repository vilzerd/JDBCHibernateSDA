package movies;

import java.util.Scanner;

public class Menu {
    //  method should operate on one level of abstraction

    private boolean running = true;

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
                System.out.println("Adding movies");
                break;
            case 2:
                System.out.println("Displaying movies");
                break;
            case 3:
                System.out.println("End");
                running = false;
                break;
        }
    }
}
