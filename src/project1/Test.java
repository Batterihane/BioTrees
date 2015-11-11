package project1;

import org.forester.archaeopteryx.Archaeopteryx;
import org.forester.archaeopteryx.MainFrame;
import org.forester.io.parsers.PhylogenyParser;
import org.forester.io.parsers.util.ParserUtils;
import org.forester.phylogeny.Phylogeny;
import org.forester.phylogeny.PhylogenyMethods;
import org.forester.phylogeny.PhylogenyNode;
import org.forester.phylogeny.iterators.PhylogenyNodeIterator;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Thomas on 06-11-2015.
 */

public class Test {

    static void SupNoobs(){

        System.out.println("sup?");
    }




    public static void main(String[] args) throws IOException{
        String nhx = "(mammal,(turtle,rayfinfish,(frog,salamander)))";
        Phylogeny phylogeny = new Phylogeny();

        final File treefile = new File("trees//test.new");
        PhylogenyParser parserDependingOnFileType = ParserUtils.createParserDependingOnFileType(treefile, true);

        Phylogeny[] phys = null;
        try {
            phys = PhylogenyMethods.readPhylogenies(parserDependingOnFileType, treefile);
        }
        catch ( final IOException e ) {
            e.printStackTrace();
        }
        //org.forester.phylogeny.Phylogeny.class.newInstance().
        //Phylogeny ph = org.forester.phylogeny.Phylogeny.class.newInstance().
        Phylogeny tree = phys[0];
        PhylogenyNode root = tree.getRoot();
        System.out.println(tree.isRerootable());
        //tree.reRoot(tree.getNode(0));
        //tree.reRoot(tree.getNode("turtle"));
        PhylogenyNodeIterator phylogenyNodeIterator = tree.iteratorPreorder();

/*
        while(phylogenyNodeIterator.hasNext()) {
            System.out.println(phylogenyNodeIterator.next().toString());
        }
*/

        List<PhylogenyNode> allDescendants = tree.getNode(0).getDescendants();
        allDescendants.forEach(node ->
                System.out.println(node.getNumberOfDescendants())
        );
        MainFrame application = Archaeopteryx.createApplication(tree);

    }

}
