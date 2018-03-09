package base64.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller class for the application pane 'About'
 * 
 * @author tpaulinoa
 * @date 2018-03-04
 *
 */
public class AboutController extends Application {

	private static String LAYOUT = "layouts/about.fxml";
	
	@FXML
	private Hyperlink linkProject;
	
	@FXML
    private void initialize() {
		linkProject.setOnAction(e -> {
		    this.getHostServices().showDocument(linkProject.getText());
		});
	}

	public static Pane loadAboutPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource(LAYOUT));
		return (Pane) loader.load();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	}
	
}
