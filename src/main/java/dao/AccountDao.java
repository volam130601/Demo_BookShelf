package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.AccountBean;

public class AccountDao {
	public AccountBean checkAdminLogin(String username, String pass) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			String sql = "  select * from DangNhap where TenDangNhap = ? and MatKhau = ? and Quyen = 1";
			statement = KetNoiDao.connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, pass);
			rs = statement.executeQuery();
			while (rs.next()) {
				AccountBean a = new AccountBean();
				a.setUsername(rs.getString("TenDangNhap"));
				a.setPassword(rs.getString("MatKhau"));
				return a;
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
}
