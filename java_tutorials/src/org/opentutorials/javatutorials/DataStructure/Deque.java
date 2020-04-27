package org.opentutorials.javatutorials.DataStructure;

public class Deque {
	static int MAX_ARRAY_SIZE = 10000;
	
	private int[] deque_front;
	private int front_front;
	private int front_rear;
	
	private int[] deque_back;
	private int back_front;
	private int back_rear;
	
	public Deque()
	{
		deque_front = new int[MAX_ARRAY_SIZE];
		front_front = 0;
		front_rear = 0;
		
		deque_back = new int[MAX_ARRAY_SIZE];
		back_front = 0;
		back_rear = 0;
	}
	
	public void front_push(int p)
	{
		deque_front[front_rear++] = p;
	}
	
	public void back_push(int p)
	{
		deque_back[back_rear++] = p;
	}
	
	public int front_pop()
	{
		if(front_front == front_rear && back_front == back_rear)
			return -1;
		
		int q = -1;
		if(front_front != front_rear)
		{
			q = deque_front[--front_rear];
		}
		else
		{
			q = deque_back[back_front++];
		}
		
		return q;
	}

	public int back_pop()
	{
		if(front_front == front_rear && back_front == back_rear)
			return -1;
		
		int q = -1;
		if(back_front != back_rear)
		{
			q = deque_back[--back_rear];
		}
		else
		{
			q = deque_front[front_front++];
		}
		
		return q;
	}
	
	public int size()
	{
		return (front_rear - front_front) + (back_rear - back_front);
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
		
		int q = -1;
		if(front_front != front_rear)
		{
			q = deque_front[front_rear - 1];
		}
		else
		{
			q = deque_back[back_front];
		}
		
		return q;
	}
	
	public int back()
	{
		if(size() <= 0)
			return -1;//비어있음
		
		int q = -1;
		if(back_front != back_rear)
		{
			q = deque_back[back_rear - 1];
		}
		else
		{
			q = deque_front[front_front];
		}
		
		return q;
	}
}
