package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entity.NhanVienEntity;

public interface NhanVienFacade extends Remote {
	public boolean themNhanvien(NhanVienEntity nhanvien) throws RemoteException;

	public NhanVienEntity findById(String manv) throws RemoteException;

	public boolean capNhatNhanVien(NhanVienEntity newValue) throws RemoteException;

}
