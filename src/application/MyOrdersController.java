package application;

import java.util.ArrayList;

import Logistics.Order;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class MyOrdersController {
	
	private TextEditor controll;

	@FXML
    public void initialize()
    {
		controll = TextEditor.getInstance();
		ArrayList<Order> orders = controll.getOrders();
		String text = "";
		int i=1;
		for (Order order : orders) {
			text+=i+") "+ order.toString()+"\n";
			i++;
		}
		textOrders.setText(text);
    }

    @FXML
    private AnchorPane c1;

    @FXML
    private TextArea textOrders;
    
}


