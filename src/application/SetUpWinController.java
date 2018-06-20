package application;

import Actors.Manager;
import Logistics.ParkingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    private TextField capacity;

    @FXML
    private Button btn;

    @FXML
    private Label labelP;

    @FXML
    void clickOnOk(ActionEvent event) {
    	contoller.SetUp(Integer.parseInt(parkingId.getText()) , loc.getText() , null, name.getText() ,Integer.parseInt(capacity.getText()));
    	
    }

}
