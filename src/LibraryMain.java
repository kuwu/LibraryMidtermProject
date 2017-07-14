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
                        Library.display(books);
                        break;
                    case 2:
                        // That validator function will only allow 1 or 2 to be returned. If not 1, it'll be 2.
                        if (LibraryValidator.getInt(scan, "Search by... \n1 - Author \n2 - Title Keyword", 1, 2) == 1) {
                            System.out.println("Type part or all of the Author's name.\n");
                            Library.searchAuthor(books, scan.nextLine());
                        } else {
                            System.out.println("Type part or all of a Title you're looking for.\n");
                            Library.searchTitleKeyword(books, scan.nextLine());
                        }
                        break;
                    case 3:
                        Library.display(books);
                        //Lots happening in this line. We get a valid number from user, -1 to align with our 0-based arrays in java, and then use .get(number) to checkout that book.
                        Library.checkoutBook(books.get(LibraryValidator.getInt(scan, "What book would you like to check out?", 1, books.size()) - 1));
                        break;
                    case 4:
                        Library.display(books);
                        //very similar to above case. Just returning a book instead.
                        Library.returnBook(books.get(LibraryValidator.getInt(scan, "What book would you like to return?", 1, books.size()) - 1));
                        break;
                    case 5:
                        Library.addBook(scan, books);
                        break;
                    case 6:
                        continue;
                }
                System.out.println();
                System.out.println("Anything else?");
                System.out.println();
            } while (select != 6);
            // once user has selected save & quit, we save and get outta here.
            Library.save(books);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find Library list of books. Looks like we're closed for now.");
        }
    }
    }
