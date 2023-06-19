package servlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SignupData;
import model.SignupRegister;
import model.SignupValidation;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action == null) {
			//Redirect to singup.jsp
			response.sendRedirect("signup.jsp");
		} else if (action.equals("cancel")) {
			//Redirect to singup.jsp
			response.sendRedirect("signup.jsp");
		} else if (action.equals("register")) {
			HttpSession session = request.getSession();
			SignupData signupData = (SignupData) session.getAttribute("signupData");
			
			//Register signup user 
			SignupRegister sigunupRegister = new SignupRegister();
			try {
				sigunupRegister.register(signupData);
			} catch (SQLException e) {
				e.printStackTrace();
				signupData.setErrMsg("Sorry, there is a problem. Please try to sign up again.");
				signupData.setErrCheck(false);
			}
			
			if (signupData.getErrCheck()) {
				try {
					//Create img and articles subdirectory
					Path imgPath = Paths.get(getServletContext().getRealPath("/img/" + signupData.getNickname()));
					Path articlePath = Paths.get(getServletContext().getRealPath ("/articles/" + signupData.getNickname()));
					Files.createDirectory(imgPath);
					Files.createDirectory(articlePath);
					//Dispatch signupFinish.jsp
					session.invalidate();
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signupFinish.jsp");
					dispatcher.forward(request, response);
				} catch (Exception e) {
					System.out.println(e);
					session.setAttribute("errorMsg", "Sorry, there is a problem. Please try to sign up again.");
					response.sendRedirect("signup.jsp");
				}
			} else {
				//Redirect signup.jsp
				session.setAttribute("errorMsg", signupData.getErrMsg());
				response.sendRedirect("signup.jsp");
			}
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String retypePassword = request.getParameter("retype-password");

		//SetParameters
		SignupData signupData = new SignupData();
		signupData.setNickname(nickname);
		signupData.setEmail(email);
		signupData.setPassword(password);
		signupData.setRetypepassword(retypePassword);

		//Validate signup form
		SignupValidation signupValidation = new SignupValidation();
		try {
			signupValidation.validation(signupData);
		} catch (SQLException e) {
			e.printStackTrace();
			signupData.setErrMsg("Sorry, there is a problem. Please try to sign up again.");
			signupData.setErrCheck(false);
		}
		
		HttpSession session = request.getSession();
		if (signupData.getErrCheck()) {
			//Dispatch signupConfirm.jsp
			session.setAttribute("signupData", signupData);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signupConfirm.jsp");
			dispatcher.forward(request, response);
		} else {
			//Redirect signup.jsp
			session.setAttribute("errorMsg", signupData.getErrMsg());
			response.sendRedirect("signup.jsp");
		}
	}
}
