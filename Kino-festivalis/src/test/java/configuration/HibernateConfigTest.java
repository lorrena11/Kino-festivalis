package configuration;

import model.Movie;
import model.Review;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import org.junit.jupiter.api.Assertions;


import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HibernateConfigTest {
    private Session session;

    private Movie movie = new Movie("TestMovie",
            "A short description of Movie",
            "2h 03m",
            LocalDate.of(2021, Month.MARCH, 31),
            BigDecimal.TEN, null);

    @Test
    @Order(1)
    public void testCreate() {
        HibernateConfig.buildSessionFactory();
        session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Running create test");

        session.save(movie);
        Review review = new Review(movie, BigDecimal.ONE, "testcomment");

        Long reviewId = (Long) session.save(review);

        transaction.commit();

        Assertions.assertTrue(reviewId > 0);
        session.close();
        HibernateConfig.closeSessionFactory();
    }

    @Test
    @Order(2)
    public void testUpdate() {
        HibernateConfig.buildSessionFactory();
        session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Running update test");

        session.save(movie);
        movie.setDescription("Changed description of TestMovie");
        session.saveOrUpdate(movie);

        Movie updatedMovie = session.find(Movie.class, movie.getId());
        transaction.commit();
        Assertions.assertEquals("Changed description of TestMovie", updatedMovie.getDescription());
        session.close();
        HibernateConfig.closeSessionFactory();
    }

    @Test
    @Order(3)
    public void testRead() {
        HibernateConfig.buildSessionFactory();
        session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Running read test");
        List<Movie> foundMovie = new ArrayList<>();
        String key = "name";
        Object value = movie.getName();

        Query<Movie> query = session.createQuery(String.format("FROM Movie WHERE %s = :%s", key, key), Movie.class);
        query.setParameter(key, value);
        query.setMaxResults(1);
        foundMovie = query.getResultList();

        transaction.commit();
        Assertions.assertEquals(movie.getName(), foundMovie.get(0).getName());
        session.close();
        HibernateConfig.closeSessionFactory();
    }

    @Test
    @Order(4)
    public void testDelete() {
        HibernateConfig.buildSessionFactory();
        session = HibernateConfig.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Running delete test");
        String key = "comment";
        Object value = "testcomment";

        List<Review> reviews = new ArrayList<>();
        Query<Review> query = session.createQuery(String.format("FROM Review WHERE %s = :%s", key, key), Review.class);
        query.setParameter(key, value);
        query.setMaxResults(1);
        reviews = query.getResultList();


        List<Movie> movies = new ArrayList<>();
        String keym = "name";
        Object valuem = movie.getName();
        Query<Movie> querym = session.createQuery(String.format("FROM Movie WHERE %s = :%s", keym, keym), Movie.class);
        querym.setParameter(keym, valuem);
        querym.setMaxResults(1);
        movies = querym.getResultList();


        session.delete(reviews.get(0));
        session.delete(movies.get(0));
        transaction.commit();
        reviews.clear();
        movies.clear();
        Movie deletedMovie = session.find(Movie.class, movie.getId());
        Assertions.assertNull(deletedMovie);
        session.close();
        HibernateConfig.closeSessionFactory();
    }
}
