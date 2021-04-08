package util;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CriteriaImpl implements Criteria {
    /**
     * method takes a list of pre-sorted movie names,
     * limits the number of entries to 5.
     * returns a list of top 5 best rated movie names.
     */
    @Override
    public List<String> meetCriteriaTop5BestRated(List<String> movieList) {
        return movieList.stream()
                .limit(5)
                .collect(Collectors.toList());
    }

    /**
     * method takes a Map of movie names and their total count of reviews as a parameter,
     * sorts them in descending order
     * and limits the number of entries to 5.
     * Returns a Map of 5 entries with the most reviews.
     */
    @Override
    public Map<String, Long> meetCriteriaTop5MostPopular(Map<String, Long> reviewMap) {
        Map<String, Long> top5MostPopular = new LinkedHashMap<>();
        reviewMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .forEach(entry -> top5MostPopular.put("Name: " + entry.getKey() + " - review count ", entry.getValue()));

        return top5MostPopular;
    }
}
