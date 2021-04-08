package model;
/**
 * a rating representation
 */
public class Review {
    private Movie movie;
    private double score;
    private String comment;

    public Review(Movie movie, double score, String comment) {
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

    public double getScore() {
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
