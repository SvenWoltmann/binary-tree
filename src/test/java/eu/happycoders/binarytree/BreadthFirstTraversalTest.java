package eu.happycoders.binarytree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class BreadthFirstTraversalTest {

  @Test
  void traverseLevelOrder_sampleTree_traversesTreeInLevelOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    new BreadthFirstTraversal(new TestTreeWithValues()).traverseLevelOrder(visitor);
    assertThat(visitor.getDataList(), contains(TestTreeWithValues.LEVEL_ORDER_VALUES));
  }

  @Test
  void traverseLevelOrder_emptyTree_traversesNoElement() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    new BreadthFirstTraversal(emptyTree()).traverseLevelOrder(visitor);
    assertThat(visitor.getDataList(), is(empty()));
  }

  private BinaryTree emptyTree() {
    return () -> null;
  }
}
