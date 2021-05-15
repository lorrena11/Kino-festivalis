import configuration.HibernateConfig;
import exceptions.IncorrectReviewException;
import exceptions.MovieNotFoundException;
import exceptions.MovieNotOutYetException;
import model.Movie;
import service.LoadApp;
import service.movies.AverageScoreCounterImpl;
import service.movies.RetrieveMovies;
import service.reviews.RetrieveReviews;
import service.reviews.ReviewListFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * this class lets the user interact with the system
 */
public class Main {
    public static void main(String[] args) throws IOException {
        // LoadApp.load();
        HibernateConfig.buildSessionFactory();
        ReviewListFactory writeReview = new ReviewListFactory();
        RetrieveMovies retrieveMovies = new RetrieveMovies();
        RetrieveReviews retrieveReviews = new RetrieveReviews();
        AverageScoreCounterImpl updateScore = new AverageScoreCounterImpl();
        updateScore.updateAllAverageScores(retrieveMovies.getAllMovies()); // necessary since we added example reviews

        //Date currentDate = new Date(System.currentTimeMillis());
        LocalDate currentDate = LocalDate.now();

        Scanner scanner = new Scanner(System.in);
        boolean program = true;
        while (program) {
            System.out.println("===================== FILM FESTIVAL - MENU ======================");
            System.out.println("[1] - show all movies; [2] - show info of chosen film; [3] - show best rated films;");
            System.out.println("[4] - show most popular films; [5] - write a review; [6] - exit");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("====== All festival entries: ====== ");
                        System.out.println(retrieveMovies.receiveAllMovieNames());
                        break;
                    case 2:
                        System.out.println("====== All festival entries: ====== ");
                        System.out.println(retrieveMovies.receiveAllMovieNames());
                        try {
                            System.out.println("Type in movie ID: ");
                            long id = scanner.nextLong();
                            Movie chosenMovieInfo = retrieveMovies.receiveChosenMovie(id);

                            if (chosenMovieInfo != null) {
                                System.out.println(chosenMovieInfo);
                                System.out.println("\nRead comments? [1] - read comments; [2] - go back");
                                int choiceRead = scanner.nextInt();
                                if (choiceRead == 1) {
                                    System.out.println(retrieveReviews.readReviews(chosenMovieInfo));
                                } else {
                                    System.out.println("Back to main menu");
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Typed in incorrect choice, do better next time\n");
                            scanner.next();
                        } catch (MovieNotFoundException e) {
                            System.out.println(e.getReason());
                        }
                        break;
                    case 3:
                        System.out.println("====== Best rated festival entries: ====== ");
                        System.out.println(retrieveMovies.receiveTop5RatedMovies() + "\n");
                        break;
                    case 4:
                        System.out.println("====== Most popular festival entries: ====== ");
                        System.out.println(retrieveMovies.receiveMostPopularMovies() + "\n");
                        break;
                    case 5:
                        System.out.println("====== All festival entries: ====== ");
                        System.out.println(retrieveMovies.receiveAllMovieNames());
                        try {
                            System.out.println("\nChoose which film to review by typing ID: ");
                            long id1 = scanner.nextLong();
                            Movie chosenMovieToReview = retrieveMovies.receiveChosenMovie(id1);

                            if (chosenMovieToReview.getPremiereDate().isBefore(currentDate)) {
                                boolean writingReview = true;

                                while (writingReview) {
                                    System.out.println("Add your rating [1-10]: ");
                                    try {
                                        BigDecimal userRating = BigDecimal.valueOf(scanner.nextDouble());
                                        if (userRating.compareTo(BigDecimal.ONE) >= 0 && userRating.compareTo(BigDecimal.valueOf(10.01)) < 0) {
                                            scanner.nextLine();
                                            System.out.println("Add a comment: ");
                                            String userComment = scanner.nextLine();
                                            writeReview.addReview(chosenMovieToReview, userRating, userComment);
                                            updateScore.updateAverageScore(chosenMovieToReview);
                                            System.out.println("Review added.");
                                            writingReview = false;
                                        } else throw new IncorrectReviewException();
                                    } catch (IncorrectReviewException e) {
                                        System.out.println("Your rating has to be in-between 1 and 10");
                                    }
                                }
                            } else throw new MovieNotOutYetException("You can't review this movie yet!");
                        } catch (MovieNotOutYetException e) {
                            System.out.println(e.getReason());
                        } catch (MovieNotFoundException e) {
                            System.out.println(e.getReason());
                        }
                        break;
                    case 6:
                        program = false;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Unknown command, maybe read the menu again?\n");
                scanner.next();
            }

        }
        scanner.close();
        HibernateConfig.closeSessionFactory();
    }
}
