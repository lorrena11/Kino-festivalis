package service.movies;

import configuration.HibernateConfig;
import exceptions.MovieNotFoundException;
import model.Movie;
import model.Review;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.reviews.RetrieveReviews;
import service.reviews.ReviewListFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;


public class AverageScoreCounterImplTest {
    private RetrieveMovies retrieveMovies = new RetrieveMovies();
    private RetrieveReviews retrieveReviews = new RetrieveReviews();
    private MovieListFactory movieListFactory = new MovieListFactory();
    private ReviewListFactory reviewListFactory = new ReviewListFactory();
    private AverageScoreCounterImpl averageScoreCounter = new AverageScoreCounterImpl();

    @BeforeAll
    static void beforeAll() {
        HibernateConfig.buildSessionFactory();
    }

    @AfterAll
    static void afterAll() {
        HibernateConfig.closeSessionFactory();
    }

    @Test
    void updateAverageScore_setsNewAverageScore() {
        Movie movie = new Movie("Parasite",
                "A short description of Parasite",
                "2h 03m",
                LocalDate.of(2021, Month.MARCH, 31),
                BigDecimal.TEN, null);
        movieListFactory.save(movie);

        Review review = new Review(movie, BigDecimal.valueOf(2), "testcomment");
        reviewListFactory.writeReview(review);

        averageScoreCounter.updateAverageScore(movie);
        Assertions.assertEquals(BigDecimal.valueOf(6.0), movie.getAverageScore());

        List<Review> dbReview = retrieveReviews.getAllReviews("comment", "testcomment", true);
        reviewListFactory.delete(dbReview.get(0));
        List<Movie> dbMovie = retrieveMovies.getAllMovies("id", movie.getId(), true);
        movieListFactory.delete(dbMovie.get(0));
    }

    @Test
    void updateAverageScore_setsNewAverageScoreForAll() {
        Movie movie1 = new Movie("TestMovie",
                "A short description of Movie1",
                "2h 03m",
                LocalDate.of(2021, Month.MARCH, 31),
                BigDecimal.TEN, null);
        Movie movie2 = new Movie("TestMovie",
                "A short description of Movie2",
                "2h 03m",
                LocalDate.of(2021, Month.MARCH, 31),
                BigDecimal.valueOf(5), null);
        Movie movie3 = new Movie("TestMovie",
                "A short description of Movie3",
                "2h 03m",
                LocalDate.of(2021, Month.MARCH, 31),
                BigDecimal.TEN, null);

        movieListFactory.save(movie1);
        movieListFactory.save(movie2);
        movieListFactory.save(movie3);
        List<Movie> dbMovies = retrieveMovies.getAllMovies("name", "TestMovie", false);

        Review review1 = new Review(movie1, BigDecimal.valueOf(2), "testcomment");
        Review review2 = new Review(movie2, BigDecimal.valueOf(8.5), "testcomment");
        Review review3 = new Review(movie3, BigDecimal.valueOf(8), "testcomment");
        reviewListFactory.writeReview(review1);
        reviewListFactory.writeReview(review2);
        reviewListFactory.writeReview(review3);
        averageScoreCounter.updateAllAverageScores(dbMovies);

        Assertions.assertEquals(BigDecimal.valueOf(6.0), movie1.getAverageScore());
        Assertions.assertEquals(BigDecimal.valueOf(6.8), movie2.getAverageScore());
        Assertions.assertEquals(BigDecimal.valueOf(9.0), movie3.getAverageScore());

        List<Review> dbReviews = retrieveReviews.getAllReviews("comment", "testcomment", false);

        dbReviews.forEach(review -> {
            reviewListFactory.delete(review);
        });

        dbMovies.forEach(movie -> {
            movieListFactory.delete(movie);
            try {
                Assertions.assertNull(retrieveMovies.receiveChosenMovie(movie.getId()));
            } catch (MovieNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

}
