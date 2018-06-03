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
		controller =new ParkingController();
    }

    @FXML
    private AnchorPane c1;

    @FXML
    private TextField numId;

    @FXML
    private TextField numParkingLot;

    @FXML
    private Button cancel;

    @FXML
    private Button exit;


    @FXML
    void clickOnCancel(ActionEvent event) {
    	if(controller.isParkingExist())
    	{
    		controller.cancelParking();
    	}

    }

    @FXML
    void clickOnExit(ActionEvent event) {
    	if(controller.isParkingExist())
    	{
    		controller.exitParking();
    		pay();
    	}

    }

	private void pay() {
		// TODO Auto-generated method stub
		
	}

    

}
