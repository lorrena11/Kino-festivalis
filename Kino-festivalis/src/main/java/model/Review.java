package model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * a rating representation
 */
public class Review {
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private BigDecimal score;

    private String comment;

    @CreationTimestamp
    @Column(name = "created")
    private LocalDateTime created;

    public Review(Movie movie, BigDecimal score, String comment) {
        this.movie = movie;
        this.score = score;
        this.comment = comment;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public BigDecimal getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "movie=" + movie.getName() +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                '}';
    }
}
