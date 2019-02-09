package checkersBoard;



import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
// paints the piece 
public class SquareBox extends JPanel {
	
	private int row;
	private int column;
	private Piece p;

	public SquareBox(int row, int column, Color panelColor, int size, Piece p) {
		this.setBackground(panelColor);
		this.setSize(size, size);
		this.setOpaque(true);
		this.setVisible(true);
		if(p != null) {
			this.p = p;
		} else {
			this.p = null;
		}
	} // end of SquareBox()

	//	 fill the piece with colors or letters
	public void paintComponent(Graphics g) {
	   super.paintComponent(g);
		if(p != null) {
			if(p.color.equals("Red")) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLACK);
			}
			g.fillOval(column, row, 40, 40);
			g.setColor(Color.WHITE);
			if (p.getKing()==true) {
				g.drawString("K", 20+column*40, 20+row*40);
			}
		} // end of if 
	} // end of paintComponent	
} // end of SquareBox
