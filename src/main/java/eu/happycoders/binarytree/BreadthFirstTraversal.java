package eu.happycoders.binarytree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Iterative breadth-first (BFS) traversal on a binary tree.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public final class BreadthFirstTraversal {

  private final BinaryTree tree;

  public BreadthFirstTraversal(BinaryTree tree) {
    this.tree = tree;
  }

  /**
   * Traverses the tree level-order and calls the {@link NodeVisitor#visit(Node)} method on each
   * node.
   *
   * @param visitor the visitor
   */
  public void traverseLevelOrder(NodeVisitor visitor) {
    traverseLevelOrder(tree.getRoot(), visitor);
  }

  /**
   * Traverses the tree level-order and calls the {@link NodeVisitor#visit(Node)} method on each
   * node.
   *
   * @param root the root node
   * @param visitor the visitor
   */
  public static void traverseLevelOrder(Node root, NodeVisitor visitor) {
    if (root == null) {
      return;
    }

    Queue<Node> queue = new ArrayDeque<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      Node node = queue.poll();
      visitor.visit(node);

      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
  }
}
