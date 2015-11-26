package project2;

import java.util.HashMap;
import java.util.Locale;
import java.util.spi.LocaleServiceProvider;

/**
 * Created by ballololz on 11/25/2015.
 */
public class NewickMaker {

    int noOfLeaves; //antal leaf nodes
    HashMap<IntPair, Double> map;
    String[] names;




    public NewickMaker(String[] names,HashMap<IntPair, Double> map){
        this.names = names;
        noOfLeaves = names.length;
        this.map=map;
    }

    public String make(){
        return makeFromStartNode(names.length);
    }

    public String makeFromStartNode(int startNode){

        int count = 0;
        int otherNodes[] = new int[3];

        for(IntPair ip : map.keySet()){
            if (ip.has(startNode)){
                int other = (ip.getLower() == startNode ? ip.getHigher():ip.getLower());
                otherNodes[count] = other;
                count++;
            }

        }
//        System.out.println(count==3 ? "count is 3. Awesome":"count is not 3, something is probably wrong");
        return String.format("(%s,%s,%s)",makeRec(otherNodes[0],startNode),makeRec(otherNodes[1],startNode),makeRec(otherNodes[2],startNode)) + ";";
    }
    public String makeRec(int node, int fromNode){
        if (node < noOfLeaves){ //return leaf node
            return String.format(Locale.ENGLISH,"'%s':%f",names[node],map.get(new IntPair(node, fromNode)));
        }   //return internal node

        int count = 0;
        int[] otherNodes = new int[2];
        for(IntPair ip : map.keySet()){
            if (ip.has(node)){
                int other = (ip.getLower() == node ? ip.getHigher():ip.getLower());
                if (other!=fromNode){
                    otherNodes[count] = other;
                    count++;
                }
            }

        }

        return String.format(Locale.ENGLISH,"(%s,%s):%f",makeRec(otherNodes[0],node),makeRec(otherNodes[1],node),map.get(new IntPair(node, fromNode)));
    }
}
