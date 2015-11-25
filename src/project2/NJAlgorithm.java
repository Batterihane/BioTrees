package project2;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class NJAlgorithm {

    public NJAlgorithm(){
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
        while(dissimilarities.size() > 3){
            numberOfTaxa = dissimilarities.size();

            // Compute r list
            double[] r = new double[numberOfTaxa];
            for (int i = 0; i < numberOfTaxa; i++) {
                int distanceSum = 0;
                List<Double> dissimilarityList = dissimilarities.get(i);
                for (int j = 1; j < dissimilarityList.size(); j++) {
                    distanceSum += dissimilarityList.get(j);
                }
                r[i] = distanceSum / (numberOfTaxa-2);
            }

            // Compute N matrix
            double[][] approxDistances = new double[numberOfTaxa][numberOfTaxa];
            for (int i = 0; i < numberOfTaxa; i++) {
                for (int j = 0; j < numberOfTaxa; j++) {
                    approxDistances[i][j] = dissimilarities.get(i).get(j+1) - (r[i] + r[j]);
                }
            }

            // Find neighbours
            int neighbour1Position = 0, neighbour2Position = 0;
            double smallestApproxDistance = Double.MAX_VALUE;
            for (int i = 0; i < numberOfTaxa; i++) {
                for (int j = 0; j < numberOfTaxa; j++) {
                    if(i==j) continue;
                    double approxDistance = approxDistances[i][j];
                    if(approxDistance < smallestApproxDistance){
                        smallestApproxDistance = approxDistance;
                        neighbour1Position = i;
                        neighbour2Position = j;
                    }
                }
            }

            // Add new edges to tree
            int newNode = numberOfNodes;
            double neighbourDistance = dissimilarities.get(neighbour1Position).get(neighbour2Position);
            int neighbour1NamePos = dissimilarities.get(neighbour1Position).get(0).intValue();
            int neighbour2NamePos = dissimilarities.get(neighbour2Position).get(0).intValue();
            tree.put(new IntPair(newNode, neighbour1NamePos), (neighbourDistance + r[neighbour1Position] - r[neighbour2Position]));
            tree.put(new IntPair(newNode, neighbour2NamePos), (neighbourDistance + r[neighbour2Position] - r[neighbour1Position]));
            numberOfNodes++;
            System.out.println("(" + neighbour1NamePos + "," + neighbour2NamePos + "," + newNode + ")");

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

            dissimilarities.set(neighbour1Position, newNodeDistances);
            dissimilarities.remove(neighbour2Position);
            for (int i = 0; i < dissimilarities.size(); i++) {
                if(i == neighbour1Position) continue;
                dissimilarities.get(i).set(neighbour1Position+1, newNodeDistances.get(i));
            }
            for (List<Double> l : dissimilarities){
                l.remove(neighbour2Position+1);
            }
        }

        // Combine tree
        int newNode = numberOfNodes;
        double distance01 = dissimilarities.get(0).get(2);
        double distance02 = dissimilarities.get(0).get(3);
        double distance12 = dissimilarities.get(1).get(3);
        tree.put(new IntPair(newNode, dissimilarities.get(0).get(0).intValue()), (distance01+distance02-distance12)/2);
        tree.put(new IntPair(newNode, dissimilarities.get(1).get(0).intValue()), (distance01+distance12-distance02)/2);
        tree.put(new IntPair(newNode, dissimilarities.get(2).get(0).intValue()), (distance02+distance12-distance01)/2);

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
        distances[0][1] = 2;
        distances[1][0] = 2;
        distances[0][2] = 3;
        distances[2][0] = 3;
        distances[0][3] = 4;
        distances[3][0] = 4;
        distances[0][4] = 4;
        distances[4][0] = 4;

        distances[1][1] = 0;
        distances[1][2] = 3;
        distances[2][1] = 3;
        distances[1][3] = 4;
        distances[3][1] = 4;
        distances[1][4] = 4;
        distances[4][1] = 4;

        distances[2][2] = 0;
        distances[2][3] = 3;
        distances[3][2] = 3;
        distances[2][4] = 3;
        distances[4][2] = 3;

        distances[3][3] = 0;
        distances[3][4] = 2;
        distances[4][3] = 2;

        distances[4][4] = 0;

        HashMap<IntPair, Double> tree = new NJAlgorithm().run(leaves, distances);
        for(IntPair p: tree.keySet()){
            System.out.println(p + "; " + tree.get(p));
        }
    }
}
