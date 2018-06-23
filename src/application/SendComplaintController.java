package application;

import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class SendComplaintController {
    @FXML
    private AnchorPane c1;

    @FXML
    private TextArea textComplaint;

    @FXML
    private Button send;

    @FXML
    void clickOnSend(ActionEvent event) {
    	ObjectSender snd = new ObjectSender(12,textComplaint.getText());
    	ClientServerController.sendMsgToServer(snd);
    }

}
