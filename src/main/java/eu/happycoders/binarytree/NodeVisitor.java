package eu.happycoders.binarytree;

/**
 * Node visitor for binary tree traversal.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public interface NodeVisitor {

  void visit(Node node);
}
