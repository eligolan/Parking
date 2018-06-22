package application;

import java.util.Locale;
import java.util.ResourceBundle;

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

public class ChoseFloorController {

    @FXML
    private AnchorPane c1;

    @FXML
    private Button f1;

    @FXML
    private Button f2;

    @FXML
    private Button f3;

    @FXML
    void clickOnFloor1(ActionEvent event) {
    	try {
    		
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("ParkingView.fxml")) ;
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

    @FXML
    void clickOnFloor2(ActionEvent event) {

    }

    @FXML
    void clickOnFloor3(ActionEvent event) {

    }

}
