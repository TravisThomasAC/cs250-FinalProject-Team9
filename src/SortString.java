import java.io.*;
import java.util.*;

public class SortString{
  private ArrayList<data> dat;
  private static final String csvLike = "../dataset/liked.csv";

  public SortString(){
    dat = new ArrayList<data>();
  }

  public void addSong(data name){
    dat.add(name);
  }

  public void readSong() throws IOException{
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    int songCol = 0;
    List <String> output = new ArrayList<>();

    try{
      br = new BufferedReader(new FileReader(csvLike));
      while((line = br.readLine()) != null){
        String[] Songs = line.split(csvSplitBy);
        //here put into ArrayList
        output.add(Songs[songCol]);
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

  public void readArtist() throws IOException{
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    int artistCol = 1;
    List <String> output = new ArrayList<>();

    try{
      br = new BufferedReader(new FileReader(csvLike));
      while((line = br.readLine()) != null){
        String[] Songs = line.split(csvSplitBy);
        //here put into ArrayList
        output.add(Songs[artistCol]);
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

  public void readGenre() throws IOException{
    int i;
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";
    int genreCol = 3;
    List <String> output = new ArrayList<>();

    try{
      br = new BufferedReader(new FileReader(csvLike));
      while((line = br.readLine()) != null){
        String[] Songs = line.split(csvSplitBy);
        //here put into ArrayList
        output.add(Songs[genreCol]);
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
    int i,j;
    String temp;

    for ( i = 0;  i < x.size() - 1;  i++ )
    {
      for ( j = i + 1;  j < x.size();  j++ )
      {
        if ( x.get(i).compareToIgnoreCase( x.get(j) ) > 0 )
          {                                             // ascending sort
            temp = x.get(i);
            x.set(i,x.get(j));    // swapping
            x.set(j, temp);
          }
        }
     }
  }
}
