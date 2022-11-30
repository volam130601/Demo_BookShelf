package bo;

import java.util.ArrayList;
import java.util.List;

import bean.CartBean;

public class CartBo {
    public List<CartBean> ds = new ArrayList<>();

    public void Them(String masach, String tensach, long soluong, long gia, String anh) {
        for (CartBean s : ds) {
            if (s.getMasach().equals(masach)) {
                s.setSoluong(s.getSoluong() + 1);
                return;
            }
        }
        ds.add(new CartBean(masach, tensach, soluong, gia, anh));
    }

    public long tongTien() {
        long sum = 0;
        for (CartBean s : ds) {
            sum += s.getThanhtien();
        }
        return sum;
    }

    public void Delete(String masach) {
        for (CartBean s : ds) {
            if (s.getMasach().equals(masach)) {
                ds.remove(s);
                return;
            }
        }
    }

    public void update(String masach, long quantity) {
        for (CartBean s : ds) {
            if (s.getMasach().equals(masach)) {
                s.setSoluong(quantity);
                return;
            }
        }
    }

    public void quantityIncrease(String masach) {
        for (CartBean s : ds) {
            if (s.getMasach().equals(masach)) {
                s.setSoluong(s.getSoluong() + 1);
                return;
            }
        }
    }

    public void quantityDecrease(String masach) {
        for (CartBean s : ds) {
            if (s.getMasach().equals(masach)) {
                s.setSoluong(s.getSoluong() - 1);
                if (s.getSoluong() == 0) ds.remove(s);
                return;
            }
        }
    }

    public void clearCart() {
        ds.clear();
    }

}
