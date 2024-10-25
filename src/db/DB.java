package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/***
 * Class responsible to carry the connection to the database.
 */
public class DB {
	
	private static Connection connection = null;
	
	/***
	 * Load the db.properties file on the project folder.
	 * @return properties
	 */
	private static Properties loadProperties() {
		try(FileInputStream fileInputStream = new FileInputStream("db.properties")){
			Properties properties = new Properties();
			properties.load(fileInputStream);
			return properties;
		}catch(IOException e) {
			throw new DBException(e.getMessage());
		}
	}
	
	
	/***
	 * Method responsible to get the connection to the simpleBank database.
	 * @return connection
	 */
	public static Connection getConnection() {
		try {
			if(connection == null) {
				Properties properties = loadProperties();
				String url = properties.getProperty("dburl");
				connection = DriverManager.getConnection(url, properties);
				return connection;
			}
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		return connection;
	}
	/***
	 * Close the opened connection.
	 */
	public static void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			}catch(SQLException e) {
				throw new DBException(e.getMessage());
			}
		}
	}
}
