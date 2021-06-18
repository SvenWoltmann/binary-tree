package eu.happycoders.binarytree;

/**
 * Validates if the given binary tree is a binary search tree.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public final class BinarySearchTreeValidator {

  private BinarySearchTreeValidator() {}

  /**
   * Validates if the given binary tree is a binary search tree (with no duplicates allowed).
   *
   * @param tree the binary tree
   * @return whether the given binary tree is a binary search tree
   */
  public static boolean isBstWithoutDuplicates(BinaryTree tree) {
    return isBstWithoutDuplicates(tree.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBstWithoutDuplicates(Node node, int minAllowedKey, int maxAllowedKey) {
    if (node == null) {
      return true;
    }

    if (node.data < minAllowedKey || node.data > maxAllowedKey) {
      return false;
    }

    return isBstWithoutDuplicates(node.left, minAllowedKey, node.data - 1)
        && isBstWithoutDuplicates(node.right, node.data + 1, maxAllowedKey);
  }

  /**
   * Validates if the given binary tree is a binary search tree (with duplicates allowed).
   *
   * @param tree the binary tree
   * @return whether the given binary tree is a binary search tree
   */
  public static boolean isBstWithDuplicates(BinaryTree tree) {
    return isBstWithDuplicates(tree.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBstWithDuplicates(Node node, int minAllowedKey, int maxAllowedKey) {
    if (node == null) {
      return true;
    }

    if (node.data < minAllowedKey || node.data > maxAllowedKey) {
      return false;
    }

    return isBstWithDuplicates(node.left, minAllowedKey, node.data)
        && isBstWithDuplicates(node.right, node.data, maxAllowedKey);
  }
}
