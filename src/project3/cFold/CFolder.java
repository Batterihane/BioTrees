package project3.cFold;

import project3.FoldValidator;
import project3.ScoreFinder;
import project3.SequenceFolder;

/**
 * Created by Thomas on 09-12-2015.
 */
public class CFolder {

    public String fold(String input){
        ScoreFinder scoreFinder = new ScoreFinder();
        int bestScore = 0;
        String bestFold = "";
        for (int i = 0; i < input.length(); i++) {
            String fold = foldWithSplitIndex(input, i);
            FoldValidator foldValidator = new FoldValidator();
            if(!foldValidator.validate(input, fold)) continue;

            int score = scoreFinder.findScore(input, fold);
            if(score > bestScore){
                bestScore = score;
                bestFold = fold;
            }
        }

        return bestFold;
    }

    private String foldWithSplitIndex(String input, int splitIndex) {
        SequenceFolder uFolder = new SequenceFolder();
        if(splitIndex == 0){
            return uFolder.fold(input);
        }
        String firstSplit = input.substring(0, splitIndex);
        String secondSplit = input.substring(splitIndex);
        String firstFold = uFolder.fold(firstSplit);
        String secondFold = uFolder.fold(secondSplit, true);

//        System.out.println(firstSplit);
//        System.out.println(secondSplit);
//        System.out.println(firstSplit);
//        System.out.println(firstSplit);

        String result = firstFold + "w" + secondFold;
        return result;
    }

}
