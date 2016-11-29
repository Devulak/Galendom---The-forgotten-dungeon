package main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.item.Item;

public class FXMLDocumentController implements Initializable {
	
	private Game game;
	private GridPane activeMenu;
	
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
	private Canvas canvasMap;
	@FXML
	private Pane showTeleporter;
	@FXML
	private Pane showVendor;
	
	@FXML
	private GridPane vendor;
	@FXML
	private ListView vendorInventory;
	
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
	private void showVendor(ActionEvent event)
	{
		updateVendorInventory();
		switchMenu(vendor);
	}
	
	@FXML
	private void closeVendor(ActionEvent event)
	{
		switchMenu(navigation);
	}
	
	@FXML
	private void showTeleporter(ActionEvent event)
	{
		switchMenu(teleporter);
	}
	
	@FXML
	private void teleport(ActionEvent event)
	{
		game.useTeleporter();
		updateRoomInventory();
		updatePanel();
	}
	
	@FXML
	private void closeTeleporter(ActionEvent event)
	{
		switchMenu(navigation);
	}
	
	@FXML
	private void buyItem(Event event)
	{
		Item selectedItem = (Item) vendorInventory.getSelectionModel().getSelectedItem();
		game.buyItem(selectedItem);
		/*Item tempItem = game.currentRoom.inventory.add(selectedItem);
		game.vendor.inventory.swap(selectedItem, tempItem);*/
		
		// Update inventories
		updatePlayerInventory();
		updateVendorInventory();
		updateRoomInventory();
		updateDialouge();
	}
	
	@FXML
	private void takeItem(Event event)
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
	private void dropItem(Event event)
	{
		Item selectedItem = (Item) playerInventory.getSelectionModel().getSelectedItem();
		if(!game.useItem(selectedItem)) // checks to see if it's an item that's suppose to be used
		{
			Item droppedItem = game.getCurrentRoom().getRoomsInventory().add(selectedItem);
			game.getPlayer().getCreaturesInventory().swap(selectedItem, droppedItem);
		}
		
		// Update inventories
		updatePlayerInventory();
		updateRoomInventory();
		updatePlayerStatus();
		updateDialouge();
		updatePoints();
		updateTurns();
                
	}
	
	@FXML
	private void up(ActionEvent event)
	{
		game.goRoom(new int[]{0, -1});
		updateMap();
		updatePlayerInventory();
		updateRoomInventory();
		updatePanel();               
	}
	
	@FXML
	private void down(ActionEvent event)
	{
		game.goRoom(new int[]{0, 1});
		updateMap();
		updatePlayerInventory();
		updateRoomInventory();
		updatePanel();                
	}
	
	@FXML
	private void left(ActionEvent event)
	{
		game.goRoom(new int[]{-1, 0});
		updateMap();
		updatePlayerInventory();
		updateRoomInventory();
		updatePanel();                
	}
	
