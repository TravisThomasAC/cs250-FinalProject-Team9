package test;
import java.util.*;
import java.io.*;

public class DailySelection{
  private final String csvLike = "../dataset/liked.csv";
  private final String csvCloud = "../dataset/music.csv";
  private final String csvTest = "../dataset/test.csv";
  private ArrayList<String> song;
  private ArrayList<ArrayList<String>> exploreSongs = new ArrayList<ArrayList<String>>();
  private int[] randomIndexes = new int[5];
  private int lineCount = 10030;

  //return song that user might likes
    public void dailySelection(){
      BufferedReader br = null;
      String line = "";
      String cvsSplitBy = ",";
      int likeLength = 0;

      try{
        br = new BufferedReader(new FileReader(csvLike));
        while ((line = br.readLine()) != null) {
          likeLength++;
        }
      }catch (FileNotFoundException e) {
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

      //Read through file and store elements in ArrayList
      try {
          br = new BufferedReader(new FileReader(csvLike));
          while ((line = br.readLine()) != null) {
              // use comma as separator
              String[] Songs = line.split(cvsSplitBy);
              //Store score to scr[]
              int[] scr = new int[likeLength];
                for(int j = 0; j < likeLength; j++){
                  scr[j] = Integer.ParseInt(Songs[5]);
                }

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

    public void cloud(){
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
            int numOfSongs = 2;
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
}
