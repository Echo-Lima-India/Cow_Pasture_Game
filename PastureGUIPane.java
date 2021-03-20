package gui;

import java.util.Random; 
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pasture.Cow;
import pasture.PastureMap;

public class PastureGUIPane extends BorderPane{
	
	private int totalRows;
	private int totalColumns;
	private Label[][] labelGrid; 
	private Label start;
	private Label goal;
	private int currentRow = 0;
	private int currentCol = 1;
	private Label player;
	private PastureMap PastureMapObj;
	private boolean value;
	private boolean cowValue;
	private Text title;
	private boolean playerStop = false;
	
	public PastureGUIPane() {
		
	}
	
	public PastureGUIPane(int totalRows, int totalColumns, PastureMap PastureMapObj) {
		super();
		this.PastureMapObj = PastureMapObj;
		this.totalRows = totalRows;
		this.totalColumns = totalColumns;
		labelGrid = new Label[this.totalRows][this.totalColumns];
		GUIStart();
	}
	
	public void GUIStart() {
		this.title = new Text("Rolling In The Pasture");
		this.title.setFont(new Font(40));
		HBox topBox = new HBox(title);
		topBox.alignmentProperty().set(Pos.CENTER);
		this.setTop(topBox);
		Button play = new Button("Generate Diff. Map / Play Again");
		HBox bottomBox = new HBox(play);
		bottomBox.alignmentProperty().set(Pos.CENTER);
		this.setBottom(bottomBox);
		creating();
		play.setOnAction(e -> {
			PastureMapObj.getListOfCows().clear();
			this.title.setText("Rolling In The Pasture");
			this.playerStop = false;
			creating();
		});
	}
	
	public void creating() {
		GridPane pastureGrid = new GridPane();
		this.setCenter(pastureGrid);
		int cowNum = 0;
		PastureMapObj.resetCoordinates(); 
		for(int rowCount = 0; rowCount < this.totalRows; rowCount++) {
			for(int colCount = 0; colCount < this.totalColumns; colCount++) {
				Label pastureLabel = new Label();
				labelGrid[rowCount][colCount] = pastureLabel;
				if(rowCount == 0 || rowCount == this.totalRows - 1) {
					pastureLabel.getStyleClass().add("fences");
					pastureGrid.add(pastureLabel, colCount, rowCount);
				}
				else if(colCount == 0 || colCount == this.totalColumns - 1) {
					pastureLabel.getStyleClass().add("fences");
					pastureGrid.add(pastureLabel, colCount, rowCount);
				}
				else {
					if(fenceAndEmptyRandom() == true) {
						pastureLabel.getStyleClass().add("fences");
						pastureGrid.add(pastureLabel, colCount, rowCount);
						PastureMapObj.getCoordinates()[rowCount][colCount].setPoint('F');
						
						final int X = rowCount;
						final int Y = colCount;
						pastureLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {

							@Override
							public void handle(MouseEvent mouse) {
								if(mouse.getClickCount() == 1) {
									fenceChange(pastureLabel);
									PastureMapObj.getCoordinates()[X][Y].setPoint(' ');
								}
							}
						});
					}
					else {
						if(cowRandom() == true && cowNum < 4) {
							pastureLabel.getStyleClass().add("cows");
							pastureGrid.add(pastureLabel, colCount, rowCount);
							PastureMapObj.getCoordinates()[rowCount][colCount].setPoint('C');
							Cow cow = new Cow(rowCount, colCount, PastureMapObj);
							PastureMapObj.getListOfCows().add(cow);
							cowNum++;
						}
						else {
							pastureLabel.getStyleClass().add("emptySpaces");
							pastureGrid.add(pastureLabel, colCount, rowCount);
						}
						
					}
				}
			}
		}
		this.currentRow = 0;
		this.currentCol = 1;
		start = labelGrid[0][1];
		start.setText("S");
		start.getStyleClass().remove(start.getStyleClass().size() - 1);
		start.getStyleClass().add("startGoalcombo");
		goal = labelGrid[this.totalRows - 1][this.totalColumns - 2];
		goal.setText("E");
		goal.getStyleClass().remove(goal.getStyleClass().size() - 1);
		goal.getStyleClass().add("startGoalcombo");
		player = labelGrid[0][1];
		player.getStyleClass().add("player");
		
	}

	public void moveKeys(int row, int col, PastureMap pasture) {
		if(pasture.moveVerifier(currentRow + row, currentCol + col) && !playerStop) {	
			
			if(pasture.getCoordinates()[currentRow + row][currentCol + col].getPoint() == 'C' || pasture.getCoordinates()[currentRow + row][currentCol + col].getPoint() == 'D') {
				Lose();
			}
			if(pasture.getCoordinates()[currentRow + row][currentCol + col].getPoint() == 'E') {
				Win();
			}
			
			
			
			player.getStyleClass().remove(player.getStyleClass().size()-1);
			player.getStyleClass().add("emptySpaces");
			this.currentRow += row;
			this.currentCol += col;
			player = labelGrid[this.currentRow][this.currentCol];
			player.getStyleClass().remove(player.getStyleClass().size() - 1);
			player.getStyleClass().add("player");	
			pasture.moveCowsHere();
			updateCows();
			pasture.moveCowsHere();
			updateCows();
		}
	}
	
	public boolean fenceAndEmptyRandom() {
		Random random = new Random();
		boolean value = (random.nextInt(7) == 0);
		this.value = value;
		return this.value;
	}
	
	public Label fenceChange(Label labelVal) {
		labelVal.getStyleClass().remove(getStyleClass().size() - 1);
		labelVal.getStyleClass().add("emptySpaces");
		return labelVal;
	}
	
	public boolean cowRandom() {
		Random random = new Random();
		boolean cowValue = (random.nextInt(90) == 0);
		this.cowValue = cowValue;
		return this.cowValue;
	}
	
	public void updateCows() {
		for(Cow c : PastureMapObj.getListOfCows()) {
			if(c.getPrevCowRow() > -1 && c.getPrevCowCol() > -1) {
				labelGrid[c.getPrevCowRow()][c.getPrevCowCol()].getStyleClass().remove(labelGrid[c.getPrevCowRow()][c.getPrevCowCol()].getStyleClass().size() - 1);
				labelGrid[c.getPrevCowRow()][c.getPrevCowCol()].getStyleClass().add("dirtySpace");
				PastureMapObj.getCoordinates()[c.getPrevCowRow()][c.getPrevCowCol()].setPoint('D');
				
			}
			labelGrid[c.getCurrCowRow()][c.getCurrCowCol()].getStyleClass().add("cows");
			PastureMapObj.getCoordinates()[c.getCurrCowRow()][c.getCurrCowCol()].setPoint('C');
		}
	}

	public void Lose() {
		title.setText("YOU LOSE! Try Again.");
		playerStop = true;
	}
	
	public void Win() {
		title.setText("YOU WIN! Congratulations.");
		playerStop = true;
	}
}