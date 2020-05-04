package org.opentutorials.javatutorials.datastructure;

public class BST<T extends Comparable<T>> {//자기 자신과 비교 가능한 모든 자료형 E
	
	Node<T> root;
	
	public BST()
	{
		root = null;
	}
	
	public BST(Node<T> nd)
	{
		root = nd;
	}
	
	public Node<T> search(T item)//검증완
	{
		Node<T> now_sel = root;
		while(now_sel != null)
		{
			int result = now_sel.data.compareTo(item);
			if(result == 0)//now_sel.data == item
				return now_sel;
			else if(result > 0)//now_sel.data > item
				now_sel = now_sel.left;
			else//now_sel.data < item
				now_sel = now_sel.right;
		}
		
		return null;//찾는 아이템이 트리 안에 존재하지 않음
	}
	
	public boolean insert(T item)//검증완
	{
		Node<T> now_sel = root;
		Node<T> before_sel = null;
		while(now_sel != null)//insert하기 적절한 리프노드까지 이동한다.
		{
			int result = now_sel.data.compareTo(item);
			before_sel = now_sel;//다음 노드로 이동하기 전에 지금 노드 저장해둠
			if(result == 0)//now_sel.data == item
				return false;//똑같은 데이터가 있으면 안됨
			else if(result > 0)//now_sel.data > item
				now_sel = now_sel.left;
			else//now_sel.data < item
				now_sel = now_sel.right;
		}
		
		Node<T> insert_node = new Node<>(item);
		//빈트리일 경우
		if(before_sel == null)
		{
			root = insert_node;
			return true;
		}
		//안비었을 경우 왼쪽이나 오른쪽에 새 노드 붙임
		int result = before_sel.data.compareTo(item);
		if(result > 0)//now_sel.data > item
			before_sel.left = insert_node;
		else//now_sel.data < item
			before_sel.right = insert_node;
		
		return true;
	}
	
	public T remove(T item)
	{
		Node<T> now_sel = root;//삭제할 노드
		
		Node<T> before_sel = null;//삭제할 노드의 부모노드
		int left_or_right = -1;//0:left 1:right
		int LEFT = 0, RIGHT = 1;
		
		while(now_sel != null)
		{
			int result = now_sel.data.compareTo(item);
			if(result == 0)//now_sel.data == item
				break;//찾음
			else if(result > 0)//now_sel.data > item
			{
				before_sel = now_sel;//다음 노드로 이동하기 전에 지금 노드 저장해둠
				left_or_right = LEFT;
				now_sel = now_sel.left;
			}
			else//now_sel.data < item
			{
				before_sel = now_sel;//다음 노드로 이동하기 전에 지금 노드 저장해둠
				left_or_right = LEFT;
				now_sel = now_sel.right;
			}
		}
		
		if(left_or_right == -1)//삭제할 노드가 존재하지 않음
			return null;
		else if(now_sel.left == null && now_sel.right == null)//삭제할 노드가 리프노드
		{
			if(left_or_right == LEFT)
				before_sel.left = null;//삭제
			else
				before_sel.right = null;
		}
		else if(now_sel.left != null && now_sel.right == null)//삭제할 노드의 왼쪽에만 자식노드가 있음
		{
			if(left_or_right == LEFT)
				before_sel.left = now_sel.left;//삭제할 노드의 자식노드를 삭제할 노드의 부모노드에 붙임
			else
				before_sel.right = now_sel.left;
		}
		else if(now_sel.left == null && now_sel.right != null)//삭제할 노드의 오른쪽에만 자식노드가 있음
		{
			if(left_or_right == LEFT)
				before_sel.left = now_sel.right;//삭제할 노드의 자식노드를 삭제할 노드의 부모노드에 붙임
			else
				before_sel.right = now_sel.right;
		}
		else//삭제할 노드의 왼쪽 오른쪽 모두에 자식노드가 있음
		{
			Node<T> suc_parent = find_successor_parent(now_sel);//석세서의 부모노드
			Node<T> sucessor = suc_parent.left;//석세서의 부모노드
			
			now_sel.data = sucessor.data;//석세서의 값을 삭제할 노드에 집어넣음
			//석세서 노드를 삭제
			if(sucessor.left == null && sucessor.right == null)//삭제할 노드가 리프노드
				suc_parent = null;//삭제
			else//삭제할 노드의 오른쪽에만 자식노드가 있음
				suc_parent = now_sel.right;//삭제할 노드의 자식노드를 삭제할 노드의 부모노드에 붙임
		}
		
		return now_sel.data;
	}
	
	private Node<T> find_successor_parent(Node<T> nd)
	{//nd노드의 값보다 크면서 가장 작은 값을 가진 노드(오름차순 정렬했을때 nd노드 바로 오른쪽 옆에 오는 값)
	 //석세서는 왼쪽 자식이 없음
		Node<T> result = null;
		
		if(nd.right != null)
		{
			nd = nd.right;
			do
			{
				result = nd;
				nd = nd.left;//successor
			} while(nd.left != null);
		}
		else if(nd.left != null)
		{
			nd = nd.left;
			do
			{
				result = nd;
				nd = nd.right;
			} while(nd.right != null);
		}
		
		return result;
	}
	
	public void print_tree()
	{
		inorder_travarsal(root);
	}
	
	private void inorder_travarsal(Node<T> now_sel)
	{
		if( now_sel == null )
			return;
		
		inorder_travarsal(now_sel.left);
		System.out.println(now_sel.data.toString());
		inorder_travarsal(now_sel.right);
	}
	
	static class Node<E extends Comparable<E>> {
		E data;
		Node<E> left, right;
		
		public Node()
		{
			data = null;
			left = null;
			right = null;
		}
		
		public Node(E item) 
		{
			data = item;
			left = null;
			right = null;
		}
	}

}
