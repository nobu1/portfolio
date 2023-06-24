package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				articles.put(rs.getString("article_title"), rs.getString("file_path")) ;
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
	
	public Boolean checkTitle(String nickName, String title) throws SQLException {
		Boolean checkFlag = true;
		ConnectDisconnectDAO_Articles condis = new ConnectDisconnectDAO_Articles();
		Connection con = null;
		String sql = "SELECT article_title FROM blog_articles.articles WHERE article_user = '"
				+ nickName + "'" + " AND delete_flag = 'false'" + " AND article_title = '" + title + "'";
		
		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				checkFlag = false;
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
}
