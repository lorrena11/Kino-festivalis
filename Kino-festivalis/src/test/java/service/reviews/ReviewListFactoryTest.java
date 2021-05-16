package service.reviews;

import configuration.HibernateConfig;
import exceptions.MovieNotFoundException;
import model.Movie;
import model.Review;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import service.movies.RetrieveMovies;

import java.math.BigDecimal;

public class ReviewListFactoryTest {
    ReviewListFactory reviewListFactory = new ReviewListFactory();
    RetrieveMovies retrieveMovies = new RetrieveMovies();

    @Test
    public void writeReview_savesReview() throws MovieNotFoundException {
        HibernateConfig.buildSessionFactory();
        Movie movie1 = retrieveMovies.receiveChosenMovie(1L);
        Movie movie2 = retrieveMovies.receiveChosenMovie(2L);
            try {
                Review review1 = new Review(movie1, BigDecimal.TEN, "comment");
                reviewListFactory.writeReview(review1);

                Review review2 = new Review(movie2, BigDecimal.TEN, "comment2");
                reviewListFactory.writeReview(review2);

                Assertions.assertEquals(review1.getMovie().getId(), retrieveMovies.receiveChosenMovie(movie1.getId()).getId());
                Assertions.assertEquals(review2.getMovie().getId(), retrieveMovies.receiveChosenMovie(movie2.getId()).getId());
            } catch (MovieNotFoundException e) {
                e.printStackTrace();
            }
        HibernateConfig.closeSessionFactory();
    }
}
