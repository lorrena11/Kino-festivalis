package service.movies;

import model.Movie;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * a class used previously to add some test values to movie list
 */
public class MovieListFactory {
    // contains all the available films
    private static List<Movie> movieList = new ArrayList<>();

    /**
     * this method generates movie data for the app
     */
    public void addTestMovies() {
        // todo: įrašinėjant su json nenaudoti localdate, o tik date
        // int year = 2021 - 1900;
        Movie movie1 = new Movie(1L, "Spring Day", "A short description of Spring Day", "1h 40m", LocalDate.of(2021, Month.MARCH, 29), BigDecimal.valueOf(7));
        Movie movie2 = new Movie(2L, "Parasite", "A short description of Parasite", "2h 03m", LocalDate.of(2021, Month.MARCH, 31), BigDecimal.valueOf(8.9));
        Movie movie3 = new Movie(3L, "Green Book", "A short description of Green Book", "1h 56m", LocalDate.of(2021, Month.MARCH, 30), BigDecimal.valueOf(8));
        Movie movie4 = new Movie(4L, "Unreleased", "A short description of Unreleased", "1h 30m", LocalDate.of(2021, Month.APRIL, 22), BigDecimal.valueOf(9.2));
        Movie movie5 = new Movie(5L, "Tom & Jerry", "A short description of Tom & Jerry", "1h 35m", LocalDate.of(2021, Month.APRIL, 1), BigDecimal.valueOf(3.7));
        Movie movie6 = new Movie(6L, "Raya and the last dragon", "A short description of Raya and the last dragon", "1h 45m", LocalDate.of(2021, Month.APRIL, 2), BigDecimal.valueOf(7.7));
        Movie movie7 = new Movie(7L, "Godzilla vs Kong", "A short description of Godzilla vs Kong", "2h 03m", LocalDate.of(2021, Month.APRIL, 3), BigDecimal.valueOf(5));
        Movie movie8 = new Movie(8L, "Minari", "A short description of Minari", "2h 00m", LocalDate.of(2021, Month.APRIL, 7), BigDecimal.valueOf(9.5));
        Movie movie9 = new Movie(9L, "Soul", "A short description of Soul", "1h 40m", LocalDate.of(2021, Month.MARCH, 25), BigDecimal.valueOf(9));
        Movie movie10 = new Movie(10L, "Harry Potter", "A short description of Harry Potter", "1h 35m", LocalDate.of(2021, Month.APRIL, 1), BigDecimal.valueOf(3.7));
        Movie movie11 = new Movie(11L, "Casablanca", "A short description of Casablanca", "2h 35m", LocalDate.of(2021, Month.APRIL, 29), BigDecimal.valueOf(8.3));
        Movie movie12 = new Movie(12L, "The Godfather", "A short description of The Godfather", "2h 15m", LocalDate.of(2021, Month.APRIL, 20), BigDecimal.valueOf(9));
        Movie movie13 = new Movie(13L, "Spotlight", "A short description of Spotlight", "2h 07m", LocalDate.of(2021, Month.APRIL, 7), BigDecimal.valueOf(8));
        Movie movie14 = new Movie(13L, "Frozen", "A short description of Frozen", "2h 00m", LocalDate.of(2021, Month.JUNE, 7), BigDecimal.valueOf(5.5));

        List<Movie> generatedList = List.of(movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10, movie11, movie12, movie13, movie14);
        setMovieList(generatedList);
    }

    public static void setMovieList(List<Movie> movieList) {
        MovieListFactory.movieList = movieList;
    }

    public static List<Movie> getMovieList() {
        return movieList;
    }

}
