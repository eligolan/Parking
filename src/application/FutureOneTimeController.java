package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * 
 * @author user
 * this class is taking the deatails from the client
 * according to the deatails we order the parking request
 *
 */
public class FutureOneTimeController {

    @FXML
    private TextField carNumtext;

    @FXML
    private TextField dateStartText;

    @FXML
    private TextField dateEndText;

    @FXML
    private TextField parkingNum;

    @FXML
    private Button pay;

    @FXML
    private TextField emailText;

    @FXML
    private TextField idText;

    @FXML
    private TextField timeEndText;

    @FXML
    private TextField timeStartText;

    @FXML
    void clickOnPay(ActionEvent event) {

    }

}
