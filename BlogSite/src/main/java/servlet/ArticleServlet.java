package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
	private static final int RECORD_PER_PAGE = 5;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String action = request.getParameter("action");
		String currentPage = request.getParameter("currentpage");
		int totalRecordCount = 0;
		int offset = 0;
		ArticlesDAO articlesDAO = new ArticlesDAO();
		
		//Get total records
		try {
			totalRecordCount = articlesDAO.getArticleCount();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//Set pagination range
		int paginationRange = 1;
		if (totalRecordCount % RECORD_PER_PAGE > 0) {
			paginationRange = totalRecordCount / RECORD_PER_PAGE + 1;
		} else {
			paginationRange = totalRecordCount / RECORD_PER_PAGE;
		}
		session.setAttribute("paginationRange", paginationRange);
		
		if (action == null) {			
			//Set current page
			session.setAttribute("currentPage", 1);
						
			//Set pagination offset
			offset = 0;
		} else if (action.equals("prevnext")) {
			//Set current page
			session.setAttribute("currentPage", currentPage);
			
			//Set pagination offset
			offset = (Integer.parseInt(currentPage) - 1) * RECORD_PER_PAGE;
		} 
		
		//Get articles per page
		try {
			session.setAttribute("articles", articlesDAO.getArticles(offset, RECORD_PER_PAGE));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Dispatch articleEditDelete.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("articleList.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
