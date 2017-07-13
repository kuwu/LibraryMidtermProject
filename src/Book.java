import java.time.LocalDate;
<<<<<<< HEAD
=======
import java.util.*;
>>>>>>> 62f88ff072de32409cf7c86c4ee5c71328e3005c

/**
 * Created by michelhayman on 7/12/17.
 */
public class Book {

<<<<<<< HEAD
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

    public Book(String title, String author, boolean status, LocalDate dueDate) {
        this.title = title;
        this.author = author;
        this.status = status;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Book{" +
=======
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

        @Override
        public String toString() {
            return "Book{" +
>>>>>>> 62f88ff072de32409cf7c86c4ee5c71328e3005c
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status=" + status +
                ", dueDate=" + dueDate +
                '}';
        }
}
