package pasture;

import java.util.Random;

import gui.PastureGUIPane;

public class Cow {
	private int currCowRow;
	private int currCowCol;
	private PastureMap pastureMapObj;
	private Random rand;
	private int prevCowRow = -1;
	private int prevCowCol = -1;
	
	public Cow(int row, int col, PastureMap pastureMapObj) {
		this.currCowRow = row;
		this.currCowCol = col;
		this.pastureMapObj = pastureMapObj;
		this.rand = new Random();
	}
	
	public void cowMove() {
		prevCowRow = currCowRow;
		prevCowCol = currCowCol;
		boolean availableMove = false; 
		int iterations = 0;
		do {
			int chance = rand.nextInt(4);
			switch(chance) {
			case 0://up
				if(cowMoveVerifier(currCowRow + -1, currCowCol + 0) == true) {
					this.currCowRow += -1;
					this.currCowCol += 0;
					return;
				}
				iterations++; 
				break;
			case 1://down
				if(cowMoveVerifier(currCowRow + 1, currCowCol + 0) == true) {
					this.currCowRow += 1;
					this.currCowCol += 0;
					return;	
				}
				iterations++;
				break;
			case 2://left
				if(cowMoveVerifier(currCowRow + 0, currCowCol + -1) == true) {
					this.currCowRow += 0;
					this.currCowCol += -1;
					return;
				}
				iterations++; 
				break;
			case 3:	//right
				if(cowMoveVerifier(currCowRow + 0, currCowCol + 1) == true) {
					this.currCowRow += 0;
					this.currCowCol += 1;
					return;
				}
				iterations++; 
				break;
			}
		}while(iterations != 4);
	}
	
	public boolean cowMoveVerifier(int row, int col) {
		if(pastureMapObj.moveVerifier(row, col) == false) {
			return false;
		}
		if(pastureMapObj.getCoordinates()[row][col].getPoint() == 'C' || pastureMapObj.getCoordinates()[row][col].getPoint() == 'S' || pastureMapObj.getCoordinates()[row][col].getPoint() == 'E') {
			return false;
		}
		return true;
	}

	public int getCurrCowRow() {
		return currCowRow;
	}

	public int getCurrCowCol() {
		return currCowCol;
	}
	
	public int getPrevCowRow() {
		return prevCowRow;
	}

	public int getPrevCowCol() {
		return prevCowCol;
	}
}