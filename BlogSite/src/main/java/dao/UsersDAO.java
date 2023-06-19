package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LoginData;
import model.SignupData;

public class UsersDAO {
	public boolean checkNickname(SignupData signupData) throws SQLException {
		ConnectDisconnectDAO_Users condis = new ConnectDisconnectDAO_Users();
		Connection con = null;
		String sql = "SELECT * FROM blog_users.users WHERE nick_name = '" + signupData.getNickname() + "'";
		Boolean checkFlag = true;

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			if (!rs.next()) {
				checkFlag = false;
			}
		} catch (SQLException e) {
			System.out.println("Checking dupricated nickname process is wrong!");
			System.out.println(e);
			con.rollback();
			checkFlag = false;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
		return checkFlag;
	}

	public boolean checkEmail(SignupData signupData) throws SQLException {
		ConnectDisconnectDAO_Users condis = new ConnectDisconnectDAO_Users();
		Connection con = null;
		String sql = "SELECT * FROM blog_users.users WHERE email = '" + signupData.getEmail() + "'";
		Boolean checkFlag = true;

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			if (!rs.next()) {
				checkFlag = false;
			}
		} catch (SQLException e) {
			System.out.println("Checking dupricated email process is wrong!");
			System.out.println(e);
			con.rollback();
			checkFlag = false;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
		return checkFlag;
	}

	public void registerSignup(SignupData signupData) throws SQLException {
		ConnectDisconnectDAO_Users condis = new ConnectDisconnectDAO_Users();
		Connection con = null;
		String sql = "INSERT INTO blog_users.users (nick_name, email, password) VALUES (?, ?, ?)";

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, signupData.getNickname());
			psmt.setString(2, signupData.getEmail());
			psmt.setString(3, signupData.getPassword());
			psmt.executeUpdate();
		} catch (SQLException | IOException e) {
			System.out.println("Insert signup user process is wrong!");
			System.out.println(e);
			con.rollback();
		} finally {
			condis.close(con);
		}
	}

	public boolean checkLoginUser(LoginData loginData) throws SQLException {
		ConnectDisconnectDAO_Users condis = new ConnectDisconnectDAO_Users();
		Connection con = null;
		String sql = "SELECT * FROM blog_users.users WHERE email = '" + loginData.getEmail() + "' " + "AND password = '"
				+ loginData.getPassword() + "'";
		Boolean checkFlag = true;

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			if (!rs.next()) {
				checkFlag = false;
			}
		} catch (SQLException | IOException e) {
			System.out.println("Checking login user process is wrong!");
			System.out.println(e);
			con.rollback();
			checkFlag = false;
		} finally {
			condis.close(con);
		}
		return checkFlag;
	}
	
	public String getNickName(LoginData loginData) throws SQLException {
		ConnectDisconnectDAO_Users condis = new ConnectDisconnectDAO_Users();
		Connection con = null;
		String sql = "SELECT nick_name FROM blog_users.users WHERE email = '" + loginData.getEmail() + "'";
		String nickName = "";
		
		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				nickName = rs.getString("nick_name");
			}
		} catch (SQLException | IOException e) {
			System.out.println("Getting nickname process is wrong!");
			System.out.println(e);
			con.rollback();
		} finally {
			condis.close(con);
		}
		return nickName;
	}
	
	public boolean checkUser(LoginData loginData) throws SQLException{
		ConnectDisconnectDAO_Users condis = new ConnectDisconnectDAO_Users();
		Connection con = null;
		String sql = "SELECT * FROM blog_users.users WHERE email = '" + loginData.getEmail() + "'";
		Boolean checkFlag = true;

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			if (!rs.next()) {
				checkFlag = false;
			}
		} catch (SQLException | IOException e) {
			System.out.println("Checking user process is wrong!");
			System.out.println(e);
			con.rollback();
			checkFlag = false;
		} finally {
			condis.close(con);
		}
		return checkFlag;
	}
}
