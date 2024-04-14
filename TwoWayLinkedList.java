import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<E> extends AbstractSequentialList<E> {

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        Node(E element) {
            this.element = element;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public TwoWayLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return new ListIteratorImpl(index);
    }

    private class ListIteratorImpl implements ListIterator<E> {
        private Node<E> nextNode;
        private int nextIndex;

        ListIteratorImpl(int index) {
            if (index == size) {
                nextNode = null;
            } else {
                nextNode = head;
                for (int i = 0; i < index; i++) {
                    nextNode = nextNode.next;
                }
            }
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> current = nextNode;
            nextNode = nextNode.next;
            nextIndex++;
            return current.element;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextNode == null) {
                nextNode = tail;
            } else {
                nextNode = nextNode.previous;
            }
            nextIndex--;
            return nextNode.element;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

    // Other methods (add, remove, get, isEmpty, contains) remain unchanged...
}
