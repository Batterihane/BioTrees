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

        Phylogeny phylogenyTree1 = parser.parseNewickFile("trees//test.new");
        Phylogeny phylogenyTree2 = parser.parseNewickFile("trees//test.new");
//
//        Phylogeny phylogenyTree1 = parser.parseNewickFile("trees//quickTree//kalign.new");
//        Phylogeny phylogenyTree2 = parser.parseNewickFile("trees//rapidnj//kalign.new");

        DayAlgorithm dayAlgorithm = new DayAlgorithm(phylogenyTree1, phylogenyTree2);
        System.out.println(dayAlgorithm.getSplitDistance());


        Rooter rooter = new Rooter();
        int[] myArray = new int[10];
        Arrays.fill(myArray, 0);
        Node tree1 = rooter.rootTrees(phylogenyTree1);
        Node tree2 = rooter.rootTrees(phylogenyTree2);
    }
}
