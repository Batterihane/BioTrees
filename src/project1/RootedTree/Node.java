package project1.RootedTree;

import java.util.List;

/**
 * Created by Thomas on 11-11-2015.
 */
public interface Node {
    Node getParent();
    void setParent(Node newParent);
    List<String> getLeafNamesDepthFirst();
    int countLeaves();
}
