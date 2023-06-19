package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDAO;
import model.ForgotPasswordValidation;
import model.LoginData;
import model.LoginValidation;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) {
			//Redirect to login.jsp
			response.sendRedirect("login.jsp");
		} else if (action.equals("forgot")) {
			//Dispatch loginForgotPassword.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginForgotPassword.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("lockout")) {
			//Redirect to login.jsp
			response.sendRedirect("login.jsp");
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Parameters
		Properties properties = new Properties();
		String propertyPath = "D:/portfolio_blog/BlogPortfolio/Login.properties";
		InputStream inputStream = new FileInputStream(propertyPath);
		properties.load(inputStream);
		final int LOCKOUT_TIME = Integer.parseInt(properties.getProperty("LOCKOUT_TIME"));
		final int SESSION_TIME = Integer.parseInt(properties.getProperty("SESSION_TIME"));
		
		String action = request.getParameter("action");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		LoginData loginData = new LoginData();
		
		HttpSession session = request.getSession();
		
		//SetParameters
		loginData.setEmail(email);
		loginData.setPassword(password);
		
		if (action == null) {			
			//Validate login form
			LoginValidation loginValidation = new LoginValidation();
			try {
				loginValidation.validation(loginData);
			} catch (SQLException e) {
				e.printStackTrace();
				loginData.setErrMsg("Sorry, there is a problem. Please try to log in again.");
				loginData.setErrCheck(false);
			}

			if (loginData.getFalseloginCount() == 1) {
				//Check login count
				Integer sessionCount = (Integer) session.getAttribute("SessionCount");
				if (sessionCount == null) {
					sessionCount = 0;
				}
				
				//Increment session count
				session.setAttribute("SessionCount", ++sessionCount);
				
				//Lockout login process
				if (sessionCount > 3) {
					//Login lockout(Max 10min)
					session.setMaxInactiveInterval(LOCKOUT_TIME);
					//Dispatch to loginLockout.jsp
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginLockout.jsp");
					dispatcher.forward(request, response);
				} else {
					//Redirect to login.jsp
					session.setAttribute("errorMsg", loginData.getErrMsg());
					response.sendRedirect("login.jsp");
				}
			} else if (loginData.getErrCheck()) {
				//Destroy current session 
				session.invalidate();
				//Create new session(Max 1 day)
				HttpSession newSession = request.getSession();
				newSession.setMaxInactiveInterval(SESSION_TIME);
				//Get nickname
				UsersDAO usersDAO = new UsersDAO();
				try {
					loginData.setNickName(usersDAO.getNickName(loginData));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				//Dispatch to admin.jsp
				newSession.setAttribute("loginData", loginData);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
				dispatcher.forward(request, response);
			} else {
				//Redirect to login.jsp
				session.setAttribute("errorMsg", loginData.getErrMsg());
				response.sendRedirect("login.jsp");
			}

		} else if (action.equals("change")) {
			String reEnterPassword = request.getParameter("ReEnterPassword");
			
			//SetParameters
			loginData.setReEnterPassword(reEnterPassword);

			//Change Password Validation
			ForgotPasswordValidation forgotPasswordValidation = new ForgotPasswordValidation();
			try {
				forgotPasswordValidation.validation(loginData);
			} catch (SQLException e) {
				e.printStackTrace();
				loginData.setErrCheck(false);
			}
			
			if (loginData.getErrCheck()) {
				//Set password reset message
				loginData.setNotification("Password is changed.");
				//Redirect to login.jsp
				session.setAttribute("notification", loginData.getNotification());
				response.sendRedirect("login.jsp");
			} else {
				//Dispatch to loginForgotPassword.jsp
				session.setAttribute("errorMsg", loginData.getErrMsg());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginForgotPassword.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
