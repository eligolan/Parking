package application;
import Actors.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class ManagerWindowController {

    @FXML
    private AnchorPane c1;

    @FXML
    private Button setUpBtn;

    @FXML
    private Button view;

    Manager manager;
    
    void initData(Manager m) {
	    manager = m;
	  }
    
    @FXML
    void clickOnSetUp(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(
    			    getClass().getResource(
    			      "SetUpWindow.fxml"
    			    )
    			  );

    			  Stage stage = new Stage(StageStyle.DECORATED);
    			  stage.setScene(
    			    new Scene(
    			      (Pane) loader.load()
    			    )
    			  );

    			  SetUpWindowController controller = 
    			    loader.<SetUpWindowController>getController();
    			  controller.initData(manager);

    			  stage.show();
		}catch (Exception e)
		{
			System.out.println("couldnt open the SetUpWindow windows");
		}

    }

    @FXML
    void clickOnView(ActionEvent event) {

    }

}
