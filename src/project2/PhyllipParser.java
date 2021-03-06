package project2;

import sun.misc.Regexp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Thomas on 19-11-2015.
 */
public class PhyllipParser{
    BufferedReader br;
    final String delimiter = "   "; //hvad de bruger i phy filerne mellem tal
    public PhyllipParser(String filePath) {
        try {
            br = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException fnf) {
            System.out.printf("File was not found!");}

    }

    public Tuple<double[][],String[]> parse(){

        double[][] res1=null;
        String[] res2=null;
        try {
            //System.out.println(br.readLine().trim());
            int n = Integer.parseInt(br.readLine().trim());
            res1 = new double[n][n];
            res2 = new String[n];
            for (int i = 0; i < n; i++) {
                String[] parts = br.readLine().split(delimiter);

                res2[i] = parts[0];

                for (int j = 1; j < n + 1; j++) {
                    res1[i][j - 1] = Double.parseDouble(parts[j]);
                }
            }
        }catch(IOException ioe){
            System.out.println("IOException i PhyllipParser. Fix jeres filer");

        }



        return new Tuple<double[][],String[]>(res1,res2);


    }
}
