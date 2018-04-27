package test;
import java.util.*;
import java.io.*;

public class Like{
  private final String csvLike = "../dataset/liked.csv";
  private Scanner scan;

  public void likes(){
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";

    System.out.println("Here is your liked songs:");

    //Read through file and store elements in ArrayList
    try {
    br = new BufferedReader(new FileReader(csvLike));
    System.out.println("Song  |  Artist  |  Year  |  Genre  |  Like");
    while ((line = br.readLine()) != null) {
        // use comma as separator
        String[] Songs = line.split(cvsSplitBy);

        System.out.println(Songs[0] + "  |  " + Songs[1] + "  |  " + Songs[2] + "  |  " + Songs[3] + "  |  " + Songs[4]);
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

  public void sortList(){
    SortInt RY = new SortInt();
    SortString SAG = new SortString();
    scan = new Scanner(System.in);

    System.out.println("How would you like to sort your music list?");
    System.out.println("Available options: Song, Artist, Year, Genre, Stop");
    try{
      while(scan.hasNext()){
        String command = scan.nextLine().toLowerCase();
        if (command.equals("song")){
          SAG.readSong();
        }
        else if(command.equals("artist")){
          SAG.readArtist();
        }
        else if(command.equals("year")){
          RY.readYear();
        }
        else if(command.equals("genre")){
          SAG.readGenre();
        }
        else if(command.equals("stop")){
          break;
        }
        else{
          System.out.println("The commands are song, artist, year, genre, and stop");
        }
      }
    }catch (IOException e) {
     }
  }
}
