package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CustomerBean;
import bo.AccountBo;
import bo.CustomerBo;
import dao.KetNoiDao;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = request.getParameter("message");
		String status = request.getParameter("status");
		if (message != null && status != null) {
			request.setAttribute("message", message);
			request.setAttribute("status", status);
		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("username") != null && request.getParameter("password") != null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			CustomerBo cBo = new CustomerBo();
			AccountBo accountBo = new AccountBo();
			KetNoiDao kn = new KetNoiDao();
			kn.getConnection();
			try {
				HttpSession session = request.getSession();
				if (accountBo.checkAdminLogin(username, password) != null) {
					session.setAttribute("username", "ADMIN");
					session.setMaxInactiveInterval(24 * 60 * 60);
					response.sendRedirect(request.getContextPath() + "/admin/order");
				} else if (cBo.checkLogin(username, password) != null) {
					CustomerBean customer = cBo.checkLogin(username, password);
					session.setAttribute("username", customer.getFullName());
					session.setAttribute("customer", customer);
					session.setMaxInactiveInterval(24 * 60 * 60);
					response.sendRedirect(request.getContextPath() + "/home");
				} else {
					request.setAttribute("report", "failed");
					doGet(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
