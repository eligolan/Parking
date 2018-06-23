package application;

import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SendComplaintController {
    @FXML
    private AnchorPane c1;

    @FXML
    private TextArea textComplaint;

    @FXML
    private Button send;

    @FXML
    void clickOnSend(ActionEvent event) {
    	String nameSender = TextEditor.getInstance().cst.getName();
    	ObjectSender snd = new ObjectSender(12,nameSender+ ":\n"+ "complaint: " + textComplaint.getText());
    	ClientServerController.sendMsgToServer(snd);
    	Stage stage = (Stage) send.getScene().getWindow();
        stage.close();
    }

}
