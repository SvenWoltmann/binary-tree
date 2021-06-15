package eu.happycoders.binarytree;

import static eu.happycoders.binarytree.BinarySearchTreeValidator.isBstWithoutDuplicates;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class BinarySearchTreeValidatorTest {

  @Test
  void shouldReturnTrueForEmptyTree() {
    BinaryTree tree = TestTree.emptyTree();
    assertThat(isBstWithoutDuplicates(tree), is(true));
  }

  @Test
  void shouldReturnTrueForTreeWithOneNode() {
    Node root = new Node(100);
    BinaryTree tree = new TestTree(root);
    assertThat(isBstWithoutDuplicates(tree), is(true));
  }

  @Test
  void shouldReturnTrueForTreeWithNodeAndSmallerLeftChild() {
    Node root = new Node(100);
    root.left = new Node(50);
    BinaryTree tree = new TestTree(root);
    assertThat(isBstWithoutDuplicates(tree), is(true));
  }

  @Test
  void shouldReturnFalseForTreeWithNodeAndGreaterLeftChild() {
    Node root = new Node(100);
    root.left = new Node(110);
    BinaryTree tree = new TestTree(root);
    assertThat(isBstWithoutDuplicates(tree), is(false));
  }

  @Test
  void shouldReturnTrueForTreeWithNodeAndGreaterRightChild() {
    Node root = new Node(100);
    root.right = new Node(110);
    BinaryTree tree = new TestTree(root);
    assertThat(isBstWithoutDuplicates(tree), is(true));
  }

  @Test
  void shouldReturnFalseForTreeWithNodeAndSmallerRightChild() {
    Node root = new Node(100);
    root.right = new Node(90);
    BinaryTree tree = new TestTree(root);
    assertThat(isBstWithoutDuplicates(tree), is(false));
  }

  @Test
  void shouldReturnTrueForComplexValidTree() {
    Node root = new Node(5);

    root.left = new Node(2);
    root.left.left = new Node(1);
    root.left.right = new Node(4);
    root.left.right.left = new Node(3);

    root.right = new Node(9);
    root.right.left = new Node(6);
    root.right.right = new Node(15);
    root.right.right.left = new Node(11);
    root.right.right.left.left = new Node(10);
    root.right.right.left.right = new Node(13);
    root.right.right.right = new Node(16);

    BinaryTree tree = new TestTree(root);

    assertThat(isBstWithoutDuplicates(tree), is(true));
  }

  @Test
  void shouldReturnFalseForComplexInvalidTree1() {
    Node root = new Node(5);

    root.left = new Node(2);
    root.left.left = new Node(1);
    root.left.right = new Node(4);
    root.left.right.right = new Node(3); // right instead of left

    root.right = new Node(9);
    root.right.left = new Node(6);
    root.right.right = new Node(15);
    root.right.right.left = new Node(11);
    root.right.right.left.left = new Node(10);
    root.right.right.left.right = new Node(13);
    root.right.right.right = new Node(16);

    BinaryTree tree = new TestTree(root);

    assertThat(isBstWithoutDuplicates(tree), is(false));
  }

  @Test
  void shouldReturnFalseForComplexInvalidTree2() {
    Node root = new Node(5);

    root.left = new Node(2);
    root.left.left = new Node(1);
    root.left.right = new Node(4);
    root.left.right.left = new Node(3);

    root.right = new Node(9);
    root.right.left = new Node(6);
    root.right.right = new Node(15);
    root.right.right.left = new Node(11);
    root.right.right.left.left = new Node(10);
    root.right.right.right = new Node(16);
    root.right.right.right.left = new Node(13);

    BinaryTree tree = new TestTree(root);

    assertThat(isBstWithoutDuplicates(tree), is(false));
  }
}
