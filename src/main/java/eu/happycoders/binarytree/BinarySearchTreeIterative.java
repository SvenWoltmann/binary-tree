package eu.happycoders.binarytree;

/**
 * An iterative binary search tree implementation with <code>int</code> keys.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class BinarySearchTreeIterative extends BinarySearchTree {

  /**
   * Searches iteratively for a node with the given search key.
   *
   * @param key the search key
   * @return the node or <code>null</code> if no node with the given search key exists
   */
  @Override
  public Node searchNode(int key) {
    Node node = root;
    while (node != null) {
      if (key == node.data) {
        return node;
      } else if (key < node.data) {
        node = node.left;
      } else {
        node = node.right;
      }
    }

    return null;
  }

  /**
   * Iteratively inserts a node with the given key.
   *
   * @param key the key of the node to be inserted
   */
  @Override
  public void insertNode(int key) {
    Node newNode = new Node(key);

    if (root == null) {
      root = newNode;
      return;
    }

    Node node = root;
    while (true) {
      // Traverse the tree to the left or right depending on the key
      if (key < node.data) {
        if (node.left != null) {
          // Left sub-tree exists --> follow
          node = node.left;
        } else {
          // Left sub-tree does not exist --> insert new node as left child
          node.left = newNode;
          newNode.parent = node;
          return;
        }
      } else if (key > node.data) {
        if (node.right != null) {
          // Right sub-tree exists --> follow
          node = node.right;
        } else {
          // Left sub-tree does not exist --> insert new node as right child
          node.right = newNode;
          newNode.parent = node;
          return;
        }
      } else {
        throw new IllegalArgumentException("BST already contains a node with key " + key);
      }
    }
  }

  /**
   * Iteratively deletes a node with the given key.
   *
   * @param key the key of the node to be deleted
   */
  @Override
  public void deleteNode(int key) {
    Node node = root;

    // Find the node to be deleted
    while (node != null && node.data != key) {
      // Traverse the tree to the left or right depending on the key
      if (key < node.data) {
        node = node.left;
      } else {
        node = node.right;
      }
    }

    // Node not found?
    if (node == null) {
      return;
    }

    // At this point, "node" is the node to be deleted

    // Node has at most one child --> replace node by its single child
    if (node.left == null || node.right == null) {
      deleteNodeWithZeroOrOneChild(key, node);
    }

    // Node has two children
    else {
      deleteNodeWithTwoChildren(node);
    }
  }

  private void deleteNodeWithZeroOrOneChild(int key, Node node) {
    Node singleChild = node.left != null ? node.left : node.right;

    if (node == root) {
      root = singleChild;
    } else if (key < node.parent.data) {
      node.parent.left = singleChild;
    } else {
      node.parent.right = singleChild;
    }

    if (singleChild != null) {
      singleChild.parent = node.parent;
    }
  }

  private void deleteNodeWithTwoChildren(Node node) {
    // Find minimum node of right subtree ("inorder successor" of current node)
    Node inOrderSuccessor = findMinimum(node.right);

    // Copy inorder successor's data to current node
    node.data = inOrderSuccessor.data;

    // Delete inorder successor

    // Case a) Inorder successor is the deleted node's right child
    if (inOrderSuccessor == node.right) {
      // --> Replace right child with inorder successor's right child
      node.right = inOrderSuccessor.right;
    }

    // Case b) Inorder successor is further down, meaning, it's a left child
    else {
      // --> Replace inorder successor's parent's left child
      //     with inorder successor's right child
      inOrderSuccessor.parent.left = inOrderSuccessor.right;
    }

    // Set parent relationship if inorder successor's right child exists
    if (inOrderSuccessor.right != null) {
      inOrderSuccessor.right.parent = inOrderSuccessor.parent;
    }
  }

  private Node findMinimum(Node node) {
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }
}
