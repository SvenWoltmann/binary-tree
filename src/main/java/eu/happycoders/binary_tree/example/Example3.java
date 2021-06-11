package eu.happycoders.binary_tree.example;

import eu.happycoders.binary_tree.BinarySearchTree;
import eu.happycoders.binary_tree.Node;
import eu.happycoders.binary_tree.NodeVisitor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Example3 {

  private static final Integer[] KEYS = new Integer[] {1, 2, 3, 4, 5, 6, 9, 10, 11, 13, 15, 16};

  private static final NodeVisitor visitor = node -> System.out.print(node.getData());

  public static void main(String[] args) {
    var tree = new BinarySearchTree();

    // Create a mutable list so that we can shuffle it
    List<Integer> keys = new ArrayList<>(Arrays.asList(KEYS));
    Collections.shuffle(keys);
    for (Integer key : keys) {
      System.out.println("Adding key " + key);
      tree.insertNodeRecursively(key);
    }

    System.out.print("\nAll keys in-order: ");
    tree.traverseInOrder(visitor);

    System.out.println("\n\nSearching...");

    for (int key : KEYS) {
      Node nodeR = tree.searchNodeRecursively(key);
      Node nodeI = tree.searchNodeIteratively(key);
      System.out.println(
          "key = "
              + key
              + " --> nodeI.data = "
              + nodeI.getData()
              + " / nodeR.data = "
              + nodeR.getData());
    }
  }
}
