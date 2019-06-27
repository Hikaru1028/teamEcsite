package com.internousdev.blue.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.blue.dao.UserInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateUserCompleteAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	public String execute() {
		if(!session.containsKey("tempUserId") && !session.containsKey("userId")) {
			return "sessionTimeout";}
		String result = ERROR;
		String sex = null;
		if (String.valueOf(session.get("sex")).toString().equals("女性")) {//////男性なら０ 女性なら１
			sex = "1";
		} else {
			sex = "0";
		}
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		int count = userInfoDAO.createUser(
				session.get("familyName").toString(),
				session.get("firstName").toString(),
				String.valueOf(session.get("familyNameKana")),
				String.valueOf(session.get("firstNameKana")),
				sex,
				String.valueOf(session.get("email")),
				session.get("userIdForCreateUser").toString(),
				session.get("password").toString());
		if(count > 0) {
			result = SUCCESS;
		}
		session.remove("familyName");
		session.remove("firstName");
		session.remove("familyNameKana");
		session.remove("firstNameKana");
		session.remove("sex");
		session.remove("sexList");
		session.remove("email");
		return result;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}