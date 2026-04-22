package breakout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Breakout extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage stage) {
		stage.setTitle("Breakout");
		BorderPane root = new BorderPane();
		BallWorld ballWrld = new BallWorld();
		root.setCenter(ballWrld);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		stage.show();
		ballWrld.start();
	}
}
