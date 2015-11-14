package project1;

import org.forester.phylogeny.Phylogeny;
import project1.RootedTree.Leaf;
import project1.RootedTree.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Thomas on 14-11-2015.
 */
public class DayAlgorithm {
    private final Phylogeny phylogenyTree1;
    private final Phylogeny phylogenyTree2;
    private final Rooter rooter;
    private List<Pair<Integer, Integer>> treeIntervalList;
    private HashMap<String, Integer> nameMapping;

    public DayAlgorithm(Phylogeny phylogenyTree1, Phylogeny phylogenyTree2){
        rooter = new Rooter();
        this.phylogenyTree1 = phylogenyTree1;
        this.phylogenyTree2 = phylogenyTree2;
    }

    public int getSplitDistance(){
        Pair<Node, Node> rootedTrees = step1();
        step2and3(rootedTrees.getLeft());
        Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> intervalLists = step4(rootedTrees.getLeft(), rootedTrees.getRight());
        int result = step5(intervalLists, rootedTrees.getLeft().countLeaves());
        return result;
    }

    private Pair<Node, Node> step1(){
        Pair<Node, Node> result;
        Node tree1 = rooter.rootTrees(phylogenyTree1);
        Node tree2 = rooter.rootTrees(phylogenyTree2);
        result = new Pair<Node, Node>(tree1, tree2);
        return result;
    }

    private void step2and3(Node tree){
        HashMap<String, Integer> result = new HashMap<>();
        List<String> leafNames = tree.getLeafNamesDepthFirst();
        for (int i = 0; i < leafNames.size(); i++) {
            result.put(leafNames.get(i), i);
        }
        nameMapping = result;
    }

    private Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> step4(Node tree1, Node tree2){
        List<Pair<Integer, Integer>> intervalList1, intervalList2;
        treeIntervalList = new ArrayList<>();
        getIntervalAndUpdateIntervalList(tree1, true);
        intervalList1 = treeIntervalList;
        treeIntervalList = new ArrayList<>();
        getIntervalAndUpdateIntervalList(tree2, false);
        intervalList2 = treeIntervalList;

        return new Pair<>(intervalList1, intervalList2);
    }

    private int step5(Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> intervalLists, int numberOfLeaves){
        List<Pair<Integer, Integer>> combinedIntervalList = intervalLists.getLeft();
        combinedIntervalList.addAll(intervalLists.getRight());
        List<Pair<Integer, Integer>> sortedIntervals = IntervalRadixSorter.sortList(combinedIntervalList, numberOfLeaves);
        List<Pair<Integer, Integer>> listofthingstoreturn = new ArrayList<>();

        for (int i = 0; i < sortedIntervals.size(); i++) {
            Pair<Integer, Integer> firstInterval = sortedIntervals.get(i);
            if(i+1 == sortedIntervals.size()){
                listofthingstoreturn.add(firstInterval);
                return listofthingstoreturn.size();
            }
            Pair<Integer, Integer> secondInterval = sortedIntervals.get(i + 1);
            if(firstInterval.getLeft() == -1 || !firstInterval.compareWithInterval(secondInterval)){
                listofthingstoreturn.add(firstInterval);
            }else{i++;}
        }
        return listofthingstoreturn.size();
    }

    private Pair<Pair<Integer, Integer>, Integer> getIntervalAndUpdateIntervalList(Node node, boolean isFirstTree){
        int lower = Integer.MAX_VALUE;
        int max = 0;
        int size = 0;
        for(Node child : node.getChildren()){
            int childLower;
            int childMax;
            if(child instanceof Leaf){
                childLower = childMax = nameMapping.get(((Leaf) child).getName());
                size++;
            }
            else{
                Pair<Pair<Integer, Integer>, Integer> currentInterval = getIntervalAndUpdateIntervalList(child, isFirstTree);
                childLower = currentInterval.getLeft().getLeft();
                childMax = currentInterval.getLeft().getRight();
                size += currentInterval.getRight();
            }
            if(childLower < lower)
                lower = childLower;
            if(childMax > max)
                max = childMax;
        }
        Pair<Integer, Integer> interval = new Pair<>(lower, max);
        if(isFirstTree || max-lower + 1 == size)
            treeIntervalList.add(interval);
        else
            treeIntervalList.add(new Pair<>(-1, -1));
        return new Pair<>(interval, size);
    }
}
