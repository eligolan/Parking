/**
 * Sample Skeleton for 'RequestOneTime.fxml' Controller Class
 */

package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import Logistics.ParkingController;
import Logistics.Parking_Lot;
import Logistics.Time;
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

public class RequestOneTimeController {

	private ParkingController controller;
	private TextEditor textEditor;

	@FXML
	public void initialize()
	{
		controller = ParkingController.getInstance();
		textEditor = TextEditor.getInstance();
	}

	@FXML
	private AnchorPane c1;

	@FXML
	private TextField carNumText;

	@FXML
	private TextField dateStart;

	@FXML
	private TextField endDate;

	@FXML
	private TextField emailText;

	@FXML
	private Button pay;

	@FXML
	private TextField parkingNum;

	@FXML
	private TextField timeStart;

	@FXML
	private TextField timeEnd;

	@FXML
	void clickOnPay(ActionEvent event) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			int parking_id = Integer.parseInt(parkingNum.getText());
			int customer_id = textEditor.getCst().getId();
			int car_id = Integer.parseInt(carNumText.getText());
			String email = emailText.getText();
			Date arrive = dateFormat.parse(dateStart.getText() + " " + timeStart.getText());
			Date end = dateFormat.parse(endDate.getText() + " " + timeEnd.getText());

			/*	Time arrival = new Time(Integer.parseInt(timeStart.getText()),Integer.parseInt(startDay.getText()),dateStart.getText());
			Time departure = new Time(Integer.parseInt(timeEnd.getText()),Integer.parseInt(endDay.getText()),endDate.getText());
			if(controller.checkIfparkingLotExist(parking_id)==false)
				throw new Exception();
			if(controller.isParkingFull(parking_id))
			{
				showNoParkingAvailable(event);
			}

			else {
				controller.orderParking(Integer.parseInt(parkingNum.getText()),Integer.parseInt(idText.getText()),Integer.parseInt(carNumText.getText()) ,2,"",arrival ,departure );
			 */	ObjectSender snd = new ObjectSender(4,parking_id+" " + customer_id + " " + car_id + " " + email + " ");
			 String msg = ClientServerController.sendMsgToServer(snd).toString();
			 if(msg.equals("failed!")) {
				 showMsg(event,"Not Approved"," ");
				 System.out.println("failed!");
			 }
			 else {
				 showMsg(event,"Approved",""+getPay(arrive, end));
				 System.out.println("success!");
			 }
			 //}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			showMsg(event,"Wrong Input","try again");
		}
	}

	private void showMsg(ActionEvent event,String text,String smallText) {
		try {
			TextEditor.getInstance().setBigText(text);
			TextEditor.getInstance().setSmallText(smallText);
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

	private void showNoParkingAvailable(ActionEvent event) {
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("NoParkingAvailable.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("NoParkingAvailable");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch (Exception e)
		{
			System.out.println("couldnt open the NoParkingAvailable windows");
		}

	}

	private double getPay(Date arrive, Date end) {
		double price = textEditor.getPrice();
		float daysBetween = 0;
		int hoursDiff = 0;
		long difference = end.getTime() - arrive.getTime();
		daysBetween = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
		hoursDiff =  (int) TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS);
		int daysDiff = Math.round(daysBetween);
		System.out.println(daysBetween);
		System.out.println(hoursDiff);
		if(daysDiff > 0) return ((daysDiff*24*price)+(hoursDiff*price));
		return (hoursDiff*price);
	}

}
