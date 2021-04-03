package util;

import exceptions.MovieNotFoundException;
import model.Movie;
import service.MovieListFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class RetrieveMovies {

    public static String receiveAllMovies() {
        List<Movie> movies = (MovieListFactory.getMovieList());
        StringBuilder movieNames = new StringBuilder();
        for (Movie entry : movies) {
            movieNames.append(entry.getId()).append(". ").append(entry.getName()).append("\n");
        }
        return String.valueOf(movieNames);
    }

    public static Movie receiveChosenMovie(long id) {
        List<Movie> movieList = MovieListFactory.getMovieList();
        Movie result = null;
        try {
            for (Movie entry : movieList) {
                if (entry.getId() == id) {
                    result = entry;
                }
            }
            if (result == null) throw new MovieNotFoundException();
        } catch (MovieNotFoundException e) {
            System.out.println("Movie with this ID not found");
        }
        return result;
    }

    // todo: look up how to un-sort list
    public static List<String> receiveBestRatedMovies() {
        List<String> topRatedMovies = new ArrayList<>();
        List<Movie> movieList = new ArrayList<>(MovieListFactory.getMovieList());
        //List<Movie> sortedList = movieList;
        movieList.sort(new AverageScoreComparator());
        for (Movie entry : movieList) {
            String id = String.valueOf(entry.getId());
            String name = entry.getName();
            String rating = String.valueOf(entry.getAverageScore());
            topRatedMovies.add(id + ". " + name + " - Average score: [" + rating + "]");
            if (topRatedMovies.size() >= 5) {
                break;
            }
        }

        return topRatedMovies;
    }
}
