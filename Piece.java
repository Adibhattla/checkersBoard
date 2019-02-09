package checkersBoard;

// This class stores the attributes of the Piece like color and type
public class Piece {
	protected String color;
	private Boolean king;

	public Piece(String color) {
		this.color = color;
		this.king =false;
	} // end of Piece constructor
	
	// override toString method
	public String toString() {
		return this.color;
	}
	// set the type of piece to King
	public void setKing() {
		this.king = true;
	}// end of setKing
	//	return whether Piece is king or not
	public boolean getKing() {
		return this.king;
	} // end of getKing

} // end of Piece
