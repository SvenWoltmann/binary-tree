package eu.happycoders.binary_tree;

/**
 * A node in a binary tree, containing an <code>int</code> data.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class Node {

  // also called "value" in a binary tree
  // also called "key" in a binary search tree
  int data;

  Node left;
  Node right;
  Node parent;

  public Node(int data) {
    this.data = data;
  }

  public int getData() {
    return data;
  }
}
