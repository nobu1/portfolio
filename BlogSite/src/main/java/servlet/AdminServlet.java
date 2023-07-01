package servlet;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ArticlesDAO;
import model.AdminAppend;
import model.AdminData;
import model.AdminValidation;
import model.ArticleData;
import model.CreateArticle;

@WebServlet("/AdminServlet")
@MultipartConfig(maxFileSize = 2097152, maxRequestSize = 2097152, fileSizeThreshold = 2097152)
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String action = request.getParameter("action");

		if (action == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
			dispatcher.forward(request, response);
		} else if	(action.equals("logout")) {
			session.invalidate();
			response.sendRedirect("login.jsp");
		} else if (action.equals("editdelete")) {
			//Get nick name
			String nickName =  request.getParameter("nickName");
			
			//Get article data
			ArticlesDAO articlesDAO = new ArticlesDAO();
			Map<String, String> articles = new HashMap<>();
			try {
				articles = articlesDAO.getArticleList(nickName, articles); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//Dispatch articleEditDelete.jsp
			session.setAttribute("nickName", nickName);
			session.setAttribute("articles", articles);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/articleEditDelete.jsp");
			dispatcher.forward(request, response);
		} else if (action.equals("edit")) {
			//Edit Selected Article
			
		} else if (action.equals("delete")) {
			//Get parameters
			String nickName =  request.getParameter("nickName");
			String articleTitle = request.getParameter("articleTitle");
			
			//Update delete flag for the selected article
			ArticlesDAO articlesDAO = new ArticlesDAO();
			try {
				articlesDAO.deleteArticle(nickName, articleTitle);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//Dispatch admin.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminData adminData = new AdminData();
		AdminAppend adminAppend = new AdminAppend();

		//Get nick name
		String nickName = request.getParameter("nickname");
		
		//Get blog title
		String blogTitile = request.getParameter("blogTitle");
		//Get all image files
		List<Part> imgFileLists = new ArrayList<Part>();
		try {
			imgFileLists = adminAppend.images(imgFileLists, request);
		} catch (Exception e) {
			e.printStackTrace();
			adminData.setErrMsg("Please each upload file with less than 2MB.");
			session.setAttribute("errorMsg", adminData.getErrMsg());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
			dispatcher.forward(request, response);
		}
		//Get Blog summary
		String blogSummary = request.getParameter("summary");
		//Get all chapters
		List<String> chapterLists = new ArrayList<String>();
		chapterLists = adminAppend.chapters(chapterLists, request);
		//Get all sections
		List<String> sectionLists = new ArrayList<String>();
		sectionLists = adminAppend.sections(sectionLists, request);
		//Get all descriptions
		List<String> descriptionLists = new ArrayList<String>();
		descriptionLists = adminAppend.descriptions(descriptionLists, request);

		//Validation
		AdminValidation adminValidation = new AdminValidation();
		//For blog title
		try {
			blogTitile = adminValidation.blogTitle(blogTitile, adminData, nickName);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//For all image files
		adminValidation.images(imgFileLists, adminData, chapterLists);
		//For blog summary
		blogSummary = adminValidation.blogSummary(blogSummary, descriptionLists, adminData);
		//For all chapters
		chapterLists = adminValidation.blogChapters(chapterLists, sectionLists, imgFileLists, descriptionLists,
				adminData);
		//For all sections
		sectionLists = adminValidation.blogSections(sectionLists, adminData);
		//For all descriptions
		descriptionLists = adminValidation.blogDescription(descriptionLists, adminData);

		//Validation Checker
		List<Boolean> validationCheckersLists = new ArrayList<Boolean>();
		validationCheckersLists = adminAppend.checkResults(validationCheckersLists, adminData);

		if (validationCheckersLists.contains(false)) {
			//Validation error
			session.setAttribute("message", adminData.getErrMsg());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
			dispatcher.forward(request, response);
		} else {
			//Set article items to ArticleData
			ArticleData articleData = new ArticleData();
			articleData.setNickName(nickName);
			articleData.setBlogTitle(blogTitile);
			articleData.setImageFiles(imgFileLists);
			articleData.setBlogSummary(blogSummary);
			articleData.setChapterLists(chapterLists);
			articleData.setSectionLists(sectionLists);
			articleData.setDescriptionLists(descriptionLists);

			//Upload all images to img subfolder of login user
			imgFileLists.forEach(imgFile -> {
				try {
					imgFile.write(
							getServletContext().getRealPath("/img/" + nickName + "/" + imgFile.getSubmittedFileName()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			//Make JSP content
			CreateArticle createArticle = new CreateArticle();
			String articleFileName = createArticle.makeFileName();
			Path articlePath = Paths
					.get(getServletContext().getRealPath("/articles/" + nickName + "/" + articleFileName));
			createArticle.makeArticle(articlePath, articleData);

			//Dispatch to admin.jsp
			session.setAttribute("message", "Your article is issued.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
			dispatcher.forward(request, response);

			//Save JSP contens to DB
			String strArticlePath = "/articles/" + nickName + "/" + articleFileName;
			ArticlesDAO articlesDAO = new ArticlesDAO();
			try {
				articlesDAO.registerArticles(articleData, strArticlePath);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
