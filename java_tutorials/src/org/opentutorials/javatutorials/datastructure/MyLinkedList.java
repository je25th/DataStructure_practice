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
		
		if(index == size-1)//맨 마지막
			return tail.data;
		
		Node<E> tmp = head;//인덱스 0
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
		Node<E> tmp = head;//인덱스 0
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
		Node<E> tmp = head;//인덱스 0
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
		
		//생성자
		public MyListIterator(int i) 
		{
			//유효하지 않은 인덱스
			if(i < 0 || i > size)//Iterator의 사이즈는 리스트사이즈+1   *□-*-□-*-□*
				throw new IndexOutOfBoundsException("Invalid index " + i);

			lastItemReturned = null;
			if(i == size)//맨마지막 인덱스
			{
				nextItem = null;// --□--□--□*
				index = size;
			}
			else//중간인덱스
			{
				nextItem = head;//  *0--1--2--
				for(index = 0; index < i; index++)// 0--1--□-*-index--□--5--
					nextItem = nextItem.next;
			}
		}
		
		@Override
		public boolean hasNext() {
			return nextItem != null;//다음 아이템이 널이 아니면 트루
		}

		@Override
		public E next() {
			//다음 노드 없음
			if(!hasNext())
				throw new NoSuchElementException();
			
			lastItemReturned = nextItem;
			nextItem = nextItem.next;
			index++;
			return lastItemReturned.data;
		}

		@Override
		public boolean hasPrevious() {
			//생성하고 아직 아무동작도 안한경우 or 삭제한경우  + 맨 앞이어서 이전이 없는 경우
			return ( (nextItem==null && size != 0) || lastItemReturned != null);
		}

		@Override
		public E previous() {
			//이전 노드 없음
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
			//삭제할게 없음
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
		public void set(E e) {//덮어쓰기
			if(lastItemReturned == null)
				throw new IllegalStateException();
			
			lastItemReturned.data = e;
		}

		@Override
		public void add(E e) {//nextItem 앞에 추가함
			if(head == null)//빈 리스트에 추가
			{
				head = new Node<E>(e);
				tail = head;
			}
			else if(nextItem == head)//제일 앞에 추가
			{
				Node<E> tmp = new Node<E>(e);
				tmp.next = nextItem;
				nextItem.prev = tmp;
				head = tmp;
			}
			else if(nextItem == null)//제일 끝에 추가
			{
				Node<E> tmp = new Node<E>(e);
				tail.next = tmp;
				tmp.prev = tail;
				tail = tmp;
			}
			else//중간에 추가
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
