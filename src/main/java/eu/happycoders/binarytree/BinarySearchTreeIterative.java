package eu.happycoders.binarytree;

/**
 * An iterative binary search tree implementation with <code>int</code> keys.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class BinarySearchTreeIterative extends BaseBinaryTree implements BinarySearchTree {

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
          return;
        }
      } else if (key > node.data) {
        if (node.right != null) {
          // Right sub-tree exists --> follow
          node = node.right;
        } else {
          // Right sub-tree does not exist --> insert new node as right child
          node.right = newNode;
          return;
        }
      } else {
        throw new IllegalArgumentException("BST already contains a node with key " + key);
      }
    }
  }

  @Override
  @SuppressWarnings("squid:S2259") // parent won't be null as it's used only if node != root
  public void deleteNode(int key) {
    Node node = root;
    Node parent = null;

    // Find the node to be deleted
    while (node != null && node.data != key) {
      // Traverse the tree to the left or right depending on the key
      parent = node;
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
      deleteNodeWithZeroOrOneChild(key, node, parent);
    }

    // Node has two children
    else {
      deleteNodeWithTwoChildren(node);
    }
  }

  private void deleteNodeWithZeroOrOneChild(int key, Node node, Node parent) {
    Node singleChild = node.left != null ? node.left : node.right;

    if (node == root) {
      root = singleChild;
    } else if (key < parent.data) {
      parent.left = singleChild;
    } else {
      parent.right = singleChild;
    }
  }

  private void deleteNodeWithTwoChildren(Node node) {
    // Find minimum node of right subtree ("inorder successor" of current node)
    Node inOrderSuccessor = node.right;
    Node inOrderSuccessorParent = node;
    while (inOrderSuccessor.left != null) {
      inOrderSuccessorParent = inOrderSuccessor;
      inOrderSuccessor = inOrderSuccessor.left;
    }

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
      inOrderSuccessorParent.left = inOrderSuccessor.right;
    }
  }
}
