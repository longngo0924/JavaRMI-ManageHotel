package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import entity.TaiKhoanEntity;

public interface TaiKhoanFacade extends Remote {
	public TaiKhoanEntity dangNhap(String tenDangNhap, String matkhau) throws RemoteException;

	public boolean thayDoiMatKhau(String id, String oldPass, String newPassword) throws RemoteException;
}
