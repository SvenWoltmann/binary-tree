package eu.happycoders.binarytree;

import java.util.Objects;

/**
 * A binary tree implementation with trivial insert and delete operations.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class SimpleBinaryTree extends BaseBinaryTree {

  /** Indicates the child position (left, right) when inserting a node. */
  public enum Side {
    LEFT,
    RIGHT
  }

  /**
   * Inserts a node with the given value at the root. If the root has children, these will be moved
   * to the node to be inserted.
   *
   * @param value the value of the node to be inserted
   */
  public Node insertRoot(int value) {
    if (root != null) {
      throw new IllegalStateException("Root already defined");
    }

    root = new Node(value);

    return root;
  }

  /**
   * Inserts a node with the given value as left or right child of the given parent node. If the
   * chosen child already exists, the new node is inserted between the parent node and the child.
   *
   * @param value the value of the node to be inserted
   * @param parent the parent under which to insert the new node
   * @param side whether to insert the node as left or right child
   */
  public Node insertNode(int value, Node parent, Side side) {
    Objects.requireNonNull(parent);
    var node = new Node(value);

    node.parent = parent;

    switch (side) {
      case LEFT -> {
        if (parent.left != null) {
          node.left = parent.left;
          node.left.parent = node; // Is this correct?
        }
        parent.left = node;
      }

      case RIGHT -> {
        if (parent.right != null) {
          node.right = parent.right;
          node.right.parent = node; // Is this correct?
        }
        parent.right = node;
      }

      default -> throw new IllegalStateException();
    }

    return node;
  }

  /**
   * Deletes the specified node from the tree.
   *
   * @param node the node to be deleted
   */
  public void deleteNode(Node node) {
    if (node.parent == null && node != root) {
      throw new IllegalStateException("Node has no parent and is not root");
    }

    // Case A: Node has no children --> set node to null in parent
    if (node.left == null && node.right == null) {
      setParentsChild(node, null);
    }

    // Case B: Node has one child --> replace node by node's child in parent
    // Case B1: Node has only left child
    else if (node.right == null) {
      setParentsChild(node, node.left);
    }

    // Case B2: Node has only right child
    else if (node.left == null) {
      setParentsChild(node, node.right);
    }

    // Case C: Node has two children
    else {
      removeNodeWithTwoChildren(node);
    }

    // Remove all references from the deleted node
    node.parent = null;
    node.left = null;
    node.right = null;
  }

  /**
   * Removes a node with two children.
   *
   * <p>Strategy: Set left child tree to deleted position. Append right child tree to right-most
   * child of left tree.
   *
   * @param node the node to remove
   */
  private void removeNodeWithTwoChildren(Node node) {
    Node leftTree = node.left;
    Node rightTree = node.right;

    setParentsChild(node, leftTree);

    // find right-most child of left tree
    Node rightMostChildOfLeftTree = leftTree;
    while (rightMostChildOfLeftTree.right != null) {
      rightMostChildOfLeftTree = rightMostChildOfLeftTree.right;
    }

    // append right tree to right child
    rightMostChildOfLeftTree.right = rightTree;
    rightTree.parent = rightMostChildOfLeftTree;
  }

  private void setParentsChild(Node node, Node child) {
    // Node is root? Has no parent, set root reference instead
    if (node == root) {
      root = child;
      if (child != null) {
        child.parent = null;
      }
      return;
    }

    // Am I the left or right child of my parent?
    if (node.parent.left == node) {
      node.parent.left = child;
    } else if (node.parent.right == node) {
      node.parent.right = child;
    } else {
      throw new IllegalStateException(
          "Node "
              + node.data
              + " is neither a left nor a right child of its parent "
              + node.parent.data);
    }

    if (child != null) {
      child.parent = node.parent;
    }
  }
}
