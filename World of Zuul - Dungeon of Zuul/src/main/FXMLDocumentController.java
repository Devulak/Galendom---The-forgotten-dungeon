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
	private void handleButtonAction(ActionEvent event)
	{
		game.attack();
		updateHealth();
		updateExperience();
		updateRoomInventory();
	}
	
	@FXML
	private void takeItem(ActionEvent event)
	{
		Item selectedItem = (Item) roomInventory.getSelectionModel().getSelectedItem();
		Item tempItem = game.hero.inventory.add(selectedItem);
		game.currentRoom.inventory.swap(selectedItem, tempItem);
		
		// Update inventories
		updatePlayerInventory();
		updateRoomInventory();
	}
	
	@FXML
	private void dropItem(ActionEvent event)
	{
		Item selectedItem = (Item) playerInventory.getSelectionModel().getSelectedItem();
		Item droppedItem = game.currentRoom.inventory.add(selectedItem);
		game.hero.inventory.swap(selectedItem, droppedItem);
		
		// Update inventories
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
