package eu.happycoders.binary_tree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;

import eu.happycoders.binary_tree.SimpleBinaryTree.Side;
import org.junit.jupiter.api.Test;

class SimpleBinaryTreeTest {

  @Test
  void insertRoot_rootIsNull_rootIsSetToNewNodeWithGivenValue() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(3);

    assertThat(root, sameInstance(tree.getRoot()));

    assertThat(root.data, is(3));
    assertThat(root.left, is(nullValue()));
    assertThat(root.right, is(nullValue()));
    assertThat(root.parent, is(nullValue()));
  }

  @Test
  void insertRoot_rootIsAlreadySet_throwsException() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    tree.insertRoot(3);
    assertThrows(IllegalStateException.class, () -> tree.insertRoot(5));
  }

  @Test
  void insertNode_parentIsNull_throwsException() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    assertThrows(NullPointerException.class, () -> tree.insertNode(1, null, Side.LEFT));
  }

  @Test
  void insertNode_leftUnderEmptyRoot_newNodeIsLeftUnderRootAndParentIsSet() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(3);
    Node node = tree.insertNode(1, root, Side.LEFT);

    assertThat(root.left, sameInstance(node));
    assertThat(root.right, is(nullValue()));

    assertThat(node.data, is(1));
    assertThat(node.left, is(nullValue()));
    assertThat(node.right, is(nullValue()));
    assertThat(node.parent, sameInstance(root));
  }

  @Test
  void insertNode_leftUnderFullRoot_newNodeIsLeftUnderRootAndPreviousLeftIsLeftUnderNewNode() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(3);
    Node node1 = tree.insertNode(1, root, Side.LEFT);
    Node node10 = tree.insertNode(10, root, Side.RIGHT);

    Node node = tree.insertNode(2, root, Side.LEFT);

    assertThat(root.left, sameInstance(node));
    assertThat(root.right, sameInstance(node10));

    assertThat(node.data, is(2));
    assertThat(node.left, sameInstance(node1));
    assertThat(node.right, is(nullValue()));
    assertThat(node.parent, sameInstance(root));

    assertThat(node1.parent, sameInstance(node));
  }

  @Test
  void insertNode_rightUnderEmptyRoot_newNodeIsRightUnderRootAndParentIsSet() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(3);
    Node node = tree.insertNode(10, root, Side.RIGHT);

    assertThat(root.left, is(nullValue()));
    assertThat(root.right, sameInstance(node));

    assertThat(node.data, is(10));
    assertThat(node.left, is(nullValue()));
    assertThat(node.right, is(nullValue()));
    assertThat(node.parent, sameInstance(root));
  }

  @Test
  void insertNode_rightUnderFullRoot_newNodeIsRightUnderRootAndPreviousRightIsRightUnderNewNode() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(3);
    Node node1 = tree.insertNode(1, root, Side.LEFT);
    Node node10 = tree.insertNode(10, root, Side.RIGHT);

    Node node = tree.insertNode(15, root, Side.RIGHT);

    assertThat(root.left, sameInstance(node1));
    assertThat(root.right, sameInstance(node));

    assertThat(node.data, is(15));
    assertThat(node.left, is(nullValue()));
    assertThat(node.right, sameInstance(node10));
    assertThat(node.parent, sameInstance(root));

    assertThat(node10.parent, sameInstance(node));
  }
}
