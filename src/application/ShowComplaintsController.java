package application;

import java.util.ArrayList;

import ClientServer.ClientServerController;
import ClientServer.ObjectSender;
import Logistics.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class ShowComplaintsController {
	
	/**
	 * Initialize variables
	 */
	@FXML
	public void initialize()
	{
		ObjectSender snd = new ObjectSender(13,"");
		ArrayList<String> compl = (ArrayList<String>) ClientServerController.sendMsgToServer(snd);
		String text = "";
		int i=1;
		for (String c : compl) {
			text+=i+") "+ c +"\n";
			i++;
		}
		textCmp.setText(text);
	}

    @FXML
    private AnchorPane c1;

    @FXML
    private TextArea textCmp;

    @FXML
    private Button refun;

    /**
     * Should refund the customer
     * @param event
     */
    @FXML
    void clickOnRefun(ActionEvent event) {}
}
