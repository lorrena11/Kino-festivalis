package model;

import java.time.LocalDate;

/**
 * a movie representation
 */
public class Movie implements Rateable {
    private long id;
    private String name;
    private String description;
    private String duration;
    private LocalDate premiereDate;
    private double rating;

    public Movie(long id, String name, String description, String duration, LocalDate premiereDate, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.premiereDate = premiereDate;
        this.rating = rating;
        // todo: nustatyti initial "kritiku" reitinga?
    }

    public LocalDate getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(LocalDate premiereDate) {
        this.premiereDate = premiereDate;
    }

    @Override
    public double getRating() {
        return rating;
    }
}
