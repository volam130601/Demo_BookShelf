package bean;

public class CustomerBean {
	private Long customerId;
	private String fullName;
	private String address;
	private String phoneNumber;
	private String email;
	private String username;
	private String password;

	public CustomerBean(Long customerId, String fullName, String address, String phoneNumber, String email,
			String username, String password) {
		super();
		this.customerId = customerId;
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public CustomerBean() {
		super();
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerBean [customerId=" + customerId + ", fullName=" + fullName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", username=" + username + ", password="
				+ password + "]";
	}

}
