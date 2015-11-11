package project1.RootedTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 11-11-2015.
 */
public class Leaf implements Node {

    private String name;
    private Node parent;

    public Leaf(String name){

        this.name = name;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public List<String> getLeafNamesDepthFirst() {
        ArrayList<String> result = new ArrayList<>();
        result.add(name);
        return result;
    }

    public String getName() {
        return name;
    }
}
