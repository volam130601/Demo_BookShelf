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
import bo.OrderBo;
import util.AccountUtil;

/**
 * Servlet implementation class PurchaseController
 */
@WebServlet(value = "/payment")
public class OrderPaymentController extends HttpServlet {
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

				request.setAttribute("orders",
						orderBo.getOrders(((CustomerBean) session.getAttribute("customer")).getCustomerId()));
				if (request.getParameter("status") != null) {
					if (request.getParameter("status").equals("success")) {
						request.setAttribute("message", "Order Success");
						request.setAttribute("status", "success");
					}
					if (request.getParameter("status").equals("fail")) {
						request.setAttribute("message", request.getParameter("message"));
						request.setAttribute("status", "danger");
					}
				}
				request.getRequestDispatcher("order.jsp").forward(request, response);
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
