package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Program extends Application{
	
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			Parent parent = loader.load();
			
		    mainScene = new Scene(parent);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("JavaFx test");
			primaryStage.show();
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args){
		launch(args);
	}

	
}