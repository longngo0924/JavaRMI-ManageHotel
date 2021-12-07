package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.HoaDonFacade;
import entity.CT_HoaDonEntity;
import entity.HoaDonEntity;
import entity.KhachHangEntity;
import entity.NhanVienEntity;
import entity.PhongEntity;

public class HoaDonDao extends UnicastRemoteObject implements HoaDonFacade {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5086893463510050949L;
	@SuppressWarnings("unused")
	private EntityManagerFactory emf;
	private EntityManager em;
	private NhanVienDao nhanVienDao;
	private KhachHangDao khachHangDao;
	private PhongDao phongDao;
	private CTHDDao cthdDao;

	protected HoaDonDao() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDonDao(EntityManagerFactory emf) throws RemoteException {
		super();
		this.emf = emf;
		em = emf.createEntityManager();
		nhanVienDao = new NhanVienDao(emf);
		khachHangDao = new KhachHangDao(emf);
		phongDao = new PhongDao(emf);
		cthdDao = new CTHDDao(emf);

	}

	@Override
	public List<HoaDonEntity> layDsHoaDon() throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<HoaDonEntity> list = new ArrayList<HoaDonEntity>();
		try {
			tr.begin();
			Query query = em.createNativeQuery("select * from hoadon");

			List<Object[]> objs = query.getResultList();
			for (Object[] obj : objs) {
				HoaDonEntity entity = new HoaDonEntity();
				entity.setMaHoaDon(obj[0].toString());
				entity.setNgayDen((Date) obj[1]);
				entity.setNgayDi((Date) obj[2]);
				entity.setNgayLap((Date) obj[3]);
				entity.setSoLuong((Integer) obj[4]);
				entity.setThanhToan((Double) obj[5]);
				entity.setTrangThai((Boolean) obj[6]);

				KhachHangEntity kh = khachHangDao.findById(obj[7].toString());
				NhanVienEntity nv = nhanVienDao.findById(obj[8].toString());
				PhongEntity p = phongDao.findById(obj[9].toString());

				entity.setKhachHang(kh);
				entity.setNhanVien(nv);
				entity.setPhong(p);
				list.add(entity);
			}
			tr.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HoaDonEntity timHoaDon(String mahd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			HoaDonEntity hoadon = em.find(HoaDonEntity.class, mahd);
			tr.commit();
			return hoadon;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean themHoaDon(HoaDonEntity hoadon) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(hoadon);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean thanhToanHoaDon(String mahd) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("update hoadon set trangThai=1 where maHoaDon=?").setParameter(1, mahd);
			int rs = query.executeUpdate();
			tr.commit();
			if (rs > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean giaHanHoaDon(String mahd, Date ngaydi) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query query = em.createNativeQuery("update hoadon set ngayDi=? where maHoaDon=?").setParameter(1, ngaydi)
					.setParameter(2, mahd);

			int rs = query.executeUpdate();
			tr.commit();
			if (rs > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public double tinhTienPhong(String mahd) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			HoaDonEntity hd = em.find(HoaDonEntity.class, mahd);
			long time = hd.getNgayDi().getTime() - hd.getNgayDen().getTime();
			tr.commit();
			return Math.ceil(time / 86400000 * hd.getPhong().getGiaPhong());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<HoaDonEntity> timHoaDonTheoNgay(Date batdau, Date ketthuc) throws RemoteException {
		List<HoaDonEntity> list = new ArrayList<HoaDonEntity>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query q = em.createNativeQuery("select * from hoadon where ngayLap between ? and ?").setParameter(1, batdau)
					.setParameter(2, ketthuc);
			List<Object[]> objs = q.getResultList();
			for (Object[] obj : objs) {
				HoaDonEntity entity = new HoaDonEntity();
				entity.setMaHoaDon(obj[0].toString());
				entity.setNgayDen((Date) obj[1]);
				entity.setNgayDi((Date) obj[2]);
				entity.setNgayLap((Date) obj[3]);
				entity.setSoLuong((Integer) obj[4]);
				entity.setThanhToan((Double) obj[5]);
				entity.setTrangThai((Boolean) obj[6]);

				KhachHangEntity kh = khachHangDao.findById(obj[7].toString());
				NhanVienEntity nv = nhanVienDao.findById(obj[8].toString());
				PhongEntity p = phongDao.findById(obj[9].toString());

				entity.setKhachHang(kh);
				entity.setNhanVien(nv);
				entity.setPhong(p);
				list.add(entity);
			}
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public double tinhTongTien(String mahd) throws RemoteException {
		// TODO Auto-generated method stub
		double tienphong = tinhTienPhong(mahd);
		double tiendv = 0;
		List<CT_HoaDonEntity> cthds = cthdDao.findByHoaDon(mahd);
		for (CT_HoaDonEntity ct : cthds) {
			tiendv += ct.getSoluong() * ct.getDichvu().getGiaDichVu();
		}
		return tiendv + tienphong;
	}

	@Override
	public HoaDonEntity timHoaDonTheoPhong(String maPhong) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		List<HoaDonEntity> list = new ArrayList<HoaDonEntity>();

		try {
			tr.begin();
			Query q = em.createNativeQuery("select * from hoadon h where h.trangThai = 0 and h.maPhong=?")
					.setParameter(1, maPhong);
			List<Object[]> objs = q.getResultList();
			for (Object[] obj : objs) {
				HoaDonEntity entity = new HoaDonEntity();
				entity.setMaHoaDon(obj[0].toString());
				entity.setNgayDen((Date) obj[1]);
				entity.setNgayDi((Date) obj[2]);
				entity.setNgayLap((Date) obj[3]);
				entity.setSoLuong((Integer) obj[4]);
				entity.setThanhToan((Double) obj[5]);
				entity.setTrangThai((Boolean) obj[6]);

				KhachHangEntity kh = khachHangDao.findById(obj[7].toString());
				NhanVienEntity nv = nhanVienDao.findById(obj[8].toString());
				PhongEntity p = phongDao.findById(obj[9].toString());

				entity.setKhachHang(kh);
				entity.setNhanVien(nv);
				entity.setPhong(p);
				list.add(entity);
			}
			tr.commit();
		} catch (Exception e) {
			tr.rollback();
		}
		return list.get(0);
	}

}
