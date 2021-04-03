package service;

import model.Movie;
import model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewListFactory {
    private static List<Review> reviewList = new ArrayList<>();

    public void addReview(Movie movie, double score, String comment) {
        reviewList.add(new Review(movie, score, comment));
    }

    public static List<Review> getReviewList() {
        return reviewList;
    }

}
