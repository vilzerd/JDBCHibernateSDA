package movies;

import movies.controller.GuiController;

public class GuiMoviesApp {
    public static void main(String[] args) {
        GuiController guiController = new GuiController();
        guiController.startMenu();
    }
}
