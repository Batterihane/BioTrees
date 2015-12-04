package project3;

import java.util.BitSet;

/**
 * Created by Thomas on 02-12-2015.
 */
public class FoldCreator {

    public String createFold(BitSet firstSet, BitSet secondSet, boolean isFirstSetOdds){
        String result = "";

        if(isFirstSetOdds){
            result = "e";
        }

        result += createFoldingPart(firstSet, true) + createFoldingPart(secondSet, false);

        return result;
    }

    private String createFoldingPart(BitSet set, boolean isFirstSet) {
        String forward;
        String levelDown;
        String levelUp;
        if(isFirstSet){
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
        for (int i = 1; i < set.length()-1; i++) {
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
        result += forward;
        result += duplicateString(levelDown, level);
        result += forward;
        if(isFirstSet)
            result += levelDown;

        return result;
    }

    public String duplicateString(String toBeDuplicated, int numberOfDuplicates){
        return new String(new char[numberOfDuplicates]).replace("\0", toBeDuplicated);
    }
}
