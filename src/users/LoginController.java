package users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserBean user = UserDao.getUser(username);
		if (user == null) {
			request.setAttribute("login_logger", "User does not exist.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else {
			if (BCrypt.checkpw(password, user.getPassword()))
				request.getRequestDispatcher("index.jsp").forward(request, response);
			else {
				request.setAttribute("login_logger", "Incorrect password.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
		
	}
	
}
