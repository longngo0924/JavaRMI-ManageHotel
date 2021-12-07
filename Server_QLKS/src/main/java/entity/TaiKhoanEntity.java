package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "taikhoan")
public class TaiKhoanEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9175967183228859624L;
	@Id
	private String maTaiKhoan;
	private String tenDangNhap;
	private String matKhau;

	@OneToOne
	@JoinColumn(name = "maNhanVien")
	private NhanVienEntity nhanVien;

	public TaiKhoanEntity() {
		super();
	}

	public TaiKhoanEntity(String maTaiKhoan, String tenDangNhap, String matKhau, NhanVienEntity nhanVien) {
		super();
		this.maTaiKhoan = maTaiKhoan;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
	}

	public String getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(String maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public NhanVienEntity getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVienEntity nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "TaiKhoanEntity [maTaiKhoan=" + maTaiKhoan + ", tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau
				+ ", nhanVien=" + nhanVien.getMaNhanVien() + "]";
	}

}
