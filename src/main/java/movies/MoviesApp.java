package movies;

import movies.controller.ConsoleController;
import movies.controller.Controller;
import movies.controller.GuiController;
import movies.exceptions.MovieServiceException;
import movies.repository.HibernateMoviesRepository;
import movies.repository.JDBCMoviesRepository;
import movies.repository.MovieRepository;
import movies.service.MovieService;

public class MoviesApp {
    private static final String CONSOLE_MODE = "console";
    private static final String GUI_MODE = "gui";
    private static final String JDBC_MODE = "jdbc";
    private static final String HIBERNATE_MODE = "hibernate";
    private static final String DEFAULT_CONTROLLER_MODE = GUI_MODE;
    private static final String DEFAULT_REPOSITORY_MODE = HIBERNATE_MODE;

    public static void main(String[] args) {
        String controllerMode = DEFAULT_CONTROLLER_MODE;
        String repositoryMode = DEFAULT_REPOSITORY_MODE;
        if (args.length != 0) {
            controllerMode = args[0];
        }
        if (args.length == 2) {
            repositoryMode = args[0];
        }
        try {
            executeProgram(controllerMode, repositoryMode);
        } catch (MovieServiceException e) {
            System.out.println("Problem with preparing repository: " + e.getMessage());
        }
    }

    private static void executeProgram(String controllerMode, String repositoryMode) throws MovieServiceException {

        MovieService service = new MovieService(createRepository(repositoryMode));
        Controller controller;
        if (controllerMode.equalsIgnoreCase(CONSOLE_MODE)) {
            controller = new ConsoleController(service);
        } else if (controllerMode.equalsIgnoreCase(GUI_MODE)) {
            controller = new GuiController(service);
        } else {
            executeProgram(DEFAULT_CONTROLLER_MODE, repositoryMode);
            return;
        }
        controller.startMenu();
    }

    private static MovieRepository createRepository(String repositoryMode) throws MovieServiceException {
        MovieRepository repository;
        if (repositoryMode.equalsIgnoreCase(JDBC_MODE)) {
            repository = new JDBCMoviesRepository();
        } else if (repositoryMode.equalsIgnoreCase(HIBERNATE_MODE)) {
            repository = new HibernateMoviesRepository();
        } else {
            return createRepository(DEFAULT_REPOSITORY_MODE);
        }
        return repository;
    }
}
