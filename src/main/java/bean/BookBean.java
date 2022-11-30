package bean;

import java.util.Date;

public class BookBean {
	private String maSach;
	private String tenSach;
	private Long soLuong;
	private String tacGia;
	private Long gia;
	private String anh;
	private String soTap;
	private String maLoai;
	private Date ngayNhap;

	public String getSoTap() {
		return soTap;
	}

	public void setSoTap(String soTap) {
		this.soTap = soTap;
	}

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public Long getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Long soLuong) {
		this.soLuong = soLuong;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public Long getGia() {
		return gia;
	}

	public void setGia(Long gia) {
		this.gia = gia;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public BookBean(String maSach, String tenSach, Long soLuong, String tacGia, Long gia, String anh, String maLoai,
			Date ngayNhap) {
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.soLuong = soLuong;
		this.tacGia = tacGia;
		this.gia = gia;
		this.anh = anh;
		this.maLoai = maLoai;
		this.ngayNhap = ngayNhap;
	}

	public BookBean() {
	}
}
