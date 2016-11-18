package main;

import java.awt.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import main.item.Item;

public class FXMLDocumentController implements Initializable {
	
	private Game game;
	
	@FXML
	private Label playerHealth;
	
	@FXML
	private ImageView playerHealthbar;
	
	@FXML
	private ImageView playerHealthbarEnd;
	
	@FXML
	private ImageView playerExperiencebar;
	
	@FXML
	private ImageView playerExperiencebarEnd;
	
	@FXML
	private Label playerArmour;
	
	@FXML
	private Label playerStrength;
	
	@FXML
	private Label experienceStatus;
	
	@FXML
	private Label playerLevel;
	
	@FXML
	private ListView playerInventory;
	
	@FXML
	private ListView roomInventory;
	
	@FXML
	private TextArea dialogue;
	
	@FXML
	private GridPane navigation;
	@FXML
	private ImageView map;
	
	@FXML
	private GridPane monster;
	@FXML
	private Label monsterHealth;
	@FXML
	private ImageView monsterHealthbar;
	@FXML
	private ImageView monsterHealthbarEnd;
	@FXML
	private Label monsterLevel;
	@FXML
	private Label monsterArmour;
	@FXML
	private Label monsterStrength;
	
	@FXML
	private void attack(ActionEvent event)
	{
		game.attack();
		updatePlayerStatus();
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void takeItem(ActionEvent event)
	{
		Item selectedItem = (Item) roomInventory.getSelectionModel().getSelectedItem();
		Item tempItem = game.player.inventory.add(selectedItem);
		game.currentRoom.inventory.swap(selectedItem, tempItem);
		
		// Update inventories
		updatePlayerInventory();
		updateRoomInventory();
		updatePlayerStatus();
	}
	
	@FXML
	private void dropItem(ActionEvent event)
	{
		Item selectedItem = (Item) playerInventory.getSelectionModel().getSelectedItem();
		Item droppedItem = game.currentRoom.inventory.add(selectedItem);
		game.player.inventory.swap(selectedItem, droppedItem);
		
		// Update inventories
		updatePlayerInventory();
		updateRoomInventory();
		updatePlayerStatus();
	}
	
	@FXML
	private void useItem(ActionEvent event)
	{
		Item selectedItem = (Item) playerInventory.getSelectionModel().getSelectedItem();
		game.useItem(selectedItem);
		
		updatePlayerInventory();
		updatePlayerStatus();
		updateDialouge();
	}
	
	@FXML
	private void up(ActionEvent event)
	{
		game.goRoom("up");
		updateMap();
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void down(ActionEvent event)
	{
		game.goRoom("down");
		updateMap();
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void left(ActionEvent event)
	{
		game.goRoom("left");
		updateMap();
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void right(ActionEvent event)
	{
		game.goRoom("right");
		updateMap();
		updateRoomInventory();
		updatePanel();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		game = new Game();
		game.play();
		updatePlayerStatus();
		updatePlayerInventory();
		updateRoomInventory();
		updatePanel();
		updateMap();
	}
	
	@FXML
	private void useTeleporter()
	{
		if(game.currentRoom.hasMonster() && game.currentRoom.hasTeleporter())
		{
		game.addDialogue("You have to defeat the monster first before you can use the teleporter");
		}
		else if(game.currentRoom.hasMonster())
		{
			game.addDialogue("There is no teleporter in the room");
		}
		else if(!game.currentRoom.hasTeleporter())
		{
			game.addDialogue("There is no teleporter in the room");
		}
		else if(game.currentRoom.hasTeleporter())
		{
			game.currentRoom.removeTeleporter();
			game.currentRoom = game.lvl_1;
			game.addDialogue("The teleporter sent you to back to start");
			game.addDialogue("The teleporter has disappeared");
		}
		else
		{
			game.addDialogue("Error");
		}
		updatePlayerInventory();
		updateRoomInventory();
		updatePlayerStatus();
		updatePanel();
	}
	
	public void updatePlayerStatus()
	{
		// Health
		playerHealth.setText(game.player.getHealth() + "/" + game.player.getMaxHealth() + " HP");
		playerHealthbar.setFitWidth((double)game.player.getHealth()/game.player.getMaxHealth()*342);
		playerHealthbarEnd.setLayoutX(playerHealthbar.getLayoutX()+playerHealthbar.getFitWidth());
		
		// Experience
		experienceStatus.setText(game.player.getExperience() + "/" + game.player.getMaxExperience() + " EXP");
		playerExperiencebar.setFitWidth((double)game.player.getExperience()/game.player.getMaxExperience()*214);
		playerExperiencebarEnd.setLayoutX(playerExperiencebar.getLayoutX()+playerExperiencebar.getFitWidth());
		
		// Level
		playerLevel.setText(String.format("%d", game.player.getLevel()));
		
		// Strength
		playerStrength.setText(String.format("%d", game.player.getStrength()));
		
		// Armour
		playerArmour.setText(String.format("%d", game.player.getArmour()));
	}
	
	public void updateMonsterStatus()
	{
		// Health
		monsterHealth.setText(game.currentRoom.monster.getHealth() + "/" + game.currentRoom.monster.getMaxHealth() + " HP");
		monsterHealthbar.setFitWidth((double)game.currentRoom.monster.getHealth()/game.currentRoom.monster.getMaxHealth()*342);
		monsterHealthbarEnd.setLayoutX(monsterHealthbar.getLayoutX()+monsterHealthbar.getFitWidth());
		
		// Level
		monsterLevel.setText(String.format("%d", game.currentRoom.monster.getLevel()));
		
		// Strength
		monsterStrength.setText(String.format("%d", game.currentRoom.monster.getStrength()));
		
		// Armour
		monsterArmour.setText(String.format("%d", game.currentRoom.monster.getArmour()));
	}
	
	public void updatePlayerInventory()
	{
		ObservableList<Item> itemsTemp = FXCollections.observableArrayList(game.player.inventory.getContent());
		playerInventory.setItems(null);
		playerInventory.setItems(itemsTemp);
	}
	
	public void updateRoomInventory()
	{
		ObservableList<Item> itemsTemp = FXCollections.observableArrayList(game.currentRoom.inventory.getContent());
		roomInventory.setItems(null);
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
			navigation.setVisible(false);
			monster.setVisible(true);
			updateMonsterStatus();
		}
		else
		{
			navigation.setVisible(true);
			monster.setVisible(false);
		}
	}
	
	public void updateMap()
	{
		int[] grid = {3, 4}; // Grid size (change this with the size of the map; maybe make this automatic?)
		int[] pos = game.currentRoom.getPos(); // Position on the map
		double[] viewSize = {map.getViewport().getWidth(), map.getViewport().getHeight()}; // Get the map size
		double[] mapSize = {map.getImage().getWidth(), map.getImage().getHeight()}; // Get the map image size
		
		// Calculations for position of the map image
		double x = mapSize[0]/(double)grid[0]*((double)pos[0] + 0.5) - viewSize[0]/2;
		double y = mapSize[1]/(double)grid[1]*((double)pos[1] + 0.5) - viewSize[1]/2;
		
		map.setViewport(new Rectangle2D(x, y, viewSize[0], viewSize[1])); // set the new view and movement
	}
}
