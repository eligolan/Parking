package ClientServer;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

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

		int numRequest = sndRequest.getNumRequest();
		try {
			switch (numRequest) {
			case 1:  /* sign in */
				String input = sndRequest.getMsg().toString();
				
				List<String> element = Arrays.asList(input.split(" "));
				String name = element.get(0);
				String pass = element.get(1);

				if(m.userExist("SELECT * FROM parking WHERE Name = '" + name + "' AND Password = '"+ pass +"';")) {	
					client.sendToClient("SignIn success!");
				}else {
					client.sendToClient("SingIn failed!");
				}
				break;
			case 2: /* register */
				input = sndRequest.getMsg().toString();
				element = Arrays.asList(input.split(" "));
						
				name = element.get(0);
				pass = element.get(1);
				String mail = element.get(2);
				String carNumber = element.get(3);
				int manager = Integer.parseInt(element.get(4));
				String startDate = element.get(5)+" " +element.get(6);

				if(m.userExist("SELECT * FROM parking WHERE Name = '" + name + "';")) {
					client.sendToClient("Register failed!");
					return;
				}	
				if(m.addUserToTable("parking",name,pass,mail,carNumber,manager,startDate)) {
					client.sendToClient("Register success!");
				}else {
					client.sendToClient("Register failed!");
				}
				break;
			case 3: /* is manager */
				name = sndRequest.getMsg().toString();
				if(m.isManager("SELECT * FROM parking WHERE Name = '" + name + "' AND Manager = 1;")){
					client.sendToClient("success!");
				}else {
					client.sendToClient("failed!");
				}
				break;
			case 4: /* order park */
				input = sndRequest.getMsg().toString();
				element = Arrays.asList(input.split(" "));
					
				int parking_id = Integer.parseInt(element.get(0));
				int customer_id = Integer.parseInt(element.get(1));
				String car_number = element.get(2);
				mail = element.get(3);
				String startOrder = element.get(4) + " " + element.get(5);
				String endOrder = element.get(6) + " " + element.get(7);
				int x = Integer.parseInt(element.get(8));
				int y = Integer.parseInt(element.get(9));
				int z = Integer.parseInt(element.get(10));
				
				if(m.addOrderToTable("parkingOrder",parking_id,customer_id,car_number,mail,startOrder,endOrder,x,y,z)){
					client.sendToClient("success!");
				}else {
					client.sendToClient("failed!");
				}
				break;
			case 5: /* get Id */
				input = sndRequest.getMsg().toString();
				client.sendToClient(m.getId(input));
				break;
			case 6: /* get user order */				
				input = sndRequest.getMsg().toString();
				element = Arrays.asList(input.split(" "));					
				name = element.get(0);
				customer_id = Integer.parseInt(element.get(1));							
				client.sendToClient(m.getOrders(name,customer_id));
				break;
			case 7: /* get All orders */				
				client.sendToClient(m.getAllOrders());
				break;
			case 8: /* get date Register by name */	
				input = sndRequest.getMsg().toString();
				element = Arrays.asList(input.split(" "));					
				customer_id = Integer.parseInt(element.get(0));				
				client.sendToClient(m.getDateRegister(customer_id));
				break;
			case 9: /* get order id */	
				input = sndRequest.getMsg().toString();
				element = Arrays.asList(input.split(" "));			
				int parkingId = Integer.parseInt(element.get(0));
				customer_id = Integer.parseInt(element.get(1));				
				String carID = element.get(2);
				client.sendToClient(m.getOrderId(parkingId, customer_id, carID));
				break;
			case 10: /* delete Order */	
				input = sndRequest.getMsg().toString();
				element = Arrays.asList(input.split(" "));					
				int OrderId = Integer.parseInt(element.get(0));				
				client.sendToClient(m.deleteOrder(OrderId));
				break;
			case 11: /* get all Parking_lot */					
				client.sendToClient(m.getAllParkingLot());
				break;
			case 12: /* add complain */
				input = sndRequest.getMsg().toString();				
				String com = input;
				client.sendToClient(m.addComplain(com));
				break;
			case 13: /* get all complains */					
				client.sendToClient(m.getAllColmpains());
				break;
			case 14: /* check if user connection */	
				input = sndRequest.getMsg().toString();
				element = Arrays.asList(input.split(" "));					
				int userId = Integer.parseInt(element.get(0));
				client.sendToClient(m.isConnection(userId));
				break;
			case 15: /* connectOnline */	
				input = sndRequest.getMsg().toString();
				element = Arrays.asList(input.split(" "));					
				userId = Integer.parseInt(element.get(0));
				client.sendToClient(m.connect(userId));
				break;
			case 16: /* disconnect */	
				input = sndRequest.getMsg().toString();
				element = Arrays.asList(input.split(" "));					
				userId = Integer.parseInt(element.get(0));
				client.sendToClient(m.disconnect(userId));
				break;
			case 17: /* if manager of parking */	
				input = sndRequest.getMsg().toString();
				element = Arrays.asList(input.split(" "));					
				userId = Integer.parseInt(element.get(0));
				parkingId = Integer.parseInt(element.get(1));
				client.sendToClient(m.isManagerOfParking(userId,parkingId));
				break;
			}			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
