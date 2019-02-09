package checkersBoard;

import java.util.ArrayList;

//import checkersBoard.Board.Store;
import checkersBoard.Tile;

//The game logic and checking for legal and illegal moves
public class Board {
	
	//	has x and y co-ordinates
	private class Coord {
		int row, col;
		protected Coord(int r,int c) {
			this.row = r;
			this.col = c;
		}
	} // end of Coord
	
	private Coord[] boardHash;
	private int toRow,toCol,fromRow,fromCol,direction;
	private String player;
	private static Tile[][] board;
	private int fr = fromRow, fc = fromCol;

	public Board() {
	  board = new Tile[8][8];
	  boardHash = new Coord[33];
	  int hashCounter = 1,
		  offset = 0;
	  		//assign the co-ordinates to the tile number
    	  for(int i= 0; i<= 7; i++) { 
    		  for( int j= offset; j<= 7; j+= 2) {
    	    	  	board[i][j] = new Tile(i,j);
    	    	  	boardHash[hashCounter++] = new Coord(i,j);
    	    	  }
    		  offset = (offset+1) % 2;
    	  }
    	  // Red always plays first
    	  player = "Red";
    	  // red moves up the board
    	  direction = -1;
    }// end of Board()
	
	//	executes the move based on Tile number
	public boolean executeMoves(int initTile, int destTile) {
		this.fromRow = boardHash[initTile].row;
		this.fromCol = boardHash[initTile].col;
		this.toRow = boardHash[destTile].row;
		this.toCol = boardHash[destTile].col;
		// returns true if any changes on the board have been done		
    	  if (changeBoard()){
    		  return true;
    	  }
    	  // return false if move is illegal and no changes have to be doen on the board
    	  else{
    		  return false;
    	  }
    	} // end of executeMoves
	
		//	has the functions which determine whether the piece is making a legal move
		//	based off its color and type
	public boolean changeBoard() {
		  // if the move is legal then checks conditions and changes positions of 
		  // piece and returns true
		  if (isLegal()) {
			 board[toRow][toCol].occupant = board[fromRow][fromCol].occupant;
			 
			  if((player.equals("Red")&& (toRow == 0)) ||(player.equals("Black")&&(toRow == 7))) {
					board[toRow][toCol].occupant.setKing();
					System.out.println(board[toRow][toCol].occupant.color);
					System.out.println("Set as king");
				}
			  board[fromRow][fromCol].occupant = null;
			  changePlayer();  // changePlayer
			  return true;
		  } // end of if
		  //returns false if any move is illegal
		  else {
			  return false;
		  }// end of else
	  }// end of changeBoard
	  

	public boolean isLegal() {
			try{
		//	checks that initial tile is not empty,destination tile is empty and piece on initial tile is the correct player 			
		if ((board[fromRow][fromCol].occupant != null && board[fromRow][fromCol].occupant.color.equals(player)) && (board[toRow][toCol].occupant == null)) {
			//checks if piece is not king then goes in correct direction and if it is king then should move only 1 unit in either direction
			if (((board[fromRow][fromCol].occupant.getKing()==false)&&toRow == fromRow+direction)
					||((board[fromRow][fromCol].occupant.getKing()== true)&&toRow == fromRow+1)
					||((board[fromRow][fromCol].occupant.getKing()==true)&&toRow == fromRow-1)) {
				// checks if no other piece on the board is eligible to make  a jump
				if (isNormalMoveLegal()){
					return true;    			
				}// end check for normalmove
			} // end of checking normal moves
			// checks legality for jumps
			else {
				// checks if no more jump is possible after the dest given by user.
				if (isFinalJumpLegal()) {
				// checks if the jumps are possible as per user input
				if (isJumpLegal(fromRow,fromCol))
					return true;
				else 
					return false;
				}
				else 
					return false;
			} // end of else
		}// end of if
		} catch (Exception e){
			}
		return false;
	} //end of isLegal

