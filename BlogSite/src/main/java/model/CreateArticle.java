package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import javax.servlet.http.Part;

import com.mysql.cj.util.StringUtils;

public class CreateArticle {
	private static boolean checkBeforeWritefile(File file) {
		if (file.exists()) {
			if (file.isFile() && file.canWrite()) {
				return true;
			}
		}
		return false;
	}

	private static void makeContents(Path articlePath, ArticleData articleData, File file) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			Iterator<Part> imgListIterator = articleData.getImageFiles().iterator();

			bw.write("<jsp:include page=\"/WEB-INF/jsp/articleHeader.jsp\"></jsp:include>\n");
			bw.write("	<main>\n"
					+ "		<div class=\"container Article\">\n"
					+ "			<article>\n"
					+ "				<div class=\"row\">\n"
					+ "					<div class=\"col-md-12\">\n"
					+ "						<header>\n"
					+ "							<h1 class=\"mt-2 border-bottom border-secondary\">"
					+ articleData.getBlogTitle() + "</h1>\n"
					+ "							<img src=\"<%=request.getContextPath()%>/img/"
					+ articleData.getNickName() + "/"
					+ articleData.getImageFiles().get(0).getSubmittedFileName() + "\" "
					+ "class=\"m-4 img-fluid rounded mx-auto d-block mainImg\" style=\"height: 350px;\" alt=\"mainImage\">\n"
					+ "						</header>\n"
					+ "					</div>\n"
					+ "				</div>\n");
			if (!StringUtils.isEmptyOrWhitespaceOnly(articleData.getBlogSummary())) {
				bw.write("					<div class=\"row\">\n"
						+ "						<div class=\"col-md-12\">\n"
						+ "							<section>\n"
						+ "								<h5 class=\"mt-2 summary\">" + articleData.getBlogSummary()
						+ "</h5>\n"
						+ "							</section>\n"
						+ "						</div>\n"
						+ "					</div>\n");
			}
			//Repeat part
			for (int i = 0; i < articleData.getSectionLists().size(); i++) {
				bw.write("				<div class=\"row\">\n"
						+ "					<div class=\"mt-2 col-md-12\">\n"
						+ "						<section class=\"mainContents\">\n");
				if (!StringUtils.isEmptyOrWhitespaceOnly(articleData.getChapterLists().get(i))) {
					bw.write("						<h2 class=\"chapter\">" + articleData.getChapterLists().get(i)
							+ "</h2>\n");
				}
				bw.write("							<h3 class=\"section\">" + articleData.getSectionLists().get(i)
						+ "</h3>\n");
				imgListIterator.next();
				if (imgListIterator.hasNext()) {
					bw.write(" 						<img src=\"<%=request.getContextPath()%>/img/"
							+ articleData.getNickName() + "/"
							+ articleData.getImageFiles().get(i + 1).getSubmittedFileName() + "\" "
							+ "class=\"m-2 img-fluid rounded mx-auto d-block imgList\" style=\"height: 350px;\" alt=\"chapterImage\""
							+ i + ">\n");
				}
				bw.write("							<h5 class=\"mt-2 description\">"
						+ articleData.getDescriptionLists().get(i) + "</h5>\n"
						+ "						</section>\n"
						+ "					</div>\n"
						+ "				</div>\n");
			}
			bw.write("			</article>\n"
					+ "		</div>\n"
					+ "	</main>\n");
			bw.write("<jsp:include page=\"/WEB-INF/jsp/articleFooter.jsp\"></jsp:include>");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void makeArticle(Path articlePath, ArticleData articleData) {
		try {
			Files.createFile(articlePath);
			File file = new File(articlePath.toString());

			if (checkBeforeWritefile(file)) {
				makeContents(articlePath, articleData, file);
			} else {
				System.out.println("It cannot write to the file.");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void editArticle(Path articlePath, ArticleData articleData) {
		File file = new File(articlePath.toString());

		if (checkBeforeWritefile(file)) {
			makeContents(articlePath, articleData, file);
		} else {
			System.out.println("It cannot write to the file.");
		}
	}

	public String makeFileName() {
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String today = sdf.format(cl.getTime());

		return today + ".jsp";
	}

}
