package eu.happycoders.binary_tree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.jupiter.api.Test;

class TraversalDepthFirstIterativeTest {

  @Test
  void traversePreOrder_sampleTree_traversesTreeInPreOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traversePreOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.PRE_ORDER_VALUES));
  }

  @Test
  void traversePostOrder_sampleTree_traversesTreeInPostOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traversePostOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.POST_ORDER_VALUES));
  }

  @Test
  void traverseInOrder_sampleTree_traversesTreeInOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traverseInOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.IN_ORDER_VALUES));
  }

  @Test
  void traverseInOrder_sampleTree_traversesTreeInReverseOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traverseReverseInOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.REVERSE_IN_ORDER_VALUES));
  }
}
