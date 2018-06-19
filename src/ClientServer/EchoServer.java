package ClientServer;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.sql.Date;

import com.mysql.jdbc.StringUtils;


/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer 
{
	//Class variables *************************************************
	static mysqlConnection m;
	private boolean signIn = true;
	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;

	//Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public EchoServer(int port) 
	{
		super(port);
	}


	//Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg The message received from the client.
	 * @param client The connection from which the message originated.
	 */
	public void handleMessageFromClient
	(Object msg, ConnectionToClient client)
	{
		System.out.println("Message received: " + msg + " from " + client);
		/* check choice */
		ObjectSender sndRequest = (ObjectSender) msg;

		/*		int id;
		if(isNumber(msg.toString())) {
			id = Integer.parseInt(msg.toString());

			if(id == 1) {
				signIn = true;
				return;
			}
			else if(id == 2){
				signIn = false;
				return;
			}
		}
		 */

		int numRequest = sndRequest.getNumRequest();
		try {
			switch (numRequest) {
			case 1:  /* sign in */
				String input = sndRequest.getMsg().toString();
				String name = input.substring(0,input.indexOf(' '));
				String pass = input.substring(input.indexOf(' ') + 1);


				if(m.userExist("SELECT * FROM parking WHERE Name = '" + name + "' AND Password = '"+ pass +"';")) {	
					client.sendToClient("SignIn success!");
				}else {
					client.sendToClient("SingIn failed!");
				}
				break;
			case 2: /* register */
				input = sndRequest.getMsg().toString();
				name = input.substring(0,input.indexOf(' '));
				pass = input.substring(input.indexOf(' ') + 1);
				String mail = input.substring(input.indexOf(' ') + 1);
				String carNumber = input.substring(input.indexOf(' ') + 1);
				int manager = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
			//	Date dateStart = Date.parse(input.substring(input.indexOf(' ') + 1));

				if(m.userExist("SELECT * FROM parking WHERE Name = '" + name + "';")) {
					client.sendToClient("Register faild!");
					return;
				}	
				if(m.addUserToTable("parking",name,pass,mail,carNumber,manager)) {
					client.sendToClient("Register success!");
				}else {
					client.sendToClient("Register faild!");
				}
				break;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean isNumber(String str) {
		try  
		{  
			int d = Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}


	/**
	 * This method overrides the one in the superclass.  Called
	 * when the server starts listening for connections.
	 */
	protected void serverStarted()
	{
		System.out.println
		("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass.  Called
	 * when the server stops listening for connections.
	 */
	protected void serverStopped()
	{
		System.out.println
		("Server has stopped listening for connections.");
	}

	//Class methods ***************************************************

	/**
	 * This method is responsible for the creation of 
	 * the server instance (there is no UI in this phase).
	 *
	 * @param args[0] The port number to listen on.  Defaults to 5555 
	 *          if no argument is entered.
	 */
	public static void main(String[] args) 
	{
		int port = 0; //Port to listen on
		m = new mysqlConnection("jdbc:mysql://cs.telhai.ac.il/studentDB_cs204191357",
				"cs204191357",
				"123456");

		// m.printQuery("SELECT * FROM Authors;");

		try
		{
			port = Integer.parseInt(args[0]); //Get port from command line
		}
		catch(Throwable t)
		{
			port = DEFAULT_PORT; //Set port to 5555
		}

		EchoServer sv = new EchoServer(port);

		try 
		{
			sv.listen(); //Start listening for connections
		} 
		catch (Exception ex) 
		{
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
//End of EchoServer class
