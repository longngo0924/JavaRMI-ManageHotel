package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.TaiKhoanFacade;
import entity.TaiKhoanEntity;

public class TaiKhoanDao extends UnicastRemoteObject implements TaiKhoanFacade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 966067169907181423L;
	@SuppressWarnings("unused")
	private EntityManagerFactory emf;
	private EntityManager em;
	private NhanVienDao nhanVienDao;

	public TaiKhoanDao() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	public TaiKhoanDao(EntityManagerFactory emf) throws RemoteException {
		super();
		this.emf = emf;
		em = emf.createEntityManager();
		nhanVienDao = new NhanVienDao(emf);

	}

	@Override
	public TaiKhoanEntity dangNhap(String tenDangNhap, String matkhau) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Object[] obj = (Object[]) em
					.createNativeQuery(
							"select * from dbo.taikhoan where taikhoan.tenDangNhap =? and taikhoan.matKhau=?")
					.setParameter(1, tenDangNhap).setParameter(2, matkhau).getSingleResult();
			tr.commit();

			TaiKhoanEntity tk = new TaiKhoanEntity();
			tk.setMaTaiKhoan(obj[0].toString());
			tk.setMatKhau(obj[1].toString());
			tk.setTenDangNhap(obj[2].toString());
			tk.setNhanVien(nhanVienDao.findById(obj[3].toString()));

			return tk;
		} catch (Exception e) {
			tr.rollback();
		}
		return null;
	}

	@Override
	public boolean thayDoiMatKhau(String id, String oldPass, String newPassword) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			TaiKhoanEntity tk = em.find(TaiKhoanEntity.class, id);
			if (!tk.getMatKhau().equals(oldPass)) {
				return false;
			}
			Query query = em.createNativeQuery("UPDATE taikhoan\r\n" + "SET matKhau = ?\r\n" + "WHERE maTaiKhoan = ?")
					.setParameter(1, newPassword).setParameter(2, id);
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

}
