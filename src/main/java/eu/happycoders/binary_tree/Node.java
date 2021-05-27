package eu.happycoders.binary_tree;

/**
 * A node in a binary tree, containing an <code>int</code> value.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class Node {

  int value;
  Node left;
  Node right;
  Node parent;

  public Node(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
