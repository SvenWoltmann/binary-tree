package eu.happycoders.binarytree.heap.benchmark;

import eu.happycoders.binarytree.heap.benchmark.PriorityQueueBuildBenchmark.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Why does the {@link PriorityQueue} constructor take longer on shuffled input than on descending
 * input, although it makes fewer comparisons? Four ways to build the same heap with Floyd's method,
 * each on random, ascending and descending input:
 *
 * <ul>
 *   <li>{@code intArray} – on a primitive {@code int[]}: no references, only the branches of the
 *       sift down depend on the input;
 *   <li>{@code intArrayBranchlessChild} – the same, but the smaller child is chosen without a
 *       branch;
 *   <li>{@code integersAllocatedAscending} – the {@code PriorityQueue} constructor on the list of
 *       {@link PriorityQueueBuildBenchmark}, whose {@code Integer} objects are allocated in
 *       ascending order before the list is shuffled: on random input, neighbors in the list lie far
 *       apart in memory;
 *   <li>{@code integersAllocatedInListOrder} – the same, but each {@code Integer} is allocated
 *       after shuffling, in list order: neighbors in the list are neighbors in memory.
 * </ul>
 *
 * <p>{@code intArray} includes copying the input array, the same cost for every order.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(3)
public class HeapBuildLocalityBenchmark {

  @Param({"1000", "100000", "1000000"})
  private int size;

  @Param({"RANDOM", "ASCENDING", "DESCENDING"})
  private Order order;

  private int[] input;
  private List<Integer> integersAllocatedAscending;
  private List<Integer> integersAllocatedInListOrder;

  @Setup
  public void setUp() {
    integersAllocatedAscending = PriorityQueueBuildBenchmark.createInput(size, order);
    input = integersAllocatedAscending.stream().mapToInt(Integer::intValue).toArray();

    integersAllocatedInListOrder = new ArrayList<>(size);
    for (int value : input) {
      integersAllocatedInListOrder.add(newInteger(value));
    }
  }

  @SuppressWarnings({"removal", "java:S2129"}) // a new object, also for values in the Integer cache
  private static Integer newInteger(int value) {
    return new Integer(value);
  }

  @Benchmark
  public int[] intArray() {
    int[] heap = input.clone();
    for (int i = heap.length / 2 - 1; i >= 0; i--) {
      siftDown(heap, i);
    }
    return heap;
  }

  /**
   * Like {@link #intArray()}, but chooses the smaller child without a branch, so that only the
   * decision to stop depends on the input. If shuffled input is slow because the processor
   * mispredicts which child is smaller, this variant closes most of the gap.
   */
  @Benchmark
  public int[] intArrayBranchlessChild() {
    int[] heap = input.clone();
    for (int i = heap.length / 2 - 1; i >= 0; i--) {
      siftDownBranchlessChild(heap, i);
    }
    return heap;
  }

  @Benchmark
  public PriorityQueue<Integer> integersAllocatedAscending() {
    return new PriorityQueue<>(integersAllocatedAscending);
  }

  @Benchmark
  public PriorityQueue<Integer> integersAllocatedInListOrder() {
    return new PriorityQueue<>(integersAllocatedInListOrder);
  }

  /**
   * Sift down as in {@code PriorityQueue.siftDownComparable()}: moves a hole instead of swapping.
   */
  private static void siftDown(int[] heap, int index) {
    int length = heap.length;
    int value = heap[index];
    int half = length >>> 1;
    while (index < half) {
      int child = 2 * index + 1;
      int childValue = heap[child];
      int right = child + 1;
      if (right < length && heap[right] < childValue) {
        child = right;
        childValue = heap[right];
      }
      if (value <= childValue) {
        break;
      }
      heap[index] = childValue;
      index = child;
    }
    heap[index] = value;
  }

  /**
   * Sift down that adds the comparison result (0 or 1) to the index of the left child. Without a
   * right child, the left child is compared with itself, and the result is 0.
   */
  private static void siftDownBranchlessChild(int[] heap, int index) {
    int length = heap.length;
    int value = heap[index];
    int half = length >>> 1;
    while (index < half) {
      int child = 2 * index + 1;
      int right = Math.min(child + 1, length - 1);
      child += heap[right] < heap[child] ? 1 : 0;
      int childValue = heap[child];
      if (value <= childValue) {
        break;
      }
      heap[index] = childValue;
      index = child;
    }
    heap[index] = value;
  }
}
