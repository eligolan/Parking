/**
 * Sample Skeleton for 'Register.fxml' Controller Class
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegisterController {

    @FXML
    private AnchorPane c1;

    @FXML
    private TextField name;

    @FXML
    private TextField carNumText;

    @FXML
    private TextField emailText;

    @FXML
    private Button register;

    @FXML
    private PasswordField pass;

    @FXML
    void clickOnRegister(ActionEvent event) {
    	try {
    		FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("MainWindow.fxml")) ;
    		Parent root1 = (Parent) fxmloader.load();
    		 Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
    		Stage stage = new Stage();
    		stage.initModality(Modality.APPLICATION_MODAL);
    		stage.initOwner(existingWindow);
    		stage.setTitle("MainWindow");
    		stage.setScene(new Scene(root1));
    		stage.show();
    	}catch (Exception e)
    	{
    		System.out.println("couldnt open the MainWindow windows");
    	}

    }

}
