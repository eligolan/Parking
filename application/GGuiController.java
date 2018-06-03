/**
 * Sample Skeleton for 'GGuiScene.fxml' Controller Class
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GGuiController {

	@FXML // fx:id="textPass"
    private PasswordField textPass; // Value injected by FXMLLoader

    @FXML // fx:id="textUser"
    private TextField textUser; // Value injected by FXMLLoader

    @FXML // fx:id="signin"
    private Button signin; // Value injected by FXMLLoader

    @FXML // fx:id="onetime"
    private Button onetime; // Value injected by FXMLLoader

    @FXML // fx:id="c1"
    private AnchorPane c1; // Value injected by FXMLLoader

    @FXML // fx:id="register"
    private Button register; // Value injected by FXMLLoader

    @FXML
    void requestOneTime(ActionEvent event) {
    	try {
    		FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("OneTimeReq.fxml")) ;
    		Parent root1 = (Parent) fxmloader.load();
    		 Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
    		Stage stage = new Stage();
    		stage.initModality(Modality.APPLICATION_MODAL);
    		stage.initOwner(existingWindow);
    		stage.setTitle("OneTime Request");
    		stage.setScene(new Scene(root1));
    		stage.show();
    	}catch (Exception e)
    	{
    		System.out.println("couldnt open the OneReq wondows");
    	}
    }
    
    @FXML
    void clickSignIn(ActionEvent event) {
    	String user = textUser.getText();
    	String pass = textPass.getText();
    	if(checkInputIsValid(user, pass))
    	{
    		getMainWindow(event);
    	}
    	else
    	{
    		wrongInput(event);
    	}

    }
    
    @FXML
    void clickRegister(ActionEvent event) {
    	String user = textUser.getText();
    	String pass = textPass.getText();
    	if(checkInputIsValid(user, pass))
    	{
    		getMainWindow(event);
    	}
    	else
    	{
    		wrongInput(event);
    	}

    }

	private void getMainWindow(ActionEvent event) {
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("MainWindow.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			 Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("Main Window");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch (Exception e)
		{
			System.out.println("couldnt open the MainWindow windows");
		}
	}

	private void wrongInput(ActionEvent event) {
		try {
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

	private boolean checkInputIsValid(String user, String pass) {
		// TODO Auto-generated method stub
		return true;
	}

}
