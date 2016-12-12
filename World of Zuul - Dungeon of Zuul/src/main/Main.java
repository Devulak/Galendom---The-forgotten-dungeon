package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.util.Duration;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application
{
	Stage thestage;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		// Init of stage
		stage.setTitle("Galendom - The forgotten dungeon");
		stage.getIcons().add(new Image("/sprites/icon.png"));
		stage.setMinWidth(960);
		stage.setMinHeight(640);
		
		// game scene
		Parent root = FXMLLoader.load(getClass().getResource("fxml/Mainmenu.fxml"));
		Scene scene = new Scene(root);
		
		// Show game scene
		stage.setScene(scene);
		stage.show();  
                
                URL resource = getClass().getResource("Calming Dungeons.mp3");
                Media media = new Media(resource.toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                mediaPlayer.setOnEndOfMedia(new Runnable(){
                    public void run(){
                        mediaPlayer.seek(Duration.ZERO);
                    }
                }
                );                
        }
        
        public static void main(String[] args)
	{
		launch(args);   
	}
}
