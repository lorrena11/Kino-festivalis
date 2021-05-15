package service.movies;

import exceptions.MovieNotFoundException;
import model.Movie;
import service.reviews.ReviewCounterImpl;
import util.AverageScoreComparator;
import util.CriteriaImpl;
import configuration.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

/**
 * class for retrieving movies. Sends movies straight back to the user or provides Lists and Maps to other classes and methods.
 */
public class RetrieveMovies {

    /**
     * get all movies:
     */
    public List<Movie> getAllMovies(String key, Object value) {
        return getAllMovies(key, value, false);
    }

    public List<Movie> getAllMovies() {
        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();
        List<Movie> movies = new ArrayList<>();

        try {
            Query<Movie> query = session.createQuery("FROM Movie ORDER BY id", Movie.class);
            movies = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

        return movies;
    }

    private List<Movie> getAllMovies(String key, Object value, boolean limitOne) {
        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();
        List<Movie> movieList = new ArrayList<>();

        try {
            Query<Movie> query = session.createQuery(String.format("FROM Movie WHERE %s = :%s", key, key), Movie.class);
            query.setParameter(key, value);

            if (limitOne) {
                query.setMaxResults(1);
            }

            movieList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

        return movieList;
    }

    /**
     * previous methods below, edited:
     */
    public String receiveAllMovieNames() {
        // List<Movie> movies = (MovieListFactory.getMovieList());
        List<Movie> movies = getAllMovies();
        StringBuilder movieNames = new StringBuilder();
        for (Movie entry : movies) {
            movieNames.append(entry.getId()).append(". ").append(entry.getName()).append("\n");
        }

        return String.valueOf(movieNames);
    }

    public Movie receiveChosenMovie(long id) throws MovieNotFoundException {
        // List<Movie> movieList = MovieListFactory.getMovieList();
        List<Movie> moviesList = getAllMovies("id", id, true);
        Movie result = moviesList.size() > 0 ? moviesList.get(0) : null;
//        for (Movie entry : movieList) {
//            if (entry.getId() == id) {
//                result = entry;
//            }
//        }
        if (result == null) throw new MovieNotFoundException("Movie with this id doesn't exist");

        return result;
    }

    /**
     * this method adds just the id, name and rating of sorted movies to a String list
     */
    public List<String> receiveMoviesSortedByRating() {
        List<String> topRatedMovies = new ArrayList<>();
        List<Movie> allMovies = new ArrayList<>(getAllMovies());
        List<Movie> sortedMovies = new ArrayList<>(allMovies);
        sortedMovies.sort(new AverageScoreComparator());
        for (Movie entry : sortedMovies) {
            String id = String.valueOf(entry.getId());
            String name = entry.getName();
            String rating = String.valueOf(entry.getAverageScore());
            topRatedMovies.add(id + ". " + name + " - Average score: [" + rating + "]");
        }

        return topRatedMovies;
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
     * this method returns a Map of movie names and their total count of reviews
     * (Map is used for another method below)
     */
    public Map<String, Long> receiveMoviesGroupedByReviewCount() {
        ReviewCounterImpl reviewCounterImpl = new ReviewCounterImpl();

        return reviewCounterImpl.countReviews();
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
