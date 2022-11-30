package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CategoryBean;

public class CategoryDAO {
    public List<CategoryBean> getCategory() {
    	ArrayList<CategoryBean> ds = new ArrayList<CategoryBean>();
    	PreparedStatement statement = null;
    	ResultSet rs = null;
    	try {
    		String sql = "SELECT * FROM loai";
    		statement = KetNoiDao.connection.prepareStatement(sql);
    		rs = statement.executeQuery();
    		while (rs.next()) {
    			CategoryBean categoryBean = new CategoryBean();
    			categoryBean.setMaLoai(rs.getString("maloai"));
    			categoryBean.setTenLoai(rs.getString("tenloai"));
    			ds.add(categoryBean);
    		}
    		return ds;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if(statement != null) {
					statement.close();
				}
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
}
