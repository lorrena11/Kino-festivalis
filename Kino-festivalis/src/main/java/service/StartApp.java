package service;

import service.movies.MovieListFactory;

/**
 * a class that prepares the program by generating some initial movies and their reviews
 * before the user gets to interact with the app
 */
public class StartApp {
    public static void load() {
        MovieListFactory movieListFactory = new MovieListFactory();
        movieListFactory.addMovies();
    }


}
