package project1;

import org.forester.archaeopteryx.Archaeopteryx;
import org.forester.archaeopteryx.MainFrame;
import org.forester.io.parsers.PhylogenyParser;
import org.forester.io.parsers.util.ParserUtils;
import org.forester.phylogeny.Phylogeny;
import org.forester.phylogeny.PhylogenyMethods;

import java.io.File;
import java.io.IOException;

/**
 * Created by Thomas on 06-11-2015.
 */

public class Test {

    static void SupNoobs(){

        System.out.println("sup?");
    }




    public static void main(String[] args) throws IOException{
        System.out.println("hej");
        String nhx = "(mammal,(turtle,rayfinfish,(frog,salamander)))";
        Phylogeny phylogeny = new Phylogeny();

        final File treefile = new File("C:\\Users\\Nikolaj\\BioTreeAndSeq\\BioTrees\\trees\\quickTree\\kalign.new");
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
        MainFrame application = Archaeopteryx.createApplication(tree);


    }

}
