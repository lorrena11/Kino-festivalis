package service.reviews;

import model.Movie;
import model.Review;

import java.util.List;

public class RetrieveReviews {
    /**
     * this method takes a certain movie as a parameter, returns all reviews of this movie from the list.
     */
    public static String readReviews(Movie movie) {
        StringBuilder allReviews = new StringBuilder();
        List<Review> reviewList = ReviewListFactory.getReviewList();
        for (Review entry : reviewList)
            if (entry.getMovie().equals(movie)) {
                allReviews.append("Comment: [");
                allReviews.append(entry.getComment());
                allReviews.append("]; Rated: [");
                allReviews.append(entry.getScore());
                allReviews.append("]\n");
            }
        if (allReviews.length()==0) allReviews.append("This movie has no audience reviews yet");
        return String.valueOf(allReviews);
    }
}
