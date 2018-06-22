package application;

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

public class GGuiController {
	private MainController controll;
//
//	public GGuiController(GuiController controll){
//		this.controll = controll;
//
//	}
	
	@FXML
    public void initialize()
    {
		controll = new MainController();
    }

	@FXML
	private Button futureoneT;

	@FXML
	private Button signin;

	@FXML
	private Button onetime;

	@FXML
	private AnchorPane c1;

	@FXML
	private Button register;

	@FXML
	void clickSignIn(ActionEvent event) {
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("SignIn.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("SignIn Request");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch (Exception e)
		{
			System.out.println("couldnt open the SignIn windows");
		}


	}

	@FXML
	void clickRegister(ActionEvent event) {
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("Register.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("Register");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch (Exception e)
		{
			System.out.println("couldnt open the Register windows");
		}

	}

	@FXML
	void requestOneTime(ActionEvent event) {
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("RequestOneTime.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("RequestOneTime");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch (Exception e)
		{
			System.out.println("couldnt open the RequestOneTime windows");
		}

	}

	@FXML
	void clickOnFutureOneT(ActionEvent event) {
		try {
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("FutureOneTime.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("FutureOneTime");
			stage.setScene(new Scene(root1));
			stage.show();
		}catch (Exception e)
		{
			System.out.println("couldnt open the FutureOneTime windows");
		}

	}

}
