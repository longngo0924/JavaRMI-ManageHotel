package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import entity.HoaDonEntity;

public interface HoaDonFacade extends Remote {
	public List<HoaDonEntity> layDsHoaDon() throws RemoteException;

	public HoaDonEntity timHoaDon(String mahd) throws RemoteException;

	public boolean themHoaDon(HoaDonEntity hoadon) throws RemoteException;

	public boolean thanhToanHoaDon(String mahd) throws RemoteException;

	public boolean giaHanHoaDon(String mahd, Date ngaydi) throws RemoteException;

	public double tinhTienPhong(String mahd) throws RemoteException;

	public double tinhTongTien(String mahd) throws RemoteException;

	public List<HoaDonEntity> timHoaDonTheoNgay(Date batdau, Date ketthuc) throws RemoteException;

	public HoaDonEntity timHoaDonTheoPhong(String maPhong) throws RemoteException;
}
