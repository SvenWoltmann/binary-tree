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

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    appendNodeToStringRecursive(getRoot(), builder);
    return builder.toString();
  }

  private void appendNodeToStringRecursive(Node node, StringBuilder builder) {
    appendNodeToString(node, builder);
    if (node.left != null) {
      builder.append(" L{");
      appendNodeToStringRecursive(node.left, builder);
      builder.append('}');
    }
    if (node.right != null) {
      builder.append(" R{");
      appendNodeToStringRecursive(node.right, builder);
      builder.append('}');
    }
  }

  protected void appendNodeToString(Node node, StringBuilder builder) {
    builder.append(node.data);
  }
}
