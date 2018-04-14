import java.io.*;
import java.util.*;

public class SortInt{
  private ArrayList<data> dat;
  private static final String csvLike = "../dataset/liked.csv";

  public SortInt(){
    dat = new ArrayList<data>();
  }

  public void addSong(data name){
    dat.add(name);
  }

  public void readRank() throws IOException{
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    int rankCol = 0;
    List <String> output = new ArrayList<>();

    try{
      br = new BufferedReader(new FileReader(csvLike));
      while((line = br.readLine()) != null){
        String[] Songs = line.split(csvSplitBy);
        //here put into Array
        output.add(Songs[rankCol]);
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
    }//read rankNum

      sortString(output);
      for(String s: output){
        System.out.println(s);
      }

  }

  public void readYear() throws IOException{
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    int yearCol = 3;
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
  }

  public void sortString(List<String> x){
    int j;
    boolean flag = true;
    String temp;

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
  }
}
