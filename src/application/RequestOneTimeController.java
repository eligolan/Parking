/**
 * Sample Skeleton for 'RequestOneTime.fxml' Controller Class
 */

package application;

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
		controller =new ParkingController();
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
    	if(controller.isParkingFull(Integer.parseInt(parkingNum.toString())))
    	{
    		/*TODO how to order? what is the right function?*/
    		Time arrival = new Time(Integer.parseInt(timeStart.toString()),Integer.parseInt(startDay.toString()),dateStart.toString());
    		Time departure = new Time(Integer.parseInt(timeEnd.toString()),Integer.parseInt(endDay.toString()),endDate.toString());
    		controller.orderParking(Integer.parseInt(parkingNum.toString()),Integer.parseInt(idText.toString()),Integer.parseInt(carNumText.toString()) ,2,"",arrival ,departure );
    	}
    	else
    	{
    		showNoParkingAvailable(event);
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

//	private void showNoParkingExist(ActionEvent event) {
//    	try {
//			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("NoParkingExist.fxml")) ;
//			Parent root1 = (Parent) fxmloader.load();
//			 Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
//			Stage stage = new Stage();
//			stage.initModality(Modality.APPLICATION_MODAL);
//			stage.initOwner(existingWindow);
//			stage.setTitle("Main Window");
//			stage.setScene(new Scene(root1));
//			stage.show();
//		}catch (Exception e)
//		{
//			System.out.println("couldnt open the MainWindow windows");
//		}
//		
//	}

}
