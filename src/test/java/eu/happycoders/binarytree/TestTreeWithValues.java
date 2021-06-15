package eu.happycoders.binarytree;

public class TestTreeWithValues implements BinaryTree {

  private static final Node ROOT;

  static {
    ROOT = new Node(3);
    ROOT.left = new Node(1);
    ROOT.left.left = new Node(13);
    ROOT.left.right = new Node(5);
    ROOT.left.right.left = new Node(6);
    ROOT.right = new Node(10);
    ROOT.right.left = new Node(11);
    ROOT.right.right = new Node(16);
    ROOT.right.right.left = new Node(15);
    ROOT.right.right.left.left = new Node(9);
    ROOT.right.right.left.right = new Node(4);
    ROOT.right.right.right = new Node(2);
  }

  static final Integer[] PRE_ORDER_VALUES = {3, 1, 13, 5, 6, 10, 11, 16, 15, 9, 4, 2};
  static final Integer[] POST_ORDER_VALUES = {13, 6, 5, 1, 11, 9, 4, 15, 2, 16, 10, 3};
  static final Integer[] IN_ORDER_VALUES = {13, 1, 6, 5, 3, 11, 10, 9, 15, 4, 16, 2};
  static final Integer[] REVERSE_IN_ORDER_VALUES = {2, 16, 4, 15, 9, 10, 11, 3, 5, 6, 1, 13};
  static final Integer[] LEVEL_ORDER_VALUES = {3, 1, 10, 13, 5, 11, 16, 6, 15, 2, 9, 4};

  @Override
  public Node getRoot() {
    return ROOT;
  }
}
