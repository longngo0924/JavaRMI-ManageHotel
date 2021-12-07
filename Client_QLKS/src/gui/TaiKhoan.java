package gui;

import java.awt.Color;
import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import dao.TaiKhoanFacade;

public class TaiKhoan extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8781093063635333197L;
	private JPasswordField txtMkCu;
	private JPasswordField txtMatKhau;
	private JPasswordField txtNhapLaiMK;
	private TaiKhoanFacade taiKhoanFacade;

	@SuppressWarnings("static-access")
	public TaiKhoan() throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager == null) {
			System.getProperty("java.security.policy", "rmi/policy.policy");
		}
		taiKhoanFacade = (TaiKhoanFacade) Naming.lookup("rmi://LONG-NGO:8080/taiKhoanFacade");

		setTitle("Đổi mật khẩu");
		setSize(700, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("ĐỔI MẬT KHẨU");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(267, 26, 197, 42);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Mật khẩu cũ: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(96, 143, 135, 26);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Mật khẩu mới: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(84, 204, 135, 26);
		getContentPane().add(lblNewLabel_2);

		txtMkCu = new JPasswordField();
		txtMkCu.setBounds(244, 143, 280, 26);
		getContentPane().add(txtMkCu);
		txtMkCu.setColumns(10);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setBounds(244, 207, 283, 27);
		getContentPane().add(txtMatKhau);
		txtMatKhau.setColumns(10);

		JButton btnDoiMK = new JButton("Đổi mật khẩu");
		btnDoiMK.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDoiMK.setBounds(121, 343, 170, 52);
		getContentPane().add(btnDoiMK);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHuy.setBounds(413, 343, 141, 52);
		getContentPane().add(btnHuy);

		JLabel lblNewLabel_2_1 = new JLabel("Nhập lại mật khẩu: ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(47, 273, 164, 26);
		getContentPane().add(lblNewLabel_2_1);

		txtNhapLaiMK = new JPasswordField();
		txtNhapLaiMK.setColumns(10);
		txtNhapLaiMK.setBounds(244, 273, 283, 27);
		getContentPane().add(txtNhapLaiMK);

		Main main = new Main();

		JLabel lblTenNhanVien = new JLabel("Tên nhân viên: " + main.nv.getTenNhanVien());
		lblTenNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTenNhanVien.setBounds(367, 92, 250, 26);
		getContentPane().add(lblTenNhanVien);

		JLabel lblMaNhanVien = new JLabel("Mã nhân viên: " + main.nv.getMaNhanVien());
		lblMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMaNhanVien.setBounds(84, 93, 250, 24);
		getContentPane().add(lblMaNhanVien);

		btnHuy.addActionListener(e -> {
			dispose();
		});
		btnDoiMK.addActionListener(e -> {
			char[] newPass1 = txtMatKhau.getPassword();
			char[] newPass2 = txtNhapLaiMK.getPassword();
			char[] oldPass = txtMkCu.getPassword();
			if (!String.valueOf(newPass1).equals(String.valueOf(newPass2))) {
				JOptionPane.showMessageDialog(null, "Mật khẩu không trùng khớp");
			} else {
				try {
					boolean rs = taiKhoanFacade.thayDoiMatKhau(main.nv.getMaNhanVien(), String.valueOf(oldPass),
							String.valueOf(newPass2));
					if (rs) {
						JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu thành công");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu thất bại");
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
