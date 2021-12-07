package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "dichvu")
public class DichVuEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4362139662675128043L;
	@Id
	private String maDichVu;
	private String tenDichVu;
	private double giaDichVu;

	@OneToMany(mappedBy = "dichvu")
	private List<CT_HoaDonEntity> cthds;

	public DichVuEntity() {
		super();
	}

	public DichVuEntity(String maDichVu, String tenDichVu, double giaDichVu) {
		super();
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.giaDichVu = giaDichVu;
	}

	public String getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public double getGiaDichVu() {
		return giaDichVu;
	}

	public void setGiaDichVu(double giaDichVu) {
		this.giaDichVu = giaDichVu;
	}

	public List<CT_HoaDonEntity> getCthds() {
		return cthds;
	}

	public void setCthds(List<CT_HoaDonEntity> cthds) {
		this.cthds = cthds;
	}

	@Override
	public String toString() {
		return "DichVuEntity [maDichVu=" + maDichVu + ", tenDichVu=" + tenDichVu + ", giaDichVu=" + giaDichVu + "]";
	}

}
