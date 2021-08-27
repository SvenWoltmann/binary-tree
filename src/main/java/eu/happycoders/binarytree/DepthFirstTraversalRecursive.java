package eu.happycoders.binarytree;

/**
 * Recursive depth-first (DFS) traversal on a binary tree.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public final class DepthFirstTraversalRecursive implements DepthFirstTraversal {

  private final BinaryTree tree;

  public DepthFirstTraversalRecursive(BinaryTree tree) {
    this.tree = tree;
  }

  @Override
  public void traversePreOrder(NodeVisitor visitor) {
    traversePreOrder(tree.getRoot(), visitor);
  }

  /**
   * Traverses the tree in pre-order and calls the {@link NodeVisitor#visit(Node)} method on each
   * node.
   *
   * @param node the node
   * @param visitor the visitor
   */
  public static void traversePreOrder(Node node, NodeVisitor visitor) {
    if (node == null) {
      return;
    }
    visitor.visit(node);
    traversePreOrder(node.left, visitor);
    traversePreOrder(node.right, visitor);
  }

  @Override
  public void traversePostOrder(NodeVisitor visitor) {
    traversePostOrder(tree.getRoot(), visitor);
  }

  /**
   * Traverses the tree in post-order and calls the {@link NodeVisitor#visit(Node)} method on each
   * node.
   *
   * @param node the node
   * @param visitor the visitor
   */
  public static void traversePostOrder(Node node, NodeVisitor visitor) {
    if (node == null) {
      return;
    }
    traversePostOrder(node.left, visitor);
    traversePostOrder(node.right, visitor);
    visitor.visit(node);
  }

  @Override
  public void traverseInOrder(NodeVisitor visitor) {
    traverseInOrder(tree.getRoot(), visitor);
  }

  /**
   * Traverses the tree in-order and calls the {@link NodeVisitor#visit(Node)} method on each node.
   *
   * @param node the node
   * @param visitor the visitor
   */
  public static void traverseInOrder(Node node, NodeVisitor visitor) {
    if (node == null) {
      return;
    }
    traverseInOrder(node.left, visitor);
    visitor.visit(node);
    traverseInOrder(node.right, visitor);
  }

  @Override
  public void traverseReverseInOrder(NodeVisitor visitor) {
    traverseReverseInOrder(tree.getRoot(), visitor);
  }

  /**
   * Traverses the tree reverse in-order and calls the {@link NodeVisitor#visit(Node)} method on
   * each node.
   *
   * @param node the node
   * @param visitor the visitor
   */
  public static void traverseReverseInOrder(Node node, NodeVisitor visitor) {
    if (node == null) {
      return;
    }
    traverseReverseInOrder(node.right, visitor);
    visitor.visit(node);
    traverseReverseInOrder(node.left, visitor);
  }
}
