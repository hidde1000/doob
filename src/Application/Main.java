package Application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	
	private static Stage stage;
	private static AnchorPane pane;

	@Override
	public void start(Stage stageIn) {
		stage = stageIn;
		stage.setTitle("DooB");
		loadScene("/Menu.fxml");
	}
	
	public static void loadScene(String path){
		try {
			// Load the achorpane 
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(path));
			pane = (AnchorPane) loader.load();
			
			// Set the pane onto the scene
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.setResizable(true);
			stage.show();
			System.out.println(path + " loaded on the stage");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Something went wrong while loading the fxml file");
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
