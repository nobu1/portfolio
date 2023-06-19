package model;

import java.io.Serializable;

public class AdminData implements Serializable {
	private String errMsg;
	private Boolean errCheck, errCheckTitle, errCheckImg, errCheckSummary, errCheckChapter, errCheckSection,
			errCheckDescription;

	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Boolean getErrCheck() {
		return errCheck;
	}
	public void setErrCheck(Boolean errCheck) {
		this.errCheck = errCheck;
	}

	public Boolean getErrCheckTitle() {
		return errCheckTitle;
	}
	public void setErrCheckTitle(Boolean errCheckTitle) {
		this.errCheckTitle = errCheckTitle;
	}

	public Boolean getErrCheckImg() {
		return errCheckImg;
	}
	public void setErrCheckImg(Boolean errCheckImg) {
		this.errCheckImg = errCheckImg;
	}

	public Boolean getErrCheckSummary() {
		return errCheckSummary;
	}
	public void setErrCheckSummary(Boolean errCheckSummary) {
		this.errCheckSummary = errCheckSummary;
	}
	
	public Boolean getErrCheckChapter() {
		return errCheckChapter;
	}
	public void setErrCheckChapter(Boolean errCheckChapter) {
		this.errCheckChapter = errCheckChapter;
	}

	public Boolean getErrCheckSection() {
		return errCheckSection;
	}
	public void setErrCheckSection(Boolean errCheckSection) {
		this.errCheckSection = errCheckSection;
	}

	public Boolean getErrCheckDescription() {
		return errCheckDescription;
	}
	public void setErrCheckDescription(Boolean errCheckDescription) {
		this.errCheckDescription = errCheckDescription;
	}
}
