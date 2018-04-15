public class data{
    private int year;
    private String song;
    private String artist;
    //private int rank;
    private String genre;
    private char like;

    public data(/*int rank, */String song, String artist, int year, String genre, char like) {
        super();
        //this.rank = rank;
        this.song = song;
        this.artist = artist;
        this.year = year;
        this.genre = genre;
        this.like = like;
    }
/*
    public int getRank() {
        return rank;
    }

    public void setRank(int rank){
      this.rank = rank;
    }
*/
    public String getSong() {
        return song;
    }

    public void setSong(String song){
      this.song = song;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist){
      this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year){
      this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre){
      this.genre = genre;
    }

    public char getLike(){
      return like;
    }

    public void setLike(){
      this.like = like;
    }

    public String toString() {
        return /*rank + ", " + */song + ", " + artist + ", " + year + "," + genre + ", " + like;
    }

}
