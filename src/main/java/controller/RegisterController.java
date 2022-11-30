package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bo.CustomerBo;
import dao.KetNoiDao;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet(name = "/RegisterController", value = "/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		KetNoiDao kn = new KetNoiDao();
		kn.getConnection();
		String fullName = request.getParameter("fullname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (fullName != null && username != null && password != null) {
			CustomerBo customerBo = new CustomerBo();
			try {
				String message = "";
				String status = "";
				if (customerBo.register(username, password, fullName) > 0) {
					status = "success";
					message = "Register is success! You can login";
				} else {
					status = "danger";
					message = "Register is failed!";
				}
				response.sendRedirect(request.getContextPath() + "/login?message=" + message + "&status=" + status);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
