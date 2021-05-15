package service.reviews;

import configuration.HibernateConfig;
import model.Movie;
import model.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RetrieveReviews {
    /**
     * get all reviews
     */
    public List<Review> getAllReviews() {
        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();
        List<Review> reviewList = new ArrayList<>();

        try {
            Query<Review> query = session.createQuery("FROM Review", Review.class);
            reviewList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

        return reviewList;
    }

    public List<Review> getAllReviews(String key, Object value) {
        return getAllReviews(key, value, false);
    }

    public List<Review> getAllReviews(String key, Object value, boolean limitOne) {
        Session session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();
        List<Review> reviewList = new ArrayList<>();

        try {
            Query<Review> query = session.createQuery(String.format("FROM Review WHERE %s = :%s", key, key), Review.class);
            query.setParameter(key, value);

            if (limitOne) {
                query.setMaxResults(1);
            }

            reviewList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

        return reviewList;
    }

    /**
     * previous methods below, edited:
     * this method takes a certain movie as a parameter, returns all reviews of this movie from the list.
     */
    public String readReviews(Movie movie) {

        StringBuilder allReviews = new StringBuilder();
        List<Review> reviewList = getAllReviews("movie_id", movie.getId(), false);
        for (Review entry : reviewList) {
            allReviews.append("Comment: [");
            allReviews.append(entry.getComment());
            allReviews.append("]; Rated: [");
            allReviews.append(entry.getScore());
            allReviews.append("]\n");
        }
        return String.valueOf(allReviews);

    }

}
