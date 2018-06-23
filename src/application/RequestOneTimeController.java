/**
 * Sample Skeleton for 'RequestOneTime.fxml' Controller Class
 */

package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Actors.Customer;
import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import Logistics.Location;
import Logistics.Order;
import Logistics.ParkingController;
import Logistics.Parking_Lot;
import Logistics.PlotInfo;
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
			ParkingController.getInstance().ExitSystem();
			/* initialize parking */
			ObjectSender snd = new ObjectSender(11,"");
			ParkingController.getInstance().SetUpParkingLot((ArrayList<PlotInfo>)
					ClientServerController.sendMsgToServer(snd));

			snd = new ObjectSender(7,"");
			ParkingController.getInstance().SetUpOrders((ArrayList<Order>)
					ClientServerController.sendMsgToServer(snd));


			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			int parking_id = Integer.parseInt(parkingNum.getText());
			int customer_id = textEditor.getCst().getId();
			int car_id = Integer.parseInt(carNumText.getText());
			String email = emailText.getText();

			String dateArrive = dateStart.getText() + " " + timeStart.getText().trim();
			String dateEnd = endDate.getText() + " " + timeEnd.getText().trim();
			Date arrive = dateFormat.parse(dateArrive);
			Date end = dateFormat.parse(dateEnd);

			if(controller.checkIfparkingLotExist(parking_id)==false)
				throw new Exception();
			if(controller.isParkingFull(parking_id))
			{
				showNoParkingAvailable(event);
			}

			else {
				ObjectSender snd1 = new ObjectSender(9,parking_id+" " + customer_id + " " + car_id);
				int orderId1 = (int) ClientServerController.sendMsgToServer(snd1);
				Location loc = controller.orderParking(Integer.parseInt(parkingNum.getText()),textEditor.getCst(),carNumText.getText(),orderId1,1,emailText.getText(),arrive,end);
				if(loc == null) {
					showMsg(event,"Not Approved"," ");
					return;
				}
				snd = new ObjectSender(4,parking_id+" " + customer_id + " " + car_id + " " + email + " " + dateArrive + " " + dateEnd + " " + loc.getX() + " " + loc.getY() + " " + loc.getZ() + " ");
				String msg = ClientServerController.sendMsgToServer(snd).toString();
				if(msg.equals("failed!")) {
					showMsg(event,"Not Approved"," ");
					System.out.println("failed!");
				}
				else {
					/* update */
					snd = new ObjectSender(9,parking_id+" " + customer_id + " " + car_id);
					int orderId = (int) ClientServerController.sendMsgToServer(snd);
					ParkingController.getInstance().ExitSystem();
					/* initialize parking */
					snd = new ObjectSender(11,"");
					ParkingController.getInstance().SetUpParkingLot((ArrayList<PlotInfo>)
							ClientServerController.sendMsgToServer(snd));

					snd = new ObjectSender(7,"");
					ParkingController.getInstance().SetUpOrders((ArrayList<Order>)
							ClientServerController.sendMsgToServer(snd));				
					/* get orders */
					String user = TextEditor.getInstance().getCst().getName();
					int idUser = TextEditor.getInstance().getCst().getId();
					snd = new ObjectSender(6, user + " " + idUser);
					ArrayList<Order> orders = (ArrayList<Order>) ClientServerController.sendMsgToServer(snd); 				
					TextEditor.getInstance().setOrders(orders);
					/**/
					showMsg(event,"Approved! \n Remember you order id: " + orderId,""+getPay(arrive, end));	
					/* close window */
					Stage stage = (Stage) pay.getScene().getWindow();
					stage.close();
				}
			}
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
		if(daysDiff > 0) return ((daysDiff*24*price)+(hoursDiff*price));
		return (hoursDiff*price);
	}

}
