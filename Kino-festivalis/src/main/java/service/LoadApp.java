package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Movie;
import service.movies.MovieListFactory;
import service.reviews.ReviewListFactory;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * a class that prepares the program by generating some initial movies (from json file) and their reviews
 * before the user gets to interact with the app
 */
public class LoadApp {
    public static void load() throws IOException {
        MovieListFactory movieListFactory = new MovieListFactory();
        ReviewListFactory reviewListFactory = new ReviewListFactory();
        File moviesFile = new File("allMovies.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);

        /**
         * generate some data if file doesn't exist yet and serialize it
         */
        if (!moviesFile.exists()) {
            movieListFactory.addTestMovies();
            mapper.writeValue(moviesFile, MovieListFactory.getMovieList());
        }

        /**
        * deserialize from json
        */
        List<Movie> movieListFromFile = mapper.readValue(moviesFile, new TypeReference<>() {
        });
        MovieListFactory.setMovieList(movieListFromFile);

        /**
         * generate some initial reviews
         */
        reviewListFactory.addTestReviews(movieListFromFile);
    }


}
