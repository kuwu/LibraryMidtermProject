import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created **MKR**
 * Created by kuwu on 2017/07/12.
 */
public class Library {


    public static ArrayList<Book> sortBookList() throws FileNotFoundException {
        ArrayList<Book> BkList = new ArrayList<Book>();
        try {

            File menu = new File("textFiles/bookList.txt");

            FileReader reader = new FileReader(menu);

            BufferedReader buff = new BufferedReader(reader);

            String line = null;

            while ((line = buff.readLine()) != null) {

                // Split line to extract individual fields

                String[] data = line.split(",");

                // Create new book item object
                Book bkitem = new Book(data[0], data[1], convertStringToBoolean(data[2]), convertStingToDate(data[3]));

                // Add object to list
                BkList.add(bkitem);
                //System.out.println(bkitem.getDueDate());
            }

            System.out.println();
            buff.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return BkList;
    }

    private static boolean convertStringToBoolean(String datum) {
        return(datum.equalsIgnoreCase("on"));
    }

    public static LocalDate convertStingToDate(String datum) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate date;

        try {
            date = LocalDate.parse(datum, formatter);
        } catch (DateTimeParseException exc) {
            throw exc;
        }
        return date;
    }
    //rewrites the ArrayList of books back to our txt file when session is over.
    public static void rewriteToBookList(ArrayList<Book> BkList) {
        try {
            File list = new File("textFiles/bookList.txt");
            FileWriter writer = new FileWriter(list);
            BufferedWriter buff = new BufferedWriter(writer);
            for (Book b : BkList) {
                buff.write(String.valueOf(b));
            }
            buff.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    // allows searching for part or all of a title's name, also ignores spaces when necessary
    public static void searchTitleKeyword(ArrayList<Book> books, String keyword) {
        System.out.println("The books with that keyword in their title:");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    books.get(i).getTitle().toLowerCase().replaceAll("\\s+","").contains(keyword.toLowerCase())) {
                System.out.println(books.get(i).getTitle() + " by " + books.get(i).getAuthor() + " at position #" + (i + 1));
            }
        }
    }
    // allows searching for part or all of an author's name, also ignores spaces when necessary
    public static void searchAuthor(ArrayList<Book> books, String author) {
        System.out.println("The books made by authors fitting your search criteria:");
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getAuthor().toLowerCase().contains(author.toLowerCase()) ||
                    books.get(i).getAuthor().toLowerCase().replaceAll("\\s+","").contains(author.toLowerCase())) {
                System.out.println(books.get(i).getTitle() + " by " + books.get(i).getAuthor() + " at position #" + (i + 1));
            }
        }
    }

    //displays a list of books
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

    //adding a book.
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
    // just for outputting and calling rewriteToBookList.
    public static void save(ArrayList<Book> books) {
        System.out.println("Changes to our library have been saved.");
        System.out.println("Thanks for using the Bookworms' Library!");
        //Pass our list of books back to Library to rewrite to file.
        Library.rewriteToBookList(books);
    }
}


