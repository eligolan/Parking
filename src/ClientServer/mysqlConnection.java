package ClientServer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Actors.Customer;
import Logistics.Order;


public class mysqlConnection {
	public String url;// = "jdbc:mysql://cs.telhai.ac.il/studentDB_cs204191357";
	public String user;// = "cs204191357"; 
	public String pass;// = "123456";
	public Connection conn;
	public mysqlConnection(String url, String user, String pass) {
		super();
		this.url = url;
		this.user = user;
		this.pass = pass;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.conn = DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("SQL connection succeed");
	}

	public void printQuery(String query)
	{
		Statement stmt;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				// Print out the values
				System.out.println(rs.getString(1));
			} 
			//rs.close();
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
			//stmt.executeUpdate("UPDATE course SET semestr=\"W08\" WHERE num=61309");
		} catch (SQLException e) {e.printStackTrace();}
	}


	public boolean userExist(String query)
	{
		Statement stmt;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				System.out.println(rs.getString(1));
				return true;
			}
			//rs.close();
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
			//stmt.executeUpdate("UPDATE course SET semestr=\"W08\" WHERE num=61309");
		} catch (SQLException e) {e.printStackTrace();}
		return false;
	}
	
	
	
	public boolean isManager(String query)
	{
		Statement stmt;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				System.out.println(rs.getString(1));
				return true;
			}
			//rs.close();
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
			//stmt.executeUpdate("UPDATE course SET semestr=\"W08\" WHERE num=61309");
		} catch (SQLException e) {e.printStackTrace();}
		return false;
	}
	

	public void createTable(String table){
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("create table "+table+"(num int, name VARCHAR(40), semestr VARCHAR(10));");
			stmt.executeUpdate("load data local infile \"courses.txt\" into table courses");

		} catch (SQLException e) {	e.printStackTrace();}

	}

	public boolean addUserToTable(String table,String name,String pass,String mail,
			String carNumber,int manager, String startDate) {
		try {
			// add record to table.
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs;

			uprs = stmt.executeQuery("SELECT * FROM " + table + ";");
			uprs.moveToInsertRow(); 
			uprs.updateString("Name",name);
			uprs.updateString("Password",pass);
			uprs.updateInt("manager",manager);
			uprs.updateString("Car_number",carNumber);
			uprs.updateString("Mail",mail);
			uprs.updateString("Date_start",startDate);
		//	uprs.updateDate("Date_start",start);
			uprs.insertRow();
			return true;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean addOrderToTable(String table, int parking_id, int customer_id, String car_number, String mail, String startOrder, String endOrder) {
		try {
			// add record to table.
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs;

			uprs = stmt.executeQuery("SELECT * FROM " + table + ";");
			uprs.moveToInsertRow(); 
			uprs.updateInt("parking_id",parking_id);
			uprs.updateInt("customer_id",customer_id);
			uprs.updateString("car_number",car_number);
			uprs.updateString("email",mail);
			uprs.updateString("start_time",startOrder);
			uprs.updateString("end_time",endOrder);
			uprs.insertRow();
			return true;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int getId(String name) {
		// 
		
		Statement stmt;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM parking WHERE Name = '" + name + "';");
			if(rs.next()) {
				System.out.println(rs.getInt(1));
				return rs.getInt(1);
			}
			//rs.close();
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
		} catch (SQLException e) {e.printStackTrace();}
		return 0;
	}
	
	
	public String getDateRegister(int customer_id) {
		Statement stmt;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM parking WHERE id = '" + customer_id + "';");
			if(rs.next()) {
				System.out.println(rs.getInt(1));
				return rs.getString(7);
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
		} catch (SQLException e) {e.printStackTrace();}
		return " ";
	}
	
	

	public ArrayList<Order> getOrders(String name,int id) {
		ArrayList<Order> orders = new ArrayList<Order>();
		Order temp;
		Statement stmt;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM parkingOrder WHERE customer_id = '" + id + "';");
			while (rs.next()) {
				
				/* add date register */
				String dateReg = getDateRegister(id);
				java.util.Date registerDate = null;
				try {
					registerDate = dateFormat.parse(dateReg);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				/**/
				
				Customer cst = new Customer(name,id,registerDate);
				java.util.Date start = dateFormat.parse(rs.getString(7));
				java.util.Date end = dateFormat.parse(rs.getString(8));
	
				System.out.println(start);
				temp = new Order(cst,rs.getString(4), rs.getString(5), start,end);
				orders.add(temp);
			}			
			//rs.close();
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
		} catch (SQLException e) {e.printStackTrace();} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	public  ArrayList<Order> getAllOrders() {
		ArrayList<Order> orders = new ArrayList<Order>();
		Order temp;
		Statement stmt;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM parkingOrder;");
			while (rs.next()) {
				int customerId = rs.getInt(3);
				
				/* add date register */
				String dateReg = getDateRegister(customerId);
				java.util.Date registerDate = null;
				try {
					registerDate = dateFormat.parse(dateReg);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				/**/
							
				Customer cst = new Customer(getName(customerId),customerId,registerDate);
				java.util.Date start = dateFormat.parse(rs.getString(7));
				java.util.Date end = dateFormat.parse(rs.getString(8));
				temp = new Order(cst,rs.getString(4), rs.getString(5), start,end);
				orders.add(temp);
			}			
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
		} catch (SQLException e) {e.printStackTrace();} catch (ParseException e) {
			e.printStackTrace();
		}
		return orders;
	}

	private String getName(int customerId) {
		Statement stmt;
		try 
		{
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM parking WHERE id = '" + customerId + "';");
			if(rs.next()) {
				return rs.getString(2);
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) { /* ignored */}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) { /* ignored */}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return " ";
	}
}
