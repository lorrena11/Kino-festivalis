package util;

import java.util.List;
import java.util.Map;

/**
 * an interface to indicate that returned results meet certain criteria
 */
public interface Criteria {
    public List<String> meetCriteriaTop5BestRated(List<String> movieList);
    public Map<String, Long> meetCriteriaTop5MostPopular(Map<String, Long> reviewMap);
}
