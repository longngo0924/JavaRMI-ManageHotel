package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "nhanvien")
public class NhanVienEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7537219727201135302L;
	@Id
	private String maNhanVien;
	private String tenNhanVien;
	private String gioiTinh;
	private String cmnd;
	private String diaChi;
	private String email;
	private String sodienthoai;

	@OneToOne(mappedBy = "nhanVien")
	private TaiKhoanEntity taiKhoan;

	@OneToMany(mappedBy = "nhanVien")
	private List<HoaDonEntity> dshd;

	public NhanVienEntity() {
		super();
	}

	public NhanVienEntity(String maNhanVien, String tenNhanVien, String gioiTinh, String cmnd, String diaChi,
			String email, String sodienthoai) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.gioiTinh = gioiTinh;
		this.cmnd = cmnd;
		this.diaChi = diaChi;
		this.email = email;
		this.sodienthoai = sodienthoai;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
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
		return "NhanVienEntity [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", gioiTinh=" + gioiTinh
				+ ", cmnd=" + cmnd + ", diaChi=" + diaChi + ", email=" + email + ", sodienthoai=" + sodienthoai + "]";
	}

}
