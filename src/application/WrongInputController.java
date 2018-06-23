package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author user
 *this class showing the messages for per process
 *
 */
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
		textEditor =TextEditor.getInstance();
		bigText.setText(textEditor.getBigText());
		smallText.setText(textEditor.getSmallText());
    }

}
