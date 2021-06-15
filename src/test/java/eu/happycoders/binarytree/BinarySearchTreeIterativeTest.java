package eu.happycoders.binarytree;

class BinarySearchTreeIterativeTest extends BinarySearchTreeTest {

  @Override
  protected BinarySearchTree createBST() {
    return new BinarySearchTreeIterative();
  }
}
