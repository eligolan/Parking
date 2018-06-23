package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class NotClientOneTimeController {

    @FXML
    private AnchorPane c1;

    @FXML
    private Button order;

    @FXML
    private Button exitCancel;

    @FXML
    void clickOnCancelExit(ActionEvent event) {
    	try {
    		FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("GettingDeatailsParking.fxml")) ;
    		Parent root1 = (Parent) fxmloader.load();
    		Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
    		Stage stage = new Stage();
    		stage.initModality(Modality.APPLICATION_MODAL);
    		stage.initOwner(existingWindow);
    		stage.setTitle("RequestOneTime");
    		stage.setScene(new Scene(root1));
    		stage.show();
    	}catch (Exception e)
    	{
    		System.out.println("couldnt open the RequestOneTime windows");
    	}

    }

    @FXML
    void clickOnOreder(ActionEvent event) {
    	
		try {
		FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("RequestOneTime.fxml")) ;
		Parent root1 = (Parent) fxmloader.load();
		Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(existingWindow);
		stage.setTitle("RequestOneTime");
		stage.setScene(new Scene(root1));
		stage.show();
	}catch (Exception e)
	{
		System.out.println("couldnt open the RequestOneTime windows");
	}

    }

}
