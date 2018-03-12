package base64.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Properties;

import org.apache.tika.Tika;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 * Utility class used to encode and decode Base64.
 * 
 * @author tpaulinoa
 * @date 2018-03-04
 *
 */
public class ConversionTool {

	public static Properties MIME_EXTENSION_PROPERTIES = new Properties();
	
	/**
	 * Load a list of MimeTypes to set file extension
	 */
	static {
		try {
			MIME_EXTENSION_PROPERTIES.load(ConversionTool.class.getResourceAsStream("mime.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method encodes a plain text to encoded text.  
	 * 
	 * @param encodedText
	 * @return
	 */
	public static String decodeText(String encodedText) {
		String result = null;
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] b = decoder.decode(encodedText);
			result = b != null ? new String(b) : null;
		} catch (IllegalArgumentException e) {
			//TODO implement a treatment for invalid input
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This method decodes a Base64 to plain text.
	 * 
	 * @param plainText
	 * @return
	 */
	public static String encodeText(String plainText) {
		String result = "";
		try {
			Base64.Encoder encoder = Base64.getEncoder();
			byte[] b = encoder.encode(plainText.getBytes());
			result = new String(b);
		} catch (IllegalArgumentException e) {
			//TODO implement a treatment for invalid input
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * This method decodes an encoded Base64 text/input in byte array.
	 * It can be used to transform a Base64 to an image, for example.
	 * 
	 * @param encodedText
	 * @return
	 */
	public static byte[] decodeTextToBytes(String encodedText) {
		byte[] b = null;
		try {
			Base64.Decoder decoder = Base64.getDecoder();
			b = decoder.decode(encodedText);
		} catch (IllegalArgumentException e) {
			//TODO implement a treatment for invalid input
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * This method encodes an input file to a Base64
	 * 
	 * @param file
	 * @return
	 */
	public static String encodeFile(File file) {
		try {
			byte[] fileBytes = Files.readAllBytes(file.toPath());
			Base64.Encoder encoder = Base64.getEncoder();
			return encoder.encodeToString(fileBytes);
		} catch (IllegalArgumentException e) {
			//TODO implement a treatment for invalid input
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * This method decodes a file (text in Base64) and creates a new binary file.
	 * Also, it can try to detect the file's mime type using the Apache Tika library 
	 * 
	 * @param file
	 * @param destinationPath
	 * @param detectFileExtension - if 'true' the mime type will be set, if possible
	 * 								if 'false' file's extension will be '.rename_me'
	 * @return
	 */
	public static String decodeFile(File file, String destinationPath, boolean detectFileExtension) {
		String resultFileName = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder allLines = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				allLines.append(line);
			}

			Base64.Decoder decoder = Base64.getDecoder();
			byte[] b = decoder.decode(allLines.toString());

			String extension = null;
			if (detectFileExtension) {
				Tika tika = new Tika();
				String mimeType = tika.detect(b);
				extension = MIME_EXTENSION_PROPERTIES.getProperty(mimeType);
			}

			if (extension == null)
				extension="rename_me";

			resultFileName = destinationPath + File.separator + file.getName() + "." + extension;
			FileOutputStream fos = new FileOutputStream(resultFileName);

			fos.write(b);
			fos.close();
			br.close();
			
		} catch (IllegalArgumentException e) {
			//TODO implement a treatment for invalid input
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultFileName;
	}

	/**
	 * This method decodes a file (text in Base64) and creates a new binary file.
	 * The file's extension will be always '.rename_me'
	 * 
	 * @param file
	 * @param destinationPath
	 * @return
	 */
	public static String decodeFile(File file, String destinationPath) {
		return decodeFile(file, destinationPath, false);
	}

	/**
	 * This method encodes a file and creates a new file with the encoded Base64.
	 * 
	 * @param file
	 * @param destinationPath
	 * @return
	 */
	public static String encodeFile(File file, String destinationPath) {
		String resultFileName = null;
		try {
			byte[] fileBytes = Files.readAllBytes(file.toPath());
			Base64.Encoder encoder = Base64.getEncoder();
			String base64 = encoder.encodeToString(fileBytes);

			resultFileName = destinationPath + File.separator + file.getName() + ".base64.txt";
			FileOutputStream fos = new FileOutputStream(resultFileName);

			fos.write(base64.getBytes());
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultFileName;
	}

	/**
	 * This method just insert some content into the system's clipboard
	 * 
	 * @param content
	 */
	public static void copyToClipboard(String content) {
		ClipboardContent clip = new ClipboardContent();
	    clip.putString(content);
	    Clipboard.getSystemClipboard().setContent(clip);
	}
	
}
