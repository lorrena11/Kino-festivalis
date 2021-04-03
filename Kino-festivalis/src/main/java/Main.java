import model.Movie;
import service.RunApp;
import service.ReviewListFactory;
import util.AverageScoreCounter;
import util.RetrieveMovies;
import util.RetrieveReviews;

import java.util.Scanner;

/**
 * this class lets the user interact with the system
 */
public class Main {
    public static void main(String[] args) {
        RunApp.run();
        ReviewListFactory writeReview = new ReviewListFactory();
        AverageScoreCounter averageScoreCounter = new AverageScoreCounter();
        Scanner scanner = new Scanner(System.in);
        boolean program = true;
        while (program) {
            System.out.println("===================== FILM FESTIVAL - MENU ======================");
            System.out.println("[1] - see all movies; [2] - see info of chosen film; [3] - best rated films;");
            System.out.println("[4] - write a review; [5] - exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("All festival entries: ");
                    System.out.println(RetrieveMovies.receiveAllMovies());
                    break;
                case 2:
                    System.out.println("All festival entries: ");
                    System.out.println(RetrieveMovies.receiveAllMovies());
                    System.out.println("Type in movie ID: ");
                    long id = scanner.nextLong();
                    Movie chosenMovieInfo = RetrieveMovies.receiveChosenMovie(id);
                    // todo: padaryt kad nespausdintu null, o lieptu toliau vesti kita id
                    // mestu exceptiona bad choice, kuris grazintu i main menu (jeigu result=null, jeigu id nera long
                    // ideti ta pati i kitus case
                    System.out.println(chosenMovieInfo);
                    System.out.println("Read comments? [1] - read comments; [2] - go back");
                    int choiceRead = scanner.nextInt();
                    if (choiceRead == 1) {
                        System.out.println(RetrieveReviews.readReviews(chosenMovieInfo));
                    } else {
                        System.out.println("Back to main menu");
                    }
                    break;
                case 3:
                    System.out.println("Best rated festival entries: ");
                    System.out.println(RetrieveMovies.receiveBestRatedMovies());
                    break;
                case 4: System.out.println("All festival entries: ");
                    System.out.println(RetrieveMovies.receiveAllMovies());
                    System.out.println("Choose which film to review by typing ID: ");
                    long id1 = scanner.nextLong();
                    Movie chosenMovieToReview = RetrieveMovies.receiveChosenMovie(id1);
                    System.out.println("Add your rating [1-5]: ");
                    double userRating = scanner.nextDouble();
                    System.out.println("Add a comment: ");
                    String userComment = scanner.next();
                    writeReview.addReview(chosenMovieToReview, userRating, userComment);
                    averageScoreCounter.updateAverageScore(chosenMovieToReview);
                    System.out.println("Review added.");
                    break;
                case 5:
                    program = false;
                    break;
            }
        }
        scanner.close();

    }
}
