package eu.happycoders.binarytree;

/**
 * Interface for depth-first (DFS) traversal on a binary tree.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public interface DepthFirstTraversal {

  /**
   * Traverses the tree in pre-order and calls the {@link NodeVisitor#visit(Node)} method on each
   * node.
   *
   * @param visitor the visitor
   */
  void traversePreOrder(NodeVisitor visitor);

  /**
   * Traverses the tree in post-order and calls the {@link NodeVisitor#visit(Node)} method on each
   * node.
   *
   * @param visitor the visitor
   */
  void traversePostOrder(NodeVisitor visitor);

  /**
   * Traverses the tree in-order and calls the {@link NodeVisitor#visit(Node)} method on each node.
   *
   * @param visitor the visitor
   */
  void traverseInOrder(NodeVisitor visitor);

  /**
   * Traverses the tree reverse in-order and calls the {@link NodeVisitor#visit(Node)} method on
   * each node.
   *
   * @param visitor the visitor
   */
  void traverseReverseInOrder(NodeVisitor visitor);
}
