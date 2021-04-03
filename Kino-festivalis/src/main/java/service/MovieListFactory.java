package service;

import model.Movie;
import model.Review;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * class for creating and filling a list of movies
 */
public class MovieListFactory {
    // contains all the available films
    private static List<Movie> movieList = new ArrayList<>();

    void importMovies() {
        // todo: nuskaityti is .txt failo?
        // todo: įrašinėjant su json nenaudoti localdate, o tik date
        movieList.add(new Movie(1L, "Spring Day", "A short description of Spring Day", "1h 40m", LocalDate.of(2021, Month.MARCH, 29), 3.2));
        movieList.add(new Movie(2L, "Parasite", "A short description of Parasite", "2h 03m", LocalDate.of(2021, Month.MARCH, 31), 5));
        movieList.add(new Movie(3L, "Green Book", "A short description of Green Book", "1h 56m", LocalDate.of(2021, Month.MARCH, 30), 4.7));
    }

    public static List<Movie> getMovieList() {
        return movieList;
    }
}
