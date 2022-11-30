package bo;

import bean.AccountBean;
import dao.AccountDao;

public class AccountBo {
	private AccountDao accountDao = new AccountDao();

	public AccountBean checkAdminLogin(String username, String pass) {
		return accountDao.checkAdminLogin(username, pass);
	}
}
