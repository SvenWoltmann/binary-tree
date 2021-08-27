package eu.happycoders.binarytree;

/**
 * A recursive binary search tree implementation with <code>int</code> keys.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class BinarySearchTreeRecursive extends BaseBinaryTree implements BinarySearchTree {

  @Override
  public Node searchNode(int key) {
    return searchNode(key, root);
  }

  private Node searchNode(int key, Node node) {
    if (node == null) {
      return null;
    }

    if (key == node.data) {
      return node;
    } else if (key < node.data) {
      return searchNode(key, node.left);
    } else {
      return searchNode(key, node.right);
    }
  }

  @Override
  public void insertNode(int key) {
    root = insertNode(key, root);
  }

  Node insertNode(int key, Node node) {
    // No node at current position --> store new node at current position
    if (node == null) {
      node = new Node(key);
    }

    // Otherwise, traverse the tree to the left or right depending on the key
    else if (key < node.data) {
      node.left = insertNode(key, node.left);
    } else if (key > node.data) {
      node.right = insertNode(key, node.right);
    } else {
      throw new IllegalArgumentException("BST already contains a node with key " + key);
    }

    return node;
  }

  @Override
  public void deleteNode(int key) {
    root = deleteNode(key, root);
  }

  Node deleteNode(int key, Node node) {
    // No node at current position --> go up the recursion
    if (node == null) {
      return null;
    }

    // Traverse the tree to the left or right depending on the key
    if (key < node.data) {
      node.left = deleteNode(key, node.left);
    } else if (key > node.data) {
      node.right = deleteNode(key, node.right);
    }

    // At this point, "node" is the node to be deleted

    // Node has no children --> just delete it
    else if (node.left == null && node.right == null) {
      node = null;
    }

    // Node has only one child --> replace node by its single child
    else if (node.left == null) {
      node = node.right;
    } else if (node.right == null) {
      node = node.left;
    }

    // Node has two children
    else {
      deleteNodeWithTwoChildren(node);
    }

    return node;
  }

  private void deleteNodeWithTwoChildren(Node node) {
    // Find minimum node of right subtree ("inorder successor" of current node)
    Node inOrderSuccessor = findMinimum(node.right);

    // Copy inorder successor's data to current node
    node.data = inOrderSuccessor.data;

    // Delete inorder successor recursively
    node.right = deleteNode(inOrderSuccessor.data, node.right);
  }

  private Node findMinimum(Node node) {
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }
}
