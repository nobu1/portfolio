package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import dao.UsersDAO;

public class ForgotPasswordValidation {
	public void validation (LoginData loginData) throws SQLException, IOException {
		String email = loginData.getEmail();
		String password = loginData.getPassword();
		String reEnterPassword = loginData.getReEnterPassword();
		String emailRegex = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9]*[a-zA-Z0-9-][a-zA-Z0-9]\\.)+[a-zA-Z]{2,}$";
		String passwordRegex = "^(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,100}";
		loginData.setErrCheck(true);
		
		//Validation
		if  (email.isBlank() || password.isBlank()) {
			loginData.setErrMsg("Please fill out all items.");
			loginData.setErrCheck(false);
		} else if (!password.equals(reEnterPassword)) {
			loginData.setErrMsg("Your new password does not match.");
			loginData.setErrCheck(false);
		}else if (!Pattern.matches(emailRegex, email)) {
			loginData.setErrMsg("Your e-mail does not match e-mail format.");
			loginData.setErrCheck(false);
		} else if (!Pattern.matches(passwordRegex, password)) {
			loginData.setErrMsg("Your password does not match password format.");
			loginData.setErrCheck(false);
		} else if (!Pattern.matches(password, reEnterPassword)) {
			loginData.setErrMsg("Your password does not match password format.");
			loginData.setErrCheck(false);
		}
		
		if (loginData.getErrCheck()) {
			//Translate password to hash
			CryptSha256 cryptSha256 = new CryptSha256();
			loginData.setPassword(cryptSha256.createhash(password));
			
			//Check login user
			UsersDAO usersDAO = new UsersDAO();
			Boolean loginUser = usersDAO.checkUser(loginData);
			
			if (!loginUser) {
				loginData.setErrMsg("Your email is not registered. Please confirm your email again.");
				loginData.setErrCheck(false);
			} 
		}
	}
}
