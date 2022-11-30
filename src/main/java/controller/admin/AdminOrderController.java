package controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.CustomerBo;
import bo.OrderBo;
import util.AccountUtil;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin/order")
public class AdminOrderController extends HttpServlet {
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
				OrderBo orderBo = new OrderBo();
				request.setAttribute("orders", orderBo.getOrders());
				request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
			} else
				response.sendRedirect(request.getContextPath() + "/login");

		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
