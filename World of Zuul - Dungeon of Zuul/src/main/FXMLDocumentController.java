package main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
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
	
	GameInterface game = new Game();
	
	@FXML
	private Label points;
	@FXML
	private Label turns;
	
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
	private GridPane vendor;
	
	@FXML
	private GridPane teleporter;
	
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
	private void flee(ActionEvent event)
	{
		game.flee();
		updatePlayerStatus();
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void leaveVendor(ActionEvent event)
	{
		
	}
	
	@FXML
	private void teleport(ActionEvent event)
	{
		game.useTeleporter();
		updatePanel();
	}
	
	@FXML
	private void destroyTeleporter(ActionEvent event)
	{
		game.getCurrentRoom().setTeleporter(null);
		updatePanel();
	}
	
	@FXML
	private void takeItem(ActionEvent event)
	{
		Item selectedItem = (Item) roomInventory.getSelectionModel().getSelectedItem();
		Item tempItem = game.getPlayer().getCreaturesInventory().add(selectedItem);
		game.getCurrentRoom().getRoomsInventory().swap(selectedItem, tempItem);
		
		// Update inventories
		updatePlayerInventory();
		updateRoomInventory();
		updatePlayerStatus();
	}
	
	@FXML
	private void dropItem(ActionEvent event)
	{
		Item selectedItem = (Item) playerInventory.getSelectionModel().getSelectedItem();
		Item droppedItem = game.getCurrentRoom().getRoomsInventory().add(selectedItem);
		game.getCurrentRoom().getRoomsInventory().swap(selectedItem, droppedItem);
		
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
		game.goRoom(new int[]{0, -1});
		updateMap();
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void down(ActionEvent event)
	{
		game.goRoom(new int[]{0, 1});
		updateMap();
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void left(ActionEvent event)
	{
		game.goRoom(new int[]{-1, 0});
		updateMap();
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void right(ActionEvent event)
	{
		game.goRoom(new int[]{1, 0});
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
	
	public void updatePlayerStatus()
	{
		// Health
		playerHealth.setText(game.getPlayer().getHealth() + " / " + game.getPlayer().getMaxHealth() + " HP");
		playerHealthbar.setFitWidth((double)game.getPlayer().getHealth()/game.getPlayer().getMaxHealth()*342);
		playerHealthbarEnd.setLayoutX(playerHealthbar.getLayoutX()+playerHealthbar.getFitWidth());
		
		// Experience
		experienceStatus.setText(game.getPlayer().getExperience() + " / " + game.getPlayer().getMaxExperience() + " EXP");
		playerExperiencebar.setFitWidth((double)game.getPlayer().getExperience()/game.getPlayer().getMaxExperience()*214);
		playerExperiencebarEnd.setLayoutX(playerExperiencebar.getLayoutX()+playerExperiencebar.getFitWidth());
		
		// Level
		playerLevel.setText(String.format("%d", game.getPlayer().getLevel()));
		
		// Strength
		playerStrength.setText(String.format("%d", game.getPlayer().getStrength()));
		
		// Armour
		playerArmour.setText(String.format("%d", game.getPlayer().getArmour()));
	}
	
	public void updateMonsterStatus()
	{
		// Health
		monsterHealth.setText(game.getCurrentRoom().getMonster().getHealth() + " / " + game.getCurrentRoom().getMonster().getMaxHealth() + " HP");
		monsterHealthbar.setFitWidth((double)game.getCurrentRoom().getMonster().getHealth()/game.getCurrentRoom().getMonster().getMaxHealth()*342);
		monsterHealthbarEnd.setLayoutX(monsterHealthbar.getLayoutX()+monsterHealthbar.getFitWidth());
		
		// Level
		monsterLevel.setText(String.format("%d", game.getCurrentRoom().getMonster().getLevel()));
		
		// Strength
		monsterStrength.setText(String.format("%d", game.getCurrentRoom().getMonster().getStrength()));
		
		// Armour
		monsterArmour.setText(String.format("%d", game.getCurrentRoom().getMonster().getArmour()));
	}
	
	public void updatePlayerInventory()
	{
		ObservableList<Item> itemsTemp = FXCollections.observableArrayList(game.getPlayer().getCreaturesInventory().getContent());
		playerInventory.setItems(null);
		playerInventory.setItems(itemsTemp);
	}
	
	public void updateRoomInventory()
	{
		ObservableList<Item> itemsTemp = FXCollections.observableArrayList(game.getCurrentRoom().getRoomsInventory().getContent());
		roomInventory.setItems(null);
		roomInventory.setItems(itemsTemp);
	}
	
	public void updatePoints()
	{
		points.setText(String.format("%05d", game.getPoints()) + " POINTS");
	}
	
	public void updateTurns()
	{
		turns.setText("TURN " + String.format("%02d", game.getTurns()) + " / " + String.format("%02d", game.getTurns()));
	}
	
	public void updateDialouge()
	{
		dialogue.setText(game.getDialogue());
		dialogue.setScrollTop(Double.MAX_VALUE);
		dialogue.selectPositionCaret(dialogue.getLength());
		dialogue.deselect();
	}
	
	public void updatePanel()
	{
		updateDialouge();
		updatePoints();
		updateTurns();
		if(game.getCurrentRoom().hasMonster())
		{
			monster.setVisible(true);
			teleporter.setVisible(false);
			vendor.setVisible(false);
			navigation.setVisible(false);
			updateMonsterStatus();
		}
		else if(game.getCurrentRoom().getTeleporter() != null)
		{
			monster.setVisible(false);
			teleporter.setVisible(true);
			vendor.setVisible(false);
			navigation.setVisible(false);
		}
		else if(game.getCurrentRoom() == game.getCurrentVendorRoom())
		{
			monster.setVisible(false);
			teleporter.setVisible(false);
			vendor.setVisible(true);
			navigation.setVisible(false);
		}
		else
		{
			monster.setVisible(false);
			teleporter.setVisible(false);
			vendor.setVisible(false);
			navigation.setVisible(true);
			updateMap();
		}
	}
	
	public void updateMap()
	{
		int[] pos = game.getPlayerPosition(); // player position on the map
		int[] grid = {3, 4}; // Grid size (change this with the size of the map; maybe make this automatic?)
		double[] viewSize = {map.getViewport().getWidth(), map.getViewport().getHeight()}; // Get the map size
		double[] mapSize = {map.getImage().getWidth(), map.getImage().getHeight()}; // Get the map image size
		
		// Calculations for position of the map image
		double x = mapSize[0]/(double)grid[0]*((double)pos[0] + 0.5) - viewSize[0]/2;
		double y = mapSize[1]/(double)grid[1]*((double)pos[1] + 0.5) - viewSize[1]/2;
		
		map.setViewport(new Rectangle2D(x, y, viewSize[0], viewSize[1])); // set the new view and movement
	}
}
