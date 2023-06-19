package model;

import java.sql.SQLException;
import java.util.regex.Pattern;

import dao.UsersDAO;

public class SignupValidation {
	public void validation(SignupData signupData) throws SQLException {
		String nickname = signupData.getNickname();
		String email = signupData.getEmail();
		String password = signupData.getPassword();
		String retypePassword = signupData.getRetypepassword();
		String nickNameRegex = "^[A-Za-z0-9]+$";
		String emailRegex = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9]*[a-zA-Z0-9-][a-zA-Z0-9]\\.)+[a-zA-Z]{2,}$";
		String passwordRegex = "^(?=.*[A-Z])(?=.*[.?/-])[a-zA-Z0-9.?/-]{8,100}";
		signupData.setErrCheck(true);
		
		//Validation
		if (nickname.isBlank() || email.isBlank() || password.isBlank() || retypePassword.isBlank()) {
			signupData.setErrMsg("Please fill out all items.");
			signupData.setErrCheck(false);
		} else if (nickname.length() > 100) {
			signupData.setErrMsg("Nickname length is less than 100 characters.");
			signupData.setErrCheck(false);
		} else if (!Pattern.matches(nickNameRegex, nickname)) {
			signupData.setErrMsg("You can only use alphabets and numbers in the Nick name label.");
			signupData.setErrCheck(false);
		} else if (!Pattern.matches(emailRegex, email)) {
			signupData.setErrMsg("Your e-mail does not match e-mail format.");
			signupData.setErrCheck(false);
		} else if (!Pattern.matches(passwordRegex, password)) {
			signupData.setErrMsg("Your password does not match password format.");
			signupData.setErrCheck(false);
		} else if (!password.equals(retypePassword)) {
			signupData.setErrMsg("Password does not match.");
			signupData.setErrCheck(false);
		}
		
		//Check duplicate user and email
		if (signupData.getErrCheck()) {
			UsersDAO usersDAO = new UsersDAO();
			Boolean nickNameFlag = usersDAO.checkNickname(signupData);
			Boolean emailFlag = usersDAO.checkEmail(signupData);

			if (nickNameFlag) {
				signupData.setErrMsg("The nickname has already used. Please input another nickname.");
				signupData.setErrCheck(false);
			} else if (emailFlag) {
				signupData.setErrMsg("The email has already registered. Please input another email.");
				signupData.setErrCheck(false);
			} 
		}
	}
}
