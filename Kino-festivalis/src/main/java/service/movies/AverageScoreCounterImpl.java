package service.movies;

import configuration.HibernateConfig;
import model.Movie;
import model.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.reviews.RetrieveReviews;
import service.reviews.ReviewListFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * class for counting the average score of the film
 */
public class AverageScoreCounterImpl implements AverageScoreCounter {
    /**
     * this method takes a movie as a param,
     * counts all of the user given ratings of this film from a review list
     * and sets a new average score.
     */
    public void updateAverageScore(Movie movie) {
        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();

        BigDecimal scoreSum = BigDecimal.ZERO;
        int totalReviewCount = 0;
        BigDecimal initialScore = BigDecimal.ZERO;
        BigDecimal newAverageScore;
        RetrieveReviews retrieveReviews = new RetrieveReviews();
        // List<Review> reviewList = ReviewListFactory.getReviewList();

        List<Review> reviewList = retrieveReviews.getAllReviews("movie_id", movie.getId(), false);

        for (Review entry : reviewList) {

            if (initialScore.equals(BigDecimal.ZERO)) {
                initialScore = initialScore.add(entry.getMovie().getInitialScore());
                scoreSum = scoreSum.add(initialScore);
                ++totalReviewCount;
            }
            scoreSum = scoreSum.add(entry.getScore());
            totalReviewCount++;

        }
        try {
            if (totalReviewCount != 0) {
                newAverageScore = scoreSum.divide(BigDecimal.valueOf(totalReviewCount), RoundingMode.HALF_EVEN);
            } else {
                newAverageScore = movie.getInitialScore();
            }
            movie.setAverageScore(newAverageScore);
            session.saveOrUpdate(movie);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    /**
     * basically this method calls the method above for every movie in the list.
     */
    public void updateAllAverageScores(List<Movie> movieList) {
        for (Movie entry : movieList) {
            updateAverageScore(entry);
        }
    }
}
