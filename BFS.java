package ProjectFiles;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class BFS {
	
	public static ArrayList <Node> open;
	public static ArrayList <Node> closed;
	public static JTextArea ta;
	
	public static int indexOfSpace;

	public static boolean state;
	
	public static Node start;
	public static Node end;
	
	public static Node x;


	@SuppressWarnings("deprecation")
	public static void BFSSearch(String initial, String goal)
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
		
		
		start = new Node(initial);
		end = new Node(goal);
		
		x = new Node();
		int xCount = 0;
		
		final long startTime = System.currentTimeMillis();
		
		open.add(start);
		
		while(!open.isEmpty() && state == false)
		{
			x = open.get(0);
			open.remove(0);
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
					if(find(open, closed, calcSpaceUp(x.data)) == false)
					{
						open.add(new Node(calcSpaceUp(x.data),x));
					}
				}
				
				if(spaceLeft())
				{
					if(find(open, closed, calcSpaceLeft(x.data)) == false)
					{
						open.add(new Node(calcSpaceLeft(x.data),x));
					}
				}
				
				if(spaceDown())
				{
					if(find(open, closed, calcSpaceDown(x.data)) == false)
					{
						open.add(new Node(calcSpaceDown(x.data),x));
					}
				}
								
				if(spaceRight())
				{
					if(find(open, closed, calcSpaceRight(x.data)) == false)
					{
						open.add(new Node(calcSpaceRight(x.data),x));
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


	
	public static boolean find(ArrayList <Node> o, ArrayList <Node> c, String data)
	{
		if(!o.isEmpty())
		{
			for(int k = 0; k < o.size(); k++)
			{
				if(o.get(k).getData().toString().equals(data))
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
	
}

