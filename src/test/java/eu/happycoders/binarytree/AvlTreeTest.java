package eu.happycoders.binarytree;

class AvlTreeTest extends BinarySearchTreeTest {

  @Override
  protected BinarySearchTree createBST() {
    return new AvlTree();
  }

  @Override
  protected void assertSpecificTreeInvariants(BinarySearchTree tree) {
    validateAVLInvariant(tree.getRoot());
  }

  private void validateAVLInvariant(Node node) {
    if (node == null) return;

    int leftHeight = node.left != null ? node.left.height : -1;
    int rightHeight = node.right != null ? node.right.height : -1;

    validateHeight(node, leftHeight, rightHeight);
    validateBalanceFactor(node, leftHeight, rightHeight);

    // Validate AVL invariant for children (recursion)
    validateAVLInvariant(node.left);
    validateAVLInvariant(node.right);
  }

  private void validateHeight(Node node, int leftHeight, int rightHeight) {
    int expectedHeight = 1 + Math.max(leftHeight, rightHeight);
    if (node.height != expectedHeight) {
      throw new AssertionError(
          "Height of node %d is %d (expected: %d)"
              .formatted(node.data, node.height, expectedHeight));
    }
  }

  private void validateBalanceFactor(Node node, int leftHeight, int rightHeight) {
    int bf = rightHeight - leftHeight;
    if (bf < -1 || bf > 1) {
      throw new AssertionError(
          "Balance factor (bf) of node %d is %d (expected: -1 <= bf <= 1)"
              .formatted(node.data, bf));
    }
  }
}
