package model;

import java.util.List;

public interface Criteria {
    public List<Movie> meetCriteria(List<Movie> movieList);
}
