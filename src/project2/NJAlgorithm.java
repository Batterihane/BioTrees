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

    public HashMap<IntPair, Integer> run(String[] leaves, double[][] distances){
        int numberOfLeaves = leaves.length;
        int numberOfNodes = numberOfLeaves;
        HashMap<IntPair, Double> tree = new HashMap<>();
        List<List<Double>> dissimilarities = new ArrayList<>();

        // Create dissimilarity matrix
        for(double[] distanceRow : distances){
            List<Double> dissimilarityRow = new ArrayList<>();
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
                for(double d : dissimilarities.get(i)){
                    distanceSum += d;
                }
                r[i] = distanceSum / (numberOfTaxa-2);
            }

            // Compute N matrix
            double[][] approxDistances = new double[numberOfTaxa][numberOfTaxa];
            for (int i = 0; i < numberOfTaxa; i++) {
                for (int j = 0; j < numberOfTaxa; j++) {
                    approxDistances[i][j] = dissimilarities.get(i).get(j) - (r[i] + r[j]);
                }
            }

            // Find neighbours
            int neighbour1 = 0, neighbour2 = 0;
            double smallestApproxDistance = Double.MAX_VALUE;
            for (int i = 0; i < numberOfTaxa; i++) {
                for (int j = 0; j < numberOfTaxa; j++) {
                    double approxDistance = approxDistances[i][j];
                    if(approxDistance < smallestApproxDistance){
                        smallestApproxDistance = approxDistance;
                        neighbour1 = i;
                        neighbour2 = j;
                    }
                }
            }

            // Add new edges to tree
            int newNode = numberOfNodes;
            double neighbourDistance = dissimilarities.get(neighbour1).get(neighbour2);
            tree.put(new IntPair(newNode, neighbour1), (neighbourDistance + r[neighbour1] - r[neighbour2]));
            tree.put(new IntPair(newNode, neighbour2), (neighbourDistance + r[neighbour2] - r[neighbour1]));
            numberOfNodes++;

            // Update dissimilarity matrix
            ArrayList<Object> newDistances = new ArrayList<>();
            for (int i = 0; i < numberOfTaxa - 1; i++) {

            }

        }





        throw new NotImplementedException();
    }

    public static void main(String[] args) {
        String[] leaves = new String[5];
        double[][] distances = new double[5][5];

        for (int i = 0; i < 5; i++) {
            leaves[i] = "leaf " + i;
            for (int j = 0; j < 5; j++) {
                distances[i][j] = 1;
            }
        }
        new NJAlgorithm().run(leaves, distances);
    }
}
