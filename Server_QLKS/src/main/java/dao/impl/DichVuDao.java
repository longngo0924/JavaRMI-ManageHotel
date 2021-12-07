package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.DichVuFacade;
import entity.DichVuEntity;

public class DichVuDao extends UnicastRemoteObject implements DichVuFacade {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7576445327982641110L;
	@SuppressWarnings("unused")
	private EntityManagerFactory emf;
	private EntityManager em;

	protected DichVuDao() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public DichVuDao(EntityManagerFactory emf) throws RemoteException {
		super();
		this.emf = emf;
		em = emf.createEntityManager();

	}

	@Override
	public boolean themDichVu(DichVuEntity dichvu) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(dichvu);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}

	@Override
	public DichVuEntity findById(String madv) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			DichVuEntity p = em.find(DichVuEntity.class, madv);
			tr.commit();
			return p;
		} catch (Exception e) {
			tr.rollback();
		}
		return null;
	}

	@Override
	public boolean capNhatDichVu(DichVuEntity newValue) throws RemoteException {
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
	public List<DichVuEntity> findByCTHD(String cthd) throws RemoteException {
		List<DichVuEntity> list = new ArrayList<DichVuEntity>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query q = em.createNativeQuery("select * from dichvu where maCTHD=?").setParameter(1, cthd);
			List<Object[]> objs = q.getResultList();
			for (Object[] obj : objs) {
				DichVuEntity dv = new DichVuEntity();
				dv.setMaDichVu(obj[0].toString());
				dv.setGiaDichVu((Double) obj[1]);
				dv.setTenDichVu(obj[3].toString());
				list.add(dv);
			}
			tr.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public boolean xoaDichvu(String madv) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			DichVuEntity dv = findById(madv);
			em.remove(dv);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public List<DichVuEntity> getAll() throws RemoteException {
		List<DichVuEntity> list = new ArrayList<DichVuEntity>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query q = em.createNativeQuery("select * from dichvu");
			List<Object[]> objs = q.getResultList();
			for (Object[] obj : objs) {
				DichVuEntity dv = new DichVuEntity();
				dv.setMaDichVu(obj[0].toString());
				dv.setGiaDichVu((Double) obj[1]);
				dv.setTenDichVu(obj[2].toString());
				list.add(dv);
			}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return list;
	}

}
