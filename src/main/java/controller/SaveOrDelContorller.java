package controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.CartBo;

@WebServlet(name = "SaveOrDelController", value = "/save-delete")
public class SaveOrDelContorller extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		CartBo gh = (CartBo) session.getAttribute("cart");
		if (request.getParameterValues("check") != null) {
			String[] checkList = request.getParameterValues("check");
			for (String c : checkList) {
				gh.Delete(c);
			}
		} else {
			Enumeration<String> d = request.getParameterNames();
			while (d.hasMoreElements()) {
				String name = d.nextElement();
				String masach = request.getParameter(name);
				if (name.equals("update-quantity")) {
					gh.update(masach, Long.parseLong(request.getParameter(masach)));
				}
				if (name.equals("delete"))
					gh.Delete(masach);
			}
		}
		session.setAttribute("cart", gh);
		response.sendRedirect(request.getContextPath() + "/cart");
//        response.sendRedirect("CartItemController");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
