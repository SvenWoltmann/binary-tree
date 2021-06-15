package eu.happycoders.binarytree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class TraversalDepthFirstIterativeTest {

  @Test
  void traversePreOrder_sampleTree_traversesTreeInPreOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traversePreOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.PRE_ORDER_VALUES));
  }

  @Test
  void traversePreOrder_emptyTree_traversesNoElement() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traversePreOrder(emptyTree().root, visitor);
    assertThat(visitor.getDataList(), is(empty()));
  }

  @Test
  void traversePostOrder_sampleTree_traversesTreeInPostOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traversePostOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.POST_ORDER_VALUES));
  }

  @Test
  void traversePostOrder_emptyTree_traversesNoElement() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traversePostOrder(emptyTree().root, visitor);
    assertThat(visitor.getDataList(), is(empty()));
  }

  @Test
  void traverseInOrder_sampleTree_traversesTreeInOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traverseInOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.IN_ORDER_VALUES));
  }

  @Test
  void traverseInOrder_emptyTree_traversesNoElement() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traverseInOrder(emptyTree().root, visitor);
    assertThat(visitor.getDataList(), is(empty()));
  }

  @Test
  void traverseReverseInOrder_sampleTree_traversesTreeInReverseOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traverseReverseInOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.REVERSE_IN_ORDER_VALUES));
  }

  @Test
  void traverseReverseInOrder_emptyTree_traversesNoElement() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstIterative.traverseReverseInOrder(emptyTree().root, visitor);
    assertThat(visitor.getDataList(), is(empty()));
  }

  private BinaryTree emptyTree() {
    return new BinaryTree();
  }
}
