package service.movies;

import model.Movie;

import java.util.List;

/**
 * service for calculating and updating average score of movies
 */
public interface AverageScoreCounter {
    public void updateAverageScore(Movie movie);

    public void updateAllAverageScores(List<Movie> movieList);
}
