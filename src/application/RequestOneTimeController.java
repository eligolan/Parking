/**
 * Sample Skeleton for 'RequestOneTime.fxml' Controller Class
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RequestOneTimeController {

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

    }

}
