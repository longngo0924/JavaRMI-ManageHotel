package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.PhongFacade;
import entity.PhongEntity;

public class PhongDao extends UnicastRemoteObject implements PhongFacade {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2676614445943865934L;
	@SuppressWarnings("unused")
	private EntityManagerFactory emf;
	private EntityManager em;

	public PhongDao() throws RemoteException {

	}

	public PhongDao(EntityManagerFactory emf) throws RemoteException {
		super();
		this.emf = emf;
		em = emf.createEntityManager();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean themPhong(PhongEntity phong) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(phong);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}

	@Override
	public PhongEntity findById(String maPhong) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			PhongEntity p = em.find(PhongEntity.class, maPhong);
			tr.commit();
			return p;
		} catch (Exception e) {
			tr.rollback();
		}
		return null;
	}

	@Override
	public boolean capNhatPhong(PhongEntity newValue) {
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
	public List<PhongEntity> getAll() throws RemoteException {
		List<PhongEntity> list = new ArrayList<PhongEntity>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query q = em.createNativeQuery("select * from phong");
			List<Object[]> objs = q.getResultList();
			for (Object[] o : objs) {
				PhongEntity entity = new PhongEntity();
				entity.setMaPhong(o[0].toString());
				entity.setGiaPhong((double) o[1]);
				entity.setLoaiPhong(o[2].toString());
				entity.setTenPhong(o[3].toString());
				entity.setTinhTrang(o[4].toString());
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
	public List<PhongEntity> timPhong(String loai, Double gia, String tinhTrang) throws RemoteException {
		// TODO Auto-generated method stub
		List<PhongEntity> list = new ArrayList<PhongEntity>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query q = em
					.createNativeQuery("select * from phong where giaPhong=? and loaiPhong like ? and tinhTrang like ?")
					.setParameter(1, gia).setParameter(2, loai).setParameter(3, tinhTrang);
			List<Object[]> objs = q.getResultList();
			for (Object[] o : objs) {
				PhongEntity entity = new PhongEntity();
				entity.setMaPhong(o[0].toString());
				entity.setGiaPhong((double) o[1]);
				entity.setLoaiPhong(o[2].toString());
				entity.setTenPhong(o[3].toString());
				entity.setTinhTrang(o[4].toString());
				list.add(entity);
			}
			tr.commit();
			return list;
		} catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean capNhatTinhTrang(String id, String tinhTrang) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query q = em.createNativeQuery(" update phong set tinhTrang=? where phong.maPhong=?")
					.setParameter(1, tinhTrang).setParameter(2, id);
			int rs = q.executeUpdate();
			tr.commit();
			if (rs > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

}
