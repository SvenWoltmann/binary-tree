package eu.happycoders.binary_tree;

import eu.happycoders.binary_tree.Node;
import eu.happycoders.binary_tree.NodeVisitor;
import java.util.ArrayList;
import java.util.List;

public class TestNodeVisitor implements NodeVisitor {

  private List<Integer> values = new ArrayList<>();

  @Override
  public void visit(Node node) {
    values.add(node.value);
  }

  public List<Integer> getValues() {
    return values;
  }
}
