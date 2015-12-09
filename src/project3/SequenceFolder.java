package project3;

import java.util.BitSet;

/**
 * Created by ballololz on 11/30/2015.
 */
public class SequenceFolder {
    private int firstSplitLength = -1;

    public SequenceFolder(){}
    public String fold(String input){
        return fold(input, false);
    }
    public String fold(String input, boolean isReversed){
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

        if(evens.length() == 0 || odds.length() == 0){ // no h's can be matched
            return new String(new char[n-1]).replace("\0", "s");
        }

        BitSet[] splits = split(evens, odds);
        System.out.println("First set is " + (splits[2].get(0) ? "odd" : "even"));

        String result = new FoldCreator().createFold(splits[0], splits[1], splits[2].get(0), inp.length, firstSplitLength, isReversed);
        return result;
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


            boolean stoppedOnEven = evenCount == (evens.cardinality() + 1) / 2;
            boolean stoppedOnOdd = oddCount == (odds.cardinality() + 1) / 2;
            if (stoppedOnEven || stoppedOnOdd){ //vi bremser på odds eller evens
                result[0] = evens.get(0,i+1);
                result[1] = evens.get(i+1,evens.length());
                if(stoppedOnEven){
                    result[2] = odds.get(0,i);
                    result[3] = odds.get(i,odds.length());
                }
                else{
                    result[2] = odds.get(0,i+1);
                    result[3] = odds.get(i + 1, odds.length());
                }
                bool.set(0,evenCount != (evens.cardinality()+1)/2); //true hvis vi bremsede på odds, false ellers
                firstSplitLength = i+1;
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
        System.out.println("Burde stadig aldrig ske!");
        return null;

    }
}
