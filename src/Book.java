import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by michelhayman on 7/12/17.
 */
public class Book {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd YYYY");
    private String title;
    private String author;
    private boolean status;
    private LocalDate dueDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public Book (String title, String author, boolean status, LocalDate dueDate) {
        this.title = title;
        this.author = author;
        this.status = status;
        this.dueDate = dueDate;
    }

    // toString will print in format
    // title,author,(on/off)state,MM dd YYYY
    // this is the same format that we're reading in from the text file!
    @Override
    public String toString() {
        String state = new String();
        if (isStatus() == true) state = "on";
        else state = "off";

        return  title + "," +
                author + "," +
                state + "," +
                dueDate.format(formatter)
                ;
    }
}
