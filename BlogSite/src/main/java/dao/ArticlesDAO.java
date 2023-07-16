package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.ArticleData;

public class ArticlesDAO {
	public void registerArticles(ArticleData articleData, String articlePath) throws SQLException {
		ConnectDisconnectDAO_Articles condis = new ConnectDisconnectDAO_Articles();
		Connection con = null;
		String sql = "INSERT INTO blog_articles.articles (article_user, article_title, file_path, delete_flag) VALUES (?, ?, ?, ?)";

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, articleData.getNickName());
			psmt.setString(2, articleData.getBlogTitle());
			psmt.setString(3, articlePath);
			psmt.setBoolean(4, false);
			psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			con.rollback();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
	}

	public Map<String, String> getArticleList(String nickName, Map<String, String> articles) throws SQLException {
		ConnectDisconnectDAO_Articles condis = new ConnectDisconnectDAO_Articles();
		Connection con = null;
		String sql = "SELECT article_title, file_path FROM blog_articles.articles WHERE article_user = '"
				+ nickName + "'" + " AND delete_flag = 'false'";

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				articles.put(rs.getString("article_title"), rs.getString("file_path"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			con.rollback();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
		return articles;
	}

	public Map<String, String> getArticles(int offset, int recordPerPage) throws SQLException {
		Map<String, String> articles = new HashMap<>();
		ConnectDisconnectDAO_Articles condis = new ConnectDisconnectDAO_Articles();
		Connection con = null;
		String sql = "SELECT article_title, file_path FROM blog_articles.articles WHERE delete_flag = 'false' ORDER BY article_title LIMIT "
				+ offset + ", " + recordPerPage;

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				articles.put(rs.getString("article_title"), rs.getString("file_path"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			con.rollback();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
		return articles;
	}
	
	public Map<String, String> getArticlesByNickName(int offset, int recordPerPage, String nickName) throws SQLException {
		Map<String, String> articles = new HashMap<>();
		ConnectDisconnectDAO_Articles condis = new ConnectDisconnectDAO_Articles();
		Connection con = null;
		String sql = "SELECT article_title, file_path FROM blog_articles.articles WHERE delete_flag = 'false' AND article_user = '" +
						 nickName + "' ORDER BY article_title LIMIT " + offset + ", " + recordPerPage;

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				articles.put(rs.getString("article_title"), rs.getString("file_path"));
			}
		} catch (SQLException e) {
			System.out.println(e);
			con.rollback();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
		return articles;
	}

	public int getArticleCount() throws SQLException {
		int count = 0;
		ConnectDisconnectDAO_Articles condis = new ConnectDisconnectDAO_Articles();
		Connection con = null;
		String sql = "SELECT COUNT(*) cnt FROM blog_articles.articles WHERE delete_flag = 'false'";
		
		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			rs.next();
			count = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println(e);
			con.rollback();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
		return count;
	}
	
	public int getArticleCountByNickName(String nickName) throws SQLException {
		int count = 0;
		ConnectDisconnectDAO_Articles condis = new ConnectDisconnectDAO_Articles();
		Connection con = null;
		String sql = "SELECT COUNT(*) cnt FROM blog_articles.articles WHERE delete_flag = 'false' AND article_user = '"	+ 
						 nickName + "'";
		
		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			rs.next();
			count = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println(e);
			con.rollback();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
		return count;
	}
	
	public Boolean checkTitle(String nickName, String title) throws SQLException {
		Boolean checkFlag = false;
		ConnectDisconnectDAO_Articles condis = new ConnectDisconnectDAO_Articles();
		Connection con = null;
		String sql = "SELECT article_title FROM blog_articles.articles WHERE article_user = '"
				+ nickName + "'" + " AND delete_flag = 'false'" + " AND article_title = '" + title + "'";

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				checkFlag = true;
			}
		} catch (SQLException e) {
			System.out.println(e);
			con.rollback();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
		return checkFlag;
	}

	public void deleteArticle(String nickName, String title) throws SQLException {
		ConnectDisconnectDAO_Articles condis = new ConnectDisconnectDAO_Articles();
		Connection con = null;
		String sql = "UPDATE blog_articles.articles SET delete_flag = '1' WHERE article_user = '"
				+ nickName + "' " + "AND article_title = '" + title + "'";

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			con.rollback();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
	}

	public void updateEditArticleTitle(String nickName, String title, String articleURL) throws SQLException {
		ConnectDisconnectDAO_Articles condis = new ConnectDisconnectDAO_Articles();
		Connection con = null;
		String sql = "UPDATE blog_articles.articles SET article_title ='" + title + "' WHERE article_user = '"
				+ nickName + "' " + "AND file_path = '" + articleURL + "'";

		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			con.rollback();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
	}
}
