package eu.happycoders.binarytree.heap.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The JDK's {@link PriorityQueue} as a min-heap (built from a collection with Floyd's method) and
 * as a max-heap (with {@link Comparator#reverseOrder()}, filled with {@code addAll()}).
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
@SuppressWarnings("squid:S106") // using stdout in this example
public class PriorityQueueHeapExample {

  public static void main(String[] args) {
    List<Integer> numbers = List.of(6, 17, 15, 12, 9, 4, 3, 7, 13);

    PriorityQueue<Integer> minHeap = new PriorityQueue<>(numbers);
    System.out.println("min-heap: " + minHeap);

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    maxHeap.addAll(numbers);
    System.out.println("max-heap: " + maxHeap);

    List<Integer> polled = new ArrayList<>();
    while (!maxHeap.isEmpty()) {
      polled.add(maxHeap.poll());
    }
    System.out.println("poll():   " + polled);
  }
}
