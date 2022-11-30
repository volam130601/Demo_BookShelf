package bo;

import java.util.ArrayList;

import bean.BookBean;
import bean.BookDto;
import dao.BookDAO;

public class BookBo {
	private BookDAO bookDAO = new BookDAO();

	public ArrayList<BookBean> searchBook(String search) {
		return bookDAO.searchBook(search);
	}

	public BookDto getBookPagination(int page) {
		return bookDAO.getBookPagination(page);
	}

	public int updateQuantityBook(String bookId, long quantity) {
		return bookDAO.updateQuantityBook(bookId, quantity);
	}

	public BookDto findByCategoryId(String categoryId, int page) {
		return bookDAO.findByCategoryId(categoryId, page);
	}
}
