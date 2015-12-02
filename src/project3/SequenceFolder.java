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
        boolean evenLength = (inp.length%2==0);

        if(!evenLength&&inp[inp.length-1]=='h'){ //set last bit if vector is of uneven length
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


        BitSet[] splits = split(evens, odds);
        System.out.println(splits[0]);
        System.out.println(splits[1]);
        System.out.println(splits[2]);
        System.out.println(splits[3]);
        System.out.println(splits[4]);


        return null;
    }

    public BitSet[] split(BitSet evens, BitSet odds){
        int evenCount=0, oddCount = 0;
        BitSet bool = new BitSet(1);


        BitSet[] result = new BitSet[4]; //[evens1,evens2,odds1,odds2, 0=even1/odd2 1=odd1/even2]
        for (int i = 0;i<Math.min(evens.length(),odds.length());i++){
            if (evens.get(i))
                evenCount++;

            if (odds.get(i))
                oddCount++;

            if (evenCount == (evens.cardinality()+1)/2 || oddCount == (odds.cardinality()+1)/2){ //vi bremser på odds eller evens
                result[0] = evens.get(0,i+1);
                result[1] = evens.get(i+1,evens.length());
                result[2] = odds.get(0,i+1);
                result[3] = odds.get(i+1,evens.length());
                bool.set(0,oddCount == (odds.cardinality()+1)/2); //true hvis vi bremsede på odds, false ellers

                //return result;
                //code below trims result before returning it
                if (bool.get(0)){
                    BitSet[] res = {result[2], result[1],bool};
                    return res;
                }
                else{
                    BitSet[] res = {result[0],result[3],bool};
                    return res;
                }

            }
        }
        System.out.println("det her burde aldrig ske!");
        return null;

    }
}
