package eu.happycoders.binary_tree;

/**
 * Validates if the given binary tree is a binary search tree.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class BinarySearchTreeValidator extends BinaryTree {

  /**
   * Validates if the given binary tree is a binary search tree (with no duplicates allowed).
   *
   * @param tree the binary tree
   * @return whether the given binary tree is a binary search tree
   */
  public static boolean isBSTWithoutDuplicates(BinaryTree tree) {
    return isBSTWithoutDuplicates(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBSTWithoutDuplicates(Node node, int minAllowedKey, int maxAllowedKey) {
    if (node == null) return true;

    if (node.data < minAllowedKey || node.data > maxAllowedKey) return false;

    return isBSTWithoutDuplicates(node.left, minAllowedKey, node.data - 1)
        && isBSTWithoutDuplicates(node.right, node.data + 1, maxAllowedKey);
  }

  /**
   * Validates if the given binary tree is a binary search tree (with duplicates allowed).
   *
   * @param tree the binary tree
   * @return whether the given binary tree is a binary search tree
   */
  public static boolean isBSTWithDuplicates(BinaryTree tree) {
    return isBSTWithDuplicates(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBSTWithDuplicates(Node node, int minAllowedKey, int maxAllowedKey) {
    if (node == null) return true;

    if (node.data <= minAllowedKey || node.data >= maxAllowedKey) return false;

    return isBSTWithDuplicates(node.left, minAllowedKey, node.data)
        && isBSTWithDuplicates(node.right, node.data, maxAllowedKey);
  }
}
