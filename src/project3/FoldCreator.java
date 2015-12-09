package project3;

import java.util.BitSet;

/**
 * Created by Thomas on 02-12-2015.
 */
public class FoldCreator {

    public String createFold(BitSet firstSet, BitSet secondSet, boolean isFirstSetOdds, int sequenceLength, int firstSplitLength) {
        return createFold(firstSet, secondSet, isFirstSetOdds, sequenceLength, firstSplitLength, false);
    }

    public String createFold(BitSet firstSet, BitSet secondSet, boolean isFirstSetOdds, int sequenceLength, int firstSplitLength, boolean isReversed){
        boolean isSequenceLengthEven = (sequenceLength%2==0);
        int secondSequenceLength = (sequenceLength - firstSplitLength * 2) + (isFirstSetOdds ? 0 : 1);
        int secondSplitLength = (secondSequenceLength+1)/2;
        String result = "";

        if(isFirstSetOdds){
            if(isReversed)
                result = "w";
            else
                result = "e";
        }

        String s1 = createFoldingPart(firstSet, true, firstSplitLength, isReversed);
        String s2 = createFoldingPart(secondSet, false, secondSplitLength, isReversed);
        result += s1 + s2;

        if(isFirstSetOdds == isSequenceLengthEven){
            if(isReversed)
                result += "e";
            else
                result += "w";
        }

        return result;
    }

    private String createFoldingPart(BitSet set, boolean isFirstSet, int splitLength, boolean isReversed) {
        String forward;
        String levelDown;
        String levelUp;
        if((isFirstSet && !isReversed) || (!isFirstSet && isReversed)){
            forward = "e";
            levelUp = "n";
            levelDown = "s";
        }
        else {
            forward = "w";
            levelUp = "s";
            levelDown = "n";
        }

        String result = "";
        int level = 0;
        for (int i = 1; i < splitLength-1; i++) {
            if(set.get(i)){
                result += forward;
                result += duplicateString(levelDown, level);
                result += forward;
                level = 0;
            }
            else{
                result += levelUp;
                level++;
            }
        }
        if(splitLength != 1){
            result += forward;
            result += duplicateString(levelDown, level);
            result += forward;
        }
        if(isFirstSet)
            result += levelDown;

        return result;
    }

    public String duplicateString(String toBeDuplicated, int numberOfDuplicates){
        return new String(new char[numberOfDuplicates]).replace("\0", toBeDuplicated);
    }
}