	//	returns true if no other piece on the board can make a jump
	private boolean isNormalMoveLegal() {
		int counter =0;
			// iterates through each piece to check condition for every piece
			for (int i = 0;i<8;i++){
				for (int j = 0; j<8;j++){
					try{
						// checks for current player color
						if(board[i][j].occupant.color.equals(player)) {
						//checks if piece is normal or king
						if(board[i][j].occupant.getKing() == false) {
						//checks possible jump moves to wards right	
						if(board[i+(2*direction)][j+2].occupant == null && !board[i+direction][j+1].occupant.color.equals(player)&& board[i+direction][j+1].occupant!=null){
    					// increases counter if any piece can make a jump
						counter++;
    				}
    				// checks possible jump moves in left direction
					else if(board[i+(2*direction)][j-2].occupant == null && !board[i+direction][j-1].occupant.color.equals(player)&&board[i+direction][j-1].occupant!=null){
    					counter++;
    				}
    			}// end checks for not king
				// check for king piece		
    			else{
    				// up-right check 
    				if(board[i+2][j+2].occupant == null && !board[i+1][j+1].occupant.color.equals(player)&& board[i+1][j+1].occupant!=null){
    					counter++;
    				}
    				// up-left check
    				else if(board[i+2][j-2].occupant == null && !board[i+1][j-1].occupant.color.equals(player)&& board[i+1][j-1].occupant!=null){
					counter++;
				    }
    				// down left
    				else if(board[i-2][j-2].occupant == null && !board[i-1][j-1].occupant.color.equals(player)&& board[i-1][j-1].occupant!=null){
					counter++;
				    }
    				// down right
    				else if(board[i-2][j+2].occupant == null && !board[i-1][j+1].occupant.color.equals(player)&& board[i-1][j+1].occupant!=null){
					counter++;
				    }
    			} // end checks for king 
    		 } 
    		} catch (Exception e){
    	 }
      }
    }
    // if no piece can make a jump returns true else return false
    if (counter == 0) {
    	return true;
    } 
    else {
    	return false;
    } 

} // end of isNormalMoveLegal
    	 
	// return true if after all the jumps the piece can not make any more jumps
	private boolean isFinalJumpLegal() {
		// TODO Auto-generated method stub
		if (board[fromRow][fromCol].occupant.getKing() == false) {
		try {
			// checks if jump is possible towards right and returns false if possible jump
			if (board[toRow+(2*direction)][toCol+2].occupant== null 
				&& !board[toRow+direction][toCol+1].occupant.color.equals(player) ) {
				return false;
			}
		    } catch (Exception e1) {
              }
		try {
			// returns false if jump is possible towards left 
			if (board[toRow+(2*direction)][toCol-2].occupant== null 
					&& !board[toRow+direction][toCol-1].occupant.color.equals(player) ){
			return false;
			}
		} catch (Exception e2) {
		}
		}
		// checks jumps validity for king pieces
		else {
			try {
				// checks if jump is possible towards right-up and returns false if possible jump
				if (board[toRow+2][toCol+2].occupant== null 
					&& !board[toRow+1][toCol+1].occupant.color.equals(player) ) {
					return false;
				}
			    } catch (Exception e1) {
			    	}
			try {
				// checks if jump is possible towards right-down and returns false if possible jump
				if (board[toRow-2][toCol+2].occupant== null 
					&& !board[toRow-1][toCol+1].occupant.color.equals(player) ) {
					return false;
				}
			    } catch (Exception e1) {
			    	}
			try {
				// checks if jump is possible towards left-down and returns false if possible jump
				if (board[toRow-2][toCol-2].occupant== null 
					&& !board[toRow-1][toCol-1].occupant.color.equals(player) ) {
					return false;
				}
			    } catch (Exception e1) {
			    	}
			try {
				// checks if jump is possible towards left-up and returns false if possible jump
				if (board[toRow+2][toCol-2].occupant== null 
					&& !board[toRow+1][toCol-1].occupant.color.equals(player) ) {
					return false;
				}
			    } catch (Exception e1) {
		          }
		}
		return true;
} // end of finalJumpLegal
	
	 
    public boolean checkUpRight(){
    
    	if (board[fr+2][fc+2].occupant == null && board[fr+1][fc+1].occupant!=null && !board[fr+1][fc+1].occupant.color.equals(player)){ //****** fixed the not equals ************
			board[fr+1][fc+1].occupant = null; // the piece which of opposite color is taken
		if ((fr+2 == toRow) && (fc+2==toCol)) {
//			System.out.println("true 1");
				return true;
			// this if statement return true if the jump checked is equal to final destination and the pieces are moved in chngeboard func
		} else {

		    return isJumpLegal(fr+2,fc+2);// making another jump  
			}// end of else
	
    	}
    	 return false;
    }
//   the below four functions  iteratively checks for multiple jumps and returns true if dest tile is found after jumps.
//    their names suggest which direction they are checking.
    public boolean checkUpLeft(){
        
    	if (board[fr+2][fc-2].occupant == null && board[fr+1][fc-1].occupant!=null && !board[fr+1][fc-1].occupant.color.equals(player)){ //****** fixed the not equals ************
			board[fr+1][fc-1].occupant = null; // the piece which of opposite color is taken
		if ((fr+2 == toRow) && (fc-2==toCol)) {
				return true;
		} else {
           return isJumpLegal(fr+2,fc-2);// making another jump  
			}// end of else
	
    	}
    	 return false;
    }
    
