package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "hoadon")
public class HoaDonEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3322131890560286340L;
	@Id
	private String maHoaDon;
	private Date ngayLap;
	private Date ngayDen;
	private Date ngayDi;
	private boolean trangThai;

	@ManyToOne
	@JoinColumn(name = "maKhachHang")
	private KhachHangEntity khachHang;

	@ManyToOne
	@JoinColumn(name = "maNhanVien")
	private NhanVienEntity nhanVien;
	private int soLuong;
	private double thanhToan;

	@ManyToOne
	@JoinColumn(name = "maPhong")
	private PhongEntity phong;

	public HoaDonEntity() {
		super();
	}

	public HoaDonEntity(String maHoaDon, Date ngayLap, Date ngayDen, Date ngayDi, boolean trangThai, int soLuong,
			double thanhToan) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.ngayDen = ngayDen;
		this.ngayDi = ngayDi;
		this.trangThai = trangThai;
		this.soLuong = soLuong;
		this.thanhToan = thanhToan;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public Date getNgayDen() {
		return ngayDen;
	}

	public void setNgayDen(Date ngayDen) {
		this.ngayDen = ngayDen;
	}

	public Date getNgayDi() {
		return ngayDi;
	}

	public void setNgayDi(Date ngayDi) {
		this.ngayDi = ngayDi;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getThanhToan() {
		return thanhToan;
	}

	public void setThanhToan(double thanhToan) {
		this.thanhToan = thanhToan;
	}

	public PhongEntity getPhong() {
		return phong;
	}

	public void setPhong(PhongEntity phong) {
		this.phong = phong;
	}

	public KhachHangEntity getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHangEntity khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVienEntity getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVienEntity nhanVien) {
		this.nhanVien = nhanVien;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "HoaDonEntity [maHoaDon=" + maHoaDon + ", ngayLap=" + ngayLap + ", ngayDen=" + ngayDen + ", ngayDi="
				+ ngayDi + ", khachHang=" + khachHang.getMaKhachHang() + ", nhanVien=" + nhanVien.getMaNhanVien()
				+ ", soLuong=" + soLuong + ", thanhToan=" + thanhToan + ", maPhong=" + phong.getMaPhong()
				+ ", trangThai=" + trangThai + "]";
	}

}
