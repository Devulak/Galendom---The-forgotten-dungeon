package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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
	private void handleButtonAction(ActionEvent event) {
		Command command = game.parser.getCommand("attack");
		boolean endGame = game.processCommand(command);
		updateHealth();
		updateExperience();
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
		ObservableList<String> itemsTemp = FXCollections.observableArrayList();
		itemsTemp.add("as");
		playerInventory = new ListView<>(itemsTemp);
	}
}
