package application;

import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    	ClientServerController.sendMsgToServer(snd);
    	Stage stage = (Stage) send.getScene().getWindow();
        stage.close();
    }
}
