package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		// Init of stage
		stage.setTitle("Galendom - The forgotten dungeon");
		stage.getIcons().add(new Image("/sprites/icon.png"));
		stage.setMinWidth(960);
		stage.setMinHeight(640);
		
		// game scene
		Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
		Scene scene = new Scene(root);
		
		// Show game scene
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}