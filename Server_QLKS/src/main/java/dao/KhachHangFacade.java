package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.KhachHangEntity;

public interface KhachHangFacade extends Remote {
	public boolean themKhachHang(KhachHangEntity khachHang) throws RemoteException;

	public KhachHangEntity findById(String maKH) throws RemoteException;

	public boolean capNhatKhachHang(KhachHangEntity newValue) throws RemoteException;

	public List<KhachHangEntity> getAll() throws RemoteException;

	public List<KhachHangEntity> findByName(String name) throws RemoteException;

	public KhachHangEntity findByPhone(String phone) throws RemoteException;
	
	public KhachHangEntity findByCMND(String cmnd) throws RemoteException;
}
