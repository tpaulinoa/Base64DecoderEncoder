package base64.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Main controller class of the application
 * 
 * @author tpaulinoa
 * @date 2018-03-04
 *
 */
public class GuiController {

	@FXML
	private AnchorPane mainPane;
	@FXML
	private Pane paneTextConversion;
	
	@FXML
	private MenuItem menuTextConversion;
	@FXML
	private MenuItem menuSingleFile;
	@FXML
	private MenuItem menuMultipleFiles;
	@FXML
	private MenuItem menuImageDecode;
	@FXML
	private MenuItem menuAbout;

	@FXML
    private void initialize() {
		
		//Menu for text conversion
		menuTextConversion.setOnAction(e -> menuActionTextDecode());
		//Menu for a single file conversion
		menuSingleFile.setOnAction(e -> menuActionSingleFile());
		//Menu for mulitple files conversion
		menuMultipleFiles.setOnAction(e -> menuActionMultipleFiles());
		//Menu for image decode and preview
		menuImageDecode.setOnAction(e -> menuActionImageDecode());
		//Info menu
		menuAbout.setOnAction(e -> menuActionAbout());

		//set 'Multiple Files' menu as 'front page'
		menuActionMultipleFiles();
	}

	private void menuActionSingleFile() {
		try {
			menuActionAddPane(SingleFileController.loadSingleFilePane());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void menuActionMultipleFiles() {
		try {
			menuActionAddPane(MultipleFilesController.loadMultipleFilesPane());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void menuActionTextDecode() {
		try {
			menuActionAddPane(TextController.loadTextConversionPane());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void menuActionImageDecode() {
		try {
			menuActionAddPane(ImageController.loadImageDecodePane());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void menuActionAbout() {
		try {
			menuActionAddPane(AboutController.loadAboutPane());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void menuActionAddPane(Pane pane) {
		mainPane.getChildren().clear();
		mainPane.getChildren().add(pane);
	}
	
}