	@FXML
	private void right(ActionEvent event)
	{
		game.goRoom(new int[]{1, 0});
		updateMap();
		updatePlayerInventory();
		updateRoomInventory();
		updatePanel();                   
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
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
	
	public void updateVendorInventory()
	{
		ObservableList<Item> itemsTemp = FXCollections.observableArrayList(game.getVendor().getCreaturesInventory().getContent());
		vendorInventory.setItems(null);
		vendorInventory.setItems(itemsTemp);
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
		int pointsAmount = game.getPoints();
		points.setText(String.format("%05d", pointsAmount) + " POINT" + (pointsAmount != 1 ? "S" : false));
	}
	
	public void updateTurns()
	{
		turns.setText("TURN " + String.format("%02d", game.getTurns()) + " / " + String.format("%02d", game.getTurnsLimit()));
                               
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
		
		// Show combat
		if(game.getCurrentRoom().hasMonster())
		{
			switchMenu(monster);
			updateMonsterStatus();
		}
		else
		{
			switchMenu(navigation);
			updateMap();
		
			// Show teleporter option
			if(game.getCurrentRoom().getTeleporter() != null)
			{
				showTeleporter.setVisible(true);
			}
			else
			{
				showTeleporter.setVisible(false);
			}

			// Show vendor option
			if(game.getCurrentRoom() == game.getCurrentVendorRoom())
			{
				showVendor.setVisible(true);
			}
			else
			{
				showVendor.setVisible(false);
			}
		}
	}
	
	public void switchMenu(GridPane switchTo)
	{
		if(activeMenu != null)
		{
			activeMenu.setVisible(false);
		}
		activeMenu = switchTo;
		activeMenu.setVisible(true);
	}
	
	public void updateMap()
	{
		// Init
		GraphicsContext mapGC = canvasMap.getGraphicsContext2D();
		double[] viewHalf = {canvasMap.getWidth()/2, canvasMap.getHeight()/2}; // Get the view size
		
		Image room = new Image("sprites/map_room.png");
		double[] roomSize = {room.getWidth(), room.getHeight()};
		double[] roomHalf = {roomSize[0]/2, roomSize[1]/2};
		
		Image doorV = new Image("sprites/map_door_vertical.png");
		double[] doorVHalf = {doorV.getWidth()/2, doorV.getHeight()/2};
		
		Image doorH = new Image("sprites/map_door_horizontal.png");
		double[] doorHHalf = {doorH.getWidth()/2, doorH.getHeight()/2};
		
		Image lock = new Image("sprites/lock.png");
		double[] lockHalf = {lock.getWidth()/2, lock.getHeight()/2};
		
		int[] playerPos = game.getPlayerPosition(); // player position on the map
		double[] globalPos = {room.getWidth()*(-0.5 - playerPos[0]) + viewHalf[0], room.getHeight()*(-0.5 - playerPos[1]) + viewHalf[1]}; // global movement of the map
		
		Image vendorImage = new Image("sprites/vendor.png");
		double[] vendorSize = {vendorImage.getWidth(), vendorImage.getHeight()};
		double[] vendorHalf = {vendorSize[0]/2, vendorSize[1]/2};
		int[] vendorPos = game.getVendorPosition(); // vendor position on the map
		double[] vendorCal = new double[2]; // sets the position calculation base
		vendorCal[0] = globalPos[0] + roomSize[0]*((double)vendorPos[0] + 0.5) - vendorHalf[0];
		vendorCal[1] = globalPos[1] + roomSize[1]*((double)vendorPos[1] + 0.5) + 4;
		
		Image frame = new Image("sprites/map_frame.png");
		
		
		// clear canvas
		mapGC.clearRect(0, 0, canvasMap.getWidth(), canvasMap.getHeight());
		
		// put down all rooms
		for (int i = 0; i < game.getRooms().length; i++)
		{
			for (int j = 0; j < game.getRooms()[i].length; j++)
			{
				if (game.getRooms()[i][j] != null && game.getRoomsSeen()[i][j])
				{
					mapGC.drawImage(room, globalPos[0] + i*roomSize[0], globalPos[1] + j*roomSize[1]);
					
					// Add the "so called" respected doors to the room
					for (Room exit : game.getRooms()[i][j].getExits())
					{
						if (j > 0 && exit == game.getRooms()[i][j - 1]) // door up
						{
							mapGC.drawImage(doorV, globalPos[0] + i*roomSize[0] + roomHalf[0] - doorVHalf[0], globalPos[1] + j*roomSize[1] - doorVHalf[1]);
							if(exit.getLocked()) // is the room locked?
							{
								mapGC.drawImage(lock, globalPos[0] + i*roomSize[0] + roomHalf[0] - lockHalf[0], globalPos[1] + j*roomSize[1] - lockHalf[1]);
							}
						}
						if (j < game.getRooms()[i].length-1 && exit == game.getRooms()[i][j + 1]) // door down
						{
							mapGC.drawImage(doorV, globalPos[0] + i*roomSize[0] + roomHalf[0] - doorVHalf[0], globalPos[1] + (j+1)*roomSize[1] - doorVHalf[1]);
							if(exit.getLocked()) // is the room locked?
							{
								mapGC.drawImage(lock, globalPos[0] + i*roomSize[0] + roomHalf[0] - lockHalf[0], globalPos[1] + (j+1)*roomSize[1] - lockHalf[1]);
							}
						}
						if (i > 0 && exit == game.getRooms()[i - 1][j]) // door left
						{
							mapGC.drawImage(doorH, globalPos[0] + i*roomSize[0] - doorHHalf[0], globalPos[1] + j*roomSize[1] + roomHalf[1] - doorHHalf[1]);
							if(exit.getLocked()) // is the room locked?
							{
								mapGC.drawImage(lock, globalPos[0] + i*roomSize[0] - lockHalf[0], globalPos[1] + j*roomSize[1] + roomHalf[1] - lockHalf[1]);
							}
						}
						if (i < game.getRooms().length-1 && exit == game.getRooms()[i + 1][j]) // door right
						{
							mapGC.drawImage(doorH, globalPos[0] + (i+1)*roomSize[0] - doorHHalf[0], globalPos[1] + j*roomSize[1] + roomHalf[1] - doorHHalf[1]);
							if(exit.getLocked()) // is the room locked?
							{
								mapGC.drawImage(lock, globalPos[0] + (i+1)*roomSize[0] - lockHalf[0], globalPos[1] + j*roomSize[1] + roomHalf[1] - lockHalf[1]);
							}
						}
					}
					
					if (game.getCurrentVendorRoom() == game.getRooms()[i][j]) // see if the vendor is in a visible room
					{
						mapGC.drawImage(vendorImage, vendorCal[0], vendorCal[1]); // Draw vendor
					}
				}
			}
		}
		mapGC.drawImage(frame, 0, 0); // Draw frame
	}
}
