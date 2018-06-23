package application;

import java.io.IOException;

import Actors.Manager;
import Logistics.ParkingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * 
 * @author user - Noa bayer
 * @author user - Shlomi Ohana
 * @author user - Eli Golan
 * @author user - Stephanie Shalmoni
 * The class used to show the parking lot initialize
 */
public class SetUpWinController {

	ParkingController contoller;

	/**
	 * Initialize variables
	 */
	@FXML
	public void initialize()
	{
		contoller = ParkingController.getInstance();
	}

	@FXML
	private AnchorPane c1;

	@FXML
	private TextField parkingId;

	@FXML
	private TextField loc;

	@FXML
	private TextField name;

	@FXML
	private Button btn;

	@FXML
	private Label labelP;

	@FXML
	private CheckBox smallCapacity;

	@FXML
	private CheckBox bigCapacity;

	/**
	 * Check the needed fields
	 * @param event
	 */
	@FXML
	void clickOnOk(ActionEvent event) {
		int capacity=0; 
		if((smallCapacity.isSelected() && bigCapacity.isSelected()) ||
				((!smallCapacity.isSelected()) && (!bigCapacity.isSelected()))) {
			showMsg(event,"Wrong Input","pick 1 on check box");

		} else {
			if(smallCapacity.isSelected()){
				capacity=12;
			} else {
				capacity=24;
			}
		}
		contoller.addParkingLot(Integer.parseInt(parkingId.getText()), 44, capacity);
	}
	
	private void openScene(String sceneName, ActionEvent event) {
		Parent parent;
		try {
			parent = FXMLLoader.load((getClass().getResource(sceneName)));
			Scene child = new Scene(parent);

			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
			window.setScene(child);
			window.show();
		} catch (IOException e) {
			System.out.println("couldnt open the MainWindow windows");
		}
	}

	private void showMsg(ActionEvent event,String text,String smallText) {
		try {
			TextEditor.getInstance().setBigText(text);
			TextEditor.getInstance().setSmallText(smallText);
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("WrongInput.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("Input");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			System.out.println("couldnt open the WrongInput wondows");
		}
	}
}
