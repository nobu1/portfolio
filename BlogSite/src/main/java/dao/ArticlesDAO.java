package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.ArticleData;

public class ArticlesDAO {
	public void registerArticles(ArticleData articleData, String articlePath) throws SQLException  {
		ConnectDisconnectDAO_Articles condis = new ConnectDisconnectDAO_Articles();
		Connection con = null;
		String sql = "INSERT INTO blog_articles.articles (article_user, article_title, file_path) VALUES (?, ?, ?)";
		
		try {
			con = condis.getConnection();
			PreparedStatement psmt = con.prepareStatement(sql);
			psmt.setString(1, articleData.getNickName());
			psmt.setString(2, articleData.getBlogTitle());
			psmt.setString(3, articlePath);
			psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			con.rollback();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			condis.close(con);
		}
	}
}