    public boolean checkDownLeft(){
        
    	if (board[fr-2][fc-2].occupant == null && board[fr-1][fc-1].occupant!=null && !board[fr-1][fc-1].occupant.color.equals(player)){ //****** fixed the not equals ************
			board[fr-1][fc-1].occupant = null; // the piece which of opposite color is taken
		if ((fr-2 == toRow) && (fc-2==toCol)) {
				return true;
		} else {
			return isJumpLegal(fr-2,fc-2);// making another jump  
			}// end of else
    	}
    	 return false;
    }
    
    public boolean checkDownRight(){
        
    	if (board[fr-2][fc+2].occupant == null && board[fr-1][fc+1].occupant!=null && !board[fr-1][fc+1].occupant.color.equals(player)){ //****** fixed the not equals ************
			board[fr-1][fc+1].occupant = null; // the piece which of opposite color is taken
		if ((fr-2 == toRow) && (fc-2==toCol)) {
//			System.out.println("true 1");
				return true;
			// this if statement return true if the jump checked is equal to final destination and the pieces are moved in chngeboard func
		} else {

		    return isJumpLegal(fr-2,fc+2);// making another jump  
			}// end of else
    	}
    	 return false;
    }
    // returns tru if the jumps made are legal based on the type and color of piece
	public boolean isJumpLegal(int fr,int fc) {   
	   int jumpDirection;
	   int tr;
		if (direction == -1)
		{
			jumpDirection = -2;
		}
		else  {
			jumpDirection = 2;
		}
		tr = fr+jumpDirection;
		if(board[fromRow][fromCol].occupant.getKing()){
			if (checkUpRight()||checkUpLeft()||checkDownRight()||checkDownLeft()){
				return true;
			}
		}
		else{
			try {
					if (board[tr][fc+2].occupant == null && board[fr+direction][fc+1].occupant!=null && !board[fr+direction][fc+1].occupant.color.equals(player)){ //****** fixed the not equals ************
						board[fr+direction][fc+1].occupant = null; // the piece which of opposite color is taken
					if ((tr == toRow) && (fc+2==toCol)) {
					return true;
					} else {
						return isJumpLegal(tr,fc+2);// making another jump  
					}// end of else
		
					} // end of if , this if statement is checking to the right side of red piece or left of black piece
			}catch (Exception e){
		}
		try{
		if (board[tr][fc-2].occupant == null && board[fr+direction][fc-1].occupant!=null && !board[fr+direction][fc-1].occupant.color.equals(player)) {
			board[fr+direction][fc-1].occupant = null; //*** changed to -1
				if ((tr == toRow) && (fc-2==toCol)) {
//				System.out.println("true 2");
					return true;
				// this if statement return true if the jump checked is equal to final destination and the pieces are moved in chngeboard func
			} else {

				
				return isJumpLegal(tr,fc-2); // making another jump 
			}// end of else
				} 
		} catch(Exception e){
			System.out.println(e);
			}
		} // else
		
		return false;
}
	
	//	switches the turn of player once a legal move is executed 
	private void changePlayer() {
		if(player.equals("Red")) {
			player = "Black";
		} else {
			player = "Red";
		}
		direction*= -1;
	}
	//	returns the Tile or grid ,used for accessing the tile outside of this class
	public static Tile[][] getboard(){
		return board;
	}

} // end of Board class


