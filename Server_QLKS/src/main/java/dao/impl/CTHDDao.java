package dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.CTHDFacade;
import entity.CT_HoaDonEntity;
import entity.DichVuEntity;
import entity.HoaDonEntity;

public class CTHDDao extends UnicastRemoteObject implements CTHDFacade {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3137394359188190782L;
	@SuppressWarnings("unused")
	private EntityManagerFactory emf;
	private EntityManager em;

	protected CTHDDao() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public CTHDDao(EntityManagerFactory emf) throws RemoteException {
		super();
		this.emf = emf;
		em = emf.createEntityManager();
	}

	@Override
	public CT_HoaDonEntity findById(String id) throws RemoteException {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			CT_HoaDonEntity cthd = em.find(CT_HoaDonEntity.class, id);
			tr.commit();
			return cthd;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public boolean themCTHD(CT_HoaDonEntity cthd) throws RemoteException {
		System.out.println(cthd);
		List<CT_HoaDonEntity> list = new ArrayList<CT_HoaDonEntity>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query q = em.createNativeQuery("select * from cthd c where c.maDichVu=? and c.maHoadon=?")
					.setParameter(1, cthd.getDichvu().getMaDichVu()).setParameter(2, cthd.getHoadon().getMaHoaDon());
			List<?> rs = q.getResultList();
			for (Object o : rs) {
				Object[] obj = (Object[]) o;
				CT_HoaDonEntity c = new CT_HoaDonEntity();
				c.setMaCTHD(String.valueOf(obj[0]));
				HoaDonEntity hd = em.find(HoaDonEntity.class, String.valueOf(obj[3]));
				DichVuEntity dv = em.find(DichVuEntity.class, String.valueOf(obj[2]));
				c.setHoadon(hd);
				c.setDichvu(dv);
				c.setSoluong((int) obj[1]);
				list.add(c);
			}
			if (list.size() > 0) {
				int soLuongCu = list.get(0).getSoluong();
				int soLuongMoi = soLuongCu + cthd.getSoluong();
				list.get(0).setSoluong(soLuongMoi);
				em.merge(list.get(0));
			} else {
				em.persist(cthd);
			}
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public List<CT_HoaDonEntity> findByHoaDon(String mahd) throws RemoteException {
		List<CT_HoaDonEntity> list = new ArrayList<CT_HoaDonEntity>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query q = em.createNativeQuery("select * from cthd where maHoadon=?").setParameter(1, mahd);
			List<?> rs = q.getResultList();
			for (Object o : rs) {
				Object[] obj = (Object[]) o;
				CT_HoaDonEntity cthd = new CT_HoaDonEntity();
				cthd.setMaCTHD(String.valueOf(obj[0]));
				HoaDonEntity hd = em.find(HoaDonEntity.class, String.valueOf(obj[3]));
				DichVuEntity dv = em.find(DichVuEntity.class, String.valueOf(obj[2]));
				cthd.setHoadon(hd);
				cthd.setDichvu(dv);
				cthd.setSoluong((int) obj[1]);
				list.add(cthd);
			}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return list;
	}

	@Override
	public int getSizeCTHD() throws RemoteException {

		List<CT_HoaDonEntity> list = new ArrayList<CT_HoaDonEntity>();
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			Query q = em.createNativeQuery("select * from cthd");
			List<?> rs = q.getResultList();
			for (Object o : rs) {
				Object[] obj = (Object[]) o;
				CT_HoaDonEntity cthd = new CT_HoaDonEntity();
				cthd.setMaCTHD(String.valueOf(obj[0]));
				HoaDonEntity hd = em.find(HoaDonEntity.class, String.valueOf(obj[3]));
				DichVuEntity dv = em.find(DichVuEntity.class, String.valueOf(obj[2]));
				cthd.setHoadon(hd);
				cthd.setDichvu(dv);
				cthd.setSoluong((int) obj[1]);
				list.add(cthd);
			}
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return list.size();
	}

}
