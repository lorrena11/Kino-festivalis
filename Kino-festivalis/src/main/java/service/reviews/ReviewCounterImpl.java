package service.reviews;

import java.util.Map;
import java.util.stream.Collectors;

public class ReviewCounterImpl implements ReviewCounter {
    /**
     * this method counts how many reviews each movie has and returns a Map
     */
    public Map<String, Long> countReviews() {
        RetrieveReviews retrieveReviews = new RetrieveReviews();
//        return ReviewListFactory.getReviewList().stream()
//                .collect(Collectors.groupingBy(m -> m.getMovie().getName(), Collectors.counting()));

        return retrieveReviews.getAllReviews().stream()
                .collect(Collectors.groupingBy(m -> m.getMovie().getName(), Collectors.counting()));
    }

}
