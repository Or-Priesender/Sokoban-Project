/* MADE BY : OR PRIESENDER 203703582 AND IDO ABOUDI 204065429 */

package boot;
	
/*things to consider:
 * text level loader is limited to 100*100 sized stages because of the reader mark, can be changed.
 * text level loader is reading the map alone, can be changed if needed.
 * char level displayer also displays map alone, can be changed if needed.
 * the CLI is currently familiar with up to 2 word commands.
 * level only handles the map, in the future : add other data members in txt loader and saver, and in move commands.	
 */



import javax.print.DocFlavor.URL;

import controller.SokobanController;
import controller.server.MyServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MyModel;
import view.ClientHandler;
import view.MainWindowController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static boolean server = false;
	public static int port;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			BorderPane root = (BorderPane)fxmlLoader.load(getClass().getResource("MainWindow.fxml").openStream());
			
			MainWindowController view = (MainWindowController) fxmlLoader.getController();
			MyModel model = new MyModel();
			SokobanController c = new SokobanController(view,model);
			view.addObserver(c);
			model.addObserver(c);
			
			if(server == true)
			{
				System.out.println("running with server");
				c.startServer(new ClientHandler(),port);
			}
			
			//set the music from outside the code and run it in loops
			String filename = getClass().getResource("DST-IceCanyon1.mp3").toString();
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
			
			Scene scene = new Scene(root,720,480);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		if (args.length >= 2  && args[0].compareToIgnoreCase("-server")==0)
		{
			server = true;
			port = Integer.parseInt(args[1]);
		}
		else System.out.println("running without server");
			
		
		launch(args);
		
		
		}
	
	
	public void stop()
	{
		
		Platform.exit();
	}

	
	
	
	

	
	
	
}
