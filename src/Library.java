import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created **MKR**
 * Created by kuwu on 2017/07/12.
 */
public class Library {


  //writeToFile();
  //readFromFile();
  //readBookList();


  public static void readBookList() throws FileNotFoundException {
    Scanner scan = new Scanner(new File("bookList.txt"));
    ArrayList<String> bk = new ArrayList<>();

    while (scan.hasNextLine())
      bk.add(scan.nextLine());
    for (int i = 1; i < 4; ++i) {
      // add book getter  bk.add(String.get*****(i));

      System.out.println(bk.get(i));
//      System.out.println(bk.get(1));
//      System.out.println(bk.get(2));
//      System.out.println(bk.get(3));

    }

  }

  public static void writeToFile() {
    try {
      Scanner scan = new Scanner(System.in);
      FileWriter writer = new FileWriter("bookList.txt", true);
      System.out.println("When adding a book, use the Following format: Title|Author|Status|DueDate|");
      System.out.println("Enter a book: ");
      String input = scan.nextLine();
      writer.write("\n" + input);
      System.out.println("Your book has been saved!\n");
      writer.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void readFromFile() {
    try {

      File countries = new File("bookList.txt");
      FileReader reader = new FileReader(countries);
      BufferedReader buff = new BufferedReader(reader);

      String line = null;

      while ((line = buff.readLine()) != null) {
        System.out.println(line);

      }
      buff.close();
      System.out.println();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

