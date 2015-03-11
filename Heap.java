import java.util.List;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {

	private static int INITIAL_SIZE_DEFAULT = 32;

	private ArrayList<T> backingArray;
	private int numberOfItems;
  
	Heap(int initialSize) {
		backingArray = new ArrayList<T>(initialSize);
	}

	Heap() {
		backingArray = new ArrayList<T>(INITIAL_SIZE_DEFAULT);
	}

	public void push(T item) {
		backingArray.set(numberOfItems++, item);
		bubbleUp();
	}

	public T pop() {
		T tmp = backingArray.get(0);
		remove();
		return tmp;
	}

	public void remove() {
		backingArray.set(0, backingArray.get(numberOfItems--));
		bubbleDown();	
	}

	public T peek() {
		return backingArray.get(0);
	}

  public void replace(T item) {
		backingArray.set(0, item);
		bubbleDown();
	}

	public boolean isEmpty() {
		return numberOfItems == 0;
	}

	public static <C> void heapify(C[] array) {
		
	}

	private void bubbleDown() {
		int itemIndex = 0;
		int leftChildIndex = 1;
		int rightChildIndex = 2;

		while(leftChildIndex < numberOfItems) {
			int smallerChildIndex = leftChildIndex;
			if (rightChildIndex < numberOfItems) smallerChildIndex = backingArray.get(rightChildIndex).compareTo(backingArray.get(leftChildIndex)) > 0 ? rightChildIndex : leftChildIndex;
			if (backingArray.get(itemIndex).compareTo(backingArray.get(smallerChildIndex)) < 0) {
				swapItems(smallerChildIndex, itemIndex);
				itemIndex = smallerChildIndex;
				leftChildIndex = (itemIndex << 1) + 1;
				rightChildIndex = leftChildIndex + 1;
			} else return;
		}
	}

	private void bubbleUp() {
		int itemIndex = numberOfItems - 1;
		int parentIndex = itemIndex - 1 >> 1;

		while(parentIndex >= 0)
			if (backingArray.get(itemIndex).compareTo(backingArray.get(parentIndex)) > 0) {
				swapItems(parentIndex, itemIndex);
				itemIndex = parentIndex;
				parentIndex = itemIndex - 1 >> 1;
			} else return;
	}

  private void swapItems(int a, int b) {
		T tmp = backingArray.get(a);
		backingArray.set(a, backingArray.get(b));
		backingArray.set(b, tmp);
	}
}
