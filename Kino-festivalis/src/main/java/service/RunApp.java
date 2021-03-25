package service;

/**
 * call all the methods here
 */
public class RunApp {
    public static void run() {
        MovieListFactory movieListFactory = new MovieListFactory();
        movieListFactory.importMovies();
    }


}
