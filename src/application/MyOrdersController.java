package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class MyOrdersController {

    @FXML
    private AnchorPane c1;

    @FXML
    private TextArea textOrders;
    
    @FXML
    public void initialize()
    {
    	textOrders.setText("1. parking at ....");
    }

}
