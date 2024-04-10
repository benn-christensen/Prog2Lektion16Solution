package opgave01.models;

import java.util.Iterator;

public class LinkedList<E> implements ListEaaa<E> {
    private Node<E> head;
    private Node<E> tail;

    private int size = 0;

    public LinkedList() {
        head = new Node<>(null);
        tail = new Node<>(null, head);
        head.next = tail;
    }

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e, tail);
        tail.next.next = newNode;
        tail.next = newNode;
        size++;
    }

    @Override
    public boolean remove(E e) {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(e)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, head.next);
        head.next = newNode;
        size++;
    }

    @Override
    public E getFirst() {
        if (head.next == tail) {
            throw new java.util.NoSuchElementException();
        }
        return head.next.element;
    }

    @Override
    public void removeFirst() {
        if (head.next == tail) {
            throw new java.util.NoSuchElementException();
        }
        head.next = head.next.next;
        size--;
    }

    @Override
    public boolean contains(E e) {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        head.next = tail;
        tail.next = head;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> currentNode = head.next;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.element;
    }

    public E get2(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return getRecursive(head.next, index);
    }

    private E getRecursive(Node<E> node, int index) {
        if (index == 0) {
            return node.element;
        }
        return getRecursive(node.next, index - 1);
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        Node<E> newNode = new Node<>(e, currentNode.next);
        currentNode.next = newNode;
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        Node<E> removedNode = currentNode.next;
        currentNode.next = currentNode.next.next;
        size--;
        return removedNode.element;
    }

    @Override
    public int indexOf(E e) {
        Iterator<E> it = iterator();
        int index = 0;
        while (it.hasNext()) {
            if (it.next().equals(e)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(head, tail);
    }

    private class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T element) {
            this.element = element;
        }

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    public class LinkedListIterator<T> implements Iterator<T> {
        private Node<T> currentNode;
        private Node<T> previousNode;
        private Node<T> lastNode;

        public LinkedListIterator(Node<T> currentNode, Node<T> lastNode) {
            this.currentNode = currentNode;
            this.lastNode = lastNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode.next != lastNode;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
            return currentNode.element;
        }

        @Override
        public void remove() {
            previousNode.next = currentNode.next;
            if (currentNode.next == lastNode) {
                lastNode = previousNode;
            }
        }
    }
}
