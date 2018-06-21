/**
 * Sample Skeleton for 'Register.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ClientServer.ObjectSender;
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
	
	private MainController controll;

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
    public void initialize()
    {
		controll =new MainController();
    }

    @FXML
    void clickOnRegister(ActionEvent event) {
    	String user = name.getText();
    	String password = pass.getText();
    	String email = emailText.getText();
    	String numCar = carNumText.getText();
    	String dateStart = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
    	
    	if(checkInputIsValid(user, password))
    	{
    		ObjectSender snd = new ObjectSender(2,user+" " + password + " " + email + " " + numCar + " " + 0 + " " + dateStart);
    		if(controll.registerUserAndPassToClient(snd)) {
    			openScene("MainWindow.fxml",event);
    			showMsg(event,"Register Success :)"," ");
    			return;
    		}else {
    			showMsg(event,"Wrong Input","try again");
    		}		
    	}
    	else
    	{
    		showMsg(event,"Wrong Input","try again");
    	}
    }
    
    private boolean checkInputIsValid(String user, String pass) {
		// TODO check if it is write propetlly and it is on the server
		return true;
	}
    


	private void openScene(String sceneName, ActionEvent event)
	{
		Parent parent;
		try {
			parent = FXMLLoader.load((getClass().getResource(sceneName)));
			Scene child = new Scene(parent);
			
			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
			window.setScene(child);
			window.show();
		} catch (IOException e) {
			System.out.println("couldnt open the MainWindow windows");
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

}
