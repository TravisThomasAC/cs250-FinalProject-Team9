package test;
import java.io.*;
import java.util.*;

public class SortInt{
  private ArrayList<data> dat;
  private static final String csvLike = "../dataset/liked.csv";
  private long timeElapsed1 = 0;

  public SortInt(){
    dat = new ArrayList<data>();
  }

  public void addSong(data name){
    dat.add(name);
  }

  public void readYear() throws IOException{
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    int yearCol = 2;
    List <String> output = new ArrayList<>();

    try{
      br = new BufferedReader(new FileReader(csvLike));
      while((line = br.readLine()) != null){
        String[] Songs = line.split(csvSplitBy);
        output.add(Songs[yearCol]);
      }
    } catch (FileNotFoundException e){
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

    sortString(output);
    for(String s: output){
      System.out.println(s);
    }
    System.out.println(timeElapsed1);
  }
//bubble sort
  public void sortString(List<String> x){
    int j;
    boolean flag = true;
    String temp;
    long startTime1 = System.nanoTime();

    while(flag){
      flag = false;
      for (j = 0;  j < x.size() - 1;  j++ ){
        if ( x.get(j).compareToIgnoreCase( x.get(j+1)) > 0 ){
          temp = x.get(j);
          x.set(j, x.get(j+1));     // swapping
          x.set(j+1, temp);
          flag = true;
        }
      }
    }
    long endTime1 = System.nanoTime();
    timeElapsed1 = endTime1-startTime1;
  }
}
