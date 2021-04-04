package service.movies;

import model.Movie;
import service.reviews.ReviewListFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * a class that holds a movie list
 */
public class MovieListFactory {
    // contains all the available films
    private static List<Movie> movieList = new ArrayList<>();

    public void addMovies() {
        // todo: nuskaityti is .txt failo?
        // todo: įrašinėjant su json nenaudoti localdate, o tik date
        Movie movie1 = new Movie(1L, "Spring Day", "A short description of Spring Day", "1h 40m", LocalDate.of(2021, Month.MARCH, 29), 3.2);
        Movie movie2 = new Movie(2L, "Parasite", "A short description of Parasite", "2h 03m", LocalDate.of(2021, Month.MARCH, 31), 5);
        Movie movie3 = new Movie(3L, "Green Book", "A short description of Green Book", "1h 56m", LocalDate.of(2021, Month.MARCH, 30), 4.7);
        Movie movie4 = new Movie(4L, "Unreleased", "A short description of Unreleased", "1h 30m", LocalDate.of(2021, Month.APRIL, 22), 3);

        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        movieList.add(movie4);

        ReviewListFactory obj = new ReviewListFactory();
        obj.addReview(movie1, 5, "very good");
        obj.addReview(movie1, 4.8, "good");
        obj.addReview(movie2, 2.5, "not great");
        obj.addReview(movie2, 3.1, "overrated");
        obj.addReview(movie3, 4, "i liked it");
        obj.addReview(movie3, 3.1, "fell asleep");
        obj.addReview(movie3, 5, "just happy to be here tbh");
        AverageScoreCounterImpl updateScore = new AverageScoreCounterImpl();
        updateScore.updateAllAverageScores(movieList);
    }

    public static List<Movie> getMovieList() {
        return movieList;
    }
}
