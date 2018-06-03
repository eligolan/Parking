/**
 * Sample Skeleton for 'OneTimeReq.fxml' Controller Class
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class OneTimeReqController {

    @FXML // fx:id="textId"
    private TextField textId; // Value injected by FXMLLoader

    @FXML // fx:id="ok"
    private Button ok; // Value injected by FXMLLoader
    
    @FXML // fx:id="parkingRes"
    private Button parkingRes; // Value injected by FXMLLoader

    @FXML
    void clickOk(ActionEvent event) {
    	String id = textId.getText();
    	if(checkInputIsValid(id))
    	{
    		getMainWindow(event);
    	}
    	else
    	{
    		wrongInput(event);
    	}

    }
    
    @FXML
    void clickRes(ActionEvent event) {

    }
    
    

	private boolean checkInputIsValid(String id) {
		// TODO Auto-generated method stub
		return false;
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

}
