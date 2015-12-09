package project3;

import java.util.BitSet;

/**
 * Created by ballololz on 11/30/2015.
 */
public class ImprovedSequenceFolder {
    private int bestSubScore = 0;

    public String reverseFold(String input){
        return fold(input, true);
    }

    public String fold(String input){
        return fold(input, false);
    }

    public String fold(String input, boolean isReversed){
        int currentlyBestScore = 0;
        String currentlyBestFold = "";
        for (int i = 1; i < input.length(); i++) {
            String fold = getBestSplitOnIndex(input, i, isReversed);
            if(bestSubScore > currentlyBestScore){
                currentlyBestFold = fold;
                currentlyBestScore = bestSubScore;
            }
        }
        return currentlyBestFold;
    }

    public String getBestSplitOnIndex(String input, int index, boolean isReversed) {
        FoldCreator foldCreator = new FoldCreator();

        String first = input.substring(0, index);
        String second = input.substring(index);
        int length = input.length();
        int firstLength = first.length();
        int secondLength = second.length();
        BitSet firstEvens, secondEvens, firstOdds, secondOdds;

        firstEvens = new BitSet(firstLength);
        firstOdds = new BitSet(firstLength);
        secondEvens = new BitSet(secondLength);
        secondOdds = new BitSet(secondLength);

        char[] inputChars = input.toCharArray();
        char[] firstChars = first.toCharArray();
        char[] secondChars = second.toCharArray();

        for (int i = 0; i < firstLength; i++) {
            if(i%2 == 0){
                if (firstChars[i] == 'h') {
                    firstEvens.set(i/2);
                }
            }
            else{
                if (firstChars[i] == 'h') {
                    firstOdds.set(i/2);
                }
            }
        }

        for (int i = index; i < length; i++) {
            if(i%2 == 0){
                if (inputChars[i] == 'h') {
                    secondEvens.set((i-index)/2);
                }
            }
            else{
                if (inputChars[i] == 'h') {
                    secondOdds.set((i-index)/2);
                }
            }
        }

        String result;
        if(index%2 == 0){
            result = foldCreator.createFold(firstOdds, secondEvens, true, input.length(), firstLength/2, isReversed);
        }
        else{
            result = foldCreator.createFold(firstEvens, secondOdds, false, input.length(), (firstLength+1)/2, isReversed);
        }

        ScoreFinder scoreFinder = new ScoreFinder();
        bestSubScore = scoreFinder.findScore(input, result);
        return result;
    }


}
