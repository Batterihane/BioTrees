package project2;

import org.forester.phylogeny.Phylogeny;
import project1.ForesterNewickParser;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by ballololz on 11/19/2015.
 */
public class Runner {
    public static void main(String[] args) {
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

        PhyllipParser parser = new PhyllipParser("distance_matrices//1849_FG-GAP.phy");
        Tuple<double[][], String[]> phyllip = parser.parse();


        HashMap<IntPair, Double> tree = new NJAlgorithm().run(phyllip.getRight(), phyllip.getLeft());
//        String[] names = {"A","B","C","D","E"};
//        NewickMaker nm = new NewickMaker(names,tree,7);
//        String result = nm.make();
//        System.out.println("RESULT\n" + result);
//        ForesterNewickParser np = new ForesterNewickParser();
//        Phylogeny p = np.parseNewickFile("distance_matrices//test.new");
//        np.displayPhylogeny(p);
    }
}
