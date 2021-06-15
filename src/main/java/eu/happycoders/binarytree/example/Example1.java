package eu.happycoders.binarytree.example;

import eu.happycoders.binarytree.BinaryTree;
import eu.happycoders.binarytree.BreadthFirstTraversal;
import eu.happycoders.binarytree.DepthFirstTraversalIterative;
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
public class Example1 {

  private static final NodeVisitor VISITOR = node -> System.out.print(node.getData() + " ");

  public static void main(String[] args) {
    TreeWithNodesToDelete treeWithNodes = createSampleTree();
    traverseTreeInVariousWays(treeWithNodes.tree);
    deleteSomeNodes(treeWithNodes);
  }

  private static TreeWithNodesToDelete createSampleTree() {
    SimpleBinaryTree tree = new SimpleBinaryTree();

    Node root = tree.insertRoot(3);

    // Left sub-tree of root
    Node node1 = tree.insertNode(1, root, Side.LEFT);
    Node node13 = tree.insertNode(13, node1, Side.LEFT);
    Node node5 = tree.insertNode(5, node1, Side.RIGHT);
    Node node6 = tree.insertNode(6, node5, Side.LEFT);

    // Right sub-tree of root
    Node node10 = tree.insertNode(10, root, Side.RIGHT);
    Node node11 = tree.insertNode(11, node10, Side.LEFT);
    Node node16 = tree.insertNode(16, node10, Side.RIGHT);
    Node node15 = tree.insertNode(15, node16, Side.LEFT);
    Node node9 = tree.insertNode(9, node15, Side.LEFT);
    Node node4 = tree.insertNode(4, node15, Side.RIGHT);
    Node node2 = tree.insertNode(2, node16, Side.RIGHT);

    return new TreeWithNodesToDelete(tree, node13, node1, node16);
  }

  private static void traverseTreeInVariousWays(BinaryTree tree) {
    var depthFirstRecursive = new DepthFirstTraversalRecursive(tree);
    var depthFirstIterative = new DepthFirstTraversalIterative(tree);
    var breadthFirst = new BreadthFirstTraversal(tree);

    System.out.println("Pre-order (recursive):");
    depthFirstRecursive.traversePreOrder(VISITOR);

    System.out.println("\n\nPre-order (iterative):");
    depthFirstIterative.traversePreOrder(VISITOR);

    System.out.println("\n\nPost-order (recursive):");
    depthFirstRecursive.traversePostOrder(VISITOR);

    System.out.println("\n\nPost-order (iterative):");
    depthFirstIterative.traversePostOrder(VISITOR);

    System.out.println("\n\nIn-order (recursive):");
    depthFirstRecursive.traverseInOrder(VISITOR);

    System.out.println("\n\nIn-order (iterative):");
    depthFirstIterative.traverseInOrder(VISITOR);

    System.out.println("\n\nReverse in-order (recursive):");
    depthFirstRecursive.traverseReverseInOrder(VISITOR);

    System.out.println("\n\nReverse in-order (iterative):");
    depthFirstIterative.traverseReverseInOrder(VISITOR);

    System.out.println("\n\nLevel-order:");
    breadthFirst.traverseLevelOrder(VISITOR);
  }

  private static void deleteSomeNodes(TreeWithNodesToDelete treeWithNodes) {
    SimpleBinaryTree tree = treeWithNodes.tree;

    // Case A - delete a leaf
    tree.deleteNode(treeWithNodes.node13);

    System.out.println("\n\nDeleted node 13 --> In-order:");
    var depthFirst = new DepthFirstTraversalRecursive(tree);
    depthFirst.traverseInOrder(VISITOR);

    // Case B - delete an internal node with one child (a.k.a. half leaf)
    tree.deleteNode(treeWithNodes.node1);

    System.out.println("\n\nDeleted node 1 --> In-order:");
    depthFirst.traverseInOrder(VISITOR);

    // Case C - delete an internal node with two children
    tree.deleteNode(treeWithNodes.node16);

    System.out.println("\n\nDeleted node 16 --> In-order:");
    depthFirst.traverseInOrder(VISITOR);
  }

  private static class TreeWithNodesToDelete {
    final SimpleBinaryTree tree;
    final Node node13;
    final Node node1;
    final Node node16;

    private TreeWithNodesToDelete(SimpleBinaryTree tree, Node node13, Node node1, Node node16) {
      this.tree = tree;
      this.node13 = node13;
      this.node1 = node1;
      this.node16 = node16;
    }
  }
}
