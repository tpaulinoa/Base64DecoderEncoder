package base64.gui;

import java.io.IOException;

import base64.utils.ConversionTool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/**
 * Controller class for the application pane 'Text'
 * 
 * @author tpaulinoa
 * @date 2018-03-04
 *
 */
public class TextController {

	private static String LAYOUT = "layouts/textlayout.fxml";
	
	@FXML
	private Button buttonTextDecode;
	@FXML
	private Button buttonTextEncode;
	@FXML
	private TextArea inputTextConversion;
	@FXML
	private TextArea outputTextConversion;
	@FXML
	private Button buttonCopyText;
	
	@FXML
    private void initialize() {
		buttonTextDecode.setOnAction(e -> actionTextDecode());
		buttonTextEncode.setOnAction(e -> actionTextEncode());
		buttonCopyText.setOnAction(e -> copyFileEncoded());
	}

	public static Pane loadTextConversionPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource(LAYOUT));
		return (Pane) loader.load();
	}
	
	private void actionTextDecode() {
		String input = inputTextConversion.getText().trim();
		String output = ConversionTool.decodeText(input);
		outputTextConversion.setText(output);
	}
	
	private void actionTextEncode() {
		String input = inputTextConversion.getText().trim();
		String output = ConversionTool.encodeText(input);
		outputTextConversion.setText(output);
	}

	private void copyFileEncoded() {
		ConversionTool.copyToClipboard(outputTextConversion.getText());
	}
	
}
