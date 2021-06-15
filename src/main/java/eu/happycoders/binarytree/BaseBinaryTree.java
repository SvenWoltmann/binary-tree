package eu.happycoders.binarytree;

/**
 * Abstract binary tree implementation containing only the root node.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class BaseBinaryTree implements BinaryTree {

  protected Node root;

  @Override
  public Node getRoot() {
    return root;
  }
}
