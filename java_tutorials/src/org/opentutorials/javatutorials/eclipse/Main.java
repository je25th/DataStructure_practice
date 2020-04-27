package org.opentutorials.javatutorials.eclipse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int node_count = Integer.parseInt(br.readLine());
		int[] array = new int[node_count];
		StringTokenizer stk = new StringTokenizer(br.readLine());
		for(int i=0; i<node_count; i++)
		{
			array[i] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(array);
		
		int find_num_count = Integer.parseInt(br.readLine());
		stk = new StringTokenizer(br.readLine());
		for(int i=0; i<find_num_count; i++)
		{
			int num = Integer.parseInt(stk.nextToken());
			bw.write(upper_index(array, num)-lower_index(array, num) + " ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int lower_index(int[] array, int num)
	{
		int mid = 0;
		int left = 0;
		int right = array.length - 1;
		
		while(left < right)
		{
			mid = (right + left)/2;
			if(array[mid] >= num)
				right = mid;
			else
				left = mid+1;
		}
		
		//if(left-1 >=0 && left-1 < array.length && array[left-1] != num)
		//	left = 0;
		
		return right;
	}
	
	public static int upper_index(int[] array, int num)
	{
		int mid = 0;
		int left = 0;
		int right = array.length - 1;
		
		while(left < right)
		{
			mid = (right + left)/2;
			if(array[mid] > num)
				right = mid;
			else
				left = mid+1;
		}
		
		//if(right+1 >=0 && right+1 < array.length && array[right+1] != num)
		//	right = 0;
		if(array[right] == num && right == array.length - 1)
			right++;
		
		return right;
	}

}
