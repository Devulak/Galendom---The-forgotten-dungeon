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
		/*ObservableList<Item> items = FXCollections.observableArrayList();
		items.add(new Item("Woppa", 0));
		playerInventory = new ListView<>(items);
        playerInventory.setCellFactory(param -> new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });*/
		List<Item> inventoryItems = game.hero.inventory.getContent();
		ObservableList<Item> itemsTemp = FXCollections.observableArrayList(inventoryItems);
		playerInventory.setItems(itemsTemp);
				
		/*ObservableList<String> itemsTemp = FXCollections.observableArrayList();
		itemsTemp.add("Hello World!");*/
		/*playerInventory = new ListView<>(itemsTemp);*/
	}
	
	public void updateRoomInventory()
	{
		List<Item> inventoryItems = game.currentRoom.inventory.getContent();
		ObservableList<Item> itemsTemp = FXCollections.observableArrayList(inventoryItems);
		roomInventory.setItems(itemsTemp);
	}
}
