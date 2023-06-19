package model;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.Part;

public class ArticleData implements Serializable {
	private String nickName, blogTitile, blogSummary;
	private List<Part> imgFileLists;
	private List<String> chapterLists, sectionLists, descriptionLists;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getBlogTitle() {
		return blogTitile;
	}
	public void setBlogTitle(String blogTitile) {
		this.blogTitile = blogTitile;
	}
	
	public List<Part> getImageFiles() {
		return imgFileLists;
	}
	public void setImageFiles(List<Part> imgFileLists) {
		this.imgFileLists = imgFileLists;
	}
	
	public String getBlogSummary() {
		return blogSummary;
	}
	public void setBlogSummary(String blogSummary) {
		this.blogSummary = blogSummary;
	}
	
	public List<String> getChapterLists() {
		return chapterLists;
	}
	public void setChapterLists(List<String> chapterLists) {
		this.chapterLists = chapterLists;
	}

	public List<String> getSectionLists() {
		return sectionLists;
	}
	public void setSectionLists(List<String> sectionLists) {
		this.sectionLists = sectionLists;
	}
	
	public List<String> getDescriptionLists() {
		return descriptionLists;
	}
	public void setDescriptionLists(List<String> descriptionLists) {
		this.descriptionLists = descriptionLists;
	}
}
