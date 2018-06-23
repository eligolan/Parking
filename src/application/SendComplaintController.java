package application;

import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * 
 * @author user - Noa bayer
 * @author user - Shlomi Ohana
 * @author user - Eli Golan
 * @author user - Stephanie Shalmoni
 * The class used to show the send complaint window
 */
public class SendComplaintController {
    @FXML
    private AnchorPane c1;

    @FXML
    private TextArea textComplaint;

    @FXML
    private Button send;

    /**
     * Send the complaint to the system
     * @param event
     */
    @FXML
    void clickOnSend(ActionEvent event) {
    	String nameSender = TextEditor.getInstance().cst.getName();
    	ObjectSender snd = new ObjectSender(12,nameSender+ ":\n"+ "complaint: " + textComplaint.getText());
//    	Stage stage = (Stage) send.getScene().getWindow();
//        stage.close();
    	ClientServerController.sendMsgToServer(snd);
    	showMsg(event,"Your massage sent","you will get answer less than 24 hours");
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
			stage.setTitle("Input");
			stage.setScene(new Scene(root1));
			stage.show();
		} catch (Exception e) {
			System.out.println("couldnt open the WrongInput wondows");
		}
	}
}
