package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.PhongEntity;

public interface PhongFacade extends Remote {
	public boolean themPhong(PhongEntity phong) throws RemoteException;

	public PhongEntity findById(String maPhong) throws RemoteException;

	public boolean capNhatPhong(PhongEntity newValue) throws RemoteException;

	public boolean capNhatTinhTrang(String id, String tinhTrang) throws RemoteException;

	public List<PhongEntity> getAll() throws RemoteException;

	public List<PhongEntity> timPhong(String loai, Double gia, String tinhTrang) throws RemoteException;

}
