package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "khachhang")
public class KhachHangEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6283889850793724248L;
	@Id
	private String maKhachHang;
	private String tenKhachHang;
	private String gioiTinh;
	private String cmnd;
	private String diaChi;
	private String email;
	private String sodienthoai;

	@OneToMany(mappedBy = "khachHang")
	private List<HoaDonEntity> dshd;

	public KhachHangEntity() {
		super();
	}

	public KhachHangEntity(String maKhachHang, String tenKhachHang, String gioiTinh, String cmnd, String diaChi,
			String email, String sodienthoai) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.gioiTinh = gioiTinh;
		this.cmnd = cmnd;
		this.diaChi = diaChi;
		this.email = email;
		this.sodienthoai = sodienthoai;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSodienthoai() {
		return sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	public List<HoaDonEntity> getDshd() {
		return dshd;
	}

	public void setDshd(List<HoaDonEntity> dshd) {
		this.dshd = dshd;
	}

	@Override
	public String toString() {
		return "KhachHangEntity [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", gioiTinh="
				+ gioiTinh + ", cmnd=" + cmnd + ", diaChi=" + diaChi + ", email=" + email + ", sodienthoai="
				+ sodienthoai + "]";
	}

}
