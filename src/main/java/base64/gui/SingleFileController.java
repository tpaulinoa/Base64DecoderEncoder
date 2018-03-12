package base64.gui;

import java.io.File;
import java.io.IOException;

import base64.utils.ConversionTool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * Controller class for the application pane 'Single File'
 * 
 * @author tpaulinoa
 * @date 2018-03-04
 *
 */
public class SingleFileController {

	private static String LAYOUT = "layouts/filelayout.fxml"; 
	
	@FXML
	private Button buttonFileChooser;
	@FXML
	private Button buttonCopyFileEncoded;
	@FXML
	private TextField inputFilePath;
	@FXML
	private TextArea outputFileEncoded;
	@FXML
	private Button buttonEncode;
	@FXML
	private Button buttonDecodeAndNew;

	private File file;

	@FXML
    private void initialize() {
		initializeComponents();
	}

	public static Pane loadSingleFilePane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource(LAYOUT));
		return (Pane) loader.load();
	}
	
	private void initializeComponents() {
		buttonFileChooser.setOnAction(e -> actionChooseFile());
		buttonCopyFileEncoded.setOnAction(e -> copyFileEncoded());
		buttonEncode.setOnAction(e -> actionEncode());
		buttonDecodeAndNew.setOnAction(e -> actionDecodeAndNew());
	}

	private void actionEncode() {
		if (file != null)
			outputFileEncoded.setText(ConversionTool.encodeFile(file));
	}
	
	private void actionDecodeAndNew() {
		if (file != null)
			outputFileEncoded.setText(ConversionTool.decodeFile(file, file.getParent(), true));
	}

	private void actionChooseFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose File");
		file = fileChooser.showOpenDialog(App.getPrimaryStage());
		if (file != null) {
			inputFilePath.setText(file.getAbsolutePath());
		}
	}
	
	private void copyFileEncoded() {
		ConversionTool.copyToClipboard(outputFileEncoded.getText());
	}
}
