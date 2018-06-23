package application;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.mysql.fabric.xmlrpc.base.Data;

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
		if(controller.checkIfparkingLotExist(Integer.parseInt(numParkingLot.getText()))) {
			controller.cancelParking(Integer.parseInt(numParkingLot.getText()) , Integer.parseInt(numId.getText()) , Integer.parseInt(carNumber.getText()));
		}

		Order order = null;
		order = controller.getOrder(Integer.parseInt(orderNum.getText()));
		if(order!=null) {
			Date current = new Date();
			long difference = order.getArrivalTime().getTime() - current.getTime();
			int hoursDiff = (int) TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS);
			if(hoursDiff > 3) {
				System.out.println("10% more");
			}
			else if(hoursDiff <=3 && hoursDiff >=1) {
				System.out.println("50% more");
			}
			else if(hoursDiff <=1 && hoursDiff >=0) {
				System.out.println("100% more");
			}
		}

		ObjectSender snd = new ObjectSender(10,orderNum.getText());
		if((boolean)ClientServerController.sendMsgToServer(snd)) {
			System.out.println("yes");
		}
	}

	@FXML
	void clickOnExit(ActionEvent event) {
		if(controller.checkIfparkingLotExist(Integer.parseInt(numParkingLot.getText()))) {
			controller.exitParking(Integer.parseInt(numParkingLot.getText()) , Integer.parseInt(numId.getText()) , Integer.parseInt(carNumber.getText()));
		}	

		ObjectSender snd = new ObjectSender(10,orderNum.getText());
		if((boolean)ClientServerController.sendMsgToServer(snd)) {
			System.out.println("yes");
		}
	}
}
