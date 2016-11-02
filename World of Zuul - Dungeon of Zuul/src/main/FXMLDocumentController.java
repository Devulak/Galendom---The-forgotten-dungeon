package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class FXMLDocumentController implements Initializable {
	
	private Game game;
	
	
	@FXML
	private ProgressBar healthBar;
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		Command command = game.parser.getCommand("attack");
		boolean endGame = game.processCommand(command);
		healthBar.setProgress((double)game.hero.health/game.hero.healthMax);
		if(endGame)
		{	
			Platform.exit();
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		game = new Game();
		game.play();
	}	
	
}
