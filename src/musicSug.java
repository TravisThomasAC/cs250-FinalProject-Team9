import java.util.*;
import java.io.*;

public class musicSug{

  private static Scanner scan;
  private static final String csvLike = "../dataset/liked.csv";

  public static void main(String[] args) {
    do{
      conversition();
    }
    while(quit());

  }

  public static void conversition(){
    System.out.println("Welcome to AlleKids Music!");
    System.out.println("Do you want to do?");
    System.out.println("Menu: 1.Like | 2.Explore | 3.Daily Selection | 4.quit");
    System.out.println("Please select number.");

    scan = new Scanner(System.in);

    while(scan.hasNext()){
      String command = scan.nextLine();
      if(command.equals("1")){
        likes();
        System.out.println("Would you like to sort your like list? (y/n)");
        if(command.equals("y")){
          sortList();
        }
        else if(command.equals("n")){
          continue;
        }
      }
      else if(command.equals("2")){
        explore();
      }
      else if(command.equals("3")){
        dailySelection();
      }
      else if(command.equals("4")){
        break;
      }
      else{
        System.out.println("The commands are 1.Like | 2.Explore | 3.Daily Selection | 4.quit");
        System.out.println("Please select number.");
      }
    }
  }

  public static void likes(){
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";

    System.out.println("Here is your liked songs:");

//Read through file and store elements in ArrayList

    try {

    br = new BufferedReader(new FileReader(csvLike));
    System.out.println("Rank   Song   Artist   Year   Genre   Like");
    while ((line = br.readLine()) != null) {

        // use comma as separator
        String[] Songs = line.split(cvsSplitBy);

        System.out.println(Songs[0] + "   " + Songs[1] + "   " + Songs[2] + "   " + Songs[3] + "   " + Songs[4] + "   " + Songs[5]);
      }

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

  }

//read through the "cloud list" and randomly return some songs
  public static void explore(){

  }

//return song that user might likes
  public static void dailySelection(){

  }

  public static void sortList(){
    SortInt RY = new SortInt();
    SortString SAG = new SortString();
    scan = new Scanner(System.in);
    
    System.out.println("How would you like to sort your music list?");
    System.out.println("Available options: Rank, Song, Artist, Year, Genre, Stop");
    try{
      while(scan.hasNext()){
        String command = scan.nextLine();
        if(command.equals("Rank")){
          RY.readRank();
        }
        else if (command.equals("Song")){
          SAG.readSong();
        }
        else if(command.equals("Artist")){
          SAG.readArtist();
        }
        else if(command.equals("Year")){
          RY.readYear();
        }
        else if(command.equals("Genre")){
          SAG.readGenre();
        }
        else if(command.equals("Stop")){
          break;
        }
        else{
          System.out.println("The commonds are Rank, Song, Artist, Year, Genre, and Stop");
        }
      }
    }catch (IOException e) {
     }
  }

  public static boolean quit(){
    scan = new Scanner(System.in);

      System.out.println("Would you like to play again? (y/n)");
        String yORn = scan.nextLine().toLowerCase();
        if (yORn.equals("y"))
        {
            return true;
        }
        else if (yORn.equals("n"))
        {
            return false;
        }
        else
        {
            System.out.println("Please answer y or n.");
            return quit();
        }
  }


}
