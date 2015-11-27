package project2;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by Nikolaj on 25-11-2015.
 */
public class stepOneWorker implements Runnable
{
    private int i;
    private double[] distances;
    private int j;
    private List<Double> dissimilarityList;
    private int numberOfTaxa;
    private List<List<Double>> dissimilarities;
    private String command;
    NJConcurrent instance;

    public stepOneWorker(int i, int j, List<List<Double>> dissimilarities, NJConcurrent instance)
    {
        this.i = i;
        this.j = j;
        this.dissimilarities = dissimilarities;
        command = "N";
        this.instance = instance;
    }

    public stepOneWorker(int i, double[] distances, int numberOfTaxa, NJConcurrent instance)
    {
        this.i = i;
        this.distances = distances;
        this.numberOfTaxa = numberOfTaxa;
        command = "FindNeighbors";
        this.instance = instance;
    }

    public stepOneWorker(int i, List<Double> dissimilarityList, int numberOfTaxa, NJConcurrent instance)
    {
        this.i = i;
        this.dissimilarityList = dissimilarityList;
        this.numberOfTaxa = numberOfTaxa;
        command = "R";
        this.instance = instance;
    }

    @Override
    public void run()
    {
        switch (command) {
            case "R":
                Double result = computeR(i, numberOfTaxa);
                instance.addToRArray(i, result);
                break;
            case "FindNeighbors":
                findAndCommitNeighborCandidates(i, distances);
                break;
            case "N":
                Double N = computeN(i, j);
                //insert somewhere
                break;
        }
        //Compute n_ij, and insert into dissimilarities (handle racing conditions through locks)
    }

    public Double computeR(int i, int numberOfTaxa) {
        Double result;
        double distanceSum = 0;
        for (int j = 1; j < dissimilarityList.size(); j++) {
            distanceSum += dissimilarityList.get(j);
        }
        result = distanceSum / (double)(numberOfTaxa-2);

        return result;
    }

    public void findAndCommitNeighborCandidates(int i, double[] distances)
    {
        int neighbour1Position = 0;
        int neighbour2Position = 0;
        double approxDistance;
        double smallestApproxDistance = Double.MAX_VALUE;

        for (int j = 0; j < numberOfTaxa; j++)
        {
            if(i==j) continue;

            approxDistance = distances[j];
            if(approxDistance < smallestApproxDistance){
                smallestApproxDistance = approxDistance;
                neighbour1Position = i;
                neighbour2Position = j;
            }
        }

        instance.commitNeighborPair(neighbour1Position, neighbour2Position, smallestApproxDistance);
        /*

            neighbour1Position = 0;
            neighbour2Position = 0;
            double smallestApproxDistance = Double.MAX_VALUE;

            for (int i = 0; i < numberOfTaxa; i++) {

                //i
                //approx[i]
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
         */

    }


    public Double computeN(int i, int j) {
        return null;
    }

}
