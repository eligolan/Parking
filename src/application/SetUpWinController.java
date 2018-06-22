package application;

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

public class SetUpWinController {

	ParkingController contoller;

	@FXML
	public void initialize()
	{
		contoller =ParkingController.getInstance();
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

	@FXML
	void clickOnOk(ActionEvent event) {
		if((smallCapacity.isSelected() && bigCapacity.isSelected()) ||
				((!smallCapacity.isSelected()) && (!bigCapacity.isSelected())))
		{
			showMsg(event,"Wrong Input","you picked 2 check box, pick 1");

		}
		//contoller.SetUp(Integer.parseInt(parkingId.getText()) , loc.getText() , null, name.getText() ,Integer.parseInt(capacity.getText()));

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
		}catch (Exception e)
		{
			System.out.println("couldnt open the WrongInput wondows");
		}
	}

}
