package eu.happycoders.binarytree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class TraversalDepthFirstRecursiveTest {

  @Test
  void traversePreOrder_sampleTree_traversesTreeInPreOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traversePreOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.PRE_ORDER_VALUES));
  }

  @Test
  void traversePreOrder_emptyTree_traversesNoElement() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traversePreOrder(emptyTree().root, visitor);
    assertThat(visitor.getDataList(), is(empty()));
  }

  @Test
  void traversePostOrder_sampleTree_traversesTreeInPostOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traversePostOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.POST_ORDER_VALUES));
  }

  @Test
  void traversePostOrder_emptyTree_traversesNoElement() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traversePostOrder(emptyTree().root, visitor);
    assertThat(visitor.getDataList(), is(empty()));
  }

  @Test
  void traverseInOrder_sampleTree_traversesTreeInOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traverseInOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.IN_ORDER_VALUES));
  }

  @Test
  void traverseInOrder_emptyTree_traversesNoElement() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traverseInOrder(emptyTree().root, visitor);
    assertThat(visitor.getDataList(), is(empty()));
  }

  @Test
  void traverseInOrder_sampleTree_traversesTreeInReverseOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traverseReverseInOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.REVERSE_IN_ORDER_VALUES));
  }

  @Test
  void traverseReverseInOrder_emptyTree_traversesNoElement() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalDepthFirstRecursive.traverseReverseInOrder(emptyTree().root, visitor);
    assertThat(visitor.getDataList(), is(empty()));
  }

  private BinaryTree emptyTree() {
    return new BinaryTree();
  }
}
