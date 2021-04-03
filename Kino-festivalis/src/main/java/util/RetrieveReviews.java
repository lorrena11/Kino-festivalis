package util;

import model.Movie;
import model.Review;
import service.ReviewListFactory;

import java.util.List;

public class RetrieveReviews {
    public static String readReviews(Movie movie) {
        ReviewListFactory obj = new ReviewListFactory();
        StringBuilder allReviews = new StringBuilder();
        List<Review> reviewList = obj.getReviewList();
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
