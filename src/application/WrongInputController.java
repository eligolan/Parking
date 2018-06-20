package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class WrongInputController {
	
	private TextEditor textEditor;
	
    @FXML
    private AnchorPane c1;

    @FXML
    private Label bigText;

    @FXML
    private Label smallText;
    
    @FXML
    public void initialize()
    {
		textEditor =new TextEditor();
		bigText.setText(textEditor.getWrongInput());
    }

}
