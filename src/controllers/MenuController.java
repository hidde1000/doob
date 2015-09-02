package controllers;

import javafx.fxml.FXML;

public class MenuController {
	
	@FXML
	public void playSinglePlayer() {
		Application.Main.loadScene("/Game.fxml");
	}
	
}
