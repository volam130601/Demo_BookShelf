package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BookBean;
import bean.BookDto;

public class BookDAO {
	public ArrayList<BookBean> getBook() {
		ArrayList<BookBean> ds = new ArrayList<BookBean>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "select * from sach";
			statement = KetNoiDao.connection.prepareStatement(sql);
			rs = statement.executeQuery();
			while (rs.next()) {
				BookBean bookBean = new BookBean();
				bookBean.setMaSach(rs.getString("masach"));
				bookBean.setTenSach(rs.getString("tensach"));
				bookBean.setSoLuong(rs.getLong("soluong"));
				bookBean.setGia(rs.getLong("gia"));
				bookBean.setMaLoai(rs.getString("maloai"));
				bookBean.setSoTap(rs.getString("sotap"));
				bookBean.setAnh(rs.getString("anh"));
				bookBean.setNgayNhap(rs.getDate("ngaynhap"));
				bookBean.setTacGia(rs.getString("tacgia"));
				ds.add(bookBean);
			}
			return ds;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public BookDto getBookPagination(int page) {
		ArrayList<BookBean> list = new ArrayList<BookBean>();
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int size = 6;
		BookDto bookDto = new BookDto();
		try {
			String sql = "SELECT * FROM sach \r\n" + "ORDER BY tensach \r\n" + "OFFSET ? ROWS\r\n"
					+ "FETCH NEXT ? ROWS ONLY";
			statement = KetNoiDao.connection.prepareStatement(sql);
			statement.setInt(1, (page - 1) * size);
			statement.setInt(2, size);
			rs = statement.executeQuery();
			while (rs.next()) {
				BookBean bookBean = new BookBean();
				bookBean.setMaSach(rs.getString("masach"));
				bookBean.setTenSach(rs.getString("tensach"));
				bookBean.setSoLuong(rs.getLong("soluong"));
				bookBean.setGia(rs.getLong("gia"));
				bookBean.setMaLoai(rs.getString("maloai"));
				bookBean.setSoTap(rs.getString("sotap"));
				bookBean.setAnh(rs.getString("anh"));
				bookBean.setNgayNhap(rs.getDate("ngaynhap"));
				bookBean.setTacGia(rs.getString("tacgia"));
				list.add(bookBean);
			}
			bookDto.setList(list);

			String sql2 = "SELECT count(*) FROM sach";
			statement2 = KetNoiDao.connection.prepareStatement(sql2);
			rs2 = statement2.executeQuery();
			int count = 0;
			while (rs2.next())
				count = rs2.getInt(1);
			bookDto.setTotalPage((count % size == 0) ? count / size : (count / size) + 1);
			return bookDto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int updateQuantityBook(String bookId, long quantity) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "update sach set soluong = soluong - ? where masach = ?";
			statement = KetNoiDao.connection.prepareStatement(sql);
			statement.setLong(1, quantity);
			statement.setString(2, bookId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	public BookDto findByCategoryId(String categoryId, int page) {
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		BookDto bookDto = new BookDto();
		int size = 6;
		List<BookBean> list = new ArrayList<>();
		try {
			String sql = "Select * from sach where maloai = ? \r\n" + "order by tensach \r\n" + "OFFSET ? ROWs\r\n"
					+ "FETCH NEXT ? ROWS ONLY";
			statement = KetNoiDao.connection.prepareStatement(sql);
			statement.setString(1, categoryId);
			statement.setInt(2, (page - 1) * size);
			statement.setInt(3, size);
			rs = statement.executeQuery();
			while (rs.next()) {
				BookBean bookBean = new BookBean();
				bookBean.setMaSach(rs.getString("masach"));
				bookBean.setTenSach(rs.getString("tensach"));
				bookBean.setSoLuong(rs.getLong("soluong"));
				bookBean.setGia(rs.getLong("gia"));
				bookBean.setMaLoai(rs.getString("maloai"));
				bookBean.setSoTap(rs.getString("sotap"));
				bookBean.setAnh(rs.getString("anh"));
				bookBean.setNgayNhap(rs.getDate("ngaynhap"));
				bookBean.setTacGia(rs.getString("tacgia"));
				list.add(bookBean);
			}
			bookDto.setList(list);
			String sql2 = "SELECT count(*) FROM sach WHERE maloai= ?";
			statement2 = KetNoiDao.connection.prepareStatement(sql2);
			statement2.setString(1, categoryId);
			rs2 = statement2.executeQuery();
			int count = 0;
			while (rs2.next())
				count = rs2.getInt(1);
			bookDto.setTotalPage((count % size == 0) ? count / size : (count / size) + 1);
			return bookDto;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<BookBean> searchBook(String search) {
		ArrayList<BookBean> ds = new ArrayList<BookBean>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "select * from sach where tensach like ? OR masach like ? OR maloai like ?";
			statement = KetNoiDao.connection.prepareStatement(sql);
			statement.setString(1, "%" + search + "%");
			statement.setString(2, "%" + search + "%");
			statement.setString(3, "%" + search + "%");
			rs = statement.executeQuery();
			while (rs.next()) {
				BookBean bookBean = new BookBean();
				bookBean.setMaSach(rs.getString("masach"));
				bookBean.setTenSach(rs.getString("tensach"));
				bookBean.setSoLuong(rs.getLong("soluong"));
				bookBean.setGia(rs.getLong("gia"));
				bookBean.setMaLoai(rs.getString("maloai"));
				bookBean.setSoTap(rs.getString("sotap"));
				bookBean.setAnh(rs.getString("anh"));
				bookBean.setNgayNhap(rs.getDate("ngaynhap"));
				bookBean.setTacGia(rs.getString("tacgia"));
				ds.add(bookBean);
			}
			return ds;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
