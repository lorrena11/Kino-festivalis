package util;

import service.movies.MovieListFactory;
import service.movies.RetrieveMovies;
import service.reviews.ReviewListFactory;

public class CriteriaImplTest {
    private RetrieveMovies retrieveMovies = new RetrieveMovies();
    private MovieListFactory movieListFactory = new MovieListFactory();
    private ReviewListFactory reviewListFactory = new ReviewListFactory();
    private CriteriaImpl criteriaImpl = new CriteriaImpl();

//    @Test
//    void meetCriteriaTop5BestRated() {
//        movieListFactory.addTestMovies();
//        reviewListFactory.addTestReviews(MovieListFactory.getMovieList());
//        assertEquals(5, criteriaImpl.meetCriteriaTop5BestRated(retrieveMovies.receiveMoviesSortedByRating()).size());
//    }
//
//    @Test
//    void meetCriteriaTop5MostPopular() {
//        movieListFactory.addTestMovies();
//        reviewListFactory.addTestReviews(MovieListFactory.getMovieList());
//       // assertEquals(5, criteriaImpl.meetCriteriaTop5MostPopular(retrieveMovies.receiveMoviesGroupedByReviewCount()).size());
//    }
}
