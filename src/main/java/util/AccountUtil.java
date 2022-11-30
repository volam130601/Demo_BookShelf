package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccountUtil {
	public static void getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null) {
			request.setAttribute("username", session.getAttribute("username").toString());
		}
	}

	public static void removeUser(HttpServletRequest request) {
		request.removeAttribute("username");
	}

}
