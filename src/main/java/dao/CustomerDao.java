package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CustomerBean;

public class CustomerDao {

	public ArrayList<CustomerBean> getCustomers() {
		ArrayList<CustomerBean> ds = new ArrayList<CustomerBean>();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "select * from KhachHang";
			statement = KetNoiDao.connection.prepareStatement(sql);
			rs = statement.executeQuery();
			while (rs.next()) {
				CustomerBean customerBean = new CustomerBean();
				customerBean.setCustomerId(rs.getLong("makh"));
				customerBean.setFullName(rs.getString("hoten"));
				customerBean.setAddress(rs.getString("diachi"));
				customerBean.setEmail(rs.getString("email"));
				customerBean.setUsername(rs.getString("tendn"));
				customerBean.setPassword(rs.getString("pass"));
				ds.add(customerBean);
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

	public CustomerBean checkLogin(String username, String pass) throws SQLException {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "select * from KhachHang where tendn = ? and  pass = ?";
			statement = KetNoiDao.connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, pass);
			rs = statement.executeQuery();
			while (rs.next()) {
				CustomerBean customerBean = new CustomerBean();
				customerBean.setCustomerId(rs.getLong("makh"));
				customerBean.setFullName(rs.getString("hoten"));
				customerBean.setAddress(rs.getString("diachi"));
				customerBean.setEmail(rs.getString("email"));
				customerBean.setUsername(rs.getString("tendn"));
				customerBean.setPassword(rs.getString("pass"));
				return customerBean;
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
		return null;
	}

	public int register(String username, String pass, String fullName) throws SQLException {
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			String sql1 = "select count(*) from KhachHang where tendn = ?";
			statement2 = KetNoiDao.connection.prepareStatement(sql1);
			statement2.setString(1, username);
			rs2 = statement2.executeQuery();
			int flag = 0;
			if (rs2.next())
				flag = rs2.getInt(1);
			if (flag == 0) {
				String sql = "insert into KhachHang(tendn, pass , hoten) values(?,?,?)";
				statement = KetNoiDao.connection.prepareStatement(sql);
				statement.setString(1, username);
				statement.setString(2, pass);
				statement.setString(3, fullName);
				return statement.executeUpdate();
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (statement2 != null) {
				statement2.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (rs2 != null) {
				rs2.close();
			}
		}
		return -1;
	}

	public int update(CustomerBean customerBean) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "UPDATE KhachHang SET hoten = ?  , diachi = ?, sodt = ?,email = ?, pass = ? where makh = ?";
			statement = KetNoiDao.connection.prepareStatement(sql);
			statement.setString(1, customerBean.getFullName());
			statement.setString(2, customerBean.getAddress());
			statement.setString(3, customerBean.getPhoneNumber());
			statement.setString(4, customerBean.getEmail());
			statement.setString(5, customerBean.getPassword());
			statement.setLong(6, customerBean.getCustomerId());
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
