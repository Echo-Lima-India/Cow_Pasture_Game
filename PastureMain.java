package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pasture.PastureMap;

public class PastureMain extends Application{
						//Player moves by WASD keys
						//DOUBLE click to change fence to empty space
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setResizable(true);
		PastureMap pastMap = new PastureMap(27, 30);
		PastureGUIPane pastureGUI = new PastureGUIPane(27, 30, pastMap);
		Scene scenery = new Scene(pastureGUI, 900, 900);
		scenery.getStylesheets().add(getClass().getResource("hw8CSSfile.css").toExternalForm());
		primaryStage.setScene(scenery);
		primaryStage.show();
		pastureGUI.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.W) {
                    pastureGUI.moveKeys(-1, 0, pastMap);
                }
                if (event.getCode() == KeyCode.S) {
                    pastureGUI.moveKeys(1, 0, pastMap);
                }
                if (event.getCode() == KeyCode.A) {
                    pastureGUI.moveKeys(0, -1, pastMap);
                }
                if (event.getCode() == KeyCode.D) {
                    pastureGUI.moveKeys(0, 1, pastMap);
                }
			}
		});
		
	}
}