package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application{

FlowPane root;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {

			root = (FlowPane)FXMLLoader.load(getClass().getResource("/fxml/Hello_world.fxml"));
			stage.setTitle("Hello World");
			Scene scene = new Scene(root,800,600);
			stage.setScene(scene);
			stage.setMinWidth(800);
			stage.setMinHeight(600);
			stage.setMaxWidth(800);
			stage.setMaxHeight(600);
			//stage.getIcons().add(new Image(getClass().getResource("/img/avion.png").toString()));
			stage.show();	
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
