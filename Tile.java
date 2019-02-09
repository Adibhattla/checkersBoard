package checkersBoard;

// has Piece object
public class Tile {
protected Piece occupant;

	//	sets up the color to the piece on the board initially based on their location 
	public Tile(int row, int column) {
		if (row <= 2) {
			this.occupant = new Piece("Black");
		} else if (row >= 5) {
			this.occupant = new Piece("Red");
		} else {
			this.occupant = null;
		}	   
	} // end of Tile()

} // end of Tile class
