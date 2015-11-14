package project1;

import org.forester.phylogeny.Phylogeny;
import project1.RootedTree.Node;

/**
 * Created by Thomas on 14-11-2015.
 */
public class Runner {
    public static void main(String[] args) {
        ForesterNewickParser parser = new ForesterNewickParser();
        Phylogeny phylogenyTree1 = parser.parseNewickFile("trees//quickTree//kalign.new");
        Phylogeny phylogenyTree2 = parser.parseNewickFile("trees//rapidnj//kalign.new");
        Rooter rooter = new Rooter();
        Node tree1 = rooter.rootTrees(phylogenyTree1);
        Node tree2 = rooter.rootTrees(phylogenyTree2);
        System.out.println(tree2.countLeaves());
        System.out.println(tree1.getLeafNamesDepthFirst().containsAll(tree2.getLeafNamesDepthFirst()));
    }
}
