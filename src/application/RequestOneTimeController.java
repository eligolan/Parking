/**
 * Sample Skeleton for 'RequestOneTime.fxml' Controller Class
 */

package application;

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

public class RequestOneTimeController {
	
	private ParkingController controller;
	
	@FXML
    public void initialize()
    {
		controller =new ParkingController();
    }

    @FXML // fx:id="carNumText"
    private TextField carNumText; // Value injected by FXMLLoader

    @FXML // fx:id="endTimeText"
    private TextField endTimeText; // Value injected by FXMLLoader

    @FXML // fx:id="pay"
    private Button pay; // Value injected by FXMLLoader

    @FXML // fx:id="emailText"
    private TextField emailText; // Value injected by FXMLLoader

    @FXML // fx:id="idText"
    private TextField idText; // Value injected by FXMLLoader

    @FXML // fx:id="startTimeText"
    private TextField startTimeText; // Value injected by FXMLLoader

    @FXML // fx:id="c1"
    private AnchorPane c1; // Value injected by FXMLLoader

    @FXML
    void clickOnPay(ActionEvent event) {
    	if(controller.isParkingFull(idText))
    	{
    		controller.orderParking();
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
