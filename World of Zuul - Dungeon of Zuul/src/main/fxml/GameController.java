package main.fxml;

import java.io.IOException;
import javafx.event.Event;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.Game;
import main.GameInterface;
import main.Room;
import main.item.Item;
import main.singleton;

public class GameController implements Initializable {
	
	private GameInterface game = new Game();
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
	
	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		game.play();
		// Run all update commands so that we both have a list of them and the GUI is prepared
		updatePlayerStatus();
		updatePlayerInventory();
		updateRoomInventory();
		updateMap();
		updateDialouge();
		updatePoints();
		updateTurns();
		updateMove();
	}
	
	@FXML
	private void attack(ActionEvent event) throws IOException
	{
		game.attack();
		updatePlayerStatus();
		updateRoomInventory();
		updateDialouge();
		updatePoints();
		updateTurns();
		getLost();
		if(game.roomHasMonster())
		{
			updateMonsterStatus();
		}
		else
		{
			switchMenu(navigation);
			updateMove();
		}
	}
	
	@FXML
	private void flee(ActionEvent event)
	{
		game.flee();
		updateRoomInventory();
		updateDialouge();
		updatePoints();
		updateTurns();
		if(game.roomHasMonster())
		{
			updatePlayerStatus();
		}
		else
		{
			switchMenu(navigation);
			updateMove();
		}
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
		updateDialouge();
		updatePoints();
		updateTurns();
		switchMenu(navigation);
		updateMove();
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
		game.takeItem(selectedItem);
		
		// Update inventories
		updatePlayerInventory();
		updateRoomInventory();
		updatePlayerStatus();
                
	}
	
	@FXML
	private void inventoryItem(Event event)
	{
		Item selectedItem = (Item) playerInventory.getSelectionModel().getSelectedItem();
		game.dropItem(selectedItem);
		
		// Update inventories
		updatePlayerInventory();
		updateRoomInventory();
		updatePlayerStatus();
		updateDialouge();
		updatePoints();
		updateTurns();
                
	}
	
	private void updateMove()
	{
		updateMap();
		updateRoomInventory();
		updatePlayerStatus();
		updateDialouge();
		updatePoints();
		updateTurns();
		if(game.roomHasMonster())
		{
			switchMenu(monster);
			updateMonsterStatus();
		}
		else
		{
			// Teleporter
			showTeleporter.setVisible(game.roomHasTeleporter());

			// Vendor
			showVendor.setVisible(game.roomHasVendor());
		}
	}
	
	@FXML
	private void up(ActionEvent event)
	{
		game.goRoom(new int[]{0, -1});
		updateMove();
	}
	
	@FXML
	private void down(ActionEvent event)
	{
		game.goRoom(new int[]{0, 1});
		updateMove();
	}
	
	@FXML
	private void left(ActionEvent event)
	{
		game.goRoom(new int[]{-1, 0});
		updateMove();
	}
	
	@FXML
	private void right(ActionEvent event)
	{
		game.goRoom(new int[]{1, 0});
		updateMove();
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
	
	public void getLost()
	{
		game.getLost();
		if(game.getLost())
		{
			/*Parent root = FXMLLoader.load(getClass().getResource("Mainmenu.fxml"));
			Scene scene = new Scene(root);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

			stage.setScene(scene);
			stage.show();*/
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
		getLost();
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
