package eu.happycoders.binarytree;

import static eu.happycoders.binarytree.BinaryTreeAssert.assertThatTree;

class BinarySearchTreeRecursiveWithParentTest extends BinarySearchTreeTest {

  @Override
  protected BinarySearchTree createBST() {
    return new BinarySearchTreeRecursiveWithParent();
  }

  @Override
  protected void assertSpecificTreeInvariants(BinarySearchTree tree) {
    assertThatTree(tree).hasAllParentsSetCorrectly();
  }
}
