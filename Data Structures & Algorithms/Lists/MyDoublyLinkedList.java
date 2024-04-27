import java.util.*;

public class MyDoublyLinkedList<E> extends MyAbstractSequentialList<E> implements Cloneable {
	private Node<E> head = new Node<E>(null);

	/** Create a default list */
	public MyDoublyLinkedList() {
		head.next = head;
		head.previous = head;
	}

	private static class Node<E> {
		E element;
		Node<E> previous;
		Node<E> next;

		public Node(E element) {
			this.element = element;
		}
	}

	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head.next;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != head) {
				result.append(", ");
			}
		}
		result.append("]");

		return result.toString();
	}

	private Node<E> getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Node<E> current = head;
		if (index < size / 2)
			for (int i = -1; i < index; i++)
				current = current.next;
		else
			for (int i = size; i > index; i--)
				current = current.previous;
		return current;
	}

	@Override
	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		if (index == 0) {
			Node<E> newNode = new Node<E>(e);
			newNode.next = head.next;
			newNode.previous = head;
			head.next = newNode;
			newNode.next.previous = newNode;
		}
		else {
			Node<E> prev = getNode(index - 1);
			Node<E> next = prev.next;
			Node<E> newNode = new Node<E>(e);
			prev.next = newNode;
			next.previous = newNode;
			newNode.previous = prev;
			newNode.next = next;
		}
		size++;
	}

	@Override
	public void clear() {
		Node<E> currentNode = head.next;
		while (currentNode != head) {
			Node<E> nextNode = currentNode.next;
			currentNode.previous = null;
			currentNode.next = null;
			currentNode = nextNode;
		}
		size = 0;
		head.next = head;
		head.previous = head;
	}

	@Override
	public boolean contains(E o) {
		for (Node<E> current = head.next; current != head; current = current.next) {
			E e = current.element;
			if (o == null ? e == null : o.equals(e))
				return true;
		}
		return false;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Node<E> currentNode = getNode(index);
		if (currentNode != null) {
			return currentNode.element;
		}
		return null;
	}

	@Override
	public int indexOf(E e) { // Note: Make sure that you check the equality with == for null  objects and with the equals() for others
		Node<E> currentNode = head.next;
		int index = 0;
		if (e == null) {
			while (currentNode != head) {
				if (currentNode.element == null) {
					return index;
				}
				currentNode = currentNode.next;
				index++;
			}
		}
		else {
			while (currentNode != head) {
				if (currentNode.element.equals(e)) {
					return index;
				}
				currentNode = currentNode.next;
				index++;
			}
		}
		return 0;
	}
	@Override
	public int lastIndexOf(E e) {
		Node<E> currentNode = head.previous;
		int index = size - 1;
		while(currentNode != head) {
			if (currentNode.element.equals(e)) {
				return index;
			}
			currentNode = currentNode.previous;
			index--;
		}
		return 0;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Node<E> nodeToRemove = getNode(index);
		Node<E> nodeBeforeRemove = nodeToRemove.previous;
		Node<E> nodeAfterRemove = nodeToRemove.next;
		nodeBeforeRemove.next = nodeAfterRemove;
		nodeAfterRemove.previous = nodeBeforeRemove;
		nodeToRemove.next = null;
		nodeToRemove.previous = null;
		size--;
		return nodeToRemove.element;
	}

	@Override
	public Object set(int index, E e) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Node<E> nodeToReplace = getNode(index);
		E replacedVal = nodeToReplace.element;
		nodeToReplace.element = e;
		return replacedVal;
	}

	@Override
	public E getFirst() {
		return head.next.element;
	}

	@Override
	public E getLast() {
		return head.previous.element;
	}

	@Override
	public void addFirst(E e) {
		add(0, e);
	}

	@Override
	public void addLast(E e) {
		add(size, e);
	}

	@Override
	public E removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node<E> removedFirst = head.next;
		head.next = removedFirst.next;
		removedFirst.next.previous = head;
		removedFirst.next = null;
		removedFirst.previous = null;
		size--;
		return removedFirst.element;
	}

	@Override
	public E removeLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node<E> removedLast = getNode(size - 1);
		Node<E> nodeBeforeRemove = removedLast.previous;
		nodeBeforeRemove.next = head;
		head.previous = nodeBeforeRemove;
		removedLast.next = null;
		removedLast.previous = null;
		size--;
		return removedLast.element;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new MyDoublyLinkedListIterator(index);
	}

	private static enum ITERATOR_STATE {
		CANNOT_REMOVE, CAN_REMOVE_PREV, CAN_REMOVE_CURRENT
	};

	private class MyDoublyLinkedListIterator implements ListIterator<E> {
		private Node<E> current; // node that holds the next element in the
									// iteration
		private int nextIndex; // index of current
		ITERATOR_STATE iterState = ITERATOR_STATE.CANNOT_REMOVE;

		private MyDoublyLinkedListIterator(int index) {
			if (index < 0 || index > size)
				throw new IndexOutOfBoundsException("iterator index out of bounds");
			current = getNode(index);
			nextIndex = index;
		}

		@Override
		public void add(E arg0) {
			Node<E> newNode = new Node<E>(arg0);
			Node<E> pred = current.previous;
			newNode.next = current;
			current.previous = newNode;
			newNode.previous = pred;
			pred.next = newNode;
			size++;
			nextIndex++;
			iterState = ITERATOR_STATE.CANNOT_REMOVE;
		}

		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public boolean hasPrevious() {
			if (nextIndex >= 1) {
				iterState = ITERATOR_STATE.CAN_REMOVE_PREV;
				return true;
			}
			else {
				return false;
			}
		}

		@Override
		public E next() {
			if (nextIndex >= size)
				throw new NoSuchElementException();
			E returnVal = current.element;
			current = current.next;
			nextIndex++;
			iterState = ITERATOR_STATE.CAN_REMOVE_PREV;
			return returnVal;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public E previous() {
			if(nextIndex <= 0) {
				throw new NoSuchElementException();
			}
			current = current.previous;
			nextIndex--;
			iterState = ITERATOR_STATE.CAN_REMOVE_CURRENT;
			return current.element;
		}

		@Override
		public int previousIndex() {
			return nextIndex - 1;
		}

		@Override
		public void remove() {
			switch (iterState) {
			case CANNOT_REMOVE:
				throw new IllegalStateException("Cannot remove");
			case CAN_REMOVE_PREV:
				if (size <= 0) {
					throw new IllegalStateException();
				}
				if (current.previous != null) {
					current.previous = current.previous.previous;
					current.previous.next = current;
				} else if (current.previous == head) {
					current.previous = head;
					head.next = current;
				}
				size--;
				iterState = ITERATOR_STATE.CANNOT_REMOVE;
				break;
			case CAN_REMOVE_CURRENT:
				if (current.next == head) {
					current.previous.next = head;
					head.previous = current.previous;
				}
				else {
					current.previous.next = current.next;
					current.next.previous = current.previous;
				}
				size--;
				iterState = ITERATOR_STATE.CANNOT_REMOVE;
				break;
			}
		}

		@Override
		public void set(E arg0) {
			if (iterState == ITERATOR_STATE.CANNOT_REMOVE)
				throw new IllegalStateException("Cannot set");
			if (current.element == null);
			current.previous.element = arg0;
		}
	}

	public Object clone() {
		try {
			//call super.clone()
			MyDoublyLinkedList<E> clone = (MyDoublyLinkedList<E>) super.clone();

			//create a new dummy head node for the clone
			clone.head = new Node<E>(null);

			//make the next and previous in the clone's dummy head node point to itself
			clone.head.next = clone.head;
			clone.head.previous = clone.head;

			//set initial size = 0
			clone.size = 0;

			//iterate through this linked list, add  each element to the clone
			for (Node<E> current = head.next; current != head; current = current.next) {
				clone.add(current.element);
			}

			//return the clone
			return clone;
		} catch (CloneNotSupportedException e) {
			//should not happen
			throw new RuntimeException(e);
		}
	}

	public boolean equals(Object other) {
		//check if this list is the same instance as other
		if (this == other) {
			return true;
		}

		//check if other is an instance of MyList
		if (!(other instanceof MyList<?>)) {
			return false;
		}

		MyList<?> otherAsList = (MyList<?>) other;
		if (this.size != otherAsList.size()) {
			return false;
		}

		//use list iterator class to perform comparisons
		Iterator<E> thisIterator = this.iterator();
		Iterator<?> otherIterator = otherAsList.iterator();
		//loop through all elements of each list
		while (thisIterator.hasNext()) {
			E thisElement = thisIterator.next();
			Object otherElement = otherIterator.next();

			//handle null elements using == and equals
			if (thisElement == null) {
				if (otherElement != null) {
					return false;
				}
			} else {
				if (!thisElement.equals(otherElement)) {
					return false;
				}
			}
		}
		return true;
	}
}
