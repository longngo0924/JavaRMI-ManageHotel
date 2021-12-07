package app;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.CTHDFacade;
import dao.DichVuFacade;
import dao.HoaDonFacade;
import dao.KhachHangFacade;
import dao.NhanVienFacade;
import dao.PhongFacade;
import dao.TaiKhoanFacade;
import dao.impl.CTHDDao;
import dao.impl.DichVuDao;
import dao.impl.HoaDonDao;
import dao.impl.KhachHangDao;
import dao.impl.NhanVienDao;
import dao.impl.PhongDao;
import dao.impl.TaiKhoanDao;
import entity.HoaDonEntity;

public class Server {
	public static void main(String[] args) throws RemoteException, NamingException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BTL_QuanLyKhachSan");

		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.getProperty("java.security.policy", "rmi/policy.policy");

		}

		Context context = new InitialContext();
		LocateRegistry.createRegistry(8080);

		TaiKhoanFacade taiKhoanFacade = new TaiKhoanDao(emf);
		context.bind("rmi://LONG-NGO:8080/taiKhoanFacade", taiKhoanFacade);

		HoaDonFacade hoaDonFacade = new HoaDonDao(emf);
		context.bind("rmi://LONG-NGO:8080/hoaDonFacade", hoaDonFacade);

		NhanVienFacade nhanVienFacade = new NhanVienDao(emf);
		context.bind("rmi://LONG-NGO:8080/nhanVienFacade", nhanVienFacade);

		KhachHangFacade khachHangFacade = new KhachHangDao(emf);
		context.bind("rmi://LONG-NGO:8080/khachHangFacade", khachHangFacade);

		DichVuFacade dichVuFacade = new DichVuDao(emf);
		context.bind("rmi://LONG-NGO:8080/dichVuFacade", dichVuFacade);

		CTHDFacade cthdFacade = new CTHDDao(emf);
		context.bind("rmi://LONG-NGO:8080/cthdFacade", cthdFacade);

		PhongFacade phongFacade = new PhongDao(emf);
		context.bind("rmi://LONG-NGO:8080/phongFacade", phongFacade);

		System.out.println("Server bound in RMIRegistry");
	}
}
