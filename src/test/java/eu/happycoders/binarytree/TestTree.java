package eu.happycoders.binarytree;

public class TestTree extends BaseBinaryTree {

  public TestTree(Node root) {
    this.root = root;
  }

  public static TestTree emptyTree() {
    return new TestTree(null);
  }
}
