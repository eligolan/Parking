package application;

import java.io.IOException;

import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import Logistics.ParkingController;
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

/**
 * 
 * @author user - Noa bayer
 * @author user - Shlomi Ohana
 * @author user - Eli Golan
 * @author user - Stephanie Shalmoni
 * The class used to show the manager window
 */
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
    private Button signOut;

	@FXML
	void clickHandleComp(ActionEvent event) {
		setWindow(event,"ShowComplaints.fxml");
	}

	/**
	 * Change the parking price
	 * @param event
	 */
	@FXML
	void clickOnChangePrice(ActionEvent event) {
		try {
		int pricePerHour =Integer.parseInt(textPrice.getText());
		showMsg(event,"The Change Accept", "new price is: "+pricePerHour);
		} catch(Exception e) {
			showMsg(event,"Wrong Input", "try again");
		}
	}

	/**
	 * Parking Lot setup
	 * @param event
	 */
	@FXML
	void clickOnSetUp(ActionEvent event) {
		setWindow(event,"SetUpWin.fxml");
	}

	/**
	 * View the parking lot
	 * @param event
	 */
	@FXML
	void clickOnView(ActionEvent event) {
		setWindow(event,"ChoseFloor.fxml");
	}

	/**
	 * 
	 * @param event
	 * @param fxmlFile
	 */
	void setWindow(ActionEvent event, String fxmlFile) {
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
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("couldn't open the SetUpWin windows");
		}
	}
	
	/**
	 * 
	 * @param event
	 * @param text
	 * @param smallText
	 */
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
		} catch (Exception e) {
			System.out.println("couldn't open the WrongInput wondows");
		}
	}
	

	/**
	 * Sign out of system
	 * @param event
	 */
    @FXML
    void clickOnSignOut(ActionEvent event) {
    	
    	int idUser = TextEditor.getInstance().getCst().getId();	
    	/* remove from online */
    	ObjectSender snd = new ObjectSender(16,idUser);
        ClientServerController.sendMsgToServer(snd);
    	
        /* clear */
        ParkingController.getInstance().ExitSystem();
		
    	Stage stage = (Stage) signOut.getScene().getWindow();
        stage.close();
    }
}