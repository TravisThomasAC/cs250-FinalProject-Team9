import java.util.*;
import java.io.*;
import java.lang.*;

public class DailySelection{
  private Scanner scan;
  private final String csvLike = "../dataset/liked.csv";
  private final String csvCloud = "../dataset/music.csv";
  private final String csvTest = "../dataset/test.csv";
  private ArrayList<String> song;
  private ArrayList<ArrayList<String>> exploreSongs = new ArrayList<ArrayList<String>>();
  private int[] randomIndexes = new int[2];
  private ArrayList<Integer> likeIndex = new ArrayList<Integer>();
  private int lineCount = 10030;
  private int mean = 0;

  //return song that user might likes
    public void dailySelection(){
      BufferedReader br = null;
      String line = "";
      String cvsSplitBy = ",";
      int likeLength = 0;
      int sum = 0;

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
              //do addition
              sum = sum + Integer.parseInt(Songs[5]);
            }
            mean = sum/likeLength;

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
          findFromCloud();
          System.out.println("mean: "+mean);
    }

    public void findFromCloud(){
      BufferedReader br = null;
      String line = "";
      String cvsSplitBy = ",";
      int likeLength = 0;

      try{
        br = new BufferedReader(new FileReader(csvLike));
        while ((line = br.readLine()) != null) {
          likeLength++;//get length
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
          br = new BufferedReader(new FileReader(csvCloud));
          System.out.println("Song   Artist   Year   Genre   Like");
          while ((line = br.readLine()) != null) {
              // use comma as separator
              String[] Songs = line.split(cvsSplitBy);
              //Store songs to 2D ArrayList
              song = new ArrayList<String>();
                for(int j = 0; j < 7; j++){
                  song.add(Songs[j]);
                }
                exploreSongs.add(song);
            }
            //lenear search
            for(int i = 0; i < likeLength; i++){
              if(Integer.parseInt(exploreSongs.get(i).get(6)) == mean){
                likeIndex.add(i);//add line numbers which equals mean
                System.out.println(i);
              }
            }
            //random Selection
            Random rand = new Random();
            int numOfSongs = 2;
            int randIndex = -1;
            int temp;

            for(int i = 0; i < numOfSongs; i++){
              temp = randIndex;
              randIndex = rand.nextInt(likeIndex.size());
              int a = i+1;
              System.out.print(a +": ");
              if(randIndex != temp){
                for(int j = 1; j < 6; j++){
                  System.out.print(exploreSongs.get(likeIndex.get(randIndex)).get(j)+"  |  ");
                }
              }
              else{
                randIndex++;
                for(int j = 1; j < 6; j++){
                  System.out.print(exploreSongs.get(likeIndex.get(randIndex)).get(j)+"  |  ");
                }
              }
              randomIndexes[i] = likeIndex.get(randIndex);
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

    public void addTo(){
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
          fileWriter.append(",");
          fileWriter.append(exploreSongs.get(randomIndexes[index]).get(6));
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
