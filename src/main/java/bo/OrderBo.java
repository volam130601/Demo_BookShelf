package bo;

import java.util.ArrayList;

import bean.OrderBean;
import bean.OrderDetailDto;
import bean.OrderInsertBean;
import dao.OrderDao;

public class OrderBo {

	public OrderDao orderDao = new OrderDao();

	public ArrayList<OrderBean> getOrders() {
		return orderDao.getOrders();
	}

	public ArrayList<OrderBean> getOrders(Long customerId) {
		return orderDao.getOrders(customerId);
	}

	public ArrayList<OrderDetailDto> getOrderDetails(String orderID) {
		return orderDao.getOrderDetails(orderID);
	}

	public long insertOrder(OrderBean orderBean) {
		return orderDao.insertOrder(orderBean);
	}

	public long insertOrderDetail(OrderInsertBean orderDetailBean) {
		return orderDao.insertOrderDetail(orderDetailBean);
	}

	public int orderDetailPayment(String orderDetailId) {
		return orderDao.orderDetailPayment(orderDetailId);
	}

	public void orderPayment(String orderId) {
		orderDao.orderPayment(orderId);
	}
}
