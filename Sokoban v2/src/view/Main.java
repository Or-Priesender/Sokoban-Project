package view;
	
import controller.SokobanController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import model.data.MyModel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application{
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
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
		
		MainWindowController view = new MainWindowController();
		MyModel model = new MyModel();
		SokobanController c = new SokobanController(view,model);
		view.addObserver(c);
		model.addObserver(c);
		launch(args);
		}
	
	
	public void stop()
	{
		
		Platform.exit();
	}

	
	
	
}
