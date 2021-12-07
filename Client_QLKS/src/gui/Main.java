package gui;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import entity.NhanVienEntity;

public class Main {
	public static NhanVienEntity nv;

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager == null) {
			System.getProperty("java.security.policy", "rmi/policy.policy");
		}
		DangNhap dangNhap = new DangNhap();
		dangNhap.setVisible(true);
	}

}
