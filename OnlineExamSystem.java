import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class User {
    String userId;
    String password;
    String userName;
    ArrayList<Answer> selectedAnswers;

    public User(String userId, String password, String userName) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.selectedAnswers = new ArrayList<>();
    }

    public void updateProfile(String newName, String newPassword) {
        this.userName = newName;
        this.password = newPassword;
        System.out.println("Profile updated successfully!");
    }
}

class Answer {
    int questionNumber;
    String selectedOption;

    public Answer(int questionNumber, String selectedOption) {
        this.questionNumber = questionNumber;
        this.selectedOption = selectedOption;
    }
}

class Question {
    int questionNumber;
    String questionText;
    ArrayList<String> options;
    String correctOption;

    public Question(int questionNumber, String questionText, ArrayList<String> options, String correctOption) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class OnlineExamSystem {
    static Scanner scanner = new Scanner(System.in);
    static User currentUser;
    static ArrayList<Question> questions;
    static Timer timer;

    public static void main(String[] args) {
        // Dummy user data and questions (In real-world scenario, fetch from a database)
        User user = new User("student123", "password", "John Doe");
        currentUser = user;

        initializeQuestions();

        login();
        mainMenu();
    }

    private static void initializeQuestions() {
        questions = new ArrayList<>();

        // Dummy questions
        ArrayList<String> options1 = new ArrayList<>();
        options1.add("A. Option 1");
        options1.add("B. Option 2");
        options1.add("C. Option 3");
        options1.add("D. Option 4");
        questions.add(new Question(1, "What is the capital of France?", options1, "C"));

        ArrayList<String> options2 = new ArrayList<>();
        options2.add("A. Java");
        options2.add("B. Python");
        options2.add("C. C++");
        options2.add("D. Ruby");
        questions.add(new Question(2, "Which programming language is used for Android app development?", options2, "A"));

        // Add more questions as needed
    }

    private static void login() {
        System.out.print("Enter User ID: ");
        String enteredUserId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String enteredPassword = scanner.nextLine();

        if (enteredUserId.equals(currentUser.userId) && enteredPassword.equals(currentUser.password)) {
            System.out.println("Login successful. Welcome, " + currentUser.userName + "!");
        } else {
            System.out.println("Invalid User ID or Password. Exiting...");
            System.exit(0);
        }
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Take Exam");
            System.out.println("2. Update Profile and Password");
            System.out.println("3. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    takeExam();
                    break;
                case 2:
                    updateProfile();
                    break;
                case 3:
                    System.out.println("Logging out. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void takeExam() {
        currentUser.selectedAnswers.clear(); // Clear previous answers

        System.out.println("\n--- Online Exam ---");
        for (Question question : questions) {
            System.out.println("Question " + question.questionNumber + ": " + question.questionText);
            for (String option : question.options) {
                System.out.println(option);
            }

            System.out.print("Your choice (A/B/C/D): ");
            String selectedOption = scanner.next().toUpperCase();

            currentUser.selectedAnswers.add(new Answer(question.questionNumber, selectedOption));
        }

        // Start a timer for the exam (e.g., 10 minutes)
        int examDurationInMinutes = 1;
        startTimer(examDurationInMinutes);

        System.out.println("\nExam started. Timer set for " + examDurationInMinutes + " minutes.");

        // Simulate exam progress (here, waiting for the timer to expire)
        try {
            Thread.sleep(examDurationInMinutes * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Timer expired, auto-submit exam
        submitExam();

        // Stop the timer
        stopTimer();
    }

    private static void startTimer(int durationInMinutes) {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Auto-submitting exam.");
                submitExam();
            }
        }, durationInMinutes * 60 * 1000);
    }

    private static void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private static void submitExam() {
        stopTimer(); // Stop the timer before submitting
        System.out.println("\n--- Exam Results ---");

        int correctAnswers = 0;
        for (Answer answer : currentUser.selectedAnswers) {
            Question question = findQuestionByNumber(answer.questionNumber);
            if (question != null && answer.selectedOption.equals(question.correctOption)) {
                correctAnswers++;
            }
        }

        System.out.println("Total Questions: " + questions.size());
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Incorrect Answers: " + (questions.size() - correctAnswers));

        // Additional logic for displaying detailed results, saving results, etc.

        // Return to the main menu
        mainMenu();
    }

    private static Question findQuestionByNumber(int questionNumber) {
        for (Question question : questions) {
            if (question.questionNumber == questionNumber) {
                return question;
            }
        }
        return null;
    }

    private static void updateProfile() {
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        currentUser.updateProfile(newName, newPassword);
    }
}
