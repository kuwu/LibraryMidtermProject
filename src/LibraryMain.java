import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by KMR on 2017/07/12.
 */

public class LibraryMain {

    //assuming Book(title, author, boolean, LocalDate)
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Billy's Adventures", "Bully Billy", true, LocalDate.now()));
        books.add(new Book("Anna's Adventures", "Astral Anna", false, LocalDate.now()));
        checkoutBook(books.get(1));
        checkoutBook(books.get(0));
        display(books, "");
    }

    public static void searchTitleKeyword(ArrayList<Book> books, String keyword) {
        System.out.println("The books with that keyword in their title:");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println((i+1) + " - " + books.get(i).getTitle() + " by " + books.get(i).getAuthor());
            }
        }
        System.out.println();
        System.out.println("Anything else?");
    }

    public static void searchAuthor(ArrayList<Book> books, String author) {
        System.out.println("The books made by that author:");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getAuthor().toLowerCase().contains(author.toLowerCase())) {
                System.out.println((i+1) + " - " + books.get(i).getTitle() + " by " + books.get(i).getAuthor());
            }
        }
        System.out.println();
        System.out.println("Anything else?");
    }


    public static void display(ArrayList<Book> books, String status){
        System.out.println("Here's a list of all of our " + status + " books:");
        for (int i = 0; i < books.size(); i++) {
            System.out.print((i+1) + " - " + books.get(i).getTitle() + " by " + books.get(i).getAuthor() + ", ");
            if(books.get(i).isStatus() == true) {
                System.out.println("currently on-shelf.");
            } else {
                System.out.println("currently checked out. Due date is: " + books.get(i).getDueDate());
            }
        }
    }

    public static void addBook(Scanner scan, ArrayList<Book> books) {
        System.out.println("Alright, a new book. What's the title?");
        String title = scan.nextLine();
        System.out.println("Who was the Author?");
        String author = scan.nextLine();
        books.add(new Book(title, author, true, LocalDate.now()));
        int index = books.size()-1;
        System.out.println("Added " + books.get(index).getTitle() + " by " + books.get(index).getAuthor());
    }

    //checking out a book
    public static void checkoutBook(Book book) {
        // if book status is false, then it's checked out.
        if (!book.isStatus())
            System.out.println("That book's currently checked out. It's due to be back at " + book.getDueDate());
        else {
            book.setStatus(false);
            book.setDueDate(LocalDate.now().plusWeeks(2));
            System.out.println("You've checked out " + book.getTitle() + ". You must return it by: " + book.getDueDate());
        }
    }

    // returning a book.
    public static void returnBook(Book book) {
        // if book status is true, then it's on shelf.
        if (book.isStatus())
            System.out.println("That book is already on shelf in our library!");
        else {
            book.setStatus(true);
            System.out.println("You returned this book in " + ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now()) + " day(s).");
        }
    }


}
