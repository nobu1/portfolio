package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ArticlesDAO;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String action = request.getParameter("action");
		
		if (action.equals("article")) {
			//Get article data
			ArticlesDAO articlesDAO = new ArticlesDAO();
			Map<String, String> articles = new HashMap<>();
			try {
				articles = articlesDAO.getAllArticleList(articles);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			//Dispatch articleEditDelete.jsp
			session.setAttribute("articles", articles);
			RequestDispatcher dispatcher = request.getRequestDispatcher("articleList.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
