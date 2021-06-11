package eu.happycoders.binary_tree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import org.junit.jupiter.api.Test;

class TraversalBreadthFirstTest {

  @Test
  void traverseLevelOrder_sampleTree_traversesTreeInLevelOrder() {
    TestNodeVisitor visitor = new TestNodeVisitor();
    TraversalBreadthFirst.traverseLevelOrder(TestTree.ROOT, visitor);
    assertThat(visitor.getDataList(), contains(TestTree.LEVEL_ORDER_VALUES));
  }
}
