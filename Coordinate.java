package pasture;

public class Coordinate {
	
	private int row;
	private int column;
	private int point;
	
	private char blank = ' ';
	private char fence = 'F';
	private char start = 'S';
	private char exit = 'E';
	private char cow = 'C';
	private char dirt = 'D';
	
	public Coordinate(int row, int column, char point) {
		this.row = row;
		this.column = column;
		if(point == blank) {
			this.point = point;
		}
		else if(point == fence) {
			this.point = point;
		}
		else if(point == start) {
			this.point = point;
		}
		else if(point == exit) {
			this.point = point;
		}
		else if(point == cow) {
			this.cow = cow;
		}
		else if(point == dirt) {
			this.dirt = dirt;
		}
	}
	

	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}