package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.fxml.FXML;;

public class SampleController {

	@FXML
	private TextField source1;
	@FXML
	private TextField source2;
	@FXML
	private Button compressBtn;
	@FXML
	private Button decompressBtn;
	@FXML
	private Label done;

	private int levels = 0;

	public void ActionCompress() throws IOException {
		boolean image = false;
		if (source1.getText().contains(".jpg") || source2.getText().contains(".jpg"))
			image = true;
		Quantizer.compress(source1.getText(), source2.getText(), levels, image);
		done.setVisible(true);
	}

	public void ActionDecompress() throws ClassNotFoundException, IOException {
		boolean image = false;
		if (source1.getText().contains(".jpg") || source2.getText().contains(".jpg"))
			image = true;

		Quantizer.decompress(source1.getText(), source2.getText(), image);
		done.setVisible(true);
	}

	public void levelOne() {
		levels = 1;
		bits.setText(bit1.getText() + "");
	}

	public void levelTwo() {
		levels = 2;
		bits.setText(bit2.getText());
	}

	public void levelThree() {

		bits.setText(bit3.getText());
		levels = 3;
	}

	public void levelFour() {

		bits.setText(bit4.getText());
		levels = 4;
	}

	public void levelFive() {

		bits.setText(bit5.getText());
		levels = 5;
	}

	public void levelSix() {

		bits.setText(bit6.getText());
		levels = 6;
	}

	public void level7() {

		bits.setText(bit7.getText());
		levels = 7;
	}

	public void level8() {

		bits.setText(bit8.getText());
		levels = 8;
	}

	public void clear()
	{
		source1.setText(null);
		source2.setText(null);
		
	}
	public void close()
	{
		System.out.println("closed");
	}
	
	public void clearall()
	{
		clear();
		bits.setText("bits");
		done.setVisible(false);
	}
	@FXML
	private MenuButton bits;
	@FXML
	private RadioMenuItem bit1;
	@FXML
	private RadioMenuItem bit2;
	@FXML
	private RadioMenuItem bit3;
	@FXML
	private RadioMenuItem bit4;
	@FXML
	private RadioMenuItem bit5;
	@FXML
	private RadioMenuItem bit6;
	@FXML
	private RadioMenuItem bit7;
	@FXML
	private RadioMenuItem bit8;

}
