package org.opentutorials.javatutorials.datastructure;

public class BST<T extends Comparable<T>> {//�ڱ� �ڽŰ� �� ������ ��� �ڷ��� E
	
	Node<T> root;
	
	public BST()
	{
		root = null;
	}
	
	public BST(Node<T> nd)
	{
		root = nd;
	}
	
	public Node<T> search(T item)//������
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
		
		return null;//ã�� �������� Ʈ�� �ȿ� �������� ����
	}
	
	public boolean insert(T item)//������
	{
		Node<T> now_sel = root;
		Node<T> before_sel = null;
		while(now_sel != null)//insert�ϱ� ������ ���������� �̵��Ѵ�.
		{
			int result = now_sel.data.compareTo(item);
			before_sel = now_sel;//���� ���� �̵��ϱ� ���� ���� ��� �����ص�
			if(result == 0)//now_sel.data == item
				return false;//�Ȱ��� �����Ͱ� ������ �ȵ�
			else if(result > 0)//now_sel.data > item
				now_sel = now_sel.left;
			else//now_sel.data < item
				now_sel = now_sel.right;
		}
		
		Node<T> insert_node = new Node<>(item);
		//��Ʈ���� ���
		if(before_sel == null)
		{
			root = insert_node;
			return true;
		}
		//�Ⱥ���� ��� �����̳� �����ʿ� �� ��� ����
		int result = before_sel.data.compareTo(item);
		if(result > 0)//now_sel.data > item
			before_sel.left = insert_node;
		else//now_sel.data < item
			before_sel.right = insert_node;
		
		return true;
	}
	
	public T remove(T item)
	{
		Node<T> now_sel = root;//������ ���
		
		Node<T> before_sel = null;//������ ����� �θ���
		int left_or_right = -1;//0:left 1:right
		int LEFT = 0, RIGHT = 1;
		
		while(now_sel != null)
		{
			int result = now_sel.data.compareTo(item);
			if(result == 0)//now_sel.data == item
				break;//ã��
			else if(result > 0)//now_sel.data > item
			{
				before_sel = now_sel;//���� ���� �̵��ϱ� ���� ���� ��� �����ص�
				left_or_right = LEFT;
				now_sel = now_sel.left;
			}
			else//now_sel.data < item
			{
				before_sel = now_sel;//���� ���� �̵��ϱ� ���� ���� ��� �����ص�
				left_or_right = LEFT;
				now_sel = now_sel.right;
			}
		}
		
		if(left_or_right == -1)//������ ��尡 �������� ����
			return null;
		else if(now_sel.left == null && now_sel.right == null)//������ ��尡 �������
		{
			if(left_or_right == LEFT)
				before_sel.left = null;//����
			else
				before_sel.right = null;
		}
		else if(now_sel.left != null && now_sel.right == null)//������ ����� ���ʿ��� �ڽĳ�尡 ����
		{
			if(left_or_right == LEFT)
				before_sel.left = now_sel.left;//������ ����� �ڽĳ�带 ������ ����� �θ��忡 ����
			else
				before_sel.right = now_sel.left;
		}
		else if(now_sel.left == null && now_sel.right != null)//������ ����� �����ʿ��� �ڽĳ�尡 ����
		{
			if(left_or_right == LEFT)
				before_sel.left = now_sel.right;//������ ����� �ڽĳ�带 ������ ����� �θ��忡 ����
			else
				before_sel.right = now_sel.right;
		}
		else//������ ����� ���� ������ ��ο� �ڽĳ�尡 ����
		{
			Node<T> suc_parent = find_successor_parent(now_sel);//�������� �θ���
			Node<T> sucessor = suc_parent.left;//�������� �θ���
			
			now_sel.data = sucessor.data;//�������� ���� ������ ��忡 �������
			//������ ��带 ����
			if(sucessor.left == null && sucessor.right == null)//������ ��尡 �������
				suc_parent = null;//����
			else//������ ����� �����ʿ��� �ڽĳ�尡 ����
				suc_parent = now_sel.right;//������ ����� �ڽĳ�带 ������ ����� �θ��忡 ����
		}
		
		return now_sel.data;
	}
	
	private Node<T> find_successor_parent(Node<T> nd)
	{//nd����� ������ ũ�鼭 ���� ���� ���� ���� ���(�������� ���������� nd��� �ٷ� ������ ���� ���� ��)
	 //�������� ���� �ڽ��� ����
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
