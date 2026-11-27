package eu.happycoders.binarytree.heap.benchmark;

import java.util.Locale;

/**
 * Counts the comparisons and moved elements of a d-ary heap ({@link IntDaryHeap}) for d = 2, 3, 4
 * and 8: first inserting {@code n} shuffled integers one by one, then removing the minimum until
 * the heap is empty. Not a time measurement, so the numbers are exact and the same on every
 * machine.
 *
 * <p>A larger d makes the tree shallower: inserting needs fewer comparisons, removing needs more,
 * because sift down compares all d children on every level.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class DaryHeapCount extends IntDaryHeap {

  private static final int[] SIZES = {1_000, 100_000, 1_000_000};
  private static final int[] DS = {2, 3, 4, 8};

  private long comparisons;
  private long moves;

  DaryHeapCount(int d, int capacity) {
    super(d, capacity);
  }

  public static void main(String[] args) {
    System.out.println(
        "n          d  height  insert: cmp/n  moves/n   removeMin: cmp/n  moves/n   total cmp/n");
    for (int n : SIZES) {
      int[] input = DaryHeapBenchmark.createInput(n);
      for (int d : DS) {
        count(n, d, input);
      }
    }
  }

  private static void count(int n, int d, int[] input) {
    DaryHeapCount heap = new DaryHeapCount(d, n);
    for (int value : input) {
      heap.insert(value);
    }
    long insertComparisons = heap.comparisons;
    long insertMoves = heap.moves;

    heap.comparisons = 0;
    heap.moves = 0;
    int previous = Integer.MIN_VALUE;
    while (heap.size() > 0) {
      int min = heap.removeMin();
      if (min < previous) {
        throw new IllegalStateException("not in order: " + previous + ", " + min);
      }
      previous = min;
    }

    System.out.printf(
        Locale.ROOT,
        "%-10d %-2d %-7d %-14.2f %-9.2f %-17.2f %-9.2f %.2f%n",
        n,
        d,
        height(n, d),
        (double) insertComparisons / n,
        (double) insertMoves / n,
        (double) heap.comparisons / n,
        (double) heap.moves / n,
        (double) (insertComparisons + heap.comparisons) / n);
  }

  /** Number of edges from the root to the deepest leaf of a complete d-ary tree with n nodes. */
  private static int height(int n, int d) {
    int height = 0;
    long nodesUpToLevel = 1;
    long levelSize = 1;
    while (nodesUpToLevel < n) {
      levelSize *= d;
      nodesUpToLevel += levelSize;
      height++;
    }
    return height;
  }

  @Override
  boolean less(int a, int b) {
    comparisons++;
    return a < b;
  }

  @Override
  void moved() {
    moves++;
  }
}
