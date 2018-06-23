package application;

import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import Logistics.Order;
import Logistics.ParkingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class GettingDeatailsController {
private ParkingController controller;
private MainController controll;
	
	@FXML
    public void initialize()
    {
		controller =ParkingController.getInstance();
		controll = new MainController();
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
    	
    	Order o=controller.getOrder(Integer.parseInt(orderNum.getText()));
    	if(controller.checkIfparkingLotExist(o.getPlotId()))
    	{
    		controller.cancelParking(o.getPlotId(),o.getOrderId());
    	}
    	ObjectSender snd = new ObjectSender(10,orderNum.getText());
    	if((boolean)ClientServerController.sendMsgToServer(snd)) {
    		System.out.println("yes");
    	}
    	

    }

    @FXML
    void clickOnExit(ActionEvent event) {
    	Order o=controller.getOrder(Integer.parseInt(orderNum.getText()));
    	if(controller.checkIfparkingLotExist(o.getPlotId()))
    	{
    		controller.cancelParking(o.getPlotId(),o.getOrderId());
    	}

    }


    

}
