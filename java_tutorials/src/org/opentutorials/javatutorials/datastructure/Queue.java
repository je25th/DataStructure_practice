package org.opentutorials.javatutorials.datastructure;

public class Queue {

	private int[] queue;
	private int front;
	private int rear;
	
	public Queue()
	{
		queue = new int[2000000];
		front = 0;
		rear = 0;
	}
	
	public void push(int p)
	{
		queue[rear++] = p;
	}
	
	public int pop()
	{
		if(front == rear)
			return -1;
		
		return queue[front++];
	}
	
	public int size()
	{
		return rear - front;
	}
	
	public int empty()
	{
		if(size() <= 0)
			return 1;//비어있음
		else
			return 0;
	}
	
	public int front()
	{
		if(size() <= 0)
			return -1;//비어있음
		else
			return queue[front];
	}
	
	public int back()
	{
		if(size() <= 0)
			return -1;//비어있음
		else
			return queue[rear-1];
	}
}
