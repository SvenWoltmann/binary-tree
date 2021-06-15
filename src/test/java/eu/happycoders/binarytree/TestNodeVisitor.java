package eu.happycoders.binarytree;

import java.util.ArrayList;
import java.util.List;

public class TestNodeVisitor implements NodeVisitor {

  private List<Integer> dataList = new ArrayList<>();

  @Override
  public void visit(Node node) {
    dataList.add(node.data);
  }

  public List<Integer> getDataList() {
    return dataList;
  }
}
