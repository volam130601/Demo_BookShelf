package bo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.CustomerBean;
import dao.CustomerDao;

public class CustomerBo {
	CustomerDao customerDao = new CustomerDao();

	public static boolean isLogin(HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null) {
			return true;
		}
		return false;
	}

	public CustomerBean checkLogin(String username, String password) throws Exception {
		password = encryptMD5(password);
		return customerDao.checkLogin(username, password);
	}

	public int register(String username, String pass, String fullName) throws Exception {
		pass = encryptMD5(pass);
		return customerDao.register(username, pass, fullName);
	}

	public int update(CustomerBean customerBean) {
		try {
			customerBean.setPassword(encryptMD5(customerBean.getPassword()));
			return customerDao.update(customerBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static boolean compareMD5(String password, String myHash) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(password.getBytes(), 0, password.length());
		String temp = new BigInteger(1, m.digest()).toString(16);
		return temp.equals(myHash);
	}

	public static String encryptMD5(String password) throws Exception {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(password.getBytes(), 0, password.length());
		return new BigInteger(1, m.digest()).toString(16);
	}
}
