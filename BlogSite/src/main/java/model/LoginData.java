package model;

import java.io.Serializable;

public class LoginData implements Serializable {
	private String nickName, email, password, reEnterPassowerd, notification, errMsg;
	private int falseloginCount;
	private Boolean errCheck;
	
	public String getNickName() { return nickName; }
	public void setNickName(String nickName) { 
		this.nickName = nickName;
	}
	
	public String getEmail() { return email; }
	public void setEmail(String email) { 
		this.email = email;
	}
	
	public String getPassword() { return password; }
	public void setPassword(String password) { 
		this.password = password;
	}
	
	public String getReEnterPassword() { return reEnterPassowerd; }
	public void setReEnterPassword(String reEnterPassowerd) { 
		this.reEnterPassowerd = reEnterPassowerd;
	}
	
	public String getErrMsg() { return errMsg; }
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public String getNotification() { return notification; }
	public void setNotification(String notification) {
		this.notification = notification;
	}
	
	public int getFalseloginCount() { return falseloginCount; }
	public void setFalseloginCount(int falseloginCount) {
		this.falseloginCount = falseloginCount;
	}
	
	public Boolean getErrCheck() { return errCheck; }
	public void setErrCheck(Boolean errCheck) {
		this.errCheck = errCheck;
	}	
}
