import java.util.*;


class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String msg) {
        super(msg);
    }
}


abstract class User {
    protected int userId;
    protected String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    abstract String getRole();
}


class Student extends User {
    public Student(int userId, String name) {
        super(userId, name);
    }

    @Override
    String getRole() {
        return "STUDENT";
    }
}


class Book implements Comparable<Book> {
    private int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    @Override
    public int compareTo(Book b) {
        return this.title.compareToIgnoreCase(b.title);
    }

    @Override
    public String toString() {
        return bookId + " | " + title + " | " + author;
    }
}


interface LibraryService {
    void addBook(Book book);
    void removeBook(int bookId);
    void viewAllBooks();
}


class LibraryServiceImpl implements LibraryService {

    private HashMap<Integer, Book> bookMap = new HashMap<>();
    private TreeSet<Book> sortedBooks = new TreeSet<>();

    @Override
    public void addBook(Book book) {
        bookMap.put(book.getBookId(), book);
        sortedBooks.add(book);
        System.out.println("✅ Book Added Successfully");
    }

    @Override
    public void removeBook(int bookId) {
        Book book = bookMap.remove(bookId);
        if (book == null) {
            throw new BookNotFoundException("❌ Book Not Found");
        }
        sortedBooks.remove(book);
        System.out.println("🗑 Book Removed Successfully");
    }

    @Override
    public void viewAllBooks() {
        if (sortedBooks.isEmpty()) {
            System.out.println("📂 No Books Available");
            return;
        }
        System.out.println("\n📚 Library Books (Sorted by Title):");
        for (Book b : sortedBooks) {
            System.out.println(b);
        }
    }
}


public class LibraryManagementSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Polymorphism
        LibraryService library = new LibraryServiceImpl();

        // Inheritance example
        User user = new Student(101, "Gowtham");
        System.out.println("Logged in as: " + user.getRole());

        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Remove Book");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Book ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Author: ");
                        String author = sc.nextLine();
                        library.addBook(new Book(id, title, author));
                        break;

                    case 2:
                        library.viewAllBooks();
                        break;

                    case 3:
                        System.out.print("Enter Book ID: ");
                        library.removeBook(sc.nextInt());
                        break;

                    case 4:
                        System.out.println("👋 Thank You");
                        System.exit(0);

                    default:
                        System.out.println("⚠ Invalid Choice");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

