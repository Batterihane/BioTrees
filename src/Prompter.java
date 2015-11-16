import org.forester.phylogeny.Phylogeny;
import project1.DayAlgorithm;
import project1.ForesterNewickParser;

/**
 * Created by Thomas on 16-11-2015.
 */
public class Prompter {
    public static void main(String[] args) {
        ForesterNewickParser parser = new ForesterNewickParser();

        Phylogeny phylogenyTree1 = parser.parseNewickFile("tree1.new");
        Phylogeny phylogenyTree2 = parser.parseNewickFile("tree2.new");

        DayAlgorithm dayAlgorithm = new DayAlgorithm(phylogenyTree1, phylogenyTree2);
        System.out.println(dayAlgorithm.getSplitDistance());
    }
}
