package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.CT_HoaDonEntity;

public interface CTHDFacade extends Remote {
	public CT_HoaDonEntity findById(String id) throws RemoteException;

	public boolean themCTHD(CT_HoaDonEntity cthd) throws RemoteException;

	public List<CT_HoaDonEntity> findByHoaDon(String mahd) throws RemoteException;

	public int getSizeCTHD() throws RemoteException;
}
