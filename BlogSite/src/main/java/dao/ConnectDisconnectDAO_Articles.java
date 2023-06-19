package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectDisconnectDAO_Articles {
	public Connection getConnection() throws IOException {
		//DB connnection properties
		Properties properties = new Properties();
		String propertyPath = "D:/portfolio_blog/BlogPortfolio/DAO.properties";
		InputStream inputStream = new FileInputStream(propertyPath);
		properties.load(inputStream);
		final String DATABASE_NAME = properties.getProperty("DATABASE_NAME_Articles");
		final String PROPATIES = "?characterEncoding=UTF-8&serverTimezone=UTC";
		final String URL = "jdbc:mySQL://localhost:3306/" + DATABASE_NAME + PROPATIES;
		final String USER = properties.getProperty("USER");
		final String PASS = properties.getProperty("PASS");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(URL, USER, PASS);
			return con;
		} catch (SQLException | ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

	public void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
