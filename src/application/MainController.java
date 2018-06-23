package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ClientServer.ClientConsole;
import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import Logistics.Order;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author user - Noa bayer
 * @author user - Shlomi Ohana
 * @author user - Eli Golan
 * @author user - Stephanie Shalmoni
 * The class is used to connect with the SQL and runs our first window
 */
public final class MainController extends Application {

	public MainController() {}
	
	/**
	 * Run the first window
	 * @param args
	 */
	public void showMainMenu(String[] args)
	{ 
		launch(args);
	}
	
	/**
	 * Show the first window of the program
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		// constructing our scene
		URL url = getClass().getResource("GGuiScene.fxml");
		AnchorPane pane = FXMLLoader.load( url );
		Scene scene = new Scene( pane );
		// setting the stage
		primaryStage.setScene( scene );
		primaryStage.setTitle( "Parking Lot" );
		primaryStage.show();
	}
	
	/**
	 * Send the name and pass to the SQL
	 * @param userAndPass
	 * @return return if exist or not
	 */
	public boolean sendUserAndPassToClient(Object userAndPass)
	{
		String msg = ClientServerController.sendMsgToServer(userAndPass).toString();
		if(msg.equals("SingIn failed!")) {
			return false;
		}
		return true;
	}
	
	
	public boolean registerUserAndPassToClient(Object userAndPass)
	{
		String msg = ClientServerController.sendMsgToServer(userAndPass).toString();
		if(msg.equals("Register failed!")) {
			return false;
		}
		return true;
	}


	public boolean isManager(Object check) {
		String msg = ClientServerController.sendMsgToServer(check).toString();
		if(msg.equals("failed!")) {
			return false;
		}
		return true;
	}


	public int getId(Object user) {
		// TODO Auto-generated method stub
		return (int) ClientServerController.sendMsgToServer(user);
	}

	
	public ArrayList<Order> getOrders(Object msg) {
		return (ArrayList<Order>) ClientServerController.sendMsgToServer(msg);
	}

	
	public String getDateReg(ObjectSender msg) {
		return ClientServerController.sendMsgToServer(msg).toString();
	}
}