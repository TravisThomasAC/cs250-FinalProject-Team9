import java.util.*;
import java.io.*;

public class Explore{
  private Scanner scan;
  private final String csvCloud = "../dataset/music.csv";
  private final String csvLike = "../dataset/liked.csv";
  private final String csvTest = "../dataset/test.csv";
  private ArrayList<String> song;
  private ArrayList<ArrayList<String>> exploreSongs = new ArrayList<ArrayList<String>>();
  private int[] randomIndexes = new int[5];
  private int lineCount = 10030;

  //read through the "cloud list" and randomly return some songs
    public void explore(){
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
    }

    public void addSong(){
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
}
