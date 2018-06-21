/**
 * Sample Skeleton for 'SignIn.fxml' Controller Class
 */

package application;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Actors.Customer;
import ClientServer.ObjectSender;
import Logistics.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SignInController {

	private MainController controll;
	private TextEditor controllEdit;
	//	
	//   public SignInController(GuiController controll)
	//   {
	//	   this.controll = controll;
	//   }

	@FXML
	public void initialize()
	{
		controll =new MainController();
		controllEdit = TextEditor.getInstance();
	}

	@FXML // fx:id="userText"
	private TextField userText; // Value injected by FXMLLoader

	@FXML // fx:id="passText"
	private PasswordField passText; // Value injected by FXMLLoader

	@FXML // fx:id="signIn"
	private Button signIn; // Value injected by FXMLLoader

	@FXML // fx:id="c1"
	private AnchorPane c1; // Value injected by FXMLLoader

	@FXML
	void clickOnSignIn(ActionEvent event) {
		String user = userText.getText();
		String pass = passText.getText();
		if(checkInputIsValid(user, pass))
		{
			ObjectSender snd = new ObjectSender(1,user+" "+pass);
			if(controll.sendUserAndPassToClient(snd)) {  
				if(controll.isManager(new ObjectSender(3,user))) {
					openScene("ManagerWindow.fxml", event);
				}else {

					openScene("MainWindow.fxml",event);
					//getMainWindow(event);
					

					/* get id */
					snd = new ObjectSender(5,user);
					int idUser = controll.getId(snd);
					
					/* get date start register */
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					snd = new ObjectSender(8,idUser);
					String dateReg = controll.getDateReg(snd);
					Date resigerDate = null;
					try {
						resigerDate = dateFormat.parse(dateReg);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Customer cst = new Customer(user, idUser,resigerDate);

					/* get orders */
					snd = new ObjectSender(6, user + " " + idUser);
					ArrayList<Order> orders = controll.getOrders(snd); 				
					TextEditor.getInstance().setCst(cst,orders);
					checkMeassage(event);
					showMsg(event,"Sign In Success :)","");
				} 			
			}else {
				showMsg(event,"Wrong Input","try again");
			}		
		}
		else
		{
			showMsg(event,"Wrong Input","try again");
		}

	}


	private void checkMeassage(ActionEvent event) {
		ArrayList<Order> orders = controllEdit.getOrders();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			for (Order order : orders) {
				Date endTimeOrder = order.getEnd(); 
				String txt = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
				Date currentDate;
				currentDate = dateFormat.parse(txt);

				if(currentDate.after(endTimeOrder))
					showMsg(event,"You are late! pay: "+getTicket(order.getStart(),endTimeOrder),order.toString());
			}

		} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}


	private double getTicket(Date arrive, Date end) {
			double price = controllEdit.getPrice();
			float daysBetween = 0;
			int hoursDiff = 0;
			long difference = end.getTime() - arrive.getTime();
			daysBetween = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
			hoursDiff =  (int) TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS);
			int daysDiff = Math.round(daysBetween);
			return ((daysDiff*24*price)+(hoursDiff*price))*1.2;
		
	}


	private boolean checkInputIsValid(String user, String pass) {
		// TODO check if it is write propetlly and it is on the server
		return true;
	}

	private void openScene(String sceneName, ActionEvent event)
	{
		Parent parent;
		try {
			parent = FXMLLoader.load((getClass().getResource(sceneName)));
			Scene child = new Scene(parent);

			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
			window.setScene(child);
			window.show();
		} catch (IOException e) {
			System.out.println("couldnt open the MainWindow windows");
		}

	}


	private void showMsg(ActionEvent event,String text,String smallText) {
		try {
			TextEditor.getInstance().setBigText(text);
			TextEditor.getInstance().setSmallText(smallText);
			FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("WrongInput.fxml")) ;
			Parent root1 = (Parent) fxmloader.load();
			Window existingWindow = ((Node) event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(existingWindow);
			stage.setTitle("Input");
			stage.setScene(new Scene(root1));

			stage.show();
		}catch (Exception e)
		{
			System.out.println("couldnt open the WrongInput wondows");
		}
	}

}
