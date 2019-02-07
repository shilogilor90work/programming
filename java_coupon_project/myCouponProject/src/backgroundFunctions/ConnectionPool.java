package backgroundFunctions;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mysql.jdbc.Connection;
/**
 * The class ConnectionPool is part of myCouponProject
 * the class ConnectionPool holds all the connections
 * @author Oshra & Shilo
 */
public class ConnectionPool {
	boolean shutdown = false;
	private Object key= new Object();
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/shilo";
	private static String username = "root";
	private static String password = "Aa123456";
	private static ArrayList<Connection> connections = new ArrayList<>();
	private static ConnectionPool instance = null;
	private int amountOfPool = 5;
	/**
	 * ConnectionPool is a singelton that creates connection pools at the amount that the amountPools value 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private ConnectionPool() throws ClassNotFoundException, SQLException 
	{
		Class.forName(driver);
		for (int i = 0 ; i < amountOfPool ; i++)
		{
			Connection con = (Connection) DriverManager.getConnection(url, username, password);
			connections.add(con);
		}
	}
	/**
	 * method getInstance returns an instance
	 * @return returns an instance
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static synchronized  ConnectionPool getInstance() throws ClassNotFoundException, SQLException {
		if (instance==null)
			instance = new ConnectionPool();
		return instance;
	}
	/**
	 * method getConnection returns a connection
	 * @return returns a connection
	 * @throws InterruptedException
	 */
	public Connection getConnection() throws InterruptedException 
	{
		synchronized(key)
		{
		Iterator <Connection> iter= connections.iterator();
		while (connections.size()==0)
				key.wait();
		
		if (shutdown)
			return null;
		
		Connection temp = iter.next();
		connections.remove(temp);
		return temp;
		}
	}
	/**
	 * method returnConnection gets a connection back that is no longer being used
	 * @param Connection connection gets a connection back and adds it back to the connection pool
	 */
	public void returnConnection(Connection connection)
	{
		synchronized(key)
		{
		connections.add(connection);
		key.notify();
		}
	}
	/**
	 * method closeAllConnections closes all connections
	 */
	public void closeAllConnections() throws SQLException
	{
		shutdown=true;
		Iterator <Connection> iter= connections.iterator();
		while (iter.hasNext())
		{
			Connection tempConnection=iter.next();	
			tempConnection.close();
			
		}	
	}
}
