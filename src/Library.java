import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


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
                Book bkitem = new Book(data[0], data[1], true, convertStingToDate(data[3]));

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
        String on = " ";
        if (datum.equalsIgnoreCase(on))
            return true;
        else {

        }
        return false;
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

    public static void rewriteToBookList(ArrayList<Book> BkList) {
        try {
            File list = new File("textFiles/bookList.txt");
            FileWriter writer = new FileWriter(list);
            for (Book b : BkList) {
                writer.write(String.valueOf(b));
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}


