package eu.happycoders.binarytree.heap.example;

/**
 * The three index formulas of a heap stored in an array, and a check of the min-heap property that
 * uses them.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
@SuppressWarnings("squid:S106") // using stdout in this example
public class HeapIndexExample {

  public static void main(String[] args) {
    int[] heap = {3, 7, 4, 12, 9, 6, 15, 17, 13};
    System.out.println("leftChild(1)  = " + leftChild(1));
    System.out.println("rightChild(1) = " + rightChild(1));
    System.out.println("parent(1)     = " + parent(1));
    System.out.println("isMinHeap: " + isMinHeap(heap));

    int[] notAHeap = {3, 7, 4, 12, 9, 6, 15, 17, 5};
    System.out.println("isMinHeap: " + isMinHeap(notAHeap));
  }

  static int leftChild(int i) {
    return 2 * i + 1;
  }

  static int rightChild(int i) {
    return 2 * i + 2;
  }

  static int parent(int i) {
    return (i - 1) / 2;
  }

  static boolean isMinHeap(int[] a) {
    for (int i = 1; i < a.length; i++) {
      if (a[i] < a[parent(i)]) {
        return false;
      }
    }
    return true;
  }
}
