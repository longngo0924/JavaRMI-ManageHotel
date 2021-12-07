package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import dao.NhanVienFacade;
import entity.NhanVienEntity;

public class NhanVienDao extends UnicastRemoteObject implements NhanVienFacade {
	/**
	 * 
	 */
	private static final long serialVersionUID = -725620980323257003L;
	@SuppressWarnings("unused")
	private EntityManagerFactory emf;
	private EntityManager em;

	public NhanVienDao() throws RemoteException {
	}

	public NhanVienDao(EntityManagerFactory emf) throws RemoteException {
		super();
		this.emf = emf;
		em = emf.createEntityManager();
	}

	@Override
	public boolean themNhanvien(NhanVienEntity nhanvien) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(nhanvien);
			tr.commit();
			return true;
		} catch (Exception e) {
			tr.rollback();
		}
		return false;
	}

	@Override
	public NhanVienEntity findById(String manv) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			NhanVienEntity nv = em.find(NhanVienEntity.class, manv);
			tr.commit();
			return nv;
		} catch (Exception e) {
			tr.rollback();
		}
		return null;
	}

	@Override
	public boolean capNhatNhanVien(NhanVienEntity newValue) {
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
}
