package eu.happycoders.binarytree;

import static eu.happycoders.binarytree.BinaryTreeAssert.assertThatTree;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import org.junit.jupiter.api.RepeatedTest;

abstract class BinarySearchTreeTest {

  @RepeatedTest(100)
  void insertingKeysShouldCreateAValidBSTWithKeysInOrderAndParentsSetCorrectly() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = createBST();
    insertKeysInRandomOrder(tree, keysOrdered);

    assertThatTree(tree) //
        .isValid()
        .hasKeysInGivenOrder(keysOrdered)
        .hasAllParentsSetCorrectly();
  }

  @RepeatedTest(100)
  void shouldThrowExceptionWhenInsertingExistingKey() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = createBST();
    insertKeysInRandomOrder(tree, keysOrdered);

    int randomKey = pickRandomKey(keysOrdered);
    assertThrows(IllegalArgumentException.class, () -> tree.insertNode(randomKey));
  }

  @RepeatedTest(100)
  void searchFindsAllKeys() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = createBST();
    insertKeysInRandomOrder(tree, keysOrdered);

    for (Integer key : keysOrdered) {
      Node node = tree.searchNode(key);
      assertThat(node.getData(), is(key));
    }
  }

  @RepeatedTest(100)
  void searchReturnsNullWhenKeyNotFound() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = createBST();
    insertKeysInRandomOrder(tree, keysOrdered);

    Integer highestKey = keysOrdered.get(keysOrdered.size() - 1);
    assertThat(tree.searchNode(highestKey + 1), is(nullValue()));
  }

  @RepeatedTest(100)
  void deleteNodeShouldLeaveAValidBSTWithoutTheDeletedNode() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = createBST();
    insertKeysInRandomOrder(tree, keysOrdered);

    // Remove every key... and after each key check if the BST is valid
    List<Integer> keysToDelete = shuffle(keysOrdered);
    List<Integer> keysRemaining = new ArrayList<>(keysOrdered);

    for (Integer keyToDelete : keysToDelete) {
      tree.deleteNode(keyToDelete);

      keysRemaining.remove(keyToDelete);

      assertThatTree(tree) //
          .isValid()
          .hasKeysInGivenOrder(keysRemaining)
          .hasAllParentsSetCorrectly();
    }
  }

  protected abstract BinarySearchTree createBST();

  private List<Integer> createOrderedSequenceOfKeys() {
    int size = ThreadLocalRandom.current().nextInt(1, 1000);
    return IntStream.range(0, size).boxed().toList();
  }

  private void insertKeysInRandomOrder(BinarySearchTree tree, List<Integer> keysOrdered) {
    List<Integer> keys = shuffle(keysOrdered);
    for (Integer key : keys) {
      tree.insertNode(key);
    }
  }

  private List<Integer> shuffle(List<Integer> keysOrdered) {
    List<Integer> keys = new ArrayList<>(keysOrdered);
    Collections.shuffle(keys);
    return Collections.unmodifiableList(keys);
  }

  private int pickRandomKey(List<Integer> keysOrdered) {
    int randomIndex = ThreadLocalRandom.current().nextInt(keysOrdered.size());
    return keysOrdered.get(randomIndex);
  }
}
