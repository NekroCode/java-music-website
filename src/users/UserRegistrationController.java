package users;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/UserRegistration")
public class UserRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserRegistrationController() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (validateInput(username) == false || validateInput(password) == false)
			request.setAttribute("registration_logger", "Username or password cannot be empty.");
		else {
			ArrayList<String> users = UserDao.getUsers();
			if (users.contains(username))
				request.setAttribute("registration_logger", "Username taken. Please try a different name.");
			else {
				String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
				UserBean user = new UserBean(username, hashedPassword, 2);
				UserDao userDao = new UserDao();
				userDao.insertUser(user);
				request.setAttribute("registration_logger", "Registration successful. Click <a href='login.jsp'>here</a> to login.");
			}
		}
		
		request.getRequestDispatcher("userRegistration.jsp").forward(request, response);
	}
	
	private boolean validateInput(String input) {
		if (input.isBlank())
			return false;
		else
			return true;
	}

}