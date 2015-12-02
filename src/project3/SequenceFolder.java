package project3;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by ballololz on 11/30/2015.
 */
public class SequenceFolder {

    public SequenceFolder(){}

    public String fold(String input){
        int n = input.length();
        BitSet evens = new BitSet(n); //index n is n'th even int. 0=0, 1=2, 2=4 etc.
        BitSet odds = new BitSet(n); //0=1, 1=3, 2=5 ...

        char[] inp = input.toCharArray();
        for(int i=0;i<inp.length/2;i++){
            if (inp[2*i]=='h')
                evens.set(i);
            if (inp[1+2*i]=='h') //hvis der er et lige antal vil det sidste odd check være ude over arrayet
                odds.set(i);
        }

        return null;
    }
}
