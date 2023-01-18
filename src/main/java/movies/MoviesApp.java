package movies;

import movies.controller.ConsoleController;
import movies.controller.Controller;
import movies.controller.GuiController;

public class MoviesApp {
    private static final String CONSOLE_MODE = "console";
    private static final String GUI_MODE = "gui";
    private static final String DEFAULT_MODE = GUI_MODE;

    public static void main(String[] args) {

        String mode = DEFAULT_MODE;
        if(args.length != 0) {
            mode = args[0];
        }
        executeProgram(mode);
    }

    private static void executeProgram(String mode) {
        Controller controller;
        if (mode.equalsIgnoreCase(CONSOLE_MODE)) {
            controller = new ConsoleController();
        } else if (mode.equalsIgnoreCase(GUI_MODE)) {
            controller = new GuiController();
        } else {
            executeProgram(DEFAULT_MODE);
            return;
        }
        controller.startMenu();
    }
}
