package application;

import Actors.Manager;
import Logistics.ParkingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SetUpWindowController {
	
	ParkingController controller;
	Manager manager;
	
	@FXML
    public void initialize()
    {
		controller =new ParkingController();
    }
	
	void initData(Manager m) {
	    manager = m;
	  }
    @FXML
    private AnchorPane c1;

    @FXML
    private TextField parkingId;

    @FXML
    private TextField location;

    @FXML
    private TextField nameParking;

    @FXML
    private TextField capacity;

    @FXML
    private Button btOk;

    @FXML
    void clickOnOk(ActionEvent event) {
    	
    	

    }

}
