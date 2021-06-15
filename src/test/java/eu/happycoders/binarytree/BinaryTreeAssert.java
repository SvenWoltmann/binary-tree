package eu.happycoders.binarytree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

public class BinaryTreeAssert {

  private final BinaryTree tree;

  private BinaryTreeAssert(BinaryTree tree) {
    this.tree = tree;
  }

  public static BinaryTreeAssert assertThatTree(BinaryTree tree) {
    return new BinaryTreeAssert(tree);
  }

  public BinaryTreeAssert isValid() {
    if (!BinarySearchTreeValidator.isBstWithoutDuplicates(tree)) {
      throw new AssertionError("Tree is not a valid BST");
    }
    return this;
  }

  public BinaryTreeAssert hasKeysInGivenOrder(List<Integer> keys) {
    TestNodeVisitor visitor = new TestNodeVisitor();
    new DepthFirstTraversalRecursive(tree).traverseInOrder(visitor);
    assertThat(visitor.getDataList(), is(keys));
    return this;
  }

  public BinaryTreeAssert hasAllParentsSetCorrectly() {
    hasAllParentsSetCorrectly(null, tree.getRoot());
    return this;
  }

  private void hasAllParentsSetCorrectly(Node parent, Node node) {
    if (node == null) return;

    // Root must not have a parent
    if (node == tree.getRoot()) {
      if (node.parent != null) {
        throw new AssertionError("Not all parents set correctly: root must not have a parent");
      }
    }

    // All other nodes must have a parent
    else {
      if (node.parent == null) {
        throw new AssertionError(
            "Not all parents set correctly: node " + node.data + " has no parent");
      }

      if (node.parent != parent) {
        throw new AssertionError(
            "Not all parents set correctly: parent "
                + node.parent.data
                + " of node "
                + node.data
                + " isn't the expected parent "
                + parent.data);
      }
    }

    hasAllParentsSetCorrectly(node, node.left);
    hasAllParentsSetCorrectly(node, node.right);
  }
}
