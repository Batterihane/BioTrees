package project2;

import java.util.List;

/**
 * Created by Nikolaj on 25-11-2015.
 */
public class stepOneWorker implements Runnable
{
    private int i;
    private int j;
    private List<Double> dissimilarityList;
    private Double[][] dissimilarities;
    private String command;

    public stepOneWorker(int i, int j, Double[][] dissimilarities)
    {
        this.i = i;
        this.j = j;
        this.dissimilarities = dissimilarities;
        command = "N";
    }

    public stepOneWorker(int i, List<Double> dissimilarityList)
    {
        this.i = i;
        this.dissimilarityList = dissimilarityList;
        command = "R";
    }

    @Override
    public void run()
    {
        switch (command) {
            case "R":
                Double R = computeR(i);
                //insert somewhere
                break;
            case "N":
                Double N = computeN(i, j);
                //insert somewhere
                break;
        }

        //Compute n_ij, and insert into dissimilarities (handle racing conditions through locks)
    }

    public Double computeR(int i) {
        return null;
    }

    public Double computeN(int i, int j) {
        return null;
    }



}
