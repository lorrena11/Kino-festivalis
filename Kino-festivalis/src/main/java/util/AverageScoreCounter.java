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
        ReviewListFactory reviewListFactory = new ReviewListFactory();
        List<Review> reviewList = reviewListFactory.getReviewList();
        for (Review entry : reviewList) {
            if (entry.getMovie().equals(movie)) {
                scoreSum += entry.getScore();
                totalReviewCount++;
            }
        }
        double newAverageScore = scoreSum / totalReviewCount;
        movie.setAverageScore(newAverageScore);
    }
}
