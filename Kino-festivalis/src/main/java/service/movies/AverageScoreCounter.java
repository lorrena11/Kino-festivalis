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
public class AverageScoreCounter {
    /**
     * this method takes a movie as a param,
     * counts all of the user given ratings of this film from a review list
     * and sets a new average score.
     */
    public static void updateAverageScore(Movie movie) {
        double scoreSum = 0;
        double totalReviewCount = 0;
        double initialScore = 0;
        List<Review> reviewList = ReviewListFactory.getReviewList();
        for (Review entry : reviewList) {
            if (entry.getMovie().equals(movie)) {
                if (initialScore == 0) {
                    initialScore += entry.getMovie().getAverageScore();
                    scoreSum += initialScore;
                    totalReviewCount++;
                }
                scoreSum += entry.getScore();
                totalReviewCount++;
            }
        }
        double scoreAverage = scoreSum / totalReviewCount;
        BigDecimal scoreAverageBD = new BigDecimal(scoreAverage).setScale(1, RoundingMode.HALF_UP);
        double newAverageScore = scoreAverageBD.doubleValue();
        movie.setAverageScore(newAverageScore);
    }

    /**
     * basically this method calls the method above for every movie in the list.
     */
    public static void updateAllAverageScores(List<Movie> movieList) {
        for (Movie entry : movieList) {
            updateAverageScore(entry);
        }
    }
}
