package org.opentutorials.javatutorials.datastructure;

public class HeapSort {

	int[] heap;//index 1부터 시작
	int heap_lastIdx;
	
	public HeapSort(int[] arr)
	{
		heap = new int[arr.length + 1];//인덱스 1부터 시작하므로!
		heap_lastIdx = arr.length;
		
		for(int i=0; i<arr.length; i++)
		{
			heap[i+1] = arr[i];
		}
		printHeap();
		array2heap();
	}
	
	private void array2heap()
	{
		for(int idx=1; idx<=heap_lastIdx; idx++)
		{
			//왼쪽
			if(idx*2 <= heap_lastIdx && heap[idx] < heap[idx*2])
			{
				int tmp = heap[idx];
				heap[idx] = heap[idx*2];
				heap[idx*2] = tmp;
			}
			//오른쪽
			if(idx*2+1 <= heap_lastIdx && heap[idx] < heap[idx*2+1])
			{
				int tmp = heap[idx];
				heap[idx] = heap[idx*2+1];
				heap[idx*2+1] = tmp;
			}
			
			upHeap(idx);
		}
	}
	
	private void upHeap(int idx)
	{
		if(idx <= 1)
			return;
		
		if(heap[idx/2] < heap[idx])
		{
			int tmp = heap[idx];
			heap[idx] = heap[idx/2];
			heap[idx/2] = tmp;
		}
		
		upHeap(idx/2);
	}
	
	private void MAX_HEAPIFY(int idx)
	{
		if(idx < 0)
			return;
		else if(idx >= heap_lastIdx)
			return;
		
		int next_idx = -1;
		
		if(idx*2 <= heap_lastIdx && idx*2+1 > heap_lastIdx)
		{
			if(heap[idx] < heap[idx*2])
			{
				next_idx = idx*2;
			}
		}
		else if(idx*2+1 <= heap_lastIdx)
		{
			if(heap[idx*2] > heap[idx*2+1] && heap[idx] < heap[idx*2])
			{
				next_idx = idx*2;
			}
			else if(heap[idx*2] <= heap[idx*2+1] && heap[idx] < heap[idx*2+1])
				next_idx = idx*2+1;
		}
		
		if(next_idx != -1)
		{
			int tmp = heap[idx];
			heap[idx] = heap[next_idx];
			heap[next_idx] = tmp;
		}
		
		MAX_HEAPIFY(next_idx);
	}
	
	public void heapsort()
	{
		if(heap_lastIdx == 1)
			return;
		int tmp = heap[1];
		heap[1] = heap[heap_lastIdx];
		heap[heap_lastIdx] = tmp;
		heap_lastIdx--;
		
		MAX_HEAPIFY(1);
		
		heapsort();
	}
	
	public void printHeap()
	{
		for(int idx=1; idx<heap.length; idx++)
			System.out.print(heap[idx] + " ");
		System.out.println();
	}
}
