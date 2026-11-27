package eu.happycoders.binarytree.heap.benchmark;

import eu.happycoders.binarytree.heap.benchmark.PriorityQueueBuildBenchmark.Order;
import java.util.List;
import java.util.Locale;
import java.util.PriorityQueue;

/**
 * Counts the comparisons and swaps needed to turn an array of {@code n} distinct integers into a
 * min-heap – with Floyd's method (sift down from the last parent back to the root) and by inserting
 * the elements one by one (sift up). Not a time measurement, so the numbers are exact and the same
 * on every machine.
 *
 * <p>As a cross-check, it also counts the comparisons of the JDK's {@link PriorityQueue}: its
 * constructor uses Floyd's method, {@code addAll()} inserts element by element. The JDK moves
 * elements into a hole instead of swapping them, so it reports comparisons only.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class HeapConstructionCount {

  private static final int[] SIZES = {1_000, 10_000, 100_000, 1_000_000};

  private long comparisons;
  private long swaps;

  public static void main(String[] args) {
    System.out.println(
        "order       n          method      comparisons  swaps        cmp/n  swaps/n"
            + "  jdk-cmp      n*log2(n)");
    for (Order order : Order.values()) {
      for (int n : SIZES) {
        List<Integer> list = PriorityQueueBuildBenchmark.createInput(n, order);
        printFloyd(order, n, list);
        printOneByOne(order, n, list);
      }
    }
  }

  private static void printFloyd(Order order, int n, List<Integer> list) {
    HeapConstructionCount counter = new HeapConstructionCount();
    int[] heap = toArray(list);
    counter.buildHeapFloyd(heap);
    checkHeap(heap);

    List<Counted> elements = Counted.wrap(list);
    Counted.comparisons = 0;
    new PriorityQueue<>(elements);

    print(order, n, "floyd", counter, Counted.comparisons);
  }

  private static void printOneByOne(Order order, int n, List<Integer> list) {
    HeapConstructionCount counter = new HeapConstructionCount();
    int[] heap = toArray(list);
    counter.buildHeapOneByOne(heap);
    checkHeap(heap);

    List<Counted> elements = Counted.wrap(list);
    Counted.comparisons = 0;
    new PriorityQueue<Counted>().addAll(elements);

    print(order, n, "one-by-one", counter, Counted.comparisons);
  }

  void buildHeapFloyd(int[] heap) {
    for (int i = heap.length / 2 - 1; i >= 0; i--) {
      siftDown(heap, i, heap.length);
    }
  }

  void buildHeapOneByOne(int[] heap) {
    for (int i = 1; i < heap.length; i++) {
      siftUp(heap, i);
    }
  }

  private void siftDown(int[] heap, int index, int length) {
    while (2 * index + 1 < length) {
      int child = 2 * index + 1;
      int right = child + 1;
      if (right < length && less(heap[right], heap[child])) {
        child = right;
      }
      if (!less(heap[child], heap[index])) {
        return;
      }
      swap(heap, index, child);
      index = child;
    }
  }

  private void siftUp(int[] heap, int index) {
    while (index > 0) {
      int parent = (index - 1) / 2;
      if (!less(heap[index], heap[parent])) {
        return;
      }
      swap(heap, index, parent);
      index = parent;
    }
  }

  private boolean less(int a, int b) {
    comparisons++;
    return a < b;
  }

  private void swap(int[] heap, int i, int j) {
    swaps++;
    int tmp = heap[i];
    heap[i] = heap[j];
    heap[j] = tmp;
  }

  private static int[] toArray(List<Integer> list) {
    return list.stream().mapToInt(Integer::intValue).toArray();
  }

  private static void checkHeap(int[] heap) {
    for (int i = 1; i < heap.length; i++) {
      if (heap[i] < heap[(i - 1) / 2]) {
        throw new IllegalStateException("not a heap at index " + i);
      }
    }
  }

  private static void print(
      Order order, int n, String method, HeapConstructionCount counter, long jdkComparisons) {
    System.out.printf(
        Locale.ROOT,
        "%-11s %-10d %-11s %-12d %-12d %-6.2f %-8.2f %-12d %d%n",
        order,
        n,
        method,
        counter.comparisons,
        counter.swaps,
        (double) counter.comparisons / n,
        (double) counter.swaps / n,
        jdkComparisons,
        Math.round(n * Math.log(n) / Math.log(2)));
  }

  /**
   * An integer that counts its comparisons. The JDK's {@code PriorityQueue} accepts a comparator
   * together with a collection only since Java 28, so the count goes through {@code compareTo()}.
   */
  private record Counted(int value) implements Comparable<Counted> {
    private static long comparisons;

    static List<Counted> wrap(List<Integer> list) {
      return list.stream().map(Counted::new).toList();
    }

    @Override
    public int compareTo(Counted other) {
      comparisons++;
      return Integer.compare(value, other.value);
    }
  }
}
