package application;


import java.util.ArrayList;

import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import Logistics.Order;
import Logistics.ParkingController;
import Logistics.PlotInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * 
 * @author user - Noa bayer
 * @author user - Shlomi Ohana
 * @author user - Eli Golan
 * @author user - Stephanie Shalmoni
 * The class is used to show the registered client main window
 */
public class MainWindowController {

	@FXML
	private Button complaint;

	@FXML
	private Button exitParking;

	@FXML
	private Button cancelParking;

	@FXML
	private AnchorPane c1;

	@FXML
	private Button orderParking;


	@FXML
	private Button myOrders;
	
    @FXML
    private Button signOut;

    /**
     * Show the order parking window
     * @param event
     */
	@FXML
	void clickOnOrderParking(ActionEvent event) {

		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("RequestOneTime.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("Request");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			System.out.println("couldnt open the MainWindow windows");
		}

	}

	/**
	 * Show the exit parking window
	 * @param event
	 */
	@FXML
	void clickOnExitParking(ActionEvent event) {
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("GettingDeatailsParking.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("GettingDeatailsParking");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			System.out.println("couldnt open the GettingDeatailsParking windows");
		}
	}

	/**
	 * Show the cancel order window
	 * @param event
	 */
	@FXML
	void clickOnCancelParking(ActionEvent event) {
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("GettingDeatailsParking.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("GettingDeatailsParking");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			System.out.println("couldnt open the GettingDeatailsParking windows");
		}
	}

	/**
	 * Show the send complaint window
	 * @param event
	 */
	@FXML
	void clickOnComplaint(ActionEvent event) {

		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("SendComplaint.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("GettingDeatailsParking");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			System.out.println("couldnt open the GettingDeatailsParking windows");
		}
	}

	/**
	 * Show the my orders window
	 * @param event
	 */
	@FXML
	void clickOnMyOrders(ActionEvent event) {
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("MyOrders.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("GettingDeatailsParking");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			System.out.println("couldn't open the GettingDeatailsParking windows");
		}
	}
	
	/**
	 * Sign out of the user
	 * @param event
	 */
    @FXML
    void clickOnsignOut(ActionEvent event) {
    	
    	int idUser = TextEditor.getInstance().getCst().getId();	
    	/* remove from online */
    	ObjectSender snd = new ObjectSender(16,idUser);
        ClientServerController.sendMsgToServer(snd);
    	
        /* clear */
        ParkingController.getInstance().ExitSystem();
		
    	Stage stage = (Stage) signOut.getScene().getWindow();
        stage.close();
    }
}
