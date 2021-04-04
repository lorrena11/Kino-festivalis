package service.reviews;

import model.Movie;
import model.Review;
import service.movies.AverageScoreCounterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * a class that holds a review list
 */
public class ReviewListFactory {
    private static List<Review> reviewList = new ArrayList<>();

    public void addReview(Movie movie, double score, String comment) {
        reviewList.add(new Review(movie, score, comment));
    }

    public void addTestReviews(List<Movie> movieList) {
        addReview(movieList.get(0), 9, "very good");
        addReview(movieList.get(0), 8, "good");
        addReview(movieList.get(1), 7, "not great");
        addReview(movieList.get(1), 4.5, "overrated");
        addReview(movieList.get(2), 7.5, "i liked it");
        addReview(movieList.get(2), 5, "fell asleep");
        addReview(movieList.get(2), 10, "just happy to be here tbh");
        addReview(movieList.get(2), 8.5, "very good movie");
        addReview(movieList.get(5), 8, "was ok I guess");
        addReview(movieList.get(5), 8, "dragon looks weird");
        addReview(movieList.get(6), 3, "unrealistic");
        addReview(movieList.get(8), 6, "gave me an existential crisis");
        addReview(movieList.get(8), 9.2, "it was nice");
        addReview(movieList.get(9), 10, "i liked the guy without nose");
        addReview(movieList.get(9), 7, "too long");
        addReview(movieList.get(9), 8.5, "very nice film");

        AverageScoreCounterImpl updateScore = new AverageScoreCounterImpl();
        updateScore.updateAllAverageScores(movieList);
    }

    public static List<Review> getReviewList() {
        return reviewList;
    }

}
