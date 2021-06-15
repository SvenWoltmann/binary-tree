package eu.happycoders.binarytree;

/**
 * Abstract class for various BST implementations.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public abstract class BinarySearchTree extends BinaryTree {

  public abstract Node searchNode(int key);

  public abstract void insertNode(int key);

  public abstract void deleteNode(int key);
}
