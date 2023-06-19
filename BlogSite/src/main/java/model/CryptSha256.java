package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class CryptSha256 {	
	private static String cryptsha256(String input) {
		String hashedStr = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(input.getBytes());
			BigInteger bi = new BigInteger(1, hash);
			hashedStr = String.format("%0" + (hash.length << 1) + "x", bi);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashedStr;
	}

	public String createhash(String password) throws IOException {
		//SALT parameter
		Properties properties = new Properties();
		String propertyPath = "D:/portfolio_blog/BlogPortfolio/DAO.properties";
		InputStream inputStream = new FileInputStream(propertyPath);
		properties.load(inputStream);
		final String SALT = properties.getProperty("SALT");
		
		String tempHash = cryptsha256(password);
		String hash = cryptsha256(SALT + tempHash);
		return hash;
	}
}
