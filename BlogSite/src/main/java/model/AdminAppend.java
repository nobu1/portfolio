package model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class AdminAppend {
	public List<Part> images(List<Part> imgFileLists, HttpServletRequest request) throws IOException, ServletException {
		//Append Image	file*
		imgFileLists.add(request.getPart("imageMain"));
		//Append all Image files
		int i = 0;
		while (true) {
			if (request.getPart("image" + i) == null || request.getPart("image" + i).getSize() == 0) {
				break;
			} else {
				imgFileLists.add(request.getPart("image" + i));
				i++;
			}
		}
		return imgFileLists;
	}

	public List<String> chapters(List<String> chapterLists, HttpServletRequest request) {
		int i = 0;
		while (true) {
			if (request.getParameter("chapter" + i) == null || request.getParameter("chapter" + i) == "") {
				break;
			} else {
				chapterLists.add(request.getParameter("chapter" + i));
				i++;
			}
		}
		return chapterLists;
	}

	public List<String> sections(List<String> sectionLists, HttpServletRequest request) {
		int i = 0;
		while (true) {
			if (request.getParameter("section" + i) == null || request.getParameter("section" + i) == "") {
				break;
			} else {
				sectionLists.add(request.getParameter("section" + i));
				i++;
			}
		}
		return sectionLists;
	}
	
	public List<String> descriptions(List<String> descriptionLists, HttpServletRequest request) {
		int i = 0;
		while (true) {
			if (request.getParameter("description" + i) == null || request.getParameter("description" + i) == "") {
				break;
			} else {
				descriptionLists.add(request.getParameter("description" + i));
				i++;
			}
		}
		return descriptionLists;
	}

	public List<Boolean> checkResults(List<Boolean> validationCheckersLists, AdminData adminData) {
		validationCheckersLists.add(adminData.getErrCheckTitle());
		validationCheckersLists.add(adminData.getErrCheckImg());
		validationCheckersLists.add(adminData.getErrCheckSummary());
		validationCheckersLists.add(adminData.getErrCheckChapter());
		validationCheckersLists.add(adminData.getErrCheckSection());
		validationCheckersLists.add(adminData.getErrCheckDescription());
		return validationCheckersLists;
		
	}
}
