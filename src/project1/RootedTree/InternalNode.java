package project1.RootedTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 11-11-2015.
 */
public class InternalNode implements Node {

    private Node parent;
    private List<Node> children;

    public InternalNode(List<Node> children){
        this.children = children;
    }

    public InternalNode(){
        children = new ArrayList<>();
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node newParent){
        parent = newParent;
    }

    public void addChild(Node newChild){
        children.add(newChild);
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Override
    public List<String> getLeafNamesDepthFirst(){
        List<String> result = new ArrayList<>();
        for(Node child : children){
            result.addAll(child.getLeafNamesDepthFirst());
        }
        return result;
    }

    @Override
    public int countLeaves() {
        int result = 0;
        for(Node child : children){
            result += child.countLeaves();
        }
        return result;
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }
}
