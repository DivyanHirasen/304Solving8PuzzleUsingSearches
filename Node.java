package ProjectFiles;

public class Node 
{
	protected String data;
	protected Node parent;
	protected int moveNum;
	protected int outOfPlace;
	
	public Node(String data, Node nextNode, int moveNum)
	{
		this.data = data;
		this.parent = nextNode;
		this.moveNum = moveNum;
		this.outOfPlace = 0;
	}
	
	public Node(String data, Node nextNode, int moveNum, int outOfPlace)
	{
		this.data = data;
		this.parent = nextNode;
		this.moveNum = moveNum;
		this.outOfPlace = outOfPlace;
	}
	
	public Node(String data, int moveNum, int outOfPlace)
	{
		this.data = data;
		this.parent = null;
		this.moveNum = moveNum;
		this.outOfPlace = outOfPlace;
	}
	
	public Node(String data, Node nextNode)
	{
		this.data = data;
		this.parent = nextNode;
		this.moveNum = 0;
		this.outOfPlace = 0;

	}
	
	public Node(String data, int moveNum)
	{
		this.data = data;
		this.parent = null;
		this.moveNum = moveNum;
		this.outOfPlace = 0;

	}
	
	public Node(String data)
	{
		this.data = data;
		this.parent = null;
		this.moveNum = 0;
		this.outOfPlace = 0;

	}
	
	public Node()
	{
		this.data = null;
		this.parent = null;
	}
	
	public String getData()
	{
		return this.data;
	}
	
	public Node getParent()
	{
		return this.parent;
	}
	
	public int getMoveNum()
	{
		return this.moveNum;
	}
	
	public int getOutOfPlace()
	{
		return this.outOfPlace;
	}
	
	public void setOutOfPlace(int out)
	{
		this.outOfPlace = out;
	}
}
