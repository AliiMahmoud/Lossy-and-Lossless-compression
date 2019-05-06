package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.fxml.FXML;

public class SampleController {

	@FXML
	private TextField source1;
	@FXML
	private TextField source2;
	@FXML
	private TextField size;
	@FXML
	private Button compressBtn;
	@FXML
	private Button decompressBtn;
	@FXML
	private Label done;

	private int levels = 0;

	public void ActionCompress() throws IOException {
		int vecSize = Integer.parseInt(size.getText());
		Quantizer.compressImg(source1.getText(), source2.getText(), vecSize, levels);
		done.setVisible(true);
	}

	public void ActionDecompress() throws ClassNotFoundException, IOException {
		Quantizer.decompress(source1.getText(), source2.getText());
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
		levels = 4;
	}

	public void levelFour() {

		bits.setText(bit4.getText());
		levels = 8;
	}

	public void levelFive() {

		bits.setText(bit5.getText());
		levels = 16;
	}

	public void levelSix() {

		bits.setText(bit6.getText());
		levels = 32;
	}

	public void level7() {

		bits.setText(bit7.getText());
		levels = 64;
	}

	public void level8() {

		bits.setText(bit8.getText());
		levels = 128;
	}

	public void clear() {
		source1.setText(null);
		source2.setText(null);
		size.setText(null);

	}

	public void close() {
	}

	public void clearall() {
		clear();
		bits.setText("Number Of Vectors");
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
