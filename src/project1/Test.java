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

    public static void main(String[] args) throws IOException{
        generateTree(20000);
    }
    public static Phylogeny generateTree(int n){
        Phylogeny p = new Phylogeny();
        PhylogenyNode p0 = new PhylogenyNode();
        PhylogenyNode p1 = p0;
        for(int i = 0; i<n; i++) {
            p1.addAsChild(new PhylogenyNode());
            p1.addAsChild(new PhylogenyNode());
            p1.getChildNode1().setName(""+Integer.toString(i));
            p1.getChildNode1().setDistanceToParent(1);
            p1 = p1.getChildNode2();

        }
        p1.setName("a");
        System.out.println(p0);
        System.out.println(p1);

        p.addAsSibling(p0);

        for (String s : p.getAllExternalNodeNames())
            System.out.println(s);
        return p;

    }


}
