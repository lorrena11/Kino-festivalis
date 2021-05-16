package service.movies;

import configuration.HibernateConfig;
import exceptions.MovieNotFoundException;
import model.Movie;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RetrieveMoviesTest {
    private RetrieveMovies retrieveMovies = new RetrieveMovies();

    @BeforeAll
    static void beforeAll() {
        HibernateConfig.buildSessionFactory();
    }

    @AfterAll
    static void afterAll() {
        HibernateConfig.closeSessionFactory();
    }

    @Test
    void getAllMovies() {
        List<Movie> movieList = retrieveMovies.getAllMovies();
        Assertions.assertEquals(14, movieList.size());
    }

    @Test
    void getAllMovies_getsOneMovieByKey() {
        Long id1 = 1L;
        List<Movie> moviesList = retrieveMovies.getAllMovies("id", id1, true);
        List<Movie> moviesList2 = retrieveMovies.getAllMovies("name", "Parasite", true);
        Assertions.assertEquals(1, moviesList.size());
        Assertions.assertEquals("Parasite", moviesList2.get(0).getName());
    }

    @Test
    void getAllMovies_getsSeveralMoviesByKey() {
        List<Movie> moviesList = retrieveMovies.getAllMovies("initial_score", 8.0, false);
        Assertions.assertEquals(3, moviesList.size());
    }

    @Test
    void receiveChosenMovie_getsMovieById() throws MovieNotFoundException {
        Movie movie2 = retrieveMovies.receiveChosenMovie(2L);
        Movie movie3 = retrieveMovies.receiveChosenMovie(3L);

        Assertions.assertEquals("Parasite", movie2.getName());
        Assertions.assertEquals(movie2.getDescription(), retrieveMovies.receiveChosenMovie(movie2.getId()).getDescription());
        Assertions.assertEquals("Green Book", movie3.getName());
        Assertions.assertEquals(movie3.getDescription(), retrieveMovies.receiveChosenMovie(movie3.getId()).getDescription());
    }
}
