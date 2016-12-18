package main.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Game;
import main.GameInterface;

public class EndController implements Initializable
{
	private GameInterface game = Game.getInstance();
	
	@FXML
	private Label status;
	
	@FXML
	private TextField nameField;
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		if(game.getWon())
		{
			status.setText("You won!");
		}
		else
		{
			status.setText("You lost!");
		}
	}
	
	@FXML
	private void scoreboard(ActionEvent event) throws IOException
	{
		// add the score
		String name = nameField.getText();
		
		if(nameField.getText().length() > 3)
		{
			name = name.substring(0, 3);
		}
			
		name = name.toUpperCase();
		game.addScore(name);
		
		// goto scoreboard
		Parent root = FXMLLoader.load(getClass().getResource("Scoreboard.fxml"));
		Scene scene = new Scene(root);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(scene);
		stage.show();
	}
}
