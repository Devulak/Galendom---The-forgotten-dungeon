package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
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
	private TextArea dialogue;
	
	@FXML
	private AnchorPane monster;
	
	@FXML
	private Label monsterLevel;
	@FXML
	private ProgressBar monsterHealth;
	@FXML
	private Label monsterHealthStatus;
	
	@FXML
	private void handleButtonAction(ActionEvent event)
	{
		game.attack();
		updateHealth();
		updateExperience();
		updateRoomInventory();
		updatePanel();
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
	
	@FXML
	private void useItem(ActionEvent event)
	{
		Item selectedItem = (Item) playerInventory.getSelectionModel().getSelectedItem();
		game.useItem(selectedItem);
		
		updatePlayerInventory();
		updateHealth();
		updateDialouge();
	}
	
	@FXML
	private void up(ActionEvent event)
	{
		game.goRoom("up");
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void down(ActionEvent event)
	{
		game.goRoom("down");
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void left(ActionEvent event)
	{
		game.goRoom("left");
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void right(ActionEvent event)
	{
		game.goRoom("right");
		updateRoomInventory();
		updatePanel();
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
		updatePanel();
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
		playerInventory.setItems(null);
		playerInventory.setItems(itemsTemp);
	}
	
	public void updateRoomInventory()
	{
		ObservableList<Item> itemsTemp = FXCollections.observableArrayList(game.currentRoom.inventory.getContent());
		roomInventory.setItems(itemsTemp);
	}
	
	public void updateDialouge()
	{
		dialogue.setText(game.dialogue);
		dialogue.setScrollTop(Double.MAX_VALUE);
		dialogue.selectPositionCaret(dialogue.getLength()); 
		dialogue.deselect();
	}
	
	public void updatePanel()
	{
		updateDialouge();
		if(game.currentRoom.hasMonster())
		{
			monster.setVisible(true);
			updateMonsterStatus();
		}
		else
		{
			monster.setVisible(false);
		}
	}
	
	public void updateMonsterStatus()
	{
		monsterHealthStatus.setText(game.currentRoom.monster.getHealth() + "/" + game.currentRoom.monster.getMaxHealth() + " HP");
		monsterHealth.setProgress((double)game.currentRoom.monster.getHealth()/game.currentRoom.monster.getMaxHealth());
	}
}
