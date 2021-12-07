package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.DichVuEntity;

public interface DichVuFacade extends Remote {
	public boolean themDichVu(DichVuEntity dichvu) throws RemoteException;

	public DichVuEntity findById(String maKH) throws RemoteException;

	public boolean capNhatDichVu(DichVuEntity newValue) throws RemoteException;

	public List<DichVuEntity> findByCTHD(String cthd) throws RemoteException;

	public boolean xoaDichvu(String madv) throws RemoteException;

	public List<DichVuEntity> getAll() throws RemoteException;
}
