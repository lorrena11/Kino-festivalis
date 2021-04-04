import model.Movie;
import service.StartApp;
import service.reviews.ReviewListFactory;
import service.movies.AverageScoreCounter;
import service.movies.RetrieveMovies;
import service.reviews.RetrieveReviews;

import java.util.Scanner;

/**
 * this class lets the user interact with the system
 */
public class Main {
    public static void main(String[] args) {
        StartApp.load();
        ReviewListFactory writeReview = new ReviewListFactory();
        RetrieveMovies retrieveMovies = new RetrieveMovies();

        Scanner scanner = new Scanner(System.in);
        boolean program = true;
        while (program) {
            System.out.println("===================== FILM FESTIVAL - MENU ======================");
            System.out.println("[1] - show all movies; [2] - show info of chosen film; [3] - show best rated films;");
            System.out.println("[4] - show most popular films; [5] - write a review; [6] - exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("====== All festival entries: ====== ");
                    System.out.println(retrieveMovies.receiveAllMovieNames());
                    break;
                case 2:
                    System.out.println("====== All festival entries: ====== ");
                    System.out.println(retrieveMovies.receiveAllMovieNames());
                    System.out.println("\nType in movie ID: ");
                    long id = scanner.nextLong();
                    Movie chosenMovieInfo = retrieveMovies.receiveChosenMovie(id);
                    // todo: padaryt kad nespausdintu null, o lieptu toliau vesti kita id
                    // mestu exceptiona bad choice, kuris grazintu i main menu (jeigu result=null, jeigu id nera long
                    // ideti ta pati i kitus case
                    System.out.println(chosenMovieInfo);
                    System.out.println("\nRead comments? [1] - read comments; [2] - go back");
                    int choiceRead = scanner.nextInt();
                    if (choiceRead == 1) {
                        System.out.println(RetrieveReviews.readReviews(chosenMovieInfo));
                    } else {
                        System.out.println("\nBack to main menu");
                    }
                    break;
                case 3:
                    System.out.println("====== Best rated festival entries: ====== ");
                    System.out.println(retrieveMovies.receiveTop5RatedMovies());
                    break;
                case 4:
                    System.out.println("====== Most popular festival entries: ====== ");
                    System.out.println(retrieveMovies.receiveMostPopularMovies());
                    break;
                case 5:
                    System.out.println("====== All festival entries: ====== ");
                    System.out.println(retrieveMovies.receiveAllMovieNames());
                    System.out.println("\nChoose which film to review by typing ID: ");
                    long id1 = scanner.nextLong();
                    Movie chosenMovieToReview = retrieveMovies.receiveChosenMovie(id1);
                    System.out.println("Add your rating [1-5]: ");
                    double userRating = scanner.nextDouble();
                    System.out.println("Add a comment: ");
                    String userComment = scanner.next();
                    writeReview.addReview(chosenMovieToReview, userRating, userComment);
                    AverageScoreCounter.updateAverageScore(chosenMovieToReview);
                    System.out.println("Review added.");
                    break;
                case 6:
                    program = false;
                    break;
            }
        }
        scanner.close();

    }
}
