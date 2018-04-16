import java.util.*;
import java.io.*;

public class musicSug{

  private static Scanner scan;
  private static final String csvLike = "../dataset/liked.csv";
  private static final String csvCloud = "../dataset/music.csv";

  public static void main(String[] args) {
    do{
      conversition();
    }while(answer());
  }

  public static void conversition(){
    System.out.println("Welcome to AlleKids Music!");
    System.out.println("What do you want to do?");
    System.out.println("Menu: 1.Like | 2.Explore | 3.Daily Selection | 4.quit");
    System.out.println("Please select a number.");

    scan = new Scanner(System.in);

    while(scan.hasNext()){
      String command = scan.nextLine().toLowerCase();
      if(command.equals("1")){
        likes();
        System.out.println("Would you like to sort your like list? (y/n)");
        if(answer()==true){
          sortList();
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
    System.out.println("Would you like to play again? (y/n)");
  }

  public static void likes(){
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

        System.out.println(Songs[1] + "  |  " + Songs[2] + "  |  " + Songs[3] + "  |  " + Songs[4] + "  |  " + Songs[5]);
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
/*ToDo:
*move raws to like list if user likes(maybe use ArrayList and the add class in lab4)
*/
  public static void explore(){
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
    ArrayList<ArrayList<String>> exploreSongs = new ArrayList<ArrayList<String>>();
    int lineCount = 0;

    try{
      br = new BufferedReader(new FileReader(csvCloud));
      //get cloud csv line number
      while(br.readLine() != null){
        lineCount++;
      }
      br.close();
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }catch (IOException e) {
        e.printStackTrace();
    }

    //Read through file and store elements in ArrayList
    try {
        br = new BufferedReader(new FileReader(csvCloud));
        System.out.println("Song   Artist   Year   Genre   Like");
        while ((line = br.readLine()) != null) {
            // use comma as separator
            String[] Songs = line.split(cvsSplitBy);
            //Store songs to 2D ArrayList
            ArrayList<String> song = new ArrayList<String>();
              for(int j = 1; j < 6; j++){
                song.add(Songs[j]);
              }
              exploreSongs.add(song);
          }
          //random Selection
          Random rand = new Random();
          int numOfSongs = 5;
          int randIndex = -1;
          int temp;

          for(int i = 0; i < numOfSongs; i++){
            temp = randIndex;
            randIndex = rand.nextInt(lineCount);
            if(randIndex != temp){
              for(int j = 0; j < 5; j++){
                System.out.print(exploreSongs.get(randIndex).get(j)+"  |  ");
              }
            }
            else{
              randIndex++;
              for(int j = 0; j < 5; j++){
                System.out.print(exploreSongs.get(randIndex).get(j)+"  |  ");
              }
            }
            System.out.println();
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

//return song that user might likes
  public static void dailySelection(){

  }

  public static boolean answer(){
    //scan = new Scanner(System.in);
    String ans = scan.nextLine().toLowerCase();

    if(ans.equals("y")){
      return true;
    }
    else if(ans.equals("n")){
      return false;
    }
    else{
      System.out.println("Please type y or n");
      return answer();
    }
  }

  public static void sortList(){
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
