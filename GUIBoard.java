package checkersBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUIBoard extends JPanel {
	static int row = 8, column = 8, size = 75;
	Board b;
	
	public GUIBoard() {
		super (new GridLayout(row,column));
		b = new Board();
		this.setPreferredSize(new Dimension (row*size,row*size));
		this.setVisible(true);
		// drawboard() draws the state of board before the game starts.
        drawBoard();
	} // end of GUIBoard constructor

	// calltoExecute function is called from the nextMoveFunction with initial and
	//	destination tiles as parameters.It will execute the move of piece and
	//	either change the position of pieces or pop up illegal move message.
	
	public void calltoExecute(int initTile, int destTile) {
		if(b.executeMoves(initTile, destTile)) {
			drawBoard();
		}
		else {
			popUpError();
		}
	} // end of calltoExecute

	//	drawborad() function draws the checkersboards with pieces in their respective positions.
	
	private void drawBoard() {
		
		// removes any previous tiles present on board
		removeAll();
		Tile[][] matrix = b.getboard();
		for(int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if((i+j) % 2 == 1) {
				      this.add(new SquareBox(i,j, Color.WHITE, size/row, null)); //There is no Tile object on white squares so we pass null
				} // end of if
				else {
				      this.add(new SquareBox(i,j, Color.GRAY, size/row, matrix[i][j].occupant));
				} // end of else
			} // end of for j
		} // end of for i
	} // end of drawBoard
	
	//	PopUpError() will show a dialog box with an error message if teh move is illegal
	private void popUpError(){
		JOptionPane.showMessageDialog(null, "IllegalMove");
	} // end of popUperror()
	
} // end of GUIBoard class
	