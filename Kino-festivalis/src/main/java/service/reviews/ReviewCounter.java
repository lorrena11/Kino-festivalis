package service.reviews;

import java.util.Map;

/**
 * service that calculates the reviews of each movie
 */
public interface ReviewCounter {
    public Map<String, Long> countReviews();
}
