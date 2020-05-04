package org.opentutorials.javatutorials.eclipse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int ARRAY_SIZE = 20000;
	static Queue q;
	static int[] check;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer stk = new StringTokenizer(br.readLine());
		int node_count = Integer.parseInt(stk.nextToken());
		int edge_count = Integer.parseInt(stk.nextToken());
		int start_node = Integer.parseInt(stk.nextToken());
		int[][] graph = new int[ARRAY_SIZE][ARRAY_SIZE];
		for(int i=0; i<edge_count; i++)
		{
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		
		check = new int[graph.length];
		DFS(graph, start_node);
		System.out.println();

		q = new Queue();
		check = new int[graph.length];
		q.push(start_node);
		check[start_node] = 1;//방문했음:1 / 아직 미방문:0
		BFS(graph);
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void DFS(int[][] array, int visit)
	{
		System.out.print(visit + " ");
		
		check[visit] = 1;//방문했음:1 / 아직 미방문:0
		for(int i=0; i<array.length; i++)
		{
			if(array[visit][i] == 1 && check[i] == 0)
			{
				DFS(array, i);
			}
		}
	}
	
	public static void BFS(int[][] array)
	{
		int pop = q.pop();
		if(pop == -1)
			return;
		
		System.out.print(pop + " ");
		
		for(int i=0; i<array.length; i++)
		{
			if(array[pop][i] == 1 && check[i] == 0)
			{
				q.push(i);
				check[i] = 1;
			}
		}
		
		BFS(array);
	}
	
	static class Queue
	{
		int[] queue = new int[ARRAY_SIZE];
		private int front = 0;
		private int rear = 0;
		
		public void push(int item)
		{
			queue[rear++] = item;
		}
		
		public int pop()
		{
			if(front < rear)
				return queue[front++];
			
			return -1;
		}
	}

}
