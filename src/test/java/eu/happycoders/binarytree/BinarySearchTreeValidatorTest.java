package eu.happycoders.binarytree;

import static eu.happycoders.binarytree.BinarySearchTreeValidator.isBstWithoutDuplicates;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class BinarySearchTreeValidatorTest {

  @Test
  void shouldReturnTrueForEmptyTree() {
    BinaryTree tree = new BinaryTree();
    assertThat(isBstWithoutDuplicates(tree), is(true));
  }

  @Test
  void shouldReturnTrueForTreeWithOneNode() {
    BinaryTree tree = new BinaryTree();
    tree.root = new Node(100);
    assertThat(isBstWithoutDuplicates(tree), is(true));
  }

  @Test
  void shouldReturnTrueForTreeWithNodeAndSmallerLeftChild() {
    BinaryTree tree = new BinaryTree();
    tree.root = new Node(100);
    tree.root.left = new Node(50);
    assertThat(isBstWithoutDuplicates(tree), is(true));
  }

  @Test
  void shouldReturnFalseForTreeWithNodeAndGreaterLeftChild() {
    BinaryTree tree = new BinaryTree();
    tree.root = new Node(100);
    tree.root.left = new Node(110);
    assertThat(isBstWithoutDuplicates(tree), is(false));
  }

  @Test
  void shouldReturnTrueForTreeWithNodeAndGreaterRightChild() {
    BinaryTree tree = new BinaryTree();
    tree.root = new Node(100);
    tree.root.right = new Node(110);
    assertThat(isBstWithoutDuplicates(tree), is(true));
  }

  @Test
  void shouldReturnFalseForTreeWithNodeAndSmallerRightChild() {
    BinaryTree tree = new BinaryTree();
    tree.root = new Node(100);
    tree.root.right = new Node(90);
    assertThat(isBstWithoutDuplicates(tree), is(false));
  }

  @Test
  void shouldReturnTrueForComplexValidTree() {
    BinaryTree tree = new BinaryTree();
    tree.root = new Node(5);

    tree.root.left = new Node(2);
    tree.root.left.left = new Node(1);
    tree.root.left.right = new Node(4);
    tree.root.left.right.left = new Node(3);

    tree.root.right = new Node(9);
    tree.root.right.left = new Node(6);
    tree.root.right.right = new Node(15);
    tree.root.right.right.left = new Node(11);
    tree.root.right.right.left.left = new Node(10);
    tree.root.right.right.left.right = new Node(13);
    tree.root.right.right.right = new Node(16);

    assertThat(isBstWithoutDuplicates(tree), is(true));
  }

  @Test
  void shouldReturnFalseForComplexInvalidTree1() {
    BinaryTree tree = new BinaryTree();
    tree.root = new Node(5);

    tree.root.left = new Node(2);
    tree.root.left.left = new Node(1);
    tree.root.left.right = new Node(4);
    tree.root.left.right.right = new Node(3); // right instead of left

    tree.root.right = new Node(9);
    tree.root.right.left = new Node(6);
    tree.root.right.right = new Node(15);
    tree.root.right.right.left = new Node(11);
    tree.root.right.right.left.left = new Node(10);
    tree.root.right.right.left.right = new Node(13);
    tree.root.right.right.right = new Node(16);

    assertThat(isBstWithoutDuplicates(tree), is(false));
  }

  @Test
  void shouldReturnFalseForComplexInvalidTree2() {
    BinaryTree tree = new BinaryTree();
    tree.root = new Node(5);

    tree.root.left = new Node(2);
    tree.root.left.left = new Node(1);
    tree.root.left.right = new Node(4);
    tree.root.left.right.left = new Node(3);

    tree.root.right = new Node(9);
    tree.root.right.left = new Node(6);
    tree.root.right.right = new Node(15);
    tree.root.right.right.left = new Node(11);
    tree.root.right.right.left.left = new Node(10);
    tree.root.right.right.right = new Node(16);
    tree.root.right.right.right.left = new Node(13);

    assertThat(isBstWithoutDuplicates(tree), is(false));
  }
}
