package org.abs.hl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;

/**
 * A list which uses hash map like structure to store elements; however, unlike
 * {@link HashMap}, it stores objects instead of {@link Entry} elements.
 * 
 * @author Yogendra Sharma
 *
 */
public class HashList<E> implements List<E> {
	private static final int INITIAL_BIN_COUNT = 1 << 5;

	private Node<E>[] bins;
	private int size;

	@SuppressWarnings("unchecked")
	public HashList() {
		this.bins = (Node<E>[]) new Node[INITIAL_BIN_COUNT];
		this.size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		int i = e.hashCode() & (this.bins.length - 1);

		if (this.bins[i] == null) {
			this.bins[i] = new Node<>(e);

		} else {
			Node<E> node = this.bins[i];

			while (node.next != null) {
				node = node.next;
			}

			node.next = new Node<E>(e);
		}

		this.size++;

		return true;
	}

	@Override
	public boolean remove(Object o) {
		int i = o.hashCode() & (this.bins.length - 1);

		if (this.bins[i] == null) {
			return false;
		}

		Node<E> prev = null, curr = this.bins[i];

		do {
			if (curr.getVal() == o || curr.getVal().equals(o)) {
				if (prev == null) {
					this.bins[i] = curr.next;
				} else {
					prev.next = curr.next;
				}

				this.size--;

			} else {
				prev = curr;
			}

		} while ((curr = curr.next) != null);

		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}

	@Override
	public E get(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	static class Node<E> {
		private E val;
		private Node<E> next;

		public Node(E val) {
			this.val = val;
		}

		public Node(E val, Node<E> next) {
			this.val = val;
			this.next = next;
		}

		public E getVal() {
			return val;
		}

		public void setVal(E val) {
			this.val = val;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}
	}

}
