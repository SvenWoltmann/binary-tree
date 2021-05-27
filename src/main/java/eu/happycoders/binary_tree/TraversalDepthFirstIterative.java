package eu.happycoders.binary_tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Iterative depth-first (DFS) traversal on a binary tree.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class TraversalDepthFirstIterative {

  private TraversalDepthFirstIterative() {}

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

    // Not using a java.util.Stack here.
    // See
    // https://www.happycoders.eu/java/queue-deque-stack-ultimate-guide/#Why_you_should_not_use_Stack
    Deque<Node> stack = new ArrayDeque<>();
    stack.push(node);

    while (!stack.isEmpty()) {
      node = stack.poll();
      visitor.visit(node);
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }
  }

  /**
   * Traverses the tree in post-order and calls the {@link NodeVisitor#visit(Node)} method on each
   * node.
   *
   * @param node the node
   * @param visitor the visitor
   */
  public static void traversePostOrder(Node node, NodeVisitor visitor) {
    // Not using a java.util.Stack here.
    // See
    // https://www.happycoders.eu/java/queue-deque-stack-ultimate-guide/#Why_you_should_not_use_Stack
    Deque<Node> stack = new ArrayDeque<>();

    Node lastVisitedNode = null;

    while (!stack.isEmpty() || node != null) {
      if (node != null) {
        stack.push(node);
        node = node.left;
      } else {
        Node topNode = stack.peek();
        if (topNode.right != null && lastVisitedNode != topNode.right) {
          node = topNode.right;
        } else {
          visitor.visit(topNode);
          lastVisitedNode = stack.poll();
        }
      }
    }
  }

  /**
   * Traverses the tree in-order and calls the {@link NodeVisitor#visit(Node)} method on each node.
   *
   * @param node the node
   * @param visitor the visitor
   */
  public static void traverseInOrder(Node node, NodeVisitor visitor) {
    // Not using a java.util.Stack here.
    // See
    // https://www.happycoders.eu/java/queue-deque-stack-ultimate-guide/#Why_you_should_not_use_Stack
    Deque<Node> stack = new ArrayDeque<>();

    while (!stack.isEmpty() || node != null) {
      if (node != null) {
        stack.push(node);
        node = node.left;
      } else {
        node = stack.pop();
        visitor.visit(node);
        node = node.right;
      }
    }
  }

  /**
   * Traverses the tree reverse in-order and calls the {@link NodeVisitor#visit(Node)} method on
   * each node.
   *
   * @param node the node
   * @param visitor the visitor
   */
  public static void traverseReverseInOrder(Node node, NodeVisitor visitor) {
    // Not using a java.util.Stack here.
    // See
    // https://www.happycoders.eu/java/queue-deque-stack-ultimate-guide/#Why_you_should_not_use_Stack
    Deque<Node> stack = new ArrayDeque<>();

    while (!stack.isEmpty() || node != null) {
      if (node != null) {
        stack.push(node);
        node = node.right;
      } else if (stack.peek() != null) {
        node = stack.pop();
        visitor.visit(node);
        node = node.left;
      }
    }
  }
}
