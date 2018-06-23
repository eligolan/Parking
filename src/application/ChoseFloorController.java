package application;

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
/**
 * 
 * @author user - Noa bayer
 * this class is the gui for the meneger for picking floor
 * to see the image in the specific parking lot
 *
 */
public class ChoseFloorController {

    @FXML
    private AnchorPane c1;

    @FXML
    private Button floor1;

    @FXML
    private Button floor2;

    @FXML
    private Button floor3;
    
    @FXML
    private TextField parkingNum;
    
    @FXML
	public void initialize()
	{
    	
	}

    @FXML
    void clickOnFloor1(ActionEvent event) {
    	TextEditor.getInstance().setNumParking(Integer.parseInt(parkingNum.getText()));
    	TextEditor.getInstance().setFloor(1);
    	setWindow(event, "ParkingView.fxml");
    	

    }

    @FXML
    void clickOnFloor2(ActionEvent event) {
    	TextEditor.getInstance().setNumParking(Integer.parseInt(parkingNum.getText()));
    	TextEditor.getInstance().setFloor(2);
    	setWindow(event, "ParkingView.fxml");

    }

    @FXML
    void clickOnFloor3(ActionEvent event) {
    	TextEditor.getInstance().setNumParking(Integer.parseInt(parkingNum.getText()));
    	TextEditor.getInstance().setFloor(3);
    	setWindow(event, "ParkingView.fxml");

    }
    /**
     * 
     * @param event
     * @param fxmlFile
     */
    
    void setWindow(ActionEvent event, String fxmlFile)
	{
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource(fxmlFile)) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("SetUpWin");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("couldnt open the SetUpWin windows");
		}

	}
	

}
