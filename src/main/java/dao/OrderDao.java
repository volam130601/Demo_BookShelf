package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.OrderBean;
import bean.OrderDetailDto;
import bean.OrderInsertBean;

public class OrderDao {

	public ArrayList<OrderDetailDto> getOrderDetails(String orderId) {
		ArrayList<OrderDetailDto> ds = new ArrayList<OrderDetailDto>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "select * from VOrderDetail where MaHoaDon = ?";
			statement = KetNoiDao.connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(orderId));
			rs = statement.executeQuery();

			while (rs.next()) {
				OrderDetailDto o = new OrderDetailDto();
				o.setOrderId(rs.getLong("MaHoaDon"));
				o.setOrderDetailId(rs.getLong("MaChiTietHD"));
				o.setProductImage(rs.getString("anh"));
				o.setProductName(rs.getString("tensach"));
				o.setQuantity(rs.getInt("SoLuongMua"));
				o.setPrice(rs.getLong("gia"));
				o.setDaMua(rs.getBoolean("DaMua"));
				o.setSubTotal(rs.getLong("Thanh Tien"));
				ds.add(o);
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

	public ArrayList<OrderBean> getOrders() {
		ArrayList<OrderBean> ds = new ArrayList<OrderBean>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "select * from VOrder " + "ORDER BY NgayMua DESC";
			statement = KetNoiDao.connection.prepareStatement(sql);
			rs = statement.executeQuery();

			while (rs.next()) {
				OrderBean o = new OrderBean();
				o.setOrderId(rs.getLong("MaHoaDon"));
				o.setOrderDate(rs.getDate("NgayMua"));
				o.setSubTotal(rs.getLong("SubTotal"));
				o.setOrdered(rs.getBoolean("damua"));
				o.setCustomerName(rs.getString("hoten"));
				ds.add(o);
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

	public ArrayList<OrderBean> getOrders(Long customerId) {
		ArrayList<OrderBean> ds = new ArrayList<OrderBean>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "select * from VOrder where makh = ? \r\n" + "ORDER BY NgayMua DESC";
			statement = KetNoiDao.connection.prepareStatement(sql);
			statement.setLong(1, customerId);
			rs = statement.executeQuery();

			while (rs.next()) {
				OrderBean o = new OrderBean();
				o.setOrderId(rs.getLong("MaHoaDon"));
				o.setOrderDate(rs.getDate("NgayMua"));
				o.setSubTotal(rs.getLong("SubTotal"));
				o.setOrdered(rs.getBoolean("damua"));
				ds.add(o);
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

	public long insertOrder(OrderBean orderBean) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "insert into hoadon(makh,NgayMua,damua) values(?,?,?)";
			statement = KetNoiDao.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, orderBean.getCustomerId());
			statement.setDate(2, new Date(orderBean.getOrderDate().getTime()));
			statement.setBoolean(3, false);
			if (statement.executeUpdate() > 0) {
				rs = statement.getGeneratedKeys();
				long temp = 0;
				if (rs.next()) {
					temp = rs.getLong(1);
				}
				return temp;
			}

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

	public long insertOrderDetail(OrderInsertBean orderInsertBean) {
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			String sql1 = "select count(*) from sach where masach = ? and soluong >= ?";
			statement2 = KetNoiDao.connection.prepareStatement(sql1);
			statement2.setString(1, orderInsertBean.getBookId());
			statement2.setLong(2, orderInsertBean.getQuantity());
			rs2 = statement2.executeQuery();
			if (rs2.next()) {
				if (rs2.getInt(1) > 0) {
					String sql = "insert into ChiTietHoaDon(MaHoaDon,MaSach,SoLuongMua, DaMua) values(?,?,?,?)";
					statement = KetNoiDao.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					statement.setLong(1, orderInsertBean.getOrderId());
					statement.setString(2, orderInsertBean.getBookId());
					statement.setInt(3, orderInsertBean.getQuantity());
					statement.setBoolean(4, false);
					if (statement.executeUpdate() > 0) {
						rs = statement.getGeneratedKeys();
						if (rs.next()) {
							return rs.getLong(1);
						}
					}
				} else {
					return -1;
				}
			}
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

	public int orderPayment(String orderId) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		PreparedStatement statement1 = null;
		ResultSet rs1 = null;
		try {
			String sql1 = "select count(DaMua) from ChiTietHoaDon where MaHoaDon = ? and DaMua = 0";
			statement1 = KetNoiDao.connection.prepareStatement(sql1);
			statement1.setLong(1, Long.parseLong(orderId));
			rs1 = statement1.executeQuery();
			if (rs1.next()) {
				if (rs1.getInt(1) == 0) {
					String sql = "update hoadon set DaMua = 1\r\n" + "where MaHoaDon= ?";
					statement = KetNoiDao.connection.prepareStatement(sql);
					statement.setLong(1, Long.parseLong(orderId));
					return statement.executeUpdate();
				} else
					return -1;
			}
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

	public int orderDetailPayment(String orderDetailId) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {

			String sql = "update ChiTietHoaDon set DaMua = 1\r\n" + "where MaChiTietHD = ?";
			statement = KetNoiDao.connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(orderDetailId));
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
}
