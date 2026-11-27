package eu.happycoders.binarytree.heap.benchmark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
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
 * Builds a {@link PriorityQueue} from a list of {@code size} distinct integers – once with the
 * constructor, which arranges the elements into a heap with Floyd's method ({@code heapify()}),
 * once with {@code addAll()}, which inserts them one by one.
 *
 * <p>{@code addAllPresized} gives the queue its final capacity up front, so that the comparison
 * with the constructor does not include the growing of the array.
 *
 * <p>The input is a permutation of {@code 0 ... size-1}: shuffled with a fixed seed, ascending (the
 * best case for single inserts into a min-heap: no element moves up) or descending (the worst case:
 * every element moves up to the root).
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(3)
public class PriorityQueueBuildBenchmark {

  public enum Order {
    RANDOM,
    ASCENDING,
    DESCENDING
  }

  @Param({"1000", "100000", "1000000"})
  private int size;

  @Param({"RANDOM", "ASCENDING", "DESCENDING"})
  private Order order;

  private List<Integer> list;

  @Setup
  public void setUp() {
    list = createInput(size, order);
  }

  static List<Integer> createInput(int size, Order order) {
    List<Integer> list = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      list.add(i);
    }
    switch (order) {
      case RANDOM -> Collections.shuffle(list, new Random(42));
      case ASCENDING -> {
        // already ascending
      }
      case DESCENDING -> Collections.reverse(list);
    }
    return list;
  }

  @Benchmark
  public PriorityQueue<Integer> constructor() {
    return new PriorityQueue<>(list);
  }

  @Benchmark
  public PriorityQueue<Integer> addAll() {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    queue.addAll(list);
    return queue;
  }

  @Benchmark
  public PriorityQueue<Integer> addAllPresized() {
    PriorityQueue<Integer> queue = new PriorityQueue<>(size);
    queue.addAll(list);
    return queue;
  }
}
