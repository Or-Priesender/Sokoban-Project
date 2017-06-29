/* MADE BY : OR PRIESENDER 203703582 AND IDO ABOUDI 204065429 */

package boot;


import controller.SokobanController;
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

/**
 * Sokoban client side application, allows the user to connect to the main Sokoban server and retrieve information.
 * @author Or Priesender
 *
 */
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

			primaryStage.setOnCloseRequest(event -> {
				view.exit();
			});

			if(server == true)
			{
				c.startServer(new ClientHandler(),port);
				view.serverStatus(true);
			}

			//set the music from outside the code and run it in loops

			String filename = getClass().getResource("Music.mp3").toString();
			Media media = new Media(filename);
			MediaPlayer player = new MediaPlayer(media);
			if(player != null){
				player.setOnEndOfMedia(new Runnable() {

					@Override
					public void run() {
						player.seek(Duration.ZERO);

					}
				});

				player.setVolume(0.3);
				player.play();

			}
			Scene scene = new Scene(root,700,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Sokoban");
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
