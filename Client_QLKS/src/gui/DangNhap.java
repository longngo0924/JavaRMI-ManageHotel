package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.NhanVienFacade;
import dao.TaiKhoanFacade;
import entity.TaiKhoanEntity;

public class DangNhap extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 280591702560698774L;
	private static TaiKhoanFacade taiKhoanFacade;
	private static DangNhap dangNhap;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private JPanel pnlDangNhap;
	private JButton btnDangNhap;
	private static NhanVienFacade nhanVienFacade;

	public DangNhap() throws MalformedURLException, RemoteException, NotBoundException {
		setTitle("CLOUD HOTEL");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		buildUI();
	}

	private void buildUI() throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager == null) {
			System.getProperty("java.security.policy", "rmi/policy.policy");
		}
		taiKhoanFacade = (TaiKhoanFacade) Naming.lookup("rmi://LONG-NGO:8080/taiKhoanFacade");
		nhanVienFacade = (NhanVienFacade) Naming.lookup("rmi://LONG-NGO:8080/nhanVienFacade");

		pnlDangNhap = new JPanel();

		add(pnlDangNhap, BorderLayout.CENTER);

		pnlDangNhap.setBackground(Color.WHITE);
		pnlDangNhap.setLayout(null);

		JLabel lblDangNhap = new JLabel("ĐĂNG NHẬP");
		JLabel lblTaiKhoan = new JLabel("Tài khoản:");
		JLabel lblMatKhau = new JLabel("Mật khẩu:");

		txtTaiKhoan = new JTextField(300);
		txtMatKhau = new JPasswordField(300);

		btnDangNhap = new JButton("Đăng nhập");

		lblDangNhap.setFont(new Font("Times new roman", Font.BOLD, 25));
		lblTaiKhoan.setFont(new Font("Times new roman", Font.BOLD, 20));
		lblMatKhau.setFont(new Font("Times new roman", Font.BOLD, 20));
		btnDangNhap.setFont(new Font("Times new roman", Font.BOLD, 16));
		txtTaiKhoan.setFont(new Font("Times new roman", Font.HANGING_BASELINE, 18));
		txtMatKhau.setFont(new Font("Times new roman", Font.HANGING_BASELINE, 18));

		lblDangNhap.setOpaque(true);

		lblDangNhap.setBackground(Color.decode("#D3D3D3"));
		btnDangNhap.setBackground(Color.decode("#4688f2"));
//		lblDangNhap.setPreferredSize(new Dimension(850,100));
		lblDangNhap.setHorizontalAlignment(JLabel.CENTER);
		lblDangNhap.setVerticalAlignment(JLabel.CENTER);

		btnDangNhap.setForeground(Color.WHITE);

		pnlDangNhap.add(lblDangNhap);
		pnlDangNhap.add(lblTaiKhoan);
		pnlDangNhap.add(txtTaiKhoan);
		pnlDangNhap.add(lblMatKhau);
		pnlDangNhap.add(txtMatKhau);
		pnlDangNhap.add(btnDangNhap);

		lblDangNhap.setBounds(5, 30, 600, 50);
		lblTaiKhoan.setBounds(80, 120, 100, 30);
		txtTaiKhoan.setBounds(200, 120, 300, 25);
		lblMatKhau.setBounds(80, 170, 100, 30);
		txtMatKhau.setBounds(200, 170, 300, 25);
		btnDangNhap.setBounds(350, 250, 150, 40);
		txtTaiKhoan.setText("120120");
		txtMatKhau.setText("123456789");

		//
		btnDangNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String maDangNhap = txtTaiKhoan.getText();
				String matkhau = txtMatKhau.getText();
				try {
					TaiKhoanEntity entity = taiKhoanFacade.dangNhap(maDangNhap, matkhau);
					if (entity != null) {
						JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
						Main main = new Main();
						main.nv = nhanVienFacade.findById(entity.getNhanVien().getMaNhanVien());
						TrangChu trangChu = new TrangChu();
						trangChu.setVisible(true);
						dispose();
					} else {
						System.out.println(entity);
						JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
						dangNhap.setVisible(false);
						HoaDon hoaDon;

						try {
							hoaDon = new HoaDon();
							hoaDon.setVisible(true);

						} catch (MalformedURLException | NotBoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		dangNhap = new DangNhap();
		dangNhap.setVisible(true);

	}

}
