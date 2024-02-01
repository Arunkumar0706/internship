import java.util.Scanner;
import java.util.ArrayList;

class Transaction {
    String type;
    double amount;
    String date;

    public Transaction(String type, double amount, String date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }
}

class Account {
    String userId;
    String userPin;
    double balance;
    ArrayList<Transaction> transactions;

    public Account(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 100.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount, "01/05/2024"));
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount, "01/05/2024"));
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            transactions.add(new Transaction("Transfer", amount, "01/05/2024"));
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.type + " - Amount: " + transaction.amount + " - Date: " + transaction.date);
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Dummy user and pin (In real-world scenario, you would fetch this from a database)
        String userId = "Arun";
        String userPin = "1234";

        System.out.print("Enter User ID: ");
        String enteredUserId = scanner.nextLine();

        System.out.print("Enter User PIN: ");
        String enteredUserPin = scanner.nextLine();

        if (enteredUserId.equals(userId) && enteredUserPin.equals(userPin)) {
            Account userAccount = new Account(userId, userPin);

            while (true) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        userAccount.printTransactionHistory();
                        break;
                    case 2:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        userAccount.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        userAccount.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter recipient's User ID: ");
                        String recipientUserId = scanner.next();
                        // For simplicity, assuming recipient's account already exists
                        Account recipientAccount = new Account(recipientUserId, "");
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        userAccount.transfer(recipientAccount, transferAmount);
                        break;
                    case 5:
                        System.out.println("Quitting ATM. Thank you!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid User ID or PIN. Exiting...");
        }
    }
}
