package movies;

//Single responsibility
//Open-closed responsibility- code should be open to extension,
//closed to modifications
//this class is responsible for starting

import movies.controller.ConsoleController;

public class ConsoleMoviesApp {
    public static void main(String[] args) {
//        Menu.startMenu(); - if method startMenu() in class Menu were static,
//        which it isn't, then I have to create the object Menu. It's example of OOP.

        ConsoleController consoleController = new ConsoleController();
        consoleController.startMenu();
    }
}