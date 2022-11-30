package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CustomerBean;
import bo.CustomerBo;
import util.AccountUtil;

/**
 * Servlet implementation class MyAccountController
 */
@WebServlet(urlPatterns = { "/my-account", "/handle-account" })
public class MyAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (CustomerBo.isLogin(request)) {
				HttpSession session = request.getSession();
				if (session.getAttribute("username") != null) {
					AccountUtil.getUser(request);
				}
				request.setAttribute("customer", session.getAttribute("customer"));
				request.getRequestDispatcher("my-account.jsp").forward(request, response);
			} else
				response.sendRedirect(request.getContextPath() + "/login");
		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		customer.setFullName(request.getParameter("fullname"));
		customer.setAddress(request.getParameter("address"));
		customer.setPhoneNumber(request.getParameter("phoneNumber"));
		customer.setEmail(request.getParameter("email"));
		customer.setPassword(request.getParameter("password"));
		CustomerBo customerBo = new CustomerBo();
		if (customerBo.update(customer) > 0) {
			session.setAttribute("username", customer.getFullName());
			session.setAttribute("customer", customer);
			request.setAttribute("status", "success");
			request.setAttribute("message", "Update customer success!");
		} else {
			request.setAttribute("status", "danger");
			request.setAttribute("message", "Update customer failed!");
		}
		doGet(request, response);
	}

}
