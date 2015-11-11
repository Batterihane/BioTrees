package project1;

import newick.NewickParser;
import newick.NewickParser.*;
import newick.ParseException;
import project1.RootedTree.InternalNode;
import project1.RootedTree.Leaf;
import project1.RootedTree.Node;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 11-11-2015.
 */
public class Rooter {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        NewickParser parser = new NewickParser(new FileInputStream(new File("trees//test.new")));
        Node tree = rootTree(parser.tree());
    }

    public static Node rootTree(TreeNode tree){
        TreeNode root = tree.getLeaves().get(0);
        List<TreeNode> children = root.getChildren().get(0).getChildren();
        children.remove(root);

        InternalNode result = new InternalNode();
        List<Node> rootedChildren = new ArrayList<>();
        for(TreeNode child : children){
            Node rootedChild = rootSubTree(child, root);
            rootedChild.setParent(result);
            rootedChildren.add(rootedChild);
        }

        result.setChildren(rootedChildren);
        return result;
    }

    private static Node rootSubTree(TreeNode subTree, TreeNode parent){
        List<TreeNode> children = subTree.getChildren();
        children.remove(parent);

        if(children.isEmpty()){
            Leaf resultNode = new Leaf(parent.getName());
            return resultNode;
        }

        InternalNode resultNode = new InternalNode();
        List<Node> rootedChildren = new ArrayList<>();
        for(TreeNode child : children){
            Node rootedChild = rootSubTree(child, subTree);
            rootedChild.setParent(resultNode);
            rootedChildren.add(rootedChild);
        }

        resultNode.setChildren(rootedChildren);
        return resultNode;
    }
}
