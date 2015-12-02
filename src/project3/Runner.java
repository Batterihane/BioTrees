package project3;

/**
 * Created by ballololz on 11/30/2015.
 */
public class Runner {
    public static void main(String[] args) {
        ScoreFinder scofi = new ScoreFinder();
        int i = scofi.findScore("hhphphphhph","nesswwnnne");
        System.out.println(i);
    }
}
