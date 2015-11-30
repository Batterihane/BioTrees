package project2;

import org.forester.phylogeny.Phylogeny;
import project1.ForesterNewickParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by ballololz on 11/19/2015.
 */
public class Runner {

    public static final String MATRICES_PATH = "project2//distance_matrices//";
    public static final String TREES_PATH = "project2//trees//";

    public static void runNJAlgorithm() throws IOException {
        Files.walk(Paths.get(MATRICES_PATH)).forEach(filepath -> {
            if (!filepath.getFileName().toString().equals("distance_matrices")){
                PhyllipParser parser = new PhyllipParser(filepath.toString());
                Tuple<double[][], String[]> phyllip = parser.parse();

                long time = System.nanoTime();
                HashMap<IntPair, Double> tree = new NJOptimized().run(phyllip.getRight(), phyllip.getLeft());
                System.out.println(filepath.getFileName().toString() + ": " + (System.nanoTime() - time));

                NewickMaker newickMaker = new NewickMaker(phyllip.getRight(), tree);
                String newickTree = newickMaker.make();
                try {
                    NewickWriter newickWriter = new NewickWriter(TREES_PATH + filepath.getFileName().toString().replaceFirst(".phy", ".new"));
                    newickWriter.write(newickTree);
                } catch (Exception e) {
                    e.printStackTrace();
                };
            }
        });


//        PhyllipParser parser = new PhyllipParser(MATRICES_PATH + "//1849_FG-GAP.phy");
//        Tuple<double[][], String[]> phyllip = parser.parse();
//        HashMap<IntPair, Double> tree = new NJAlgorithm().run(phyllip.getRight(), phyllip.getLeft());
//        NewickMaker newickMaker = new NewickMaker(phyllip.getRight(), tree);
//        String newickTree = newickMaker.make();
//        NewickWriter newickWriter = new NewickWriter(TREES_PATH + "1849_FG-GAP.new");
//        newickWriter.write(newickTree);
    }

    public static void main(String[] args) throws IOException {
        runNJAlgorithm();

//        String[] leaves = new String[5];
//        double[][] distances = new double[5][5];
//
//        for (int i = 0; i < 5; i++) {
//            leaves[i] = "leaf " + i;
////            for (int j = 0; j < 5; j++) {
////                distances[i][j] = 1;
////            }
//        }
//        distances[0][0] = 0;
//        distances[0][1] = 2;
//        distances[1][0] = 2;
//        distances[0][2] = 3;
//        distances[2][0] = 3;
//        distances[0][3] = 4;
//        distances[3][0] = 4;
//        distances[0][4] = 4;
//        distances[4][0] = 4;
//
//        distances[1][1] = 0;
//        distances[1][2] = 3;
//        distances[2][1] = 3;
//        distances[1][3] = 4;
//        distances[3][1] = 4;
//        distances[1][4] = 4;
//        distances[4][1] = 4;
//
//        distances[2][2] = 0;
//        distances[2][3] = 3;
//        distances[3][2] = 3;
//        distances[2][4] = 3;
//        distances[4][2] = 3;
//
//        distances[3][3] = 0;
//        distances[3][4] = 2;
//        distances[4][3] = 2;
//
//        distances[4][4] = 0;

//        PhyllipParser parser = new PhyllipParser(MATRICES_PATH + "89_Adeno_E3_CR1.phy");
//        Tuple<double[][], String[]> phyllip = parser.parse();
//
//
//        HashMap<IntPair, Double> tree = new NJOptimized().run(phyllip.getRight(), phyllip.getLeft());
//
//        NewickMaker newickMaker = new NewickMaker(phyllip.getRight(), tree);
//        String newickTree = newickMaker.make();
//        NewickWriter newickWriter = new NewickWriter("src//test.new");
//        newickWriter.write(newickTree);

//        String[] names = {"A","B","C","D","E"};
//        NewickMaker nm = new NewickMaker(names,tree,7);
//        String result = nm.makeFromStartNode();
//        System.out.println("RESULT\n" + result);
//        ForesterNewickParser np = new ForesterNewickParser();
//        Phylogeny p = np.parseNewickFile("distance_matrices//test.new");
//        np.displayPhylogeny(p);
    }
}
