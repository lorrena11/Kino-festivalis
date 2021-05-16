package service.movies;

import configuration.HibernateConfig;
import exceptions.MovieNotFoundException;
import model.Movie;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;


public class MovieListFactoryTest {
    private RetrieveMovies retrieveMovies = new RetrieveMovies();
    private MovieListFactory movieListFactory = new MovieListFactory();

    @Test
    public void save() throws MovieNotFoundException {
        HibernateConfig.buildSessionFactory();
        Movie movie1 = new Movie("Spring Day",
                "A short description of Spring Day",
                "1h 40m",
                LocalDate.of(2021, Month.MARCH, 29),
                BigDecimal.valueOf(7), null);
        movieListFactory.save(movie1);

        Movie movie2 = new Movie("Parasite",
                "A short description of Parasite",
                "2h 03m",
                LocalDate.of(2021, Month.MARCH, 31),
                BigDecimal.valueOf(8.9), null);
        movieListFactory.save(movie2);

        Assertions.assertEquals(movie1.getId(), retrieveMovies.receiveChosenMovie(movie1.getId()).getId());
        Assertions.assertEquals(movie2.getId(), retrieveMovies.receiveChosenMovie(movie2.getId()).getId());

        movieListFactory.delete(movie1);
        movieListFactory.delete(movie2);
        HibernateConfig.closeSessionFactory();
    }

}
