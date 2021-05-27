package eu.happycoders.binary_tree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.jupiter.api.Test;

class TraversalDepthFirstRecursiveTest {

  @Test
  void traversePreOrder_sampleTree_traversesTreeInPreOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traversePreOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getValues(), contains(TestTree.PRE_ORDER_VALUES));
  }

  @Test
  void traversePostOrder_sampleTree_traversesTreeInPostOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traversePostOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getValues(), contains(TestTree.POST_ORDER_VALUES));
  }

  @Test
  void traverseInOrder_sampleTree_traversesTreeInOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traverseInOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getValues(), contains(TestTree.IN_ORDER_VALUES));
  }

  @Test
  void traverseInOrder_sampleTree_traversesTreeInReverseOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traverseReverseInOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getValues(), contains(TestTree.REVERSE_IN_ORDER_VALUES));
  }
}
