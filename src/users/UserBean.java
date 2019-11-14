package users;

public class UserBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private int userRole;
	
	public UserBean(String username, String password, int userRole) {
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
}
