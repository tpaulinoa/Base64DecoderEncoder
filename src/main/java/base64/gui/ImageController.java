package base64.gui;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import base64.utils.ConversionTool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Controller class for the application pane 'Decode Image'
 * 
 * @author tpaulinoa
 * @date 2018-03-04
 *
 */
public class ImageController {

	private static String LAYOUT = "layouts/imagelayout.fxml";
	
	@FXML
	private Button buttonTextImageDecode;
	@FXML
	private TextArea inputTextImage;
	@FXML
	private ImageView imageView;
	
	@FXML
    private void initialize() {
		buttonTextImageDecode.setOnAction(e -> actionTextEncode());
	}

	public static Pane loadImageDecodePane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(App.class.getResource(LAYOUT));
		return (Pane) loader.load();
	}
	
	private void actionTextEncode() {
		String input = inputTextImage.getText().trim();
		byte[] imageBytes = ConversionTool.decodeTextToBytes(input);

		if (imageBytes != null) {
			Image image = new Image(new ByteArrayInputStream(imageBytes));
			imageView.setPreserveRatio(true);
			imageView.setFitWidth(image.getWidth());
			imageView.setImage(image);
		}
	}

}
