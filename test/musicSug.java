import java.util.*;
import java.io.*;

public class musicSug{
  /*private static final String csvCloud = "../dataset/music.csv";
  private static final String csvLike = "../dataset/liked.csv";
  private static final String csvTest = "../dataset/test.csv";*/
  private static Scanner scan;
  public static void main(String[] args) {
    do{
      conversition();
    }while(answer());
  }

  public static void conversition(){
    Like lk = new Like();
    Explore ep = new Explore();
    DailySelection ds = new DailySelection();

    System.out.println("Welcome to AlleKids Music!");
    System.out.println("What do you want to do?");
    System.out.println("Menu: 1.Like | 2.Explore | 3.Daily Selection | 4.quit");
    System.out.println("Please select a number.");

    scan = new Scanner(System.in);

    while(scan.hasNext()){
      String command = scan.nextLine().toLowerCase();
      if(command.equals("1")){
        lk.likes();
        System.out.println("Would you like to sort your like list? (y/n)");
        if(answer()==true){
          lk.sortList();
        } System.out.println("You are at menu now");
      }
      else if(command.equals("2")){
        ep.explore();
        System.out.println("Do you like any of these songs?(y/n)");
        if(answer()==true){
          ep.addSong();
        }System.out.println("You are at menu now");
      }
      else if(command.equals("3")){
        ds.dailySelection();
        System.out.println("Do you like any of these songs?(y/n)");
        if(answer()==true){
          ds.addTo();
        }System.out.println("You are at menu now");
      }
      else if(command.equals("4")){
        break;
      }
      else{
        System.out.println("The commands are 1.Like | 2.Explore | 3.Daily Selection | 4.quit");
        System.out.println("Please select number.");
      }
    }
    System.out.println("Are you sure you want to quit? (y/n)");
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

}
