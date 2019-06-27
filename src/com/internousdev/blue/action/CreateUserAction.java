package com.internousdev.blue.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateUserAction extends ActionSupport implements SessionAware{
	private int backFlag;
	private Map<String, Object> session;
	public String execute() { //仮ユーザーを消します
		if(!session.containsKey("tempUserId") && !session.containsKey("userId")) {
			return "sessionTimeout";
		}
		if (backFlag != 1) {
			session.remove("familyName");
			session.remove("firstName");
			session.remove("familyNameKana");
			session.remove("firstNameKana");
			session.remove("sex");
			session.remove("sexList");
			session.remove("email");
			session.remove("userIdForCreateUser");
			session.remove("password");
		}
		List<String> sexList = new ArrayList<String>(); //リストの生成
		if(!session.containsKey("sex")) { //性別が無かったら、男性とする
			session.put("sex", "男性");
		}else {
			session.put("sex", String.valueOf(session.get("sex")));
		}
		sexList.add("男性");//男性女性が表示される。
		sexList.add("女性");
		session.put("sexList", sexList);
		return SUCCESS;
	}
	public int getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(int backFlag) {
		this.backFlag = backFlag;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
