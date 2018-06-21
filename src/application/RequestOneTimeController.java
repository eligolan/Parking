/**
 * Sample Skeleton for 'RequestOneTime.fxml' Controller Class
 */

package application;

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

	@FXML
	public void initialize()
	{
		controller = ParkingController.getInstance();
	}
	@FXML
	private AnchorPane c1;

	@FXML
	private TextField idText;

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
	private TextField startDay;

	@FXML
	private TextField timeEnd;

	@FXML
	private TextField endDay;

	@FXML
	void clickOnPay(ActionEvent event) {
		try {
			int parking_id = Integer.parseInt(parkingNum.getText());
			int customer_id = Integer.parseInt(idText.getText());
			int car_id = Integer.parseInt(carNumText.getText());
			String email = emailText.getText();

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
					showMsg(event,"Your Request Is Not Approved"," ");
					System.out.println("failed!");
				}
				else {
					showMsg(event,"Your Request Is Approved"," ");
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

}
