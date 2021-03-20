package pasture;

import java.util.ArrayList;
import java.util.List;

import gui.PastureGUIPane;

public class PastureMap {
	
	private Coordinate[][] coordinates;
	private int coorRow;
	private int coorCol;
	private Coordinate startCoor;
	private Coordinate exitCoor;
	private List<Cow> listOfCows = new ArrayList<Cow>();
	
	public PastureMap(int coorRow, int coorCol) {
		this.coorRow = coorRow;
		this.coorCol = coorCol;
		coordinates = new Coordinate[coorRow][coorCol];
		pastureMapData();
	}
	
	public void pastureMapData() {
		for(int rowCount = 0; rowCount < coorRow; rowCount++) {
			for(int colCount = 0; colCount < coorCol; colCount++) {
				if(rowCount == 0 || rowCount == this.coorRow - 1) {
					Coordinate pointer = new Coordinate(rowCount, colCount, 'F');
					coordinates[rowCount][colCount] = pointer;
				}
				else if(colCount == 0 || colCount == this.coorCol - 1) {
					Coordinate pointer = new Coordinate(rowCount, colCount, 'F');
					coordinates[rowCount][colCount] = pointer;
				}
				else {
					Coordinate pointer = new Coordinate(rowCount, colCount, ' ');
					coordinates[rowCount][colCount] = pointer;
				}
			}
		}
		startCoor = new Coordinate(0, 1, 'S');
		exitCoor = new Coordinate(this.coorRow - 1, this.coorCol - 2, 'E');
		coordinates[0][1] = startCoor;
		coordinates[this.coorRow - 1][this.coorCol - 2] = exitCoor;
	}
	
	public boolean moveVerifier(int row, int col) {

		if(row < 0 || col < 0 || row >= this.coorRow || col >= this.coorCol) {
			return false;
		}
		if(coordinates[row][col].getPoint() == 'F') {
			return false;
		}
		return true;
	}

	public Coordinate[][] getCoordinates() {
		return coordinates;
	}
	
	public void resetCoordinates() {
		coordinates = new Coordinate[coorRow][coorCol];
		pastureMapData();
	}
	
	public List<Cow> getListOfCows() {
		return listOfCows;
	}
	
	public void moveCowsHere() {
		for(Cow c : listOfCows) {
			c.cowMove();
		}
	}
}