package eu.happycoders.binarytree;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;

import eu.happycoders.binarytree.SimpleBinaryTree.Side;
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

  @Test
  void deleteNode_leaf_leafIsRemoved() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(3);
    Node node1 = tree.insertNode(1, root, Side.LEFT);
    Node node10 = tree.insertNode(10, root, Side.RIGHT);

    tree.deleteNode(node10);

    assertThat(root.left, sameInstance(node1));
    assertThat(root.right, is(nullValue()));
  }

  @Test
  void deleteNode_nonRootNodeWithoutParent_throwsException() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(3);
    Node node1 = tree.insertNode(1, root, Side.LEFT);
    Node node10 = tree.insertNode(10, root, Side.RIGHT);
    node10.parent = null;

    assertThrows(IllegalStateException.class, () -> tree.deleteNode(node10));
  }

  @Test
  void deleteNode_nodeWithLeftChildOnly_nodeIsReplacedByChild() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(3);
    Node node1 = tree.insertNode(1, root, Side.LEFT);
    Node node10 = tree.insertNode(10, root, Side.RIGHT);
    Node node8 = tree.insertNode(8, node10, Side.LEFT);
    Node node7 = tree.insertNode(7, node8, Side.LEFT);
    Node node9 = tree.insertNode(9, node8, Side.RIGHT);

    tree.deleteNode(node10);

    assertThat(root.left, sameInstance(node1));
    assertThat(root.right, sameInstance(node8));
    assertThat(node8.parent, sameInstance(root));
  }

  @Test
  void deleteNode_nodeWithRightChildOnly_nodeIsReplacedByChild() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(3);
    Node node1 = tree.insertNode(1, root, Side.LEFT);
    Node node10 = tree.insertNode(10, root, Side.RIGHT);
    Node node12 = tree.insertNode(12, node10, Side.RIGHT);
    Node node11 = tree.insertNode(11, node12, Side.LEFT);
    Node node13 = tree.insertNode(13, node12, Side.RIGHT);

    tree.deleteNode(node10);

    assertThat(root.left, sameInstance(node1));
    assertThat(root.right, sameInstance(node12));
    assertThat(node12.parent, sameInstance(root));
  }

  @Test
  void
      deleteNode_rightChildNodeWithTwoChildren_nodeIsReplacedByLeftSubtreeAndRightSubtreeIsAppendedToRightmostNodeOfLeftSubtree() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(3);
    Node node1 = tree.insertNode(1, root, Side.LEFT);
    Node node10 = tree.insertNode(10, root, Side.RIGHT);

    // left subtree of 10
    Node node8 = tree.insertNode(8, node10, Side.LEFT);
    Node node7 = tree.insertNode(7, node8, Side.LEFT);
    Node node9 = tree.insertNode(9, node8, Side.RIGHT);

    // right subtree of 10
    Node node12 = tree.insertNode(12, node10, Side.RIGHT);
    Node node11 = tree.insertNode(11, node12, Side.LEFT);
    Node node13 = tree.insertNode(13, node12, Side.RIGHT);

    tree.deleteNode(node10);

    assertThat(root.left, sameInstance(node1));
    assertThat(root.right, sameInstance(node8));

    assertThat(node8.parent, sameInstance(root));
    assertThat(node9.right, sameInstance(node12));
    assertThat(node12.parent, sameInstance(node9));
  }

  @Test
  void
      deleteNode_leftChildNodeWithTwoChildren_nodeIsReplacedByLeftSubtreeAndRightSubtreeIsAppendedToRightmostNodeOfLeftSubtree() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(15);
    Node node10 = tree.insertNode(10, root, Side.LEFT);
    Node node20 = tree.insertNode(20, root, Side.RIGHT);

    // left subtree of 10
    Node node8 = tree.insertNode(8, node10, Side.LEFT);
    Node node7 = tree.insertNode(7, node8, Side.LEFT);
    Node node9 = tree.insertNode(9, node8, Side.RIGHT);

    // right subtree of 10
    Node node12 = tree.insertNode(12, node10, Side.RIGHT);
    Node node11 = tree.insertNode(11, node12, Side.LEFT);
    Node node13 = tree.insertNode(13, node12, Side.RIGHT);

    tree.deleteNode(node10);

    assertThat(root.left, sameInstance(node8));
    assertThat(root.right, sameInstance(node20));

    assertThat(node8.parent, sameInstance(root));
    assertThat(node9.right, sameInstance(node12));
    assertThat(node12.parent, sameInstance(node9));
  }

  @Test
  void
      deleteNode_rootWithTwoChildren_nodeIsReplacedByLeftSubtreeAndRightSubtreeIsAppendedToRightmostNodeOfLeftSubtree() {
    SimpleBinaryTree tree = new SimpleBinaryTree();
    Node root = tree.insertRoot(3);
    Node node1 = tree.insertNode(1, root, Side.LEFT);
    Node node10 = tree.insertNode(10, root, Side.RIGHT);

    tree.deleteNode(root);

    assertThat(tree.getRoot(), sameInstance(node1));
    assertThat(node1.parent, is(nullValue()));

    assertThat(tree.getRoot().right, sameInstance(node10));
    assertThat(node10.parent, sameInstance(tree.getRoot()));
  }
}
