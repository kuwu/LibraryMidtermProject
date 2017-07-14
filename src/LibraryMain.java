import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by KMR on 2017/07/12.
 */

public class LibraryMain {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        // force initialize
        ArrayList<Book> books = null;
        try {
            books = Library.sortBookList();
            System.out.println("Welcome to the Bookworms' Library! ");
            System.out.println("What do you want to do? Use numbers to navigate. ");
            int select = 0;
            do {
                select = LibraryValidator.getInt(scan, "1: Display List\n2: Search\n3: Checkout a book\n4: Return a book\n" +
                        "5: Add a book\n6: Save and Quit", 1, 6);
                switch (select) {
                    case 1:
                        display(books);
                        break;
                    case 2:
                        // That validator function will only allow 1 or 2 to be returned. If not 1, it'll be 2.
                        if (LibraryValidator.getInt(scan, "Search by... \n1 - Author \n2 - Title Keyword", 1, 2) == 1) {
                            System.out.println("Type part or all of the Author's name.\n");
                            searchAuthor(books, scan.nextLine());
                        } else {
                            System.out.println("Type part or all of a Title you're looking for.\n");
                            searchTitleKeyword(books, scan.nextLine());
                        }
                        break;
                    case 3:
                        display(books);
                        //Lots happening in this line. We get a valid number from user, -1 to align with our 0-based arrays in java, and then use .get(number) to checkout that book.
                        checkoutBook(books.get(LibraryValidator.getInt(scan, "What book would you like to check out?", 1, books.size()) - 1));
                        break;
                    case 4:
                        display(books);
                        //very similar to above case. Just returning a book instead.
                        returnBook(books.get(LibraryValidator.getInt(scan, "What book would you like to return?", 1, books.size()) - 1));
                        break;
                    case 5:
                        addBook(scan, books);
                        break;
                    case 6:
                        continue;
                }
                System.out.println();
                System.out.println("Anything else?");
                System.out.println();
            } while (select != 6);
            // once user has selected save & quit, we save and get outta here.
            save(books);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find Library list of books. Looks like we're closed for now.");
        }
    }

    public static void searchTitleKeyword(ArrayList<Book> books, String keyword) {
        System.out.println("The books with that keyword in their title:");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    books.get(i).getTitle().toLowerCase().replaceAll("\\s+","").contains(keyword.toLowerCase())) {
                System.out.println(books.get(i).getTitle() + " by " + books.get(i).getAuthor() + " at position #" + (i + 1));
            }
        }
    }

    public static void searchAuthor(ArrayList<Book> books, String author) {
        System.out.println("The books made by authors fitting your search criteria:");
        for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getAuthor().toLowerCase().contains(author.toLowerCase()) ||
                        books.get(i).getAuthor().toLowerCase().replaceAll("\\s+","").contains(author.toLowerCase())) {
                System.out.println(books.get(i).getTitle() + " by " + books.get(i).getAuthor() + " at position #" + (i + 1));
            }
        }
    }


    public static void display(ArrayList<Book> books) {
        System.out.println("Here's a list of all of our current books:");
        for (int i = 0; i < books.size(); i++) {
            System.out.print((i + 1) + " - " + books.get(i).getTitle() + " by " + books.get(i).getAuthor() + ", ");
            if (books.get(i).isStatus() == true) {
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
        int index = books.size() - 1;
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
            System.out.println("You're attempting to return a book that is already on shelf in our library!");
        else {
            book.setStatus(true);
            if (ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now()) < 0)
                System.out.println("You returned " + book.getTitle() + " in a timely manner. Thanks!");
            else
                System.out.println("You returned " + book.getTitle() + " in " + ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now()) + " day(s).");
            if (ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now()) > 14)
                System.out.println("You're late returning this! Expect an overdue fine.");
        }
    }

    public static void save(ArrayList<Book> books) {
        System.out.println("Changes to our library have been saved.");
        System.out.println("Thanks for using the Bookworms' Library!");
        //Pass our list of books back to Library to rewrite to file.
        Library.rewriteToBookList(books);
    }

}
