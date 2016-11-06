package main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import main.item.Item;

public class FXMLDocumentController implements Initializable {
	
	private Game game;
	
	@FXML
	private ProgressBar healthBar;
	
	@FXML
	private Label healthStatus;
	
	@FXML
	private ProgressBar experienceBar;
	
	@FXML
	private Label experienceStatus;
	
	@FXML
	private Label experienceLevel;
	
	@FXML
	private ListView playerInventory;
	
	@FXML
	private ListView roomInventory;
	
	@FXML
	private TextArea dialouge;
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		Command command = game.parser.getCommand("attack");
		boolean endGame = game.processCommand(command);
		updateHealth();
		updateExperience();
		updateRoomInventory();
		if(endGame)
		{	
			Platform.exit();
		}
	}
	
	@FXML
	private void takeItem(ActionEvent event)
	{
		Command command = game.parser.getCommand("take " + roomInventory.getSelectionModel().getSelectedItem());
		boolean endGame = game.processCommand(command);
		updatePlayerInventory();
		updateRoomInventory();
	}
	
	@FXML
	private void dropItem(ActionEvent event)
	{
		Command command = game.parser.getCommand("drop " + playerInventory.getSelectionModel().getSelectedItem());
		boolean endGame = game.processCommand(command);
		updatePlayerInventory();
		updateRoomInventory();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		game = new Game();
		game.play();
		updateHealth();
		updateExperience();
		updatePlayerInventory();
		updateRoomInventory();
	}
	
	public void updateHealth()
	{
		healthStatus.setText(game.hero.getHealth() + "/" + game.hero.getMaxHealth() + " HP");
		healthBar.setProgress((double)game.hero.getHealth()/game.hero.getMaxHealth());
	}
	
	public void updateExperience()
	{
		experienceLevel.setText("Level " + game.hero.getLevel());
		experienceStatus.setText(game.hero.getExperience() + "/" + game.hero.getMaxExperience() + " EXP");
		experienceBar.setProgress((double)game.hero.getExperience()/game.hero.getMaxExperience());
	}
	
	public void updatePlayerInventory()
	{
		ObservableList<Item> itemsTemp = FXCollections.observableArrayList(game.hero.inventory.getContent());
		playerInventory.setItems(itemsTemp);
	}
	
	public void updateRoomInventory()
	{
		ObservableList<Item> itemsTemp = FXCollections.observableArrayList(game.currentRoom.inventory.getContent());
		roomInventory.setItems(itemsTemp);
	}
	
	public void updateDialouge()
	{
		dialouge.setText(dialouge.getText());
	}
}
