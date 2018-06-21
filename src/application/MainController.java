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
public final class MainController extends Application {
	public MainController() {
	}
	
	
	
	public void showMainMenu(String[] args)
	{ 
		launch(args);
	}
	
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
	
}