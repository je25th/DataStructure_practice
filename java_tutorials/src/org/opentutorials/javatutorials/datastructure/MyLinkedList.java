package org.opentutorials.javatutorials.datastructure;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public E get(int index)
	{
		return listIterator(index).next();
/*
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Invalid index " + index);
		
		if(index == size-1)//�� ������
			return tail.data;
		
		Node<E> tmp = head;//�ε��� 0
		for(int i=1; i<=index; i++)
			tmp = tmp.next;
		
		return tmp.data;
*/
	}
	
	public void add(int index, E item)
	{
		listIterator(index).add(item);
	}
	
	public int indexOf(E item)
	{
		Node<E> tmp = head;//�ε��� 0
		int index = 0;
		do
		{
			if(tmp.data == item)
				return index;
			tmp = tmp.next;
			index++;
		} while(tmp.next != null);
		
		return -1;
	}
	
	public E remove(int index)
	{
		ListIterator<E> li = new MyListIterator(index);
		E tmp = li.next();
		li.remove();
		
		return tmp;
	}
	
	public void set(int index, E item)
	{
		ListIterator<E> li = new MyListIterator(index);
		E tmp = li.next();
		li.set(item);
	}
	
	public boolean remove(E item)
	{
		Node<E> tmp = head;//�ε��� 0
		do
		{
			if(tmp.data == item)
				return true;
			tmp = tmp.next;
		} while(tmp.next != null);
		
		return false;
	}
	
	public int size()
	{
		return size;
	}
	
	//Iterator  /  ListIterator
	public Iterator<E> iterator()
	{
		return new MyListIterator(0);
	}
	
	public ListIterator<E> listIterator()
	{
		return new MyListIterator(0);
	}
	
	public ListIterator<E> listIterator(int index)
	{
		return new MyListIterator(index);
	}
	
	
	private static class Node<T> 
	{
		private Node<T> next;
		private Node<T> prev;
		private T data;
		
		public Node(T dataItem)
		{
			next = null;
			prev = null;
			data = dataItem;
		}
	}
	
	private class MyListIterator implements ListIterator<E> 
	{
		private Node<E> nextItem;
		private Node<E> lastItemReturned;
		private int index;//0 ~ size
		
		//������
		public MyListIterator(int i) 
		{
			//��ȿ���� ���� �ε���
			if(i < 0 || i > size)//Iterator�� ������� ����Ʈ������+1   *��-*-��-*-��*
				throw new IndexOutOfBoundsException("Invalid index " + i);

			lastItemReturned = null;
			if(i == size)//�Ǹ����� �ε���
			{
				nextItem = null;// --��--��--��*
				index = size;
			}
			else//�߰��ε���
			{
				nextItem = head;//  *0--1--2--
				for(index = 0; index < i; index++)// 0--1--��-*-index--��--5--
					nextItem = nextItem.next;
			}
		}
		
		@Override
		public boolean hasNext() {
			return nextItem != null;//���� �������� ���� �ƴϸ� Ʈ��
		}

		@Override
		public E next() {
			//���� ��� ����
			if(!hasNext())
				throw new NoSuchElementException();
			
			lastItemReturned = nextItem;
			nextItem = nextItem.next;
			index++;
			return lastItemReturned.data;
		}

		@Override
		public boolean hasPrevious() {
			//�����ϰ� ���� �ƹ����۵� ���Ѱ�� or �����Ѱ��  + �� ���̾ ������ ���� ���
			return ( (nextItem==null && size != 0) || lastItemReturned != null);
		}

		@Override
		public E previous() {
			//���� ��� ����
			if(!hasPrevious())
				throw new NoSuchElementException();
			
			lastItemReturned = nextItem;
			nextItem = nextItem.prev;
			index--;
			return lastItemReturned.data;
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void remove() {
			//�����Ұ� ����
			if(lastItemReturned == null)
				throw new IllegalStateException();
			
			lastItemReturned.prev.next = nextItem;
			if(nextItem != null)
				nextItem.prev = lastItemReturned.prev;
			lastItemReturned = null;
			
			index--;
			size--;
		}

		@Override
		public void set(E e) {//�����
			if(lastItemReturned == null)
				throw new IllegalStateException();
			
			lastItemReturned.data = e;
		}

		@Override
		public void add(E e) {//nextItem �տ� �߰���
			if(head == null)//�� ����Ʈ�� �߰�
			{
				head = new Node<E>(e);
				tail = head;
			}
			else if(nextItem == head)//���� �տ� �߰�
			{
				Node<E> tmp = new Node<E>(e);
				tmp.next = nextItem;
				nextItem.prev = tmp;
				head = tmp;
			}
			else if(nextItem == null)//���� ���� �߰�
			{
				Node<E> tmp = new Node<E>(e);
				tail.next = tmp;
				tmp.prev = tail;
				tail = tmp;
			}
			else//�߰��� �߰�
			{
				Node<E> tmp = new Node<E>(e);
				tmp.prev = nextItem.prev;
				tmp.next = nextItem;
				
				nextItem.prev.next = tmp;
				
				nextItem.prev = tmp;
			}
			
			size++;
			index++;
		}
	}
	
}
