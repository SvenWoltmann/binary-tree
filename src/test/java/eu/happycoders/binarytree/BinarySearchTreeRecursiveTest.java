package eu.happycoders.binarytree;

class BinarySearchTreeRecursiveTest extends BinarySearchTreeTest {

  @Override
  protected BinarySearchTree createBST() {
    return new BinarySearchTreeRecursive();
  }
}
