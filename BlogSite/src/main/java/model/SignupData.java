package model;

import java.io.Serializable;

public class SignupData implements Serializable {
	private String nickname, email, password, retypePassword, errMsg;
	private Boolean errCheck;
	
	public String getNickname() { return nickname; }
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getEmail() { return email; }
	public void setEmail(String email) { 
		this.email = email;
	}
	
	public String getPassword() { return password; }
	public void setPassword(String password) { 
		this.password = password;
	}
	
	public String getRetypepassword() { return retypePassword; }
	public void setRetypepassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}
	
	public String getErrMsg() { return errMsg; }
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public Boolean getErrCheck() { return errCheck; }
	public void setErrCheck(Boolean errCheck) {
		this.errCheck = errCheck;
	}
}
