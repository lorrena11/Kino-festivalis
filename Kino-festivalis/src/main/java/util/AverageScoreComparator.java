package util;

import model.Movie;

import java.util.Comparator;

/**
 * method in this class compares movies by their average score
 */
public class AverageScoreComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return Double.compare(o2.getAverageScore(), o1.getAverageScore());
    }
}
