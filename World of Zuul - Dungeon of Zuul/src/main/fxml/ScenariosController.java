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
import main.singleton;

public class ScenariosController implements Initializable
{
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		
	}

	@FXML
	private void scenario1(ActionEvent event) throws IOException
	{
		singleton.setScenario(1);
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void scenario2(ActionEvent event) throws IOException
	{
		singleton.setScenario(2);
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void build(ActionEvent event) throws IOException
	{
		singleton.setScenario(0);
		Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		Scene scene = new Scene(root);
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(scene);
		stage.show();
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
