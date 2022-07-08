package com.cos.photogramstart.util;

public class Script {
	//경고창 띄우고 뒤로 돌아가는 함수
	public static String back(String msg) { 
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}
}
