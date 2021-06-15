package eu.happycoders.binarytree;

/**
 * A binary tree implementation with facade methods for recursive depth-first traversal and
 * iterative breadth-first traversal.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class BinaryTree {

  protected Node root;

  /**
   * Returns the binary tree's root node.
   *
   * @return the root node
   */
  public Node getRoot() {
    return root;
  }

  /**
   * Traverses the tree in pre-order and calls the {@link NodeVisitor#visit(Node)} method on each
   * node.
   *
   * @param visitor the visitor
   */
  public void traversePreOrder(NodeVisitor visitor) {
    TraversalDepthFirstRecursive.traversePreOrder(root, visitor);
  }

  /**
   * Traverses the tree in post-order and calls the {@link NodeVisitor#visit(Node)} method on each
   * node.
   *
   * @param visitor the visitor
   */
  public void traversePostOrder(NodeVisitor visitor) {
    TraversalDepthFirstRecursive.traversePostOrder(root, visitor);
  }

  /**
   * Traverses the tree in-order and calls the {@link NodeVisitor#visit(Node)} method on each node.
   *
   * @param visitor the visitor
   */
  public void traverseInOrder(NodeVisitor visitor) {
    TraversalDepthFirstRecursive.traverseInOrder(root, visitor);
  }

  /**
   * Traverses the tree reverse in-order and calls the {@link NodeVisitor#visit(Node)} method on
   * each node.
   *
   * @param visitor the visitor
   */
  public void traverseReverseInOrder(NodeVisitor visitor) {
    TraversalDepthFirstRecursive.traverseReverseInOrder(root, visitor);
  }

  /**
   * Traverses the tree level-order and calls the {@link NodeVisitor#visit(Node)} method on each
   * node.
   *
   * @param visitor the visitor
   */
  public void traverseLevelOrder(NodeVisitor visitor) {
    TraversalBreadthFirst.traverseLevelOrder(root, visitor);
  }
}
