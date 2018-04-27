import java.util.*;
import java.io.*;

public class musicSug{

  private static Scanner scan;
  private static final String csvLike = "../dataset/liked.csv";
  private static final String csvCloud = "../dataset/music.csv";
  private static final String csvTest = "../dataset/test.csv";
  private static int[] randomIndexes = new int[5];
  private static ArrayList<String> song;
  private static ArrayList<ArrayList<String>> exploreSongs = new ArrayList<ArrayList<String>>();
  private static int lineCount = 10030;

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
        } System.out.println("You are at menu now");
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

//read through the "cloud list" and randomly return some songs
  public static void explore(){
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";

    //Read through file and store elements in ArrayList
    try {
        br = new BufferedReader(new FileReader(csvCloud));
        System.out.println("Song   Artist   Year   Genre   Like");
        while ((line = br.readLine()) != null) {
            // use comma as separator
            String[] Songs = line.split(cvsSplitBy);
            //Store songs to 2D ArrayList
            song = new ArrayList<String>();
              for(int j = 0; j < 6; j++){
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
            int a = i+1;
            System.out.print(a +": ");
            if(randIndex != temp){
              for(int j = 1; j < 6; j++){
                System.out.print(exploreSongs.get(randIndex).get(j)+"  |  ");
              }
            }
            else{
              randIndex++;
              for(int j = 1; j < 6; j++){
                System.out.print(exploreSongs.get(randIndex).get(j)+"  |  ");
              }
            }
            randomIndexes[i] = randIndex;
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
      System.out.println("Do you like any of these songs?(y/n)");
      if(answer()==true){
        addSong();
      }System.out.println("You are at menu now");
  }

  public static void addSong(){
    //problems here: I can do copy from csv to csv
    //also, implement a search would be better
    scan = new Scanner(System.in);
    System.out.println("What is the number of the song?");
    int index = scan.nextInt()-1;
    exploreSongs.get(randomIndexes[index]).set(5, "y");
    for(int i = 1; i < 6; i++){
      System.out.print(exploreSongs.get(randomIndexes[index]).get(i));
      if(i<5){
        System.out.print("  |  ");
      }
    }

    FileWriter fileWriter = null;
    try{
      fileWriter = new FileWriter(csvLike, true);
        fileWriter.append(exploreSongs.get(randomIndexes[index]).get(1));
        fileWriter.append(",");
        fileWriter.append(exploreSongs.get(randomIndexes[index]).get(2));
        fileWriter.append(",");
        fileWriter.append(exploreSongs.get(randomIndexes[index]).get(3));
        fileWriter.append(",");
        fileWriter.append(exploreSongs.get(randomIndexes[index]).get(4));
        fileWriter.append(",");
        fileWriter.append(exploreSongs.get(randomIndexes[index]).get(5));
        fileWriter.append("\n");
      fileWriter.flush();
    }catch(Exception e){
      System.out.println("Error in CsvFileWriter !!!");
      e.printStackTrace();
    }finally {
      try {
        fileWriter.close();
      } catch (IOException e){
        System.out.println("Error while flushing/closing fileWriter !!!");
        e.printStackTrace();
      }
    }
    System.out.println("\nThe song has been added to your like list!");

    BufferedWriter writer = null;
    try{
    writer = new BufferedWriter(new FileWriter(csvTest));
    for(int i = 0; i < lineCount; i++){
      for(int j = 0; j < 6; j++){
        writer.write(exploreSongs.get(i).get(j));
        if(j<5){
          writer.write(",");
        }
      }
      writer.newLine();
    }
    writer.flush();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
  }

//return song that user might likes
  public static void dailySelection(){
    System.out.println("dailySelection feature coming soon...");
    System.out.println("You are at menu now!(Sorry...)");
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
          System.out.println("You are at menu now.");
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
