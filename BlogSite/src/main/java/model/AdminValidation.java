package model;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.Part;

import com.mysql.cj.util.StringUtils;

import dao.ArticlesDAO;

public class AdminValidation {
	private static String sanitizing(String str) {
		str = str.replaceAll("&", "&amp");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("'", "&#39;");
		return str;
	}

	public String blogTitle(String title, AdminData adminData, String nickName, String editProcess) throws SQLException {
		//Check duplication of blogTitle
		ArticlesDAO articlesDAO = new ArticlesDAO();
		Boolean titleDuplicate = articlesDAO.checkTitle(nickName, title);
		
		if (StringUtils.isEmptyOrWhitespaceOnly(title)) {
			adminData.setErrMsg("Please input Blog Title*.");
			adminData.setErrCheckTitle(false);
		} else if (title.length() > 100) {
			adminData.setErrMsg("Blog Title* length is less than 100 characters.");
			adminData.setErrCheckTitle(false);
		} else if (titleDuplicate) {
			if (StringUtils.isEmptyOrWhitespaceOnly(editProcess)) {
				adminData.setErrMsg("There is a same Blog Title. Please input another title.");
				adminData.setErrCheckTitle(false);
			} else {
				adminData.setErrCheckTitle(true);
				title = sanitizing(title);
			}
		} else {
			adminData.setErrCheckTitle(true);
			title = sanitizing(title);
		}
		return title;
	}
	
	public void images(List<Part> imgFiles, AdminData adminData, List<String> chapterLists) {
		if (imgFiles.get(0) == null || imgFiles.get(0).getSize() == 0) {
			adminData.setErrMsg("Please upload jpg file at the Image file*.");
			adminData.setErrCheckImg(false);
			return;
		} else if (imgFiles.size() > 1 &&  StringUtils.isEmptyOrWhitespaceOnly(chapterLists.get(0))) {
			adminData.setErrMsg("Please input chapter if you upload a image file.");
			adminData.setErrCheckImg(false);
			return;
		}
		
		for (int i = 0; i < imgFiles.size(); i++) {
			//Check upload files extension
			String imageFileName = imgFiles.get(i).getSubmittedFileName();
			String imgFileExtension = imageFileName.substring(imageFileName.lastIndexOf("."));

			if (imgFileExtension.equals(".jpg")) {
				adminData.setErrCheckImg(true);
			} else {
				adminData.setErrMsg("Please upload jpg file.");
				adminData.setErrCheckImg(false);
				break;
			}
		}
	}
	
	public void imagesEdit(List<String> imgFiles, AdminData adminData, List<String> chapterLists) {
		if (imgFiles.size() > 1 &&  StringUtils.isEmptyOrWhitespaceOnly(chapterLists.get(0))) {
			adminData.setErrMsg("Please input chapter if you upload a image file.");
			adminData.setErrCheckImg(false);
			return;
		}
		
		for (int i = 0; i < imgFiles.size(); i++) {
			//Check upload files extension
			String[] imageFileArray = imgFiles.get(i).split("/");
			String imageFileName = imageFileArray[imageFileArray.length - 1];
			String imgFileExtension = imageFileName.substring(imageFileName.lastIndexOf("."));

			if (imgFileExtension.equals(".jpg")) {
				adminData.setErrCheckImg(true);
			} else {
				adminData.setErrMsg("Please upload jpg file.");
				adminData.setErrCheckImg(false);
				break;
			}
		}
	}
	
	public String blogSummary(String summary, List<String> descriptionLists, AdminData adminData) {
		if (StringUtils.isEmptyOrWhitespaceOnly(summary)
				&& !StringUtils.isEmptyOrWhitespaceOnly(descriptionLists.get(0))) {
			if (descriptionLists.get(0).length() > 140) {
				summary = descriptionLists.get(0).substring(0, 141);
			} else {
				summary = descriptionLists.get(0);
			}
		}
		summary = sanitizing(summary);
		return summary;
	}

	public List<String> blogChapters(List<String> chapterLists, List<String> sectionLists, AdminData adminData) {
		adminData.setErrCheckChapter(true);

		if (StringUtils.isEmptyOrWhitespaceOnly(chapterLists.get(0)) && sectionLists.size() >= 1) {
			adminData.setErrMsg("Please input Chapter if you input section.");
			adminData.setErrCheckChapter(false);
			return chapterLists;
		}
		
		for (int i = 0; i < chapterLists.size(); i++) {
			if (chapterLists.get(i).length() > 100) {
				adminData.setErrMsg("Chapter length is less than 100 characters.");
				adminData.setErrCheckChapter(false);
				break;
			} else {
				chapterLists.set(i, sanitizing(chapterLists.get(i)));
			}
		}
		return chapterLists;
	}

	public List<String> blogSections(List<String> sectionLists, AdminData adminData) {
		adminData.setErrCheckSection(true);
	
		for (int i = 0; i < sectionLists.size(); i++) {
			if (sectionLists.get(i).length() > 100) {
				adminData.setErrMsg("Section length is less than 100 characters.");
				adminData.setErrCheckSection(false);
				break;
			} else {
				sectionLists.set(i, sanitizing(sectionLists.get(i)));
			}
		}
		return sectionLists;
	}
	
	public List<String> blogDescription(List<String> descriptionLists, AdminData adminData) {
		adminData.setErrCheckDescription(true);
		
		for (int i = 0; i < descriptionLists.size(); i++) {
			if (descriptionLists.get(i).length() > 2000) {
				adminData.setErrMsg("Description length is less than 2000 characters.");
				adminData.setErrCheckDescription(false);
				break;
			} else {
				descriptionLists.set(i, sanitizing(descriptionLists.get(i)));
			}
		}
		return descriptionLists;
	}
	
}
