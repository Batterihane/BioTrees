package project1;

import org.forester.phylogeny.Phylogeny;
import org.forester.phylogeny.PhylogenyNode;
import project1.RootedTree.Node;

import java.util.Arrays;

/**
 * Created by Thomas on 14-11-2015.
 */
public class Runner {
    public static void main(String[] args) {
//        ForesterNewickParser parser = new ForesterNewickParser();

//        Phylogeny phylogenyTree1 = parser.parseNewickFile("trees//test//tree1.new");
//        Phylogeny phylogenyTree2 = parser.parseNewickFile("trees//test//tree2.new");

//        Phylogeny phylogenyTree1 = parser.parseNewickFile("trees//quickTree//permuted-kalign.new");
//        Phylogeny phylogenyTree2 = parser.parseNewickFile("trees//rapidnj//permuted-kalign.new");
//
//        DayAlgorithm dayAlgorithm = new DayAlgorithm(phylogenyTree1, phylogenyTree2);
//        System.out.println(dayAlgorithm.getSplitDistance());

//        String[] fileNames = new String[8];
//        Pair<String[], String[]> allFileNames = getFileNames();
//        for (int i = 0; i < 4; i++) {
//            fileNames[i] = allFileNames.getLeft()[i+4];
//            fileNames[i+4] = allFileNames.getRight()[i];
//        }
        compareTrees(getFileNames().getRight());
    }

    public static void compareTrees(String[] fileNames) {
        ForesterNewickParser parser = new ForesterNewickParser();
        DayAlgorithm dayAlgorithm;

        Phylogeny phylogenyTree1;
        Phylogeny phylogenyTree2;
        for (int i = 0; i < 8; i++) {
            phylogenyTree1 = parser.parseNewickFile(fileNames[i]);
            for (int j = 0; j < 8; j++) {
                phylogenyTree2 = parser.parseNewickFile(fileNames[j]);
                dayAlgorithm = new DayAlgorithm(phylogenyTree1, phylogenyTree2);
                System.out.println(i + "," + j + ": " + dayAlgorithm.getSplitDistance());
                dayAlgorithm = new DayAlgorithm(phylogenyTree2, phylogenyTree1);
                System.out.println(j + "," + i + ": " + dayAlgorithm.getSplitDistance());
            }
        }
    }

    public static Pair<String[], String[]> getFileNames() {
        String[] unpermuted = new String[8];
        String[] permuted = new String[8];

        unpermuted[0] = "trees//quickTree//kalign.new";
        unpermuted[1] = "trees//quickTree//mafft.new";
        unpermuted[2] = "trees//quickTree//muscle.new";
        unpermuted[3] = "trees//quickTree//omega.new";
        unpermuted[4] = "trees//rapidnj//kalign.new";
        unpermuted[5] = "trees//rapidnj//mafft.new";
        unpermuted[6] = "trees//rapidnj//muscle.new";
        unpermuted[7] = "trees//rapidnj//omega.new";

        permuted[4] = "trees//rapidnj//permuted-kalign.new";
        permuted[5] = "trees//rapidnj//permuted-mafft.new";
        permuted[6] = "trees//rapidnj//permuted-muscle.new";
        permuted[7] = "trees//rapidnj//permuted-omega.new";
        permuted[0] = "trees//quickTree//permuted-kalign.new";
        permuted[1] = "trees//quickTree//permuted-mafft.new";
        permuted[2] = "trees//quickTree//permuted-muscle.new";
        permuted[3] = "trees//quickTree//permuted-omega.new";
        return new Pair<>(unpermuted, permuted);
    }
}
