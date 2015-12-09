package project3.cFold;

import project3.SequenceFolder;

/**
 * Created by Thomas on 09-12-2015.
 */
public class CFolder {

    public String fold(String input){
        SequenceFolder uFolder = new SequenceFolder();
        int inputLength = input.length();
        String firstSplit = input.substring(0, inputLength / 2);
        String secondSplit = input.substring(inputLength / 2);
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
