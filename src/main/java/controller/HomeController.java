package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BookBean;
import bean.BookDto;
import bo.BookBo;
import bo.CategoryBo;
import dao.KetNoiDao;
import util.AccountUtil;

@WebServlet(name = "HomeController", urlPatterns = { "", "/home" })
public class HomeController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		KetNoiDao kn = new KetNoiDao();
		kn.getConnection();
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null) {
			AccountUtil.getUser(request);
		}
		if (request.getParameter("message") != null) {
			if (request.getParameter("message").equals("logout")) {
				session.removeAttribute("username");
				AccountUtil.removeUser(request);
				session.removeAttribute("customer");
			}
		}
		CategoryBo categoryBo = new CategoryBo();
		request.setAttribute("categories", categoryBo.getCategory());
		BookBo bookBo = new BookBo();
		BookDto bookDto = new BookDto();
		List<BookBean> bookList = new ArrayList<>();
		// Paging book
		int page = Integer.parseInt((request.getParameter("page") != null) ? request.getParameter("page") : "1");
		bookDto = bookBo.getBookPagination(page);
		bookList = bookDto.getList();
		int endPage = bookDto.getTotalPage();
		request.setAttribute("p", page);
		if (page <= 2) {
			request.setAttribute("begin", 1);
			request.setAttribute("end", 5);
		} else if (page >= endPage - 2) {
			request.setAttribute("begin", page - 2);
			request.setAttribute("end", endPage);
		} else {
			request.setAttribute("begin", page - 2);
			request.setAttribute("end", page + 2);
		}

		String categoryId = request.getParameter("cId");
		String search = request.getParameter("search");
		if (categoryId != null) {
			bookDto = bookBo.findByCategoryId(categoryId, page);
			bookList = bookDto.getList();
			request.setAttribute("begin", 1);
			request.setAttribute("end", bookDto.getTotalPage());
			request.setAttribute("categoryId", categoryId);
		} else if (search != null) {
			bookList = bookBo.searchBook(search);
			request.setAttribute("search", 1);
		}
		request.setAttribute("p", page);
		request.setAttribute("books", bookList);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
