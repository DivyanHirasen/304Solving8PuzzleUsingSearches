package ProjectFiles;

import javax.swing.JOptionPane;

import ProjectFiles.BFS;
import ProjectFiles.DFS;
import ProjectFiles.AStar;

public class MyAssg {
	
	public static String start;
	public static String end;
		
	public static void main(String[] args) 
	{
		        
		String [] colours = {"BFS", "DFS", "A*"};//0,1,2
		int search = 0;
		
		while(search == 0)
		{
			
			 start = JOptionPane.showInputDialog("Enter Initial State:");
		     end = JOptionPane.showInputDialog("Enter Goal State:");
		       
			int selection = JOptionPane.showOptionDialog(null,
				    "Select A Search Type",
				    "Search",
				    JOptionPane.DEFAULT_OPTION,
				    JOptionPane.QUESTION_MESSAGE,
				    null,
				    colours,
				    colours[0]);

			if(selection == 0)
			{
				BFS.BFSSearch(start, end);
			}
			
			if(selection == 1)
			{
				DFS.DFSSearch(start, end);
			}
			
			if(selection == 2)
			{
				AStar.ASSearch(start, end);
			}
			
			search = JOptionPane.showConfirmDialog (null, "Would You Like To Search A New State ?","Search Again", JOptionPane.YES_NO_OPTION);
		}
	}
	
}
