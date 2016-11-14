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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
	private AnchorPane monster;
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
	private void handleButtonAction(ActionEvent event)
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
		Item tempItem = game.hero.inventory.add(selectedItem);
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
		game.hero.inventory.swap(selectedItem, droppedItem);
		
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
		updatePlayerStatus();
		updatePlayerInventory();
		updateRoomInventory();
		updatePanel();
	}
	
	public void updatePlayerStatus()
	{
		// Health
		playerHealth.setText(game.hero.getHealth() + "/" + game.hero.getMaxHealth() + " HP");
		playerHealthbar.setFitWidth((double)game.hero.getHealth()/game.hero.getMaxHealth()*342);
		playerHealthbarEnd.setLayoutX(playerHealthbar.getLayoutX()+playerHealthbar.getFitWidth());
		
		// Experience
		experienceStatus.setText(game.hero.getExperience() + "/" + game.hero.getMaxExperience() + " EXP");
		playerExperiencebar.setFitWidth((double)game.hero.getExperience()/game.hero.getMaxExperience()*214);
		playerExperiencebarEnd.setLayoutX(playerExperiencebar.getLayoutX()+playerExperiencebar.getFitWidth());
		
		// Level
		playerLevel.setText(String.format("%d", game.hero.getLevel()));
		
		// Strength
		playerStrength.setText(String.format("%d", game.hero.getStrength()));
		
		// Armour
		playerArmour.setText(String.format("%d", game.hero.getArmour()));
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
}
