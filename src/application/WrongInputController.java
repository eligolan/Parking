package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author user - Noa bayer
 * @author user - Shlomi Ohana
 * @author user - Eli Golan
 * @author user - Stephanie Shalmoni
 * The class use to show errors to the client
 */
public class WrongInputController {
	
	private TextEditor textEditor;
	
    @FXML
    private AnchorPane c1;

    @FXML
    private Label bigText;

    @FXML
    private Label smallText;
    
    /**
     * Initialize variables
     */
    @FXML
    public void initialize()
    {
		textEditor =TextEditor.getInstance();
		bigText.setText(textEditor.getBigText());
		smallText.setText(textEditor.getSmallText());
    }

}
