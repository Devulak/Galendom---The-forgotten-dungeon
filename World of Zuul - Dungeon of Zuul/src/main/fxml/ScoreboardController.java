package main.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.Game;
import main.GameInterface;
import main.Score;
import main.Scoreboard;

public class ScoreboardController implements Initializable
{
	private GameInterface game = Game.getInstance();
	
	@FXML
	private ListView scoreboard;
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		ObservableList<Score> itemsTemp = FXCollections.observableArrayList((List) game.getScoreboard());
		scoreboard.setItems(itemsTemp);
	}
	
	@FXML
	private void back(ActionEvent event) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("Mainmenu.fxml"));
		Scene scene = new Scene(root);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(scene);
		stage.show();
	}
}
