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


  public static void sortBookList() throws FileNotFoundException {
    try {

      File menu = new File("textFiles/bookList.txt");

      FileReader reader = new FileReader(menu);

      BufferedReader buff = new BufferedReader(reader);

      String line = null;

      ArrayList<Book> BkList = new ArrayList<>();

      while ((line = buff.readLine()) != null) {

        // Split line to extract individual fields

        String[] data = line.split(",");

        // Create new book item object
        Book bkitem = new Book(data[0], data[1], true, convertStingToDate(data[3]));

        // Add object to list
        BkList.add(bkitem);
        //System.out.println("Title:  "+ "\u001B[34m"+ data[0]+ "   "+"\u001B[0m"+ " Author:  " + "\u001B[35m"+data[1]+"\u001B[0m"+ "");
        //System.out.println(bkitem.getDueDate());
      }

      System.out.println();
      buff.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
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


}

