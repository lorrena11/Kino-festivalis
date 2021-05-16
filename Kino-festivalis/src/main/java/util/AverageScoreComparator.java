package util;

import model.Movie;

import java.util.Comparator;

/**
 * method in this class compares movies by their average score
 */
public class AverageScoreComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o2.getAverageScore().compareTo(o1.getAverageScore());
    }
}
