package eu.happycoders.binarytree.heap.benchmark;

/**
 * A min-heap of {@code int} values in which every node has up to {@code d} children – for the d-ary
 * heap measurements, not a general-purpose implementation (fixed capacity, no checks).
 *
 * <p>Node {@code i} has its children at {@code d * i + 1} to {@code d * i + d} and its parent at
 * {@code (i - 1) / d}. Like the JDK's {@code PriorityQueue}, sift up and sift down move a hole
 * instead of swapping elements.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
class IntDaryHeap {

  private final int d;
  private final int[] elements;
  private int size;

  IntDaryHeap(int d, int capacity) {
    this.d = d;
    this.elements = new int[capacity];
  }

  int size() {
    return size;
  }

  void insert(int value) {
    int index = size++;
    while (index > 0) {
      int parent = (index - 1) / d;
      int parentValue = elements[parent];
      if (!less(value, parentValue)) {
        break;
      }
      elements[index] = parentValue;
      moved();
      index = parent;
    }
    elements[index] = value;
  }

  int removeMin() {
    int min = elements[0];
    int last = elements[--size];
    if (size > 0) {
      siftDown(last);
    }
    return min;
  }

  private void siftDown(int value) {
    int index = 0;
    while (true) {
      int firstChild = d * index + 1;
      if (firstChild >= size) {
        break;
      }
      int end = Math.min(firstChild + d, size);
      int minChild = firstChild;
      int minValue = elements[firstChild];
      for (int child = firstChild + 1; child < end; child++) {
        int childValue = elements[child];
        if (less(childValue, minValue)) {
          minChild = child;
          minValue = childValue;
        }
      }
      if (!less(minValue, value)) {
        break;
      }
      elements[index] = minValue;
      moved();
      index = minChild;
    }
    elements[index] = value;
  }

  /** Overridden by {@link DaryHeapCount} to count comparisons. */
  boolean less(int a, int b) {
    return a < b;
  }

  /** Overridden by {@link DaryHeapCount} to count moved elements. */
  void moved() {
    // nothing to do here
  }
}
