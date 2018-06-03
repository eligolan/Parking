package ClientServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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


	public void createTable(String table){
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("create table "+table+"(num int, name VARCHAR(40), semestr VARCHAR(10));");
			stmt.executeUpdate("load data local infile \"courses.txt\" into table courses");

		} catch (SQLException e) {	e.printStackTrace();}

	}

	public boolean addUserToTable(String table,String name,String pass) {
		try {
			// add record to table.
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet uprs;

			uprs = stmt.executeQuery("SELECT * FROM " + table + ";");
			uprs.moveToInsertRow(); 
			uprs.updateString("Name",name);
			uprs.updateString("Password",pass);
			uprs.insertRow();
			return true;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
