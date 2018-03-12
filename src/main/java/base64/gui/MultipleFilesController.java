package base64.gui;

import java.io.File;
import java.io.IOException;

import base64.utils.ConversionTool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;

/**
 * Controller class for the application pane 'Mulitple Files'
 * 
 * @author tpaulinoa
 * @date 2018-03-04
 *
 */
public class MultipleFilesController {

	private static String LAYOUT = "layouts/multiplefileslayout.fxml";
	
	@FXML
	private Button buttonDirInputChooser;
	@FXML
	private Button buttonDirOutputChooser;
	@FXML
	private Button buttonEncodeFiles;
	@FXML
	private Button buttonDecodeFiles;
	@FXML
	private TextField inputDirPath;
	@FXML
	private TextField outputDirPath;
	@FXML
	private TextArea outputFilesEncoded;
	@FXML
	private CheckBox checkSetFileExtension;
	
	@FXML
    private void initialize() {
		initializeComponents();
	}

	public static Pane loadMultipleFilesPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource(LAYOUT));
		return (Pane) loader.load();
	}
	
	private void initializeComponents() {
		buttonDirInputChooser.setOnAction(e -> actionInputDirectory());
		buttonDirOutputChooser.setOnAction(e -> actionOutputDirectory());
		buttonEncodeFiles.setOnAction(e -> actionEncodeFiles());
		buttonDecodeFiles.setOnAction(e -> actionDecodeFiles());
		addCheckboxTooltip();
	}
	
	private void actionInputDirectory() {
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Choose input directory");
		File file = dirChooser.showDialog(App.getPrimaryStage());
		if (file != null && file.isDirectory()) {
			inputDirPath.setText(file.getAbsolutePath());
		}
	}
	
	private void actionOutputDirectory() {
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Choose output directory");
		File file = dirChooser.showDialog(App.getPrimaryStage());
		if (file != null && file.isDirectory()) {
			outputDirPath.setText(file.getAbsolutePath());
		}
	}
	
	private void actionEncodeFiles() {
		File dir = new File(inputDirPath.getText());
		String outputDir = outputDirPath.getText();
		outputFilesEncoded.clear();
		if (dir != null && dir.exists()) {
			for (File f: dir.listFiles()) {
				String result = ConversionTool.encodeFile(f, outputDir);
				outputFilesEncoded.appendText(result + System.lineSeparator());
			}
		}
	}
	
	private void actionDecodeFiles() {
		File dir = new File(inputDirPath.getText());
		String outputDir = outputDirPath.getText();
		outputFilesEncoded.clear();
		if (dir != null && dir.exists()) {
			for (File f: dir.listFiles()) {
				String result = ConversionTool.decodeFile(f, outputDir, checkSetFileExtension.isSelected());
				if (result == null)
					result = f.getName() + " --> FAIL";
				outputFilesEncoded.appendText(result + System.lineSeparator());
			}
		}
	}

	private void addCheckboxTooltip() {
		Tooltip tooltip = new Tooltip("Apache Tika library will be used to determine file type.");
        tooltip.setFont(new Font("Sans Serif", 12));
        checkSetFileExtension.setTooltip(tooltip);
	}
	
}
