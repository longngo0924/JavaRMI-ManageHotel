package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.KhachHangFacade;
import entity.KhachHangEntity;

public class KhachHangDao extends UnicastRemoteObject implements KhachHangFacade {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8587330081287978789L;
	@SuppressWarnings("unused")
	private EntityManagerFactory emf;
	private EntityManager em;

	public KhachHangDao() throws RemoteException {
	}

	public KhachHangDao(EntityManagerFactory emf) throws RemoteException {
		super();
		this.emf = emf;
		em = emf.createEntityManager();
	}

	@Override
	public boolean themKhachHang(KhachHangEntity khachHang) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(khachHang);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}

	@Override
	public KhachHangEntity findById(String maKH) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			KhachHangEntity kh = em.find(KhachHangEntity.class, maKH);
			tr.commit();
			return kh;
		} catch (Exception e) {
			tr.rollback();
		}
		return null;
	}

	@Override
	public boolean capNhatKhachHang(KhachHangEntity newValue) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(newValue);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<KhachHangEntity> getAll() throws RemoteException {
		// TODO Auto-generated method stub
		List<KhachHangEntity> listCustomer = new ArrayList<KhachHangEntity>();
		EntityTransaction entityTransaction = em.getTransaction();
		try {
			entityTransaction.begin();
			Query q = em.createNativeQuery("select * from khachhang");
			List<Object[]> objs = q.getResultList();
			for (Object[] o : objs) {
				KhachHangEntity tmp = new KhachHangEntity();
				tmp.setMaKhachHang(o[0].toString());
				tmp.setCmnd(o[1].toString());
				tmp.setDiaChi(o[2].toString());
				tmp.setEmail(o[3].toString());
				tmp.setGioiTinh(o[4].toString());
				tmp.setSodienthoai(o[5].toString());
				tmp.setTenKhachHang(o[6].toString());
				listCustomer.add(tmp);
			}
			entityTransaction.commit();
			return listCustomer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<KhachHangEntity> findByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		List<KhachHangEntity> listCustomer = new ArrayList<KhachHangEntity>();
		EntityTransaction entityTransaction = em.getTransaction();
		try {
			entityTransaction.begin();
			Query q = em.createNativeQuery(" select * from khachhang where tenKhachHang='" + name + "'");
			List<Object[]> objs = q.getResultList();
			for (Object[] o : objs) {
				KhachHangEntity tmp = new KhachHangEntity();
				tmp.setMaKhachHang(o[0].toString());
				tmp.setCmnd(o[1].toString());
				tmp.setDiaChi(o[2].toString());
				tmp.setEmail(o[3].toString());
				tmp.setGioiTinh(o[4].toString());
				tmp.setSodienthoai(o[5].toString());
				tmp.setTenKhachHang(o[6].toString());
				listCustomer.add(tmp);
			}
			entityTransaction.commit();
			return listCustomer;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public KhachHangEntity findByPhone(String phone) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction entityTransaction = em.getTransaction();
		List<KhachHangEntity> listCustomer = new ArrayList<KhachHangEntity>();
		try {
			entityTransaction.begin();
			Query q = em.createNativeQuery(" select * from khachhang where sodienthoai='" + phone + "'");
			List<Object[]> objs = q.getResultList();
			for (Object[] o : objs) {
				KhachHangEntity tmp = new KhachHangEntity();
				tmp.setMaKhachHang(o[0].toString());
				tmp.setCmnd(o[1].toString());
				tmp.setDiaChi(o[2].toString());
				tmp.setEmail(o[3].toString());
				tmp.setGioiTinh(o[4].toString());
				tmp.setSodienthoai(o[5].toString());
				tmp.setTenKhachHang(o[6].toString());
				listCustomer.add(tmp);
			}
			entityTransaction.commit();
			return listCustomer.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public KhachHangEntity findByCMND(String cmnd) throws RemoteException {
		// TODO Auto-generated method stub
		EntityTransaction entityTransaction = em.getTransaction();
		List<KhachHangEntity> listCustomer = new ArrayList<KhachHangEntity>();
		try {
			entityTransaction.begin();
			Query q = em.createNativeQuery(" select * from khachhang where cmnd='" + cmnd + "'");
			List<Object[]> objs = q.getResultList();
			for (Object[] o : objs) {
				KhachHangEntity tmp = new KhachHangEntity();
				tmp.setMaKhachHang(o[0].toString());
				tmp.setCmnd(o[1].toString());
				tmp.setDiaChi(o[2].toString());
				tmp.setEmail(o[3].toString());
				tmp.setGioiTinh(o[4].toString());
				tmp.setSodienthoai(o[5].toString());
				tmp.setTenKhachHang(o[6].toString());
				listCustomer.add(tmp);
			}
			entityTransaction.commit();
			return listCustomer.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
