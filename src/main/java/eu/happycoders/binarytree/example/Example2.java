package eu.happycoders.binarytree.example;

import eu.happycoders.binarytree.BinaryTree;
import eu.happycoders.binarytree.BreadthFirstTraversal;
import eu.happycoders.binarytree.DepthFirstTraversalRecursive;
import eu.happycoders.binarytree.Node;
import eu.happycoders.binarytree.NodeVisitor;
import eu.happycoders.binarytree.SimpleBinaryTree;
import eu.happycoders.binarytree.SimpleBinaryTree.Side;

@SuppressWarnings({ //
  "squid:S106", // using stdout in this example
  "squid:S1481", // using unused local variables in this example
  "squid:S1854" // using useless assignments in this example
})
public class Example2 {

  private static final NodeVisitor VISITOR = node -> System.out.print(node.getData() + " ");

  public static void main(String[] args) {
    SimpleBinaryTree tree = createSampleTree();
    traverseTreeInVariousWays(tree);
  }

  private static SimpleBinaryTree createSampleTree() {
    SimpleBinaryTree tree = new SimpleBinaryTree();

    Node root = tree.insertRoot(3);

    // Left sub-tree of root
    Node node1 = tree.insertNode(1, root, Side.LEFT);
    Node node13 = tree.insertNode(13, node1, Side.LEFT);

    // Right sub-tree of root
    Node node10 = tree.insertNode(10, root, Side.RIGHT);
    Node node11 = tree.insertNode(11, node10, Side.LEFT);
    Node node16 = tree.insertNode(16, node10, Side.RIGHT);
    Node node15 = tree.insertNode(15, node16, Side.LEFT);
    Node node2 = tree.insertNode(2, node16, Side.RIGHT);

    return tree;
  }

  private static void traverseTreeInVariousWays(BinaryTree tree) {
    var depthFirst = new DepthFirstTraversalRecursive(tree);
    var breadthFirst = new BreadthFirstTraversal(tree);

    System.out.println("Pre-order (recursive):");
    depthFirst.traversePreOrder(VISITOR);

    System.out.println("\n\nPost-order (recursive):");
    depthFirst.traversePostOrder(VISITOR);

    System.out.println("\n\nIn-order (recursive):");
    depthFirst.traverseInOrder(VISITOR);

    System.out.println("\n\nReverse in-order (recursive):");
    depthFirst.traverseReverseInOrder(VISITOR);

    System.out.println("\n\nLevel-order:");
    breadthFirst.traverseLevelOrder(VISITOR);
  }
}
