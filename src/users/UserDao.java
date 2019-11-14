package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import util.DBConnection;

public class UserDao {
	
	private static Logger LOGGER = Logger.getLogger(UserDao.class.getName());
	
	public static ArrayList<String> getUsers() {
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			Connection connection = DBConnection.getConnection();
			
			String query = "SELECT * FROM users";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) 
				list.add(resultSet.getString("username"));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Error while retrieving " + " users: " + e.getMessage());
		}
		
		return list;
	}
	
	public void insertUser(UserBean user) {
		try {
			Connection connection = DBConnection.getConnection();
			String query = "INSERT INTO `users`(`role_id`, `username`, `password`) VALUES (?, ?, ?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, 2);
			preparedStatement.setString(2, user.getUsername());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.executeUpdate();	
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Error while inserting " + " user: " + e.getMessage());
		}
	}
	
	public static UserBean getUser(String username) {
		try {
			Connection connection = DBConnection.getConnection();
			String query = "SELECT * FROM users WHERE username=(?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				String name = resultSet.getString("username");
				String password = resultSet.getString("password");
				int roleID = resultSet.getInt("role_id");
				return new UserBean(name, password, roleID);
			}
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Error while inserting " + " user: " + e.getMessage());
		}
		return null;
	}
	
}
