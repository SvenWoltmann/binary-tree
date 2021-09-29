package eu.happycoders.binarytree;

import static eu.happycoders.binarytree.BinaryTreeAssert.assertThatTree;
import static eu.happycoders.binarytree.RedBlackTree.BLACK;
import static eu.happycoders.binarytree.RedBlackTree.RED;

class RedBlackTreeTest extends BinarySearchTreeTest {

  @Override
  protected BinarySearchTree createBST() {
    return new RedBlackTree();
  }

  @Override
  protected void assertSpecificTreeInvariants(BinarySearchTree tree) {
    assertThatTree(tree).hasAllParentsSetCorrectly();

    Node root = tree.getRoot();
    if (root != null) {
      // Check rule 2?
      // if (root.color == RED) {
      //   throw new AssertionError("Root is red");
      // }
      validateRedBlackInvariant(null, root, 0, new MutableValueContainer());
    }
  }

  private void validateRedBlackInvariant(
      Node parent, Node node, int blackHeightThisPath, MutableValueContainer blackHeightFirstPath) {
    // NIL node reached?
    if (node == null) {
      // We're not counting the NIL nodes on our path. That makes each path 1 black node shorter.
      // That makes no difference in comparing the black-heights of every path.

      // First NIL node?
      if (blackHeightFirstPath.value == null) {
        blackHeightFirstPath.value = blackHeightThisPath;
      } else if (blackHeightFirstPath.value != blackHeightThisPath) {
        throw new AssertionError(
            "Black-height rule violated (blackHeightFirstPath.value = "
                + blackHeightFirstPath.value
                + "; blackHeightThisPath = "
                + blackHeightThisPath
                + ")");
      }
      return;
    }

    // Count black nodes on path
    if (node.color == BLACK) {
      blackHeightThisPath++;
    }

    // Red node must not have a red parent
    else if (parent != null && parent.color == RED) {
      throw new AssertionError(
          "Node " + node.data + " and its parent " + parent.data + " are both red");
    }

    // We're using the simplified approach of not forcing the root to be black
    validateRedBlackInvariant(node, node.left, blackHeightThisPath, blackHeightFirstPath);
    validateRedBlackInvariant(node, node.right, blackHeightThisPath, blackHeightFirstPath);
  }

  private static class MutableValueContainer {
    private Integer value;
  }
}
