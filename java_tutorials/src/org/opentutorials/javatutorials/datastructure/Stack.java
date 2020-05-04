package org.opentutorials.javatutorials.datastructure;

public class Stack {
	public int[] SATCK;
	public int SATCK_TOP;
	
	public Stack()
	{
		SATCK = new int[100000];
		SATCK_TOP = 0;
	}
	
	public void reset()
	{
		SATCK = new int[100000];
		SATCK_TOP = 0;
	}
	
	public int size()
	{
		return SATCK_TOP;//0¿Ã∏È ∫Û∞Õ
	}
	
	public void push(int insert_num)
	{
		SATCK[SATCK_TOP] = insert_num;
		SATCK_TOP++;
	}
	
	public int pop()
	{
		if(SATCK_TOP == 0)
			return -1;
		
		int result = SATCK[SATCK_TOP-1];
		SATCK_TOP--;
		
		return result;
	}
}
