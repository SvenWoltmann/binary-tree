package eu.happycoders.binary_tree.example;

import eu.happycoders.binary_tree.BinaryTree;
import eu.happycoders.binary_tree.Node;
import eu.happycoders.binary_tree.NodeVisitor;
import eu.happycoders.binary_tree.SimpleBinaryTree;
import eu.happycoders.binary_tree.SimpleBinaryTree.Side;
import eu.happycoders.binary_tree.TraversalDepthFirstIterative;

public class Example1 {

  private static final NodeVisitor visitor = node -> System.out.print(node.getData() + " ");

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
    System.out.println("Pre-order (recursive):");
    tree.traversePreOrder(visitor);

    System.out.println("\n\nPre-order (iterative):");
    TraversalDepthFirstIterative.traversePreOrder(tree.getRoot(), visitor);

    System.out.println("\n\nPost-order (recursive):");
    tree.traversePostOrder(visitor);

    System.out.println("\n\nPost-order (iterative):");
    TraversalDepthFirstIterative.traversePostOrder(tree.getRoot(), visitor);

    System.out.println("\n\nIn-order (recursive):");
    tree.traverseInOrder(visitor);

    System.out.println("\n\nIn-order (iterative):");
    TraversalDepthFirstIterative.traverseInOrder(tree.getRoot(), visitor);

    System.out.println("\n\nReverse in-order (recursive):");
    tree.traverseReverseInOrder(visitor);

    System.out.println("\n\nReverse in-order (iterative):");
    TraversalDepthFirstIterative.traverseReverseInOrder(tree.getRoot(), visitor);

    System.out.println("\n\nLevel-order:");
    tree.traverseLevelOrder(visitor);
  }

  private static void deleteSomeNodes(TreeWithNodesToDelete treeWithNodes) {
    SimpleBinaryTree tree = treeWithNodes.tree;

    // Case A - delete a leaf
    tree.deleteNode(treeWithNodes.node13);

    System.out.println("\n\nDeleted node 13 --> In-order:");
    tree.traverseInOrder(visitor);

    // Case B - delete an internal node with one child (a.k.a. half leaf)
    tree.deleteNode(treeWithNodes.node1);

    System.out.println("\n\nDeleted node 1 --> In-order:");
    tree.traverseInOrder(visitor);

    // Case C - delete an internal node with two children
    tree.deleteNode(treeWithNodes.node16);

    System.out.println("\n\nDeleted node 16 --> In-order:");
    tree.traverseInOrder(visitor);
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
