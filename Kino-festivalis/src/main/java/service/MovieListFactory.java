package service;

import model.Movie;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * class for creating and filling a list of movies
 */
public class MovieListFactory {
    private static List<Movie> movieList = new ArrayList<>();


    void importMovies() {
        // todo: nuskaityti is .txt failo
        movieList.add(new Movie(1L, "Spring Day", "A short description of Spring Day", "1h 40m", LocalDate.of(2021, Month.MARCH, 29), 4.5));
        movieList.add(new Movie(2L, "Parasite", "A short description of Parasite", "2h 03m", LocalDate.of(2021, Month.MARCH, 31), 4.7));
        movieList.add(new Movie(3L, "Green Book", "A short description of Green Book", "1h 56m", LocalDate.of(2021, Month.MARCH, 30), 4.8));


    }

    public static List<Movie> getMovieList() {
        return movieList;
    }

    public static void setMovieList(List<Movie> movieList) {
        MovieListFactory.movieList = movieList;
    }
}
