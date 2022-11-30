package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.CartBo;
import util.AccountUtil;

@WebServlet(name = "CartItemController", urlPatterns = { "/handle-cart", "/cart" })
public class CartItemController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		CartBo gh = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null) {
			AccountUtil.getUser(request);
		}
		if (session.getAttribute("cart") == null) {// value
			gh = new CartBo();
			session.setAttribute("cart", gh);
		}
		if (request.getParameter("ts") != null) {
			String masach = request.getParameter("ms");
			String tensach = request.getParameter("ts");
			String anh = request.getParameter("anh");
			long gia = Long.parseLong(request.getParameter("gia"));
			long soluong = 1;
			gh = (CartBo) session.getAttribute("cart");
			gh.Them(masach, tensach, soluong, gia, anh);
			session.setAttribute("cart", gh);
		}
		if (url.contains("/cart")) {
			if (session.getAttribute("username") != null)
				request.setAttribute("username", session.getAttribute("username").toString());
			if (request.getParameter("message") != null) {
				if (request.getParameter("message").equals("logout")) {
					session.removeAttribute("username");
				}
			}
			CartBo cartBo = (CartBo) session.getAttribute("cart");
			request.setAttribute("cartItems", cartBo.ds);
			request.setAttribute("count", cartBo.ds.size());
			long totalPrice = cartBo.ds.stream().mapToLong(value -> value.getThanhtien()).sum();
			request.setAttribute("totalPrice", totalPrice);
			long discount = 0;
			request.setAttribute("discount", discount);
			request.setAttribute("subTotal", totalPrice - discount);
			request.getRequestDispatcher("show-cart.jsp").forward(request, response);
		}
		if (url.contains("/handle-cart")) {
			response.sendRedirect(request.getContextPath() + "/cart");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
