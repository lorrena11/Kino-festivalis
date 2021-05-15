package model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * a movie representation
 */
@Entity
@Table(name = "movie", schema = "public")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private String duration;

    @Column(name = "premiere_date")
    private LocalDate premiereDate;
    // private Date premiereDate;

    @Column(name = "initial_score")
    private BigDecimal initialScore;

    @Column(name = "average_score")
    private BigDecimal averageScore;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    private List<Review> reviews;

    //private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Movie() {
    }

    public Movie(String name, String description, String duration, LocalDate premiereDate, BigDecimal initialScore, BigDecimal averageScore) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.premiereDate = premiereDate;
        this.initialScore = initialScore;
        this.averageScore = averageScore;
    }

//    public Movie(String name, String description, String duration, LocalDate premiereDate, BigDecimal initialScore) {
//        this.name = name;
//        this.description = description;
//        this.duration = duration;
//        this.premiereDate = premiereDate;
//        this.initialScore = initialScore;
//    }

    /**
     * old project constructor
     */
    public Movie(long id, String name, String description, String duration, LocalDate premiereDate, BigDecimal averageScore) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.premiereDate = premiereDate;
        this.averageScore = averageScore;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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

    public LocalDate getPremiereDate() {
        return premiereDate;
    }

    public BigDecimal getInitialScore() {
        return initialScore;
    }

    public void setAverageScore(BigDecimal averageScore) {
        this.averageScore = averageScore;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }

    @Override
    public String toString() {
        return "Movie {" + id +
                " - '" + name + '\'' +
                ", description: '" + description + '\'' +
                ". Duration: '" + duration + '\'' +
                "; Premiere date: " + premiereDate +
                "; Average audience score: " + averageScore +
                '}';
    }
}
