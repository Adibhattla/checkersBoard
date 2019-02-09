package checkersBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

//import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
// This class deals with the interaction between the game moves and the 
// actions performed on the board ( next button pressed)
public class UserInterface extends JFrame {
	int row, column;
	String[] splited;
	int counter = 0;
	JFrame window = new JFrame ();
	GUIBoard checkboxes;
	JButton Next = new JButton("Next");
	JButton Previous = new JButton("Previous");
	private String[] movesStored2;
	
	public UserInterface(String[] movesStored2) {
		super();
		this.movesStored2= movesStored2;
		checkboxes = new GUIBoard();
		window.setBackground(Color.GRAY);
		checkboxes.setBackground(Color.black);
		window.setSize(1000, 1000);
		checkboxes.setPreferredSize(new Dimension(500,500));
		window.add(checkboxes);
		window.add(Next);
		window.add(Previous);
		window .setLocation(100,100);
		window.setLayout(new FlowLayout());
	
			ActionListener nextMove = new ActionListener() {
			
				public void actionPerformed(ActionEvent e) {
					nextMoveFunction();
					checkboxes.revalidate();
					checkboxes.repaint();
			    } //end of actionPerformed
			}; // end of Action Listener
		
		Next.addActionListener(nextMove);
		window.setVisible(true);
	} // end of UserInterface Constructor
	
	//	The function is called when the next button in the UI is pressed.
	//	The moves that are stored in the array are converted to integers.
	//	the split () separates the value before and after '-'
	private void nextMoveFunction() {
		try{
			String getMoves = movesStored2[counter];
			splited = getMoves.split("[\\-]");
		} catch (Exception e) {
			}
		int initTile = Integer.parseInt(splited[0]);
		int destTile = Integer.parseInt(splited[1]);
		checkboxes.calltoExecute(initTile,destTile);
		counter++;
	} //end of nextMoveFunction
} // end of UserInterface class

		
	


