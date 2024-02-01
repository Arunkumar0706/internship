import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
}

class Member {
    String name;
    int memberId;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
    }
}

class Library {
    private Map<Integer, Book> books;
    private Map<Integer, Member> members;
    private int memberIdCounter;

    public Library() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.memberIdCounter = 1;
    }

    public void addBook(String title, String author) {
        int bookId = books.size() + 1;
        books.put(bookId, new Book(title, author));
        System.out.println("Book added successfully. Book ID: " + bookId);
    }

    public void removeBook(int bookId) {
        if (books.containsKey(bookId)) {
            books.remove(bookId);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }
    }

    public void displayBooks() {
        System.out.println("Available Books:");
        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            Book book = entry.getValue();
            System.out.println("ID: " + entry.getKey() + ", Title: " + book.title + ", Author: " + book.author + ", Available: " + book.isAvailable);
        }
    }

    public void addUser(String name) {
        int memberId = memberIdCounter++;
        members.put(memberId, new Member(name, memberId));
        System.out.println("User added successfully. Member ID: " + memberId);
    }

    public void displayUsers() {
        System.out.println("Library Members:");
        for (Map.Entry<Integer, Member> entry : members.entrySet()) {
            Member member = entry.getValue();
            System.out.println("ID: " + entry.getKey() + ", Name: " + member.name);
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Book\n2. Remove Book\n3. Display Books\n4. Add User\n5. Display Users\n6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    scanner.nextLine(); // Consume the newline character left by nextInt()
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;

                case 2:
                    System.out.print("Enter book ID to remove: ");
                    int bookId = scanner.nextInt();
                    library.removeBook(bookId);
                    break;

                case 3:
                    library.displayBooks();
                    break;

                case 4:
                    System.out.print("Enter user name: ");
                    scanner.nextLine(); // Consume the newline character left by nextInt()
                    String userName = scanner.nextLine();
                    library.addUser(userName);
                    break;

                case 5:
                    library.displayUsers();
                    break;

                case 6:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
