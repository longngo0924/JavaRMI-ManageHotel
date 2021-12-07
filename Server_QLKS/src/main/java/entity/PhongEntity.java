package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "phong")
public class PhongEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8705714429717466358L;
	@Id
	private String maPhong;
	private String loaiPhong;
	private double giaPhong;
	private String tinhTrang;
	private String tenPhong;

	@OneToMany(mappedBy = "phong")
	private List<HoaDonEntity> dshd;

	public PhongEntity() {
		super();
	}

	public PhongEntity(String maPhong, String loaiPhong, double giaPhong, String tinhTrang, String tenPhong) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.giaPhong = giaPhong;
		this.tinhTrang = tinhTrang;
		this.tenPhong = tenPhong;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public double getGiaPhong() {
		return giaPhong;
	}

	public void setGiaPhong(double giaPhong) {
		this.giaPhong = giaPhong;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public List<HoaDonEntity> getHoadons() {
		return dshd;
	}

	public void setHoadons(List<HoaDonEntity> hoadons) {
		this.dshd = hoadons;
	}

	@Override
	public String toString() {
		return "PhongEntity [maPhong=" + maPhong + ", loaiPhong=" + loaiPhong + ", giaPhong=" + giaPhong
				+ ", tinhTrang=" + tinhTrang + ", tenPhong=" + tenPhong + "]";
	}

}
