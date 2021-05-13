package model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * a movie representation
 */
public class Movie {
    private long id;
    private String name;
    private String description;
    private String duration;
    private Date premiereDate;
    private BigDecimal averageScore;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Movie() {
    }

    public Movie(long id, String name, String description, String duration, Date premiereDate, BigDecimal averageScore) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.premiereDate = premiereDate;
        this.averageScore = averageScore;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public Date getPremiereDate() {
        return premiereDate;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(BigDecimal averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public String toString() {
        return "Movie {" + id +
                " - '" + name + '\'' +
                ", description: '" + description + '\'' +
                ". Duration: '" + duration + '\'' +
                "; Premiere date: " + df.format(premiereDate) +
                "; Average audience score: " + averageScore +
                '}';
    }
}
