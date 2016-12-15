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
import javafx.stage.Stage;

public class HighscoreController implements Initializable
{
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
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