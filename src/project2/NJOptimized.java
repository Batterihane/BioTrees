package project2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NJOptimized {
//    long step1 = 0;
//    long step2 = 0;
//    long step3 = 0;
//    long step4 = 0;
//    long step5 = 0;
//    long time;

    public NJOptimized(){
    }

    public HashMap<IntPair, Double> run(String[] leaves, double[][] distances){
        int numberOfLeaves = leaves.length;
        int numberOfNodes = numberOfLeaves;
        HashMap<IntPair, Double> tree = new HashMap<>();
        List<List<Double>> dissimilarities = new ArrayList<>();

        // Create dissimilarity matrix
        for(int i = 0 ; i<distances.length ; i++){
            double[] distanceRow = distances[i];
            List<Double> dissimilarityRow = new ArrayList<>();
            dissimilarityRow.add(1.0 * i);
            dissimilarities.add(dissimilarityRow);
            for(double distance : distanceRow){
                dissimilarityRow.add(distance);
            }
        }

        int numberOfTaxa;
        boolean isFirstIteration = true;
        double[] rSums = new double[dissimilarities.size()];
        List<Double> lastPickedNeighbour1Dissimilarities = new ArrayList<>();
        List<Double> lastPickedNeighbour2Dissimilarities = new ArrayList<>();
        int lastNeighbour1Position = -1, lastNeighbour2Position = -1;
        double[][] approxDistances = new double[dissimilarities.size()][dissimilarities.size()];
        double[] r = new double[dissimilarities.size()];

        while(dissimilarities.size() > 3){
            numberOfTaxa = dissimilarities.size();

            // Compute r list
//            time = System.currentTimeMillis();
            if(isFirstIteration){
                for (int i = 0; i < numberOfTaxa; i++) {
                    double distanceSum = 0;
                    List<Double> dissimilarityList = dissimilarities.get(i);
                    for (int j = 1; j < dissimilarityList.size(); j++) {
                        distanceSum += dissimilarityList.get(j);
                    }
//                    if(isFirstIteration)
                        rSums[i] = distanceSum;
                    r[i] = distanceSum / (double)(numberOfTaxa-2);
                }
                isFirstIteration = false;
            }
            else {
//            double[] rr = new double[numberOfTaxa];
//            if(!isFirstIteration){
                for (int i = 0; i < numberOfTaxa; i++) {
                    double distanceSum = 0;
                    int oldPosition = i;
                    if(i == lastNeighbour1Position){
                        List<Double> dissimilarityList = dissimilarities.get(i);
                        for (int j = 1; j < dissimilarityList.size(); j++) {
                            distanceSum += dissimilarityList.get(j);
                        }
                    }
                    else {
                        if(i >= lastNeighbour2Position){
                            oldPosition = i+1;
                        }
                        distanceSum = rSums[oldPosition] - lastPickedNeighbour1Dissimilarities.get(oldPosition + 1)
                                                         - lastPickedNeighbour2Dissimilarities.get(oldPosition + 1)
                                                         + dissimilarities.get(i).get(lastNeighbour1Position+1);
                    }
                    rSums[i] = distanceSum;
                    r[i] = distanceSum / (double)(numberOfTaxa-2);
                }
            }
//            step1 += System.currentTimeMillis()-time;

            // Compute N matrixT
//            time = System.currentTimeMillis();

            for (int i = 0; i < numberOfTaxa; i++) {
                for (int j = i+1; j < numberOfTaxa; j++) {
                    approxDistances[i][j] = dissimilarities.get(i).get(j + 1) - (r[i] + r[j]);
                }
            }
//            step2 += System.currentTimeMillis()-time;

            // Find neighbours
//            time = System.currentTimeMillis();
            int neighbour1Position = 0, neighbour2Position = 0;
            double smallestApproxDistance = Double.MAX_VALUE;
            for (int i = 0; i < numberOfTaxa; i++) {
                for (int j = i+1; j < numberOfTaxa; j++) {
//                    if(i==j) continue;
                    double approxDistance = approxDistances[i][j];
                    if(approxDistance < smallestApproxDistance){
                        smallestApproxDistance = approxDistance;
                        neighbour1Position = i;
                        neighbour2Position = j;
                    }
                }
            }
//            step3 += System.currentTimeMillis()-time;

//            time = System.currentTimeMillis();
            // Add new edges to tree
            int newNode = numberOfNodes;
            double neighbourDistance = dissimilarities.get(neighbour1Position).get(neighbour2Position+1);
            int neighbour1NamePos = dissimilarities.get(neighbour1Position).get(0).intValue();
            int neighbour2NamePos = dissimilarities.get(neighbour2Position).get(0).intValue();
            double distance1 = (neighbourDistance + r[neighbour1Position] - r[neighbour2Position]) / 2;
            double distance2 = (neighbourDistance + r[neighbour2Position] - r[neighbour1Position]) / 2;
            tree.put(new IntPair(newNode, neighbour1NamePos), distance1);
            tree.put(new IntPair(newNode, neighbour2NamePos), distance2);
            numberOfNodes++;

            // Setup for next iteration
            lastPickedNeighbour1Dissimilarities = dissimilarities.get(neighbour1Position);
            lastPickedNeighbour2Dissimilarities = dissimilarities.get(neighbour2Position);
            lastNeighbour1Position = neighbour1Position;
            lastNeighbour2Position = neighbour2Position;

            // Update dissimilarity matrix
            ArrayList<Double> newNodeDistances = new ArrayList<>();
            newNodeDistances.add((double)newNode);
            for (int m = 0; m < numberOfTaxa; m++) {
                if(m == neighbour1Position){
                    newNodeDistances.add(0.0);
                    continue;
                }
                if(m == neighbour2Position){
                    newNodeDistances.add(-1.0); // is removed later
                    continue;
                }
                newNodeDistances.add((dissimilarities.get(neighbour1Position).get(m+1) + dissimilarities.get(neighbour2Position).get(m+1) - neighbourDistance) / 2);
            }
//            step4 += System.currentTimeMillis()-time;

//            time = System.currentTimeMillis();
            dissimilarities.set(neighbour1Position, newNodeDistances);
            dissimilarities.remove(neighbour2Position);
            for (int i = 0; i < dissimilarities.size(); i++) {
                if(i == neighbour1Position) continue;
                if(i < neighbour2Position){
                    dissimilarities.get(i).set(neighbour1Position+1, newNodeDistances.get(i + 1));
                    continue;
                }
                dissimilarities.get(i).set(neighbour1Position+1, newNodeDistances.get(i+2));
            }
            for (List<Double> l : dissimilarities){
                l.remove(neighbour2Position+1);
            }
//            step5 += System.currentTimeMillis()-time;
        }

        // Combine tree
        int newNode = numberOfNodes;
        double distance01 = dissimilarities.get(0).get(2);
        double distance02 = dissimilarities.get(0).get(3);
        double distance12 = dissimilarities.get(1).get(3);
        tree.put(new IntPair(newNode, dissimilarities.get(0).get(0).intValue()), (distance01+distance02-distance12)/2);
        tree.put(new IntPair(newNode, dissimilarities.get(1).get(0).intValue()), (distance01+distance12-distance02)/2);
        tree.put(new IntPair(newNode, dissimilarities.get(2).get(0).intValue()), (distance02+distance12-distance01)/2);

//        System.out.println("step 1: " + step1);
//        System.out.println("step 2: " + step2);
//        System.out.println("step 3: " + step3);
//        System.out.println("step 4: " + step4);
//        System.out.println("step 5: " + step5);
        return tree;
    }

    public static void main(String[] args) {
        String[] leaves = new String[5];
        double[][] distances = new double[5][5];

        for (int i = 0; i < 5; i++) {
            leaves[i] = "leaf " + i;
//            for (int j = 0; j < 5; j++) {
//                distances[i][j] = 1;
//            }
        }
        distances[0][0] = 0;
        distances[0][1] = 0.23;
        distances[1][0] = 0.23;
        distances[0][2] = 0.16;
        distances[2][0] = 0.16;
        distances[0][3] = 0.20;
        distances[3][0] = 0.20;
        distances[0][4] = 0.17;
        distances[4][0] = 0.17;

        distances[1][1] = 0;
        distances[1][2] = 0.23;
        distances[2][1] = 0.23;
        distances[1][3] = 0.17;
        distances[3][1] = 0.17;
        distances[1][4] = 0.24;
        distances[4][1] = 0.24;

        distances[2][2] = 0;
        distances[2][3] = 0.20;
        distances[3][2] = 0.20;
        distances[2][4] = 0.11;
        distances[4][2] = 0.11;

        distances[3][3] = 0;
        distances[3][4] = 0.21;
        distances[4][3] = 0.21;

        distances[4][4] = 0;

        HashMap<IntPair, Double> tree = new NJAlgorithm().run(leaves, distances);
        String[] names = {"A","B","C","D","E"};
        NewickMaker nm = new NewickMaker(names,tree);
        String result = nm.makeFromStartNode(7);
        System.out.println("RESULT\n" + result);
//        ForesterNewickParser np = new ForesterNewickParser();
//        Phylogeny p = np.parseNewickFile("distance_matrices//test.new");
//        np.displayPhylogeny(p);
    }
}
