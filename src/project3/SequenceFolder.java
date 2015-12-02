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
        if(inp.length%2==1&&inp[inp.length-1]=='h'){ //set last bit if vector is of uneven length
                        evens.set(inp.length/2);
        }
        for(int i=0;i<inp.length/2;i++){ //do the rest
            if (inp[2*i]=='h')
                evens.set(i);
            if (inp[1+2*i]=='h')
                odds.set(i);
        }
        System.out.println(evens);
        System.out.println(odds);
        return null;
    }
}
