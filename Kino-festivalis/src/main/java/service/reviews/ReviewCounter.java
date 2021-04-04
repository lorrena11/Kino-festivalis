package service.reviews;

import java.util.Map;
import java.util.stream.Collectors;

public class ReviewCounter {
    /**
     * this method counts how many reviews each movie has and returns a Map
     */
    public Map<String, Long> countReviews() {
        return ReviewListFactory.getReviewList().stream()
                .collect(Collectors.groupingBy(m -> m.getMovie().getName(), Collectors.counting()));
    }

}
