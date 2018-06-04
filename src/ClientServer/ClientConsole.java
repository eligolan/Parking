package ClientServer;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;

import application.MainController;
import client.*;
import common.*;

/**
 * This class constructs the UI for a chat client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole 
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge  
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
public class ClientConsole implements ParkingClientIF 
{
	//Class variables *************************************************

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	//Instance variables **********************************************

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	ConnectionClient client;
	ClientServerController controller;


	//Constructors ****************************************************

	/**
	 * Constructs an instance of the ClientConsole UI.
	 *
	 * @param host The host to connect to.
	 * @param port The port to connect on.
	 */
	public ClientConsole(String host, int port) 
	{  
		try 
		{
			client= new ConnectionClient(host, port, this);
			controller = new ClientServerController(this);
		} 
		catch(IOException exception) 
		{
			System.out.println("Error: Can't setup connection!"
					+ " Terminating client.");
			System.exit(1);
		}

		/*TODO: add fields as needed in class*/
		/*TODO: extend the constructor to receive the needed info (user name, user id)*/
		/*TODO:check out AbstractClient API(NOTE ChatClient extends AbstractClient), to find out how to get the address of the client*/
	}


	//Instance methods ************************************************

	/**
	 * This method waits for input from the console.  Once it is 
	 * received, it sends it to the client's message handler.
	 */
	public void accept(String[] args) 
	{
		
		
		
		
		try
		{
			controller.showMainMenu(args);
//			BufferedReader fromConsole = 
//					new BufferedReader(new InputStreamReader(System.in));
//			String message;
//			System.out.println("Connection success!");
//			while(true) {
//				System.out.println("Please enter your command number:");
//				System.out.println("(1) Sign in");
//				System.out.println("(2) Register");
//				message = fromConsole.readLine();
//				int getOrder = Integer.parseInt(message);
//				if(getOrder < 1 || getOrder > 2) {
//					System.out.println("bad command, try again..");
//					continue;
//				}
//				client.handleMessageFromClientUI(message);
//
//				/*..*/
//
//				String userNameAndPass = "";
//				System.out.println("Please Enter userName");
//				userNameAndPass += fromConsole.readLine() + " "; 
//				System.out.println("Please Enter Password");
//				userNameAndPass += fromConsole.readLine();
//				client.handleMessageFromClientUI(userNameAndPass);				
//				break;
//			}
		}
		catch (Exception ex) 
		{
			System.out.println
			("Unexpected error while reading from console!");
		}
	}
	
	public boolean sendMsgToServer(String userNameAndPass)
	{
		return client.handleMessageFromClientUI(userNameAndPass);
	}

	/**
	 * This method overrides the method in the ChatIF interface.  It
	 * displays a message onto the screen.
	 *
	 * @param message The string to be displayed.
	 */
	public void display(String message) 
	{
		System.out.println("> " + message);
	}


	//Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the Client UI.
	 *
	 * @param args[0] The host to connect to.
	 */
	public static void main(String[] args) 
	{
		String host = "";
		int port = 0;  //The port number


			host = "localhost";
		

		/*TODO: add code to get user info(user name, user id) and pass it, NOTE: no need to check if info is valid */

		ClientConsole chat= new ClientConsole(host, DEFAULT_PORT);
		
		
		chat.accept(args);  //Wait for console data
	}
}
//End of ConsoleChat class
