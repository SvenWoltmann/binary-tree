package eu.happycoders.binarytree;

class DepthFirstTraversalIterativeTest extends DepthFirstTraversalTest {

  @Override
  DepthFirstTraversal getTraversal(BinaryTree tree) {
    return new DepthFirstTraversalIterative(tree);
  }
}
