import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Reservation {
    String trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String from;
    String to;
    String pnrNumber;
    String passengerName;

    public Reservation(String trainNumber, String trainName, String classType, String dateOfJourney, String from, String to) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
        this.pnrNumber = generatePNR();
    }

    private String generatePNR() {
        // Logic to generate a unique PNR number
        // In a real-world scenario, you might use a more sophisticated method
        return "PNR" + System.currentTimeMillis();
    }
}

class ReservationSystem {
    Map<String, Reservation> reservations;

    public ReservationSystem() {
        this.reservations = new HashMap<>();
    }

    public void makeReservation(String userId, Reservation reservation) {
        reservations.put(userId, reservation);
        System.out.println("Reservation successful. Your PNR number is: " + reservation.pnrNumber);
    }

    public void cancelReservation(String userId, String pnrNumber) {
        Reservation reservation = reservations.get(userId);
        if (reservation != null && reservation.pnrNumber.equals(pnrNumber)) {
            reservations.remove(userId);
            System.out.println("Reservation with PNR " + pnrNumber + " canceled successfully.");
        } else {
            System.out.println("Invalid PNR number. Please check and try again.");
        }
    }

    public void displayReservationDetails(String userId) {
        Reservation reservation = reservations.get(userId);
        if (reservation != null) {
            System.out.println("Reservation Details:");
            System.out.println("PNR Number: " + reservation.pnrNumber);
            System.out.println("Train Number: " + reservation.trainNumber);
            System.out.println("Train Name: " + reservation.trainName);
            System.out.println("Class Type: " + reservation.classType);
            System.out.println("Date of Journey: " + reservation.dateOfJourney);
            System.out.println("From: " + reservation.from);
            System.out.println("To: " + reservation.to);
        } else {
            System.out.println("No reservation found for the provided user ID.");
        }
    }
}

public class OnlineReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    static ReservationSystem reservationSystem = new ReservationSystem();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Login");
            System.out.println("2. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Exiting Online Reservation System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Basic authentication (replace with more secure authentication in a real-world scenario)
        if (isValidUser(userId, password)) {
            showReservationMenu(userId);
        } else {
            System.out.println("Invalid User ID or Password. Please try again.");
        }
    }

    private static boolean isValidUser(String userId, String password) {
        // Basic validation logic (replace with more secure authentication in a real-world scenario)
        return userId.equals("Arun") && password.equals("NightKing");
    }

    private static void showReservationMenu(String userId) {
        while (true) {
            System.out.println("\nReservation Menu:");
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Display Reservation Details");
            System.out.println("4. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    makeReservation(userId);
                    break;
                case 2:
                    cancelReservation(userId);
                    break;
                case 3:
                    displayReservationDetails(userId);
                    break;
                case 4:
                    System.out.println("Logging out. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void makeReservation(String userId) {
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter Train Number: ");
        String trainNumber = scanner.nextLine();

        System.out.print("Enter Train Name: ");
        String trainName = scanner.nextLine();

        System.out.print("Enter Class Type: ");
        String classType = scanner.nextLine();

        System.out.print("Enter Date of Journey: ");
        String dateOfJourney = scanner.nextLine();

        System.out.print("Enter From (place): ");
        String from = scanner.nextLine();

        System.out.print("Enter To (destination): ");
        String to = scanner.nextLine();

        Reservation reservation = new Reservation(trainNumber, trainName, classType, dateOfJourney, from, to);
        reservationSystem.makeReservation(userId, reservation);
    }

    private static void cancelReservation(String userId) {
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter PNR Number to cancel reservation: ");
        String pnrNumber = scanner.nextLine();

        reservationSystem.cancelReservation(userId, pnrNumber);
    }

    private static void displayReservationDetails(String userId) {
        reservationSystem.displayReservationDetails(userId);
    }
}
