package application;

import Logistics.ParkingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class GettingDeatailsController {
private ParkingController controller;
	
	@FXML
    public void initialize()
    {
		controller =ParkingController.getInstance();
    }

    @FXML
    private AnchorPane c1;

    @FXML
    private TextField orderNum;

    @FXML
    private Button cancel;

    @FXML
    private Button exit;



    @FXML
    void clickOnCancel(ActionEvent event) {
//    	if(controller.checkIfparkingLotExist(Integer.parseInt(numParkingLot.getText())))
//    	{
//    		controller.cancelParking(Integer.parseInt(numParkingLot.getText()) , Integer.parseInt(numId.getText()) , Integer.parseInt(carNumber.getText()));
//    	}
    	

    }

    @FXML
    void clickOnExit(ActionEvent event) {
//    	if(controller.checkIfparkingLotExist(Integer.parseInt(numParkingLot.getText())))
//    	{
//    		controller.exitParking(Integer.parseInt(numParkingLot.getText()) , Integer.parseInt(numId.getText()) , Integer.parseInt(carNumber.getText()));
//    	}

    }


    

}
