package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ManagerWindowController {

	@FXML
	private AnchorPane c1;

	@FXML
	private Button setUpBtn;

	@FXML
	private Button view;

	@FXML
	private Button changePrice;

	@FXML
	private Button handleComp;

	@FXML
	private TextField textPrice;

	@FXML
	void clickHandleComp(ActionEvent event) {
		setWindow(event,"ShowComplaints.fxml");

	}

	@FXML
	void clickOnChangePrice(ActionEvent event) {
		try {
		int pricePerHour =Integer.parseInt(textPrice.getText());
		showMsg(event,"The Change Accept", "new price is: "+pricePerHour);
		}
		catch(Exception e)
		{
			showMsg(event,"Wrong Input", "try again");
		}

	}

	@FXML
	void clickOnSetUp(ActionEvent event) {
		setWindow(event,"SetUpWin.fxml");
	}




	@FXML
	void clickOnView(ActionEvent event) {
		setWindow(event,"ChoseFloor.fxml");

	}

	void setWindow(ActionEvent event, String fxmlFile)
	{
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource(fxmlFile)) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("SetUpWin");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("couldnt open the SetUpWin windows");
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
			stage.setTitle("WrongInput");
			stage.setScene(new Scene(root1));
			
			stage.show();
		}catch (Exception e)
		{
			System.out.println("couldnt open the WrongInput wondows");
		}
	}

}