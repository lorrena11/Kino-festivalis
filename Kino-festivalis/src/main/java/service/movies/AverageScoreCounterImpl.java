package service.movies;

import model.Movie;
import model.Review;
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
        BigDecimal scoreSum = BigDecimal.ZERO;
        int totalReviewCount = 0;
        BigDecimal initialScore = BigDecimal.ZERO;
        List<Review> reviewList = ReviewListFactory.getReviewList();
        for (Review entry : reviewList) {
            if (entry.getMovie().equals(movie)) {
                if (initialScore.equals(BigDecimal.ZERO)) {
                    initialScore = initialScore.add(entry.getMovie().getAverageScore());
                    scoreSum = scoreSum.add(initialScore);
                    ++totalReviewCount;
                }
                scoreSum = scoreSum.add(entry.getScore());
                totalReviewCount++;
            }
        }
        if (totalReviewCount != 0) {
            BigDecimal newAverageScore = scoreSum.divide(BigDecimal.valueOf(totalReviewCount), RoundingMode.HALF_EVEN);
            //double scoreAverage = scoreSum / totalReviewCount;
            //BigDecimal newAverageScore = (double)Math.round(scoreAverage * 10d) / 10d;
            movie.setAverageScore(newAverageScore);
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
