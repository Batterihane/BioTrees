package project1;

import newick.ParseException;
import org.forester.phylogeny.Phylogeny;
import org.forester.phylogeny.PhylogenyNode;
import project1.RootedTree.InternalNode;
import project1.RootedTree.Leaf;
import project1.RootedTree.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 11-11-2015.
 */
public class Rooter {
    private String rootLeaf;

    private PhylogenyNode findRootNode(Phylogeny tree){
        PhylogenyNode currentNode = tree.getRoot();
        PhylogenyNode child = currentNode.getDescendants().get(0);
        while (!child.getDescendants().isEmpty()){
            currentNode = child;
            child = child.getDescendants().get(0);
        }
        rootLeaf = child.getName();
        return currentNode;
    }

    public Node rootTrees(Phylogeny tree){
        PhylogenyNode root;
        if(rootLeaf == null)
            root = findRootNode(tree);
        else
            root = tree.getNode(rootLeaf).getParent();
        List<PhylogenyNode> children = root.getDescendants();
        children.remove(0);

        children.add(root.getParent());

        InternalNode result = new InternalNode();
        List<Node> rootedChildren = new ArrayList<>();
        for(PhylogenyNode child : children){
            Node rootedChild = rootSubTree(child, root);
            rootedChild.setParent(result);
            rootedChildren.add(rootedChild);
        }

        result.setChildren(rootedChildren);
        return result;
    }

    private Node rootSubTree(PhylogenyNode subTree, PhylogenyNode parent){
        List<PhylogenyNode> children = subTree.getDescendants();
        PhylogenyNode phylogenyParent = subTree.getParent();
        if(phylogenyParent != null)
            children.add(phylogenyParent);
        children.remove(parent);

        if(children.isEmpty()){
            Leaf resultNode = new Leaf(subTree.getName());
            return resultNode;
        }

        InternalNode resultNode = new InternalNode();
        List<Node> rootedChildren = new ArrayList<>();
        for(PhylogenyNode child : children){
            Node rootedChild = rootSubTree(child, subTree);
            rootedChild.setParent(resultNode);
            rootedChildren.add(rootedChild);
        }

        resultNode.setChildren(rootedChildren);
        return resultNode;
    }
}
