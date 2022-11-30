package bean;

public class OrderInsertBean {
	private long orderDetailId;
	private String bookId;
	private long orderId;
	private int quantity;

	public long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public OrderInsertBean(long orderDetailId, String bookId, long orderId, int quantity) {
		super();
		this.orderDetailId = orderDetailId;
		this.bookId = bookId;
		this.orderId = orderId;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderInsertBean() {
		super();
	}
}
