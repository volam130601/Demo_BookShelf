package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.CustomerBean;
import bean.OrderBean;
import bean.OrderInsertBean;
import bo.BookBo;
import bo.CartBo;
import bo.CustomerBo;
import bo.OrderBo;

/**
 * Servlet implementation class checkOutControlle
 */
@WebServlet(name = "CheckOutController", value = "/check-out")
public class CheckOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckOutController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (CustomerBo.isLogin(request)) {
				OrderBo orderBo = new OrderBo();
				BookBo bookBo = new BookBo();
				HttpSession session = request.getSession();
				// Insert Order
				OrderBean orderBean = new OrderBean();
				CustomerBean customer = (CustomerBean) session.getAttribute("customer");
				orderBean.setCustomerId(customer.getCustomerId());
				orderBean.setOrderDate(new Date());
				long orderId = orderBo.insertOrder(orderBean);
				CartBo cartBo = (CartBo) session.getAttribute("cart");
				OrderInsertBean oDetail = new OrderInsertBean();
				boolean flag = true;
				for (CartBean c : cartBo.ds) {
					oDetail.setBookId(c.getMasach());
					oDetail.setOrderId(orderId);
					oDetail.setQuantity((int) c.getSoluong());
					if (orderBo.insertOrderDetail(oDetail) > 0) {
						bookBo.updateQuantityBook(oDetail.getBookId(), c.getSoluong());
						session.removeAttribute("cart");
					} else {
						flag = false;
						break;
					}
				}
				if (flag)
					response.sendRedirect(request.getContextPath() + "/purchase?status=success");
				else
					response.sendRedirect(
							request.getContextPath() + "/purchase?status=fail&message=Insert Order Detail Failed!");
			} else {
				response.sendRedirect("login");
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
