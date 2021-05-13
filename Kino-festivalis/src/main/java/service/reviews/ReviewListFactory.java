package service.reviews;

import model.Movie;
import model.Review;
import service.movies.AverageScoreCounterImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * a class that holds a review list
 */
public class ReviewListFactory {
    private static List<Review> reviewList = new ArrayList<>();

    public void addReview(Movie movie, BigDecimal score, String comment) {
        reviewList.add(new Review(movie, score, comment));
    }

    public void addTestReviews(List<Movie> movieList) {
        addReview(movieList.get(0), BigDecimal.valueOf(9), "very good");
        addReview(movieList.get(0), BigDecimal.valueOf(8), "good");
        addReview(movieList.get(1), BigDecimal.valueOf(7), "not great");
        addReview(movieList.get(1), BigDecimal.valueOf(4.5), "overrated");
        addReview(movieList.get(2), BigDecimal.valueOf(7.5), "i liked it");
        addReview(movieList.get(2), BigDecimal.valueOf(5), "fell asleep");
        addReview(movieList.get(2), BigDecimal.valueOf(10), "just happy to be here tbh");
        addReview(movieList.get(2), BigDecimal.valueOf(8.5), "very good movie");
        addReview(movieList.get(5), BigDecimal.valueOf(8), "was ok I guess");
        addReview(movieList.get(5), BigDecimal.valueOf(6), "dragon looks weird");
        addReview(movieList.get(6), BigDecimal.valueOf(3), "unrealistic");
        addReview(movieList.get(8), BigDecimal.valueOf(6), "gave me an existential crisis");
        addReview(movieList.get(8), BigDecimal.valueOf(9.2), "it was nice");
        addReview(movieList.get(9), BigDecimal.valueOf(10), "i liked the guy without nose");
        addReview(movieList.get(9), BigDecimal.valueOf(7), "too long");
        addReview(movieList.get(9), BigDecimal.valueOf(8.5), "very nice film");

        AverageScoreCounterImpl updateScore = new AverageScoreCounterImpl();
        updateScore.updateAllAverageScores(movieList);
    }

    public static List<Review> getReviewList() {
        return reviewList;
    }

}
