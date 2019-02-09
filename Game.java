package checkersBoard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//****This is the driver class.The file inputs are read and stored in the
//array as string.****
public class Game {

		public static void main(String[] args) throws FileNotFoundException {
		String movesStored[] = new String[50];
		int movesStoredCounter=0;
		File file = new File("MovesFile/Moves");
		Scanner sc = new Scanner(file);
			try{
					while(sc.hasNextLine()) {
						movesStored[movesStoredCounter] = sc.nextLine();
						movesStoredCounter++;
					}
			} catch (Exception e) {
				}
		sc.close();
//the array which has the moves is passed as a parameter to the UserInterface class.
		new UserInterface(movesStored);
			} // end of main
} // end of Game 
