package view;
	


import javax.print.DocFlavor.URL;

import controller.SokobanController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MyModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;


public class Main extends Application implements Runnable{
	private static MainWindowController view;
	public static String[] args;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			BorderPane root = (BorderPane)fxmlLoader.load(getClass().getResource("MainWindow.fxml").openStream());
			view = (MainWindowController) fxmlLoader.getController();
			MyModel model = new MyModel();
			SokobanController c = new SokobanController(view,model);
			view.addObserver(c);
			model.addObserver(c);
			
			//set the music from outside the code and run it in loops
			String filename = getClass().getResource("Music.mp3").toString();
			Media media = new Media(filename);
			MediaPlayer player = new MediaPlayer(media);
			player.setOnEndOfMedia(new Runnable() {
				
				@Override
				public void run() {
					player.seek(Duration.ZERO);
					
				}
			});
			player.setVolume(0.3);
			player.play();
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		
		launch(args);
		
		}
	
	
	public void stop()
	{
		
		Platform.exit();
	}

	
	public void run() {
		
		launch(args);
		
	}
	
	

	
	
	
}
