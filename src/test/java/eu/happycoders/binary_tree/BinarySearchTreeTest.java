package eu.happycoders.binary_tree;

import static eu.happycoders.binary_tree.BinaryTreeAssert.assertThatTree;
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

class BinarySearchTreeTest {

  @RepeatedTest(100)
  void insertingKeysRecursivelyShouldCreateAValidBSTWithKeysInOrderAndParentsSetCorrectly() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = new BinarySearchTree();
    insertKeysInRandomOrderRecursively(tree, keysOrdered);

    assertThatTree(tree) //
        .isValid()
        .hasKeysInGivenOrder(keysOrdered)
        .hasAllParentsSetCorrectly();
  }

  @RepeatedTest(100)
  void insertingKeysIterativelyShouldCreateAValidBSTWithKeysInOrderAndParentsSetCorrectly() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = new BinarySearchTree();
    insertKeysInRandomOrderIteratively(tree, keysOrdered);

    assertThatTree(tree) //
        .isValid()
        .hasKeysInGivenOrder(keysOrdered)
        .hasAllParentsSetCorrectly();
  }

  @RepeatedTest(100)
  void shouldThrowExceptionWhenInsertingExistingKeyRecursively() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = new BinarySearchTree();
    insertKeysInRandomOrderRecursively(tree, keysOrdered);

    int randomKey = pickRandomKey(keysOrdered);
    assertThrows(IllegalArgumentException.class, () -> tree.insertNodeRecursively(randomKey));
  }

  @RepeatedTest(100)
  void shouldThrowExceptionWhenInsertingExistingKeyIteratively() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = new BinarySearchTree();
    insertKeysInRandomOrderIteratively(tree, keysOrdered);

    int randomKey = pickRandomKey(keysOrdered);
    assertThrows(IllegalArgumentException.class, () -> tree.insertNodeIteratively(randomKey));
  }

  @RepeatedTest(100)
  void recursiveSearchFindsAllKeys() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = new BinarySearchTree();
    insertKeysInRandomOrderIteratively(tree, keysOrdered);

    for (Integer key : keysOrdered) {
      Node node = tree.searchNodeRecursively(key);
      assertThat(node.getData(), is(key));
    }
  }

  @RepeatedTest(100)
  void recursiveSearchReturnsNullWhenKeyNotFound() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = new BinarySearchTree();
    insertKeysInRandomOrderIteratively(tree, keysOrdered);

    Integer highestKey = keysOrdered.get(keysOrdered.size() - 1);
    assertThat(tree.searchNodeRecursively(highestKey + 1), is(nullValue()));
  }

  @RepeatedTest(100)
  void iterativeSearchFindsAllKeys() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = new BinarySearchTree();
    insertKeysInRandomOrderIteratively(tree, keysOrdered);

    for (Integer key : keysOrdered) {
      Node node = tree.searchNodeIteratively(key);
      assertThat(node.getData(), is(key));
    }
  }

  @RepeatedTest(100)
  void iterativeSearchReturnsNullWhenKeyNotFound() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = new BinarySearchTree();
    insertKeysInRandomOrderIteratively(tree, keysOrdered);

    Integer highestKey = keysOrdered.get(keysOrdered.size() - 1);
    assertThat(tree.searchNodeIteratively(highestKey + 1), is(nullValue()));
  }

  @RepeatedTest(100)
  void deleteNodeRecursivelyShouldLeaveAValidBSTWithoutTheDeletedNode() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = new BinarySearchTree();
    insertKeysInRandomOrderIteratively(tree, keysOrdered);

    // Remove every key... and after each key check if the BST is valid
    List<Integer> keysToDelete = shuffle(keysOrdered);
    List<Integer> keysRemaining = new ArrayList<>(keysOrdered);

    for (Integer keyToDelete : keysToDelete) {
      tree.deleteNodeRecursively(keyToDelete);

      keysRemaining.remove(keyToDelete);

      assertThatTree(tree) //
          .isValid()
          .hasKeysInGivenOrder(keysRemaining)
          .hasAllParentsSetCorrectly();
    }
  }

  @RepeatedTest(100)
  void deleteNodeIterativelyShouldLeaveAValidBSTWithoutTheDeletedNode() {
    List<Integer> keysOrdered = createOrderedSequenceOfKeys();

    var tree = new BinarySearchTree();
    insertKeysInRandomOrderIteratively(tree, keysOrdered);

    // Remove every key... and after each key check if the BST is valid
    List<Integer> keysToDelete = shuffle(keysOrdered);
    List<Integer> keysRemaining = new ArrayList<>(keysOrdered);

    for (Integer keyToDelete : keysToDelete) {
      tree.deleteNodeIteratively(keyToDelete);

      keysRemaining.remove(keyToDelete);

      assertThatTree(tree) //
          .isValid()
          .hasKeysInGivenOrder(keysRemaining)
          .hasAllParentsSetCorrectly();
    }
  }

  private List<Integer> createOrderedSequenceOfKeys() {
    int size = ThreadLocalRandom.current().nextInt(1, 1000);
    return IntStream.range(0, size).boxed().toList();
  }

  private void insertKeysInRandomOrderRecursively(
      BinarySearchTree tree, List<Integer> keysOrdered) {
    List<Integer> keys = shuffle(keysOrdered);
    for (Integer key : keys) {
      tree.insertNodeRecursively(key);
    }
  }

  private void insertKeysInRandomOrderIteratively(
      BinarySearchTree tree, List<Integer> keysOrdered) {
    List<Integer> keys = shuffle(keysOrdered);
    for (Integer key : keys) {
      tree.insertNodeIteratively(key);
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
