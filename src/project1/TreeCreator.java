package project1;

import org.forester.phylogeny.Phylogeny;
import org.forester.phylogeny.PhylogenyNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Thomas on 15-11-2015.
 */
public class TreeCreator {
    public static void main(String[] args) {
        runTest(200);
    }

    public static void runTest(int numberOfLeaves){
        Phylogeny tree1 = createTree(numberOfLeaves);
        Phylogeny tree2 = createTree(numberOfLeaves);

        DayAlgorithm dayAlgorithm1 = new DayAlgorithm(tree1, tree2);
        System.out.println(dayAlgorithm1.getSplitDistance());
    }

    private static Phylogeny createTree(int size) {
        Phylogeny tree = new Phylogeny();
        PhylogenyNode root = new PhylogenyNode();
        tree.setRoot(root);

        PhylogenyNode lastNode = root;

        List<String> names = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            names.add("Leaf " + i);
        }
        String name;
        for (int i = 0; i < size; i++) {
            int nameIndex = new Random().nextInt(names.size());
            name = names.get(nameIndex);
            names.remove(nameIndex);
            boolean lastChild = i == size-1;
            lastNode = addChild(lastNode, name, lastChild);
        }
        return tree;
    }

    private static PhylogenyNode addChild(PhylogenyNode root, String childName, boolean lastChild) {
        PhylogenyNode child1 = new PhylogenyNode();
        child1.setName(childName);
        root.addAsChild(child1);
        if(!lastChild){
            PhylogenyNode child2 = new PhylogenyNode();
            root.addAsChild(child2);
            return child2;
        }
        return child1;
    }
}
