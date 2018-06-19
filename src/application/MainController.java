package application;

import java.io.IOException;
import java.net.URL;

import ClientServer.ClientConsole;
import ClientServer.ClientServerController;
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
	
	public boolean sendUserAndPassToClient(String userAndPass)
	{
		String msg = ClientServerController.sendMsgToServer(userAndPass).toString();
		if(msg.equals("SingIn faild!")) {
			return false;
		}
		return true;
	}
	
}