package application;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.mysql.fabric.xmlrpc.base.Data;

import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import Logistics.Order;
import Logistics.ParkingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

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
		/*		if(controller.checkIfparkingLotExist(Integer.parseInt(numParkingLot.getText()))) {
			controller.cancelParking(Integer.parseInt(numParkingLot.getText()) , Integer.parseInt(numId.getText()) , Integer.parseInt(carNumber.getText()));
		}
		 */
		Order order = null;
		order = controller.getOrder(Integer.parseInt(orderNum.getText()));
		if(order!=null) {
			getPay(order);
		}

		ObjectSender snd = new ObjectSender(10,orderNum.getText());
		if((boolean)ClientServerController.sendMsgToServer(snd)) {
			System.out.println("yes");
		}
	}

	@FXML
	void clickOnExit(ActionEvent event) {
		/*		if(controller.checkIfparkingLotExist(Integer.parseInt(numParkingLot.getText()))) {
			controller.exitParking(Integer.parseInt(numParkingLot.getText()) , Integer.parseInt(numId.getText()) , Integer.parseInt(carNumber.getText()));
		}*/	

		ObjectSender snd = new ObjectSender(10,orderNum.getText());
		if((boolean)ClientServerController.sendMsgToServer(snd)) {
			System.out.println("yes");
		}
	}


	private void showMsg(ActionEvent event,String text,String smallText,String sceneName) {
		try {
			TextEditor.getInstance().setBigText(text);
			TextEditor.getInstance().setSmallText(smallText);
			Parent parent;
			parent = FXMLLoader.load((getClass().getResource(sceneName)));
			Scene child = new Scene(parent);

			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
			window.setScene(child);
			window.show();
		} catch (IOException e) {
			System.out.println("couldnt open the MainWindow windows");
		}
	}

	private double getPay(Order order) {
		float daysBetween = 0;
		int hoursDiff = 0;
		double price = order.getPricePerHour();
		Date arrivalDate = order.getArrivalTime();
		Date current = new Date();
		long difference = current.getTime() - arrivalDate.getTime();
		daysBetween = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
		hoursDiff =  (int) TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS);
		int daysDiff = Math.round(daysBetween);
		if(daysDiff > 0) {
			System.out.println("More than 3 hours - 10%");
			return ((daysDiff*24*price)+(hoursDiff*price)*0.1);
		}

		else if(hoursDiff <=1 && hoursDiff >=0) {
			System.out.println("Between 0 to 1 hours - full charge");
			return (hoursDiff*price);
		}

		else if(hoursDiff <=3 && hoursDiff >=1) {
			System.out.println("Between 1 to 3 hours - 50%");
			return (hoursDiff*price*0.5);
		}

		System.out.println("More than 3 hours - 10%");
		return ((daysDiff*24*price)+(hoursDiff*price)*0.1);
	}
}
