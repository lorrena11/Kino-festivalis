package service.movies;

import exceptions.MovieNotFoundException;
import model.Movie;
import service.reviews.ReviewCounterImpl;
import util.AverageScoreComparator;
import util.CriteriaImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class for retrieving movies. Sends movies straight back to the user or provides Lists and Maps to other classes and methods.
 */
public class RetrieveMovies {

    public String receiveAllMovieNames() {
        List<Movie> movies = (MovieListFactory.getMovieList());
        StringBuilder movieNames = new StringBuilder();
        for (Movie entry : movies) {
            movieNames.append(entry.getId()).append(". ").append(entry.getName()).append("\n");
        }

        return String.valueOf(movieNames);
    }

    public Movie receiveChosenMovie(long id) throws MovieNotFoundException {
        List<Movie> movieList = MovieListFactory.getMovieList();
        Movie result = null;
        for (Movie entry : movieList) {
            if (entry.getId() == id) {
                result = entry;
            }
        }
        if (result == null) throw new MovieNotFoundException("Movie with this id doesn't exist");

        return result;
    }

    /**
     * this method adds just the id, name and rating of sorted movies to a String list
     */
    public List<String> receiveMoviesSortedByRating() {
        List<String> topRatedMovies = new ArrayList<>();
        List<Movie> movieList = new ArrayList<>(MovieListFactory.getMovieList());
        movieList.sort(new AverageScoreComparator());
        for (Movie entry : movieList) {
            String id = String.valueOf(entry.getId());
            String name = entry.getName();
            String rating = String.valueOf(entry.getAverageScore());
            topRatedMovies.add(id + ". " + name + " - Average score: [" + rating + "]");
        }

        return topRatedMovies;
    }

    /**
     * this method returns a Map of movie names and their total count of reviews
     */
    public Map<String, Long> receiveMoviesGroupedByReviewCount() {
        ReviewCounterImpl reviewCounterImpl = new ReviewCounterImpl();

        return reviewCounterImpl.countReviews();
    }

    /**
     * this method returns a list of top 5 best rated movies to the user,
     * by providing a sorted list to the criteriaImpl class method
     */
    public List<String> receiveTop5RatedMovies() {
        CriteriaImpl criteriaImpl = new CriteriaImpl();

        return criteriaImpl.meetCriteriaTop5BestRated(receiveMoviesSortedByRating());
    }

    /**
     * this method returns a list of top 5 movies that have the most reviews to the user,
     * by providing a Map with pre-counted values to the criteriaImpl class method
     */
    public Map<String, Long> receiveMostPopularMovies() {
        CriteriaImpl criteriaImpl = new CriteriaImpl();

        return criteriaImpl.meetCriteriaTop5MostPopular(receiveMoviesGroupedByReviewCount());
    }
}
