package eu.happycoders.binarytree.heap.benchmark;

import eu.happycoders.binarytree.heap.benchmark.PriorityQueueBuildBenchmark.Order;
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
 * Binary heap against 4-ary and 8-ary heap ({@link IntDaryHeap}): inserting {@code size} shuffled
 * integers one by one ({@code insertAll}), and inserting them and then removing the minimum until
 * the heap is empty ({@code insertAllRemoveAll}). The difference between the two is the time for
 * removing.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(3)
public class DaryHeapBenchmark {

  @Param({"2", "4", "8"})
  private int d;

  @Param({"1000", "100000", "1000000"})
  private int size;

  private int[] input;

  @Setup
  public void setUp() {
    input = createInput(size);
  }

  static int[] createInput(int size) {
    return PriorityQueueBuildBenchmark.createInput(size, Order.RANDOM).stream()
        .mapToInt(Integer::intValue)
        .toArray();
  }

  @Benchmark
  public IntDaryHeap insertAll() {
    IntDaryHeap heap = new IntDaryHeap(d, size);
    for (int value : input) {
      heap.insert(value);
    }
    return heap;
  }

  @Benchmark
  public long insertAllRemoveAll() {
    IntDaryHeap heap = insertAll();
    long sum = 0;
    while (heap.size() > 0) {
      sum += heap.removeMin();
    }
    return sum;
  }
}
