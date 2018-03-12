package base64.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Application class
 * 
 * @author tpaulinoa
 * @date 2018-03-04
 *
 */
public class App extends Application {

	private static String LAYOUT = "layouts/applayout.fxml";
	
	private static Stage primaryStage;
	private Pane rootLayout;
		
	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		primaryStage.setTitle("Portable Encoder/Decoder to/from Base64");
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
	        System.exit(0);
	    });
		initRootLayout();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(App.class.getResource(LAYOUT));
			rootLayout = (Pane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("layouts/icon.png")));
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

}
