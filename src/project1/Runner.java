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

        Phylogeny phylogenyTree1 = parser.parseNewickFile("trees//test//tree1.new");
        Phylogeny phylogenyTree2 = parser.parseNewickFile("trees//test//tree2.new");
//
//        Phylogeny phylogenyTree1 = parser.parseNewickFile("trees//quickTree//kalign.new");
//        Phylogeny phylogenyTree2 = parser.parseNewickFile("trees//rapidnj//kalign.new");

        DayAlgorithm dayAlgorithm = new DayAlgorithm(phylogenyTree1, phylogenyTree2);
        System.out.println(dayAlgorithm.getSplitDistance());

    }
}
