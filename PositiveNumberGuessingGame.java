import java.util.Random;
import java.util.Scanner;

public class PositiveNumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int generatedNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        int userGuess;

        System.out.println("Welcome to the Positive Number Guessing Game!");
        System.out.println("Guess a number between " + lowerBound + " and " + upperBound);

        do {
            System.out.print("Enter your guess: ");
            userGuess = scanner.nextInt();

            if (userGuess == generatedNumber) {
                System.out.println("Congratulations! You guessed the correct number: " + generatedNumber);
            } else if (userGuess < generatedNumber) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }

        } while (userGuess != generatedNumber);

        scanner.close();
    }
}
