package bo;



import java.util.List;

import bean.CategoryBean;
import dao.CategoryDAO;

public class CategoryBo {
    CategoryDAO categoryDAO = new CategoryDAO();

    public List<CategoryBean> getCategory() {
        return categoryDAO.getCategory();
    }
    
    public static void main(String[] args) {
		CategoryBo cbo = new CategoryBo();
		for(CategoryBean c : cbo.getCategory()) {
			System.out.println(c.getTenLoai());
		}
	}
}
