package util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBConnection {
	
	private static Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
	private static DataSource dataSource;
	
	public static Connection getConnection() {
		if (dataSource == null) {
			try {
				Context initContext = new InitialContext();
				dataSource = (DataSource) initContext.lookup("java:comp/env/JavaWebDev");
			} catch(NamingException e) {
				LOGGER.log(Level.WARNING, "Error while retreiving application context " + e.getMessage());
			}
		}
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Error while connecting to database: " + e.getMessage());
		}
		
		return null;
	}
	
}
