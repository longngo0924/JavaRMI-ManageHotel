package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "cthd")
public class CT_HoaDonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4484620560595445055L;

	@Id
	private String maCTHD;

	@ManyToOne
	@JoinColumn(name = "maDichVu")
	private DichVuEntity dichvu;

	@OneToOne
	@JoinColumn(name = "maHoadon")
	private HoaDonEntity hoadon;

	private int soluong;

	public CT_HoaDonEntity() {
		super();
	}

	public String getMaCTHD() {
		return maCTHD;
	}

	public void setMaCTHD(String maCTHD) {
		this.maCTHD = maCTHD;
	}

	public DichVuEntity getDichvu() {
		return dichvu;
	}

	public void setDichvu(DichVuEntity dichvu) {
		this.dichvu = dichvu;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public HoaDonEntity getHoadon() {
		return hoadon;
	}

	public void setHoadon(HoaDonEntity hoadon) {
		this.hoadon = hoadon;
	}

	@Override
	public String toString() {
		return "CT_HoaDonEntity [maCTHD=" + maCTHD + ", soluong=" + soluong + "]";
	}

}
