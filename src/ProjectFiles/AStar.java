package ProjectFiles;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AStar {
	
	public static ArrayList <Node> open;
	public static ArrayList <Node> closed;
	
	public static int indexOfSpace;
	
	public static JTextArea ta;

	public static boolean state;

	public static Node start;
	public static Node end;
	public static Node x;

	@SuppressWarnings("deprecation")
	public static void ASSearch(String initial, String goal)
	{
		
		//
		 JFrame frame;
		  JLabel l = new JLabel();
		  l.setText("Processing, May Take A Few Minutes");
		  frame = new JFrame("Loading");
		  frame.setSize(300, 100);
		  
		  frame.add(l);

		  frame.setLocationRelativeTo(null);
		  frame.setVisible(true);
		  frame.show();
		//
		open = new ArrayList <Node>();
		closed = new ArrayList <Node>();
		ta = new JTextArea(20, 20);
		state = false;
		
		Comparator<Node> nodeComparator = new NodeQueueComparator();
        PriorityQueue<Node> openQueue = new PriorityQueue<Node>(nodeComparator);
		
		start = new Node(initial);		
		end = new Node(goal);
		
		int xCount = 0;
		
		x = new Node();
		
		final long startTime = System.currentTimeMillis();

		openQueue.add(start);

		while(!openQueue.isEmpty() && state == false)
		{

			x = openQueue.remove();
			xCount++;
				
			if(x.getData().equals(end.getData()))
			{
				state = true;
			}
			else
			{
				closed.add(x);
				
				indexOfSpace = x.getData().indexOf("b");
				
				if(spaceUp())
				{
					if(find(openQueue, closed, calcSpaceUp(x.data)) == false)
					{
						openQueue.add(new Node(calcSpaceUp(x.data),x,x.getMoveNum()+1,calcHeuristic(calcSpaceUp(x.data))));
					}
				}
				
				if(spaceLeft())
				{
					if(find(openQueue, closed, calcSpaceLeft(x.data)) == false)
					{
						openQueue.add(new Node(calcSpaceLeft(x.data),x,x.getMoveNum()+1,calcHeuristic(calcSpaceLeft(x.data))));
					}
				}
				
				if(spaceDown())
				{
					if(find(openQueue, closed, calcSpaceDown(x.data)) == false)
					{
						openQueue.add(new Node(calcSpaceDown(x.data),x,x.getMoveNum()+1,calcHeuristic(calcSpaceDown(x.data))));
					}
				}
				
				
				if(spaceRight())
				{
					if(find(openQueue, closed, calcSpaceRight(x.data)) == false)
					{
						openQueue.add(new Node(calcSpaceRight(x.data),x,x.getMoveNum()+1,calcHeuristic(calcSpaceRight(x.data))));
					}
				}
			}
		}
		
		final long stopTime = System.currentTimeMillis();
		
		frame.dispose();
		
		String disp = "";
		
		ta.append("***********************"+"\n");
		int q = -1;
		if(state)
		{
			q = 0;
			while(x.getData() != start.getData())
			{
				q++;
				disp = x.getData().substring(0,3)+"\n"+x.getData().substring(3,6)+"\n"+x.getData().substring(6);
				ta.insert(disp+"\n",0);
				ta.insert("\n",0);
				ta.insert("\n",0);
				x = x.getParent();
			}
			ta.insert(start.getData().substring(0,3)+"\n"+start.getData().substring(3,6)+"\n"+start.getData().substring(6), 0);
		}
		
		
		
		
		ta.insert("***********************"+"\n", 0);
		
		ta.insert("Code Execution Time: "+((stopTime-startTime)/1000.00)+"s"+"\n", 0);
		
		ta.insert("States Visited: "+xCount+"\n", 0);
		
		ta.insert("Solution Path Length : "+q+"\n", 0);
		
		if(state)
		{
			ta.insert("State : "+"Success"+"\n", 0);
		}
		else
		{
			ta.insert("State : "+"Failure"+"\n", 0);

		}
		
		JOptionPane.showMessageDialog(null, new JScrollPane(ta));
	}
	
	public static boolean spaceUp()
	{
		if(indexOfSpace > 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean spaceDown()
	{
		if(indexOfSpace < 6)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean spaceLeft()
	{
		if(indexOfSpace!= 0 && indexOfSpace != 3 && indexOfSpace != 6)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean spaceRight()
	{
		if(indexOfSpace != 2 && indexOfSpace != 5 && indexOfSpace != 8)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public static String calcSpaceUp(String data)
	{
	
		return data.replace(""+data.charAt(indexOfSpace-3), "A").replace(data.charAt(indexOfSpace), data.charAt(indexOfSpace-3)).replace("A", ""+data.charAt(indexOfSpace));
	}
	
	public static String calcSpaceDown(String data)
	{
		return data.replace(""+data.charAt(indexOfSpace+3), "A").replace(data.charAt(indexOfSpace), data.charAt(indexOfSpace+3)).replace("A", ""+data.charAt(indexOfSpace));
	}
	
	public static String calcSpaceLeft(String data)
	{	
		return data.replace(""+data.charAt(indexOfSpace-1), "A").replace(data.charAt(indexOfSpace), data.charAt(indexOfSpace-1)).replace("A", ""+data.charAt(indexOfSpace));
	}
	
	public static String calcSpaceRight(String data)
	{	
		return data.replace(""+data.charAt(indexOfSpace+1), "A").replace(data.charAt(indexOfSpace), data.charAt(indexOfSpace+1)).replace("A", ""+data.charAt(indexOfSpace));
	}


	
	public static boolean find(PriorityQueue <Node> o, ArrayList <Node> c, String data)
	{
		
		ArrayList <Node> m = new ArrayList <Node>(o);
		
		if(!m.isEmpty())
		{
			for(int k = 0; k < o.size(); k++)
			{
				if(m.get(k).getData().toString().equals(data))
				{
					return true;
				}
			}
		}
		
		if(!c.isEmpty())
		{
			for(int k = 0; k < c.size(); k++)
			{
				if(c.get(k).getData().toString().equals(data))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	public static int calcHeuristic(String a)
	{
		int retValue = 0;
		
		for(int k = 0; k < 9; k++)
		{
			if(end.getData().charAt(k) != a.charAt(k))
			{
				retValue++;
			}
		}
		
		return retValue;
	}
	
	public static class NodeQueueComparator implements Comparator <Node>
	{

		public int compare(Node a, Node b) 
		{
			if(a.getOutOfPlace()+a.getMoveNum() < b.getOutOfPlace()+b.getMoveNum())
			{
				return -1;
			}
			
			if(a.getOutOfPlace()+a.getMoveNum() > b.getOutOfPlace()+a.getMoveNum())
			{
				return 1;
			}
			return 0;
		}	
	}
}

