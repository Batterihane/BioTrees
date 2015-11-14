package project1;

import org.forester.phylogeny.Phylogeny;
import project1.RootedTree.Node;

import java.util.Arrays;

/**
 * Created by Thomas on 14-11-2015.
 */
public class Runner {
    public static void main(String[] args) {
        ForesterNewickParser parser = new ForesterNewickParser();

//        Phylogeny phylogenyTree1 = parser.parseNewickFile("trees//test//tree1.new");
//        Phylogeny phylogenyTree2 = parser.parseNewickFile("trees//test//tree2.new");

        Phylogeny phylogenyTree1 = parser.parseNewickFile("trees//quickTree//permuted-mafft.new");
        Phylogeny phylogenyTree2 = parser.parseNewickFile("trees//rapidnj//permuted-mafft.new");

        DayAlgorithm dayAlgorithm = new DayAlgorithm(phylogenyTree1, phylogenyTree2);
        System.out.println(dayAlgorithm.getSplitDistance());
        //compareTrees();
    }

    public static void compareTrees() {
        Pair<String[], String[]> fileNames = getFileNames();
        ForesterNewickParser parser = new ForesterNewickParser();
        DayAlgorithm dayAlgorithm;

        Phylogeny phylogenyTree1;
        Phylogeny phylogenyTree2;
        for (int i = 0; i < 8; i++) {
            phylogenyTree1 = parser.parseNewickFile(fileNames.getLeft()[i]);
            phylogenyTree2 = parser.parseNewickFile(fileNames.getRight()[i]);

            dayAlgorithm = new DayAlgorithm(phylogenyTree1, phylogenyTree2);
            System.out.println(dayAlgorithm.getSplitDistance());
        }
    }

    public static Pair<String[], String[]> getFileNames() {
        String[] quickTreeFiles = new String[8];
        String[] rapidnjFiles = new String[8];

        quickTreeFiles[0] = "trees//quickTree//kalign.new";
        quickTreeFiles[1] = "trees//quickTree//mafft.new";
        quickTreeFiles[2] = "trees//quickTree//muscle.new";
        quickTreeFiles[3] = "trees//quickTree//omega.new";
        quickTreeFiles[4] = "trees//quickTree//permuted-kalign.new";
        quickTreeFiles[5] = "trees//quickTree//permuted-mafft.new";
        quickTreeFiles[6] = "trees//quickTree//permuted-muscle.new";
        quickTreeFiles[7] = "trees//quickTree//permuted-omega.new";

        rapidnjFiles[0] = "trees//rapidnj//kalign.new";
        rapidnjFiles[1] = "trees//rapidnj//mafft.new";
        rapidnjFiles[2] = "trees//rapidnj//muscle.new";
        rapidnjFiles[3] = "trees//rapidnj//omega.new";
        rapidnjFiles[4] = "trees//rapidnj//permuted-kalign.new";
        rapidnjFiles[5] = "trees//rapidnj//permuted-mafft.new";
        rapidnjFiles[6] = "trees//rapidnj//permuted-muscle.new";
        rapidnjFiles[7] = "trees//rapidnj//permuted-omega.new";
        return new Pair<>(quickTreeFiles, rapidnjFiles);
    }
}
