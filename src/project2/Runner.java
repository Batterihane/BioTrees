package project2;

import java.io.IOException;

/**
 * Created by ballololz on 11/19/2015.
 */
public class Runner {
    public static void main(String[] args) {
        PhyllipParser pp = new PhyllipParser("src//test.phy");
        Tuple<double[][], String[]> result=null;

        result = pp.parse();


        System.out.println(result.getLeft()[4][1]);
        System.out.println(result.getRight()[3]);

    }
}
