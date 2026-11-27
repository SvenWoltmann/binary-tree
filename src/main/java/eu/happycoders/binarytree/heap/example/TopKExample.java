package eu.happycoders.binarytree.heap.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Finds the k largest of a million random numbers with a min-heap that never holds more than k
 * elements – O(n log k) instead of sorting all numbers in O(n log n).
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
@SuppressWarnings("squid:S106") // using stdout in this example
public class TopKExample {

  public static void main(String[] args) {
    int[] numbers = new Random(42).ints(1_000_000, 0, 1_000_000_000).toArray();
    System.out.println(largest(numbers, 5));
  }

  static List<Integer> largest(int[] numbers, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    for (int number : numbers) {
      if (heap.size() < k) {
        heap.offer(number);
      } else if (number > heap.peek()) {
        heap.poll();
        heap.offer(number);
      }
    }
    List<Integer> result = new ArrayList<>(heap);
    result.sort(Comparator.reverseOrder());
    return result;
  }
}
