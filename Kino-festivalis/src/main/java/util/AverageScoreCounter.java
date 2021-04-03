package util;

import model.Movie;
import model.Review;
import service.ReviewListFactory;

import java.util.Comparator;
import java.util.List;

/**
 * counts average rating of the film
 */
public class AverageScoreCounter {
    public void updateAverageScore(Movie movie) {
        double scoreSum = 0;
        double totalReviewCount = 0;
        double initialScore = 0;
        ReviewListFactory reviewListFactory = new ReviewListFactory();
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
        double newAverageScore = scoreSum / totalReviewCount;
        movie.setAverageScore(newAverageScore); // todo: suapvalinti reiksme
    }
}
