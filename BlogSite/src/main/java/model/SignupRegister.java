package model;

import java.io.IOException;
import java.sql.SQLException;

import dao.UsersDAO;

public class SignupRegister {
	public void register(SignupData signupData) throws SQLException, IOException {
		//Crypt salt + password
		CryptSha256 cryptSha256 = new CryptSha256();
		String cryptHash = cryptSha256.createhash(signupData.getPassword());
		signupData.setPassword(cryptHash);
		
		//Register signup user
		UsersDAO usersDAO = new UsersDAO();
		usersDAO.registerSignup(signupData);
	}
}
