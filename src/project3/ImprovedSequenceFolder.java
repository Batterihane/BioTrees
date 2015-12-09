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

    private String fold(String input, boolean isReversed){
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

    private String getBestSplitOnIndex(String input, int index, boolean isReversed) {
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

        String firstResult = foldCreator.createFold(firstEvens, secondOdds, false, input.length(), firstLength, isReversed);
        String secondResult = foldCreator.createFold(firstOdds, secondEvens, true, input.length(), firstLength, isReversed);

        ScoreFinder scoreFinder = new ScoreFinder();
        int firstScore = scoreFinder.findScore(input, firstResult);
        int secondScore = scoreFinder.findScore(input, secondResult);

        bestSubScore = (firstScore > secondScore)? firstScore : secondScore;
        return (firstScore > secondScore)? firstResult : secondResult;
    }


}
