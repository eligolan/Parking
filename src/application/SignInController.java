/**
 * Sample Skeleton for 'SignIn.fxml' Controller Class
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

public class SignInController {
	
	private MainController controll;
//	
//   public SignInController(GuiController controll)
//   {
//	   this.controll = controll;
//   }
	
	@FXML
    public void initialize()
    {
		controll =new MainController();
    }

    @FXML // fx:id="userText"
    private TextField userText; // Value injected by FXMLLoader

    @FXML // fx:id="passText"
    private PasswordField passText; // Value injected by FXMLLoader

    @FXML // fx:id="signIn"
    private Button signIn; // Value injected by FXMLLoader

    @FXML // fx:id="c1"
    private AnchorPane c1; // Value injected by FXMLLoader

    @FXML
    void clickOnSignIn(ActionEvent event) {
    	String user = userText.getText();
    	String pass = passText.getText();
    	if(checkInputIsValid(user, pass))
    	{
    		controll.sendUserAndPassToClient(user+" "+pass);
    		getMainWindow(event);
    	}
    	else
    	{
    		wrongInput(event);
    	}

    }

	private boolean checkInputIsValid(String user, String pass) {
		// TODO check if it is write propetlly and it is on the server
		return true;
	}
	
	private void getMainWindow(ActionEvent event) {
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("MainWindow.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			 Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("Main Window");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch (Exception e)
		{
			System.out.println("couldnt open the MainWindow windows");
		}
	}

	private void wrongInput(ActionEvent event) {
		try {
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
