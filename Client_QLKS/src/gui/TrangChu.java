package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import dao.HoaDonFacade;
import dao.PhongFacade;
import entity.HoaDonEntity;
import entity.PhongEntity;

public class TrangChu extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu mnHeThong;
	private JMenu mnQuanLy;
//	private JMenu mnThongKe;
	private JMenu mnHoTro;

	private JMenuItem itTrangChu;
	private JMenuItem itDangXuat;
	private JMenuItem itThoat;

	private JMenuItem itPhong;
	private JMenuItem itKhachHang;
	private JMenuItem itHoaDon;
	private JMenuItem itDichvu;
//	private JMenuItem itNhaCungCap;
//	private JMenuItem itNhanVien;
	private JMenuItem itTaiKhoan;

//	private JMenuItem itTKLK;
//	private JMenuItem itTKHD;

	private JMenuItem itHuongDan;
	private JMenuItem itGioiThieu;
	private PhongFacade phongFacade;
	private HoaDonFacade hoaDonFacade;
	private JMenuItem itRefresh;
	private List<PhongEntity> phong;

	public TrangChu() throws MalformedURLException, RemoteException, NotBoundException {

		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager == null) {
			System.getProperty("java.security.policy", "rmi/policy.policy");
		}
		phongFacade = (PhongFacade) Naming.lookup("rmi://LONG-NGO:8080/phongFacade");
		hoaDonFacade = (HoaDonFacade) Naming.lookup("rmi://LONG-NGO:8080/hoaDonFacade");

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setTitle("CLOUD HOTEL");
		setSize(1500, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		Image icon = Toolkit.getDefaultToolkit().getImage("./icon/homepage.png");
		setIconImage(icon);
		buildUI();
		thongKePhong(phong);

	}

	private void buildUI() throws RemoteException {
		phong = phongFacade.getAll();
		setJMenuBar(taoMenu());

		setVisible(true);
	}

	private void thongKePhong(List<PhongEntity> phong) throws RemoteException {
		JPanel pnTang1 = new JPanel();
		pnTang1.setBounds(0, 10, 103, 200);
		getContentPane().add(pnTang1);
		pnTang1.setLayout(null);

		JLabel lblTang1 = new JLabel("Tầng 1");
		lblTang1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTang1.setBounds(28, 100, 65, 17);
		pnTang1.add(lblTang1);

		JPanel pnTang2 = new JPanel();
		pnTang2.setLayout(null);
		pnTang2.setBounds(0, 215, 103, 200);
		getContentPane().add(pnTang2);

		JLabel lblTang2 = new JLabel("Tầng 2");
		lblTang2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTang2.setBounds(28, 100, 65, 17);
		pnTang2.add(lblTang2);

		JPanel pnTang3 = new JPanel();
		pnTang3.setLayout(null);
		pnTang3.setBounds(0, 420, 103, 200);
		getContentPane().add(pnTang3);

		JLabel lblTang3 = new JLabel("Tầng 3");
		lblTang3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTang3.setBounds(28, 100, 65, 17);
		pnTang3.add(lblTang3);

		int x1 = 104;
		int x2 = 104;
		int x3 = 104;
		for (PhongEntity p : phong) {

			JPanel pnPhong = new JPanel();
			if (p.getTinhTrang().equals("TRONG")) {
				pnPhong.setBackground(Color.GREEN);
				JLabel lbTrangThai = new JLabel("Trạng thái: Trống");
				lbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lbTrangThai.setBounds(23, 70, 120, 20);
				pnPhong.add(lbTrangThai);

			} else {

				HoaDonEntity hd = hoaDonFacade.timHoaDonTheoPhong(p.getMaPhong());

				pnPhong.setBackground(Color.ORANGE);

				JLabel lbTrangThai = new JLabel("Trạng thái: Đã thuê");
				lbTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lbTrangThai.setBounds(23, 70, 120, 20);
				pnPhong.add(lbTrangThai);

				String ngayDen = String.valueOf(hd.getNgayDen()).substring(0, 11);
				String ngayDi = String.valueOf(hd.getNgayDi()).substring(0, 11);

				JLabel lbNgayDi = new JLabel("Ngày đi: " + ngayDi);
				lbNgayDi.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lbNgayDi.setBounds(23, 130, 150, 20);
				pnPhong.add(lbNgayDi);

				JLabel lbNgayDen = new JLabel("Ngày đến: " + ngayDen);
				lbNgayDen.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lbNgayDen.setBounds(23, 100, 150, 20);
				pnPhong.add(lbNgayDen);
			}
			switch (p.getTenPhong().charAt(3)) {
			case '1':
				pnPhong.setBounds(x1, 10, 225, 200);
				x1 += 230;
				break;
			case '2':
				pnPhong.setBounds(x2, 215, 225, 200);
				x2 += 230;
				break;
			case '3':
				pnPhong.setBounds(x3, 420, 225, 200);
				x3 += 230;
				break;

			default:
				break;
			}
			pnPhong.setForeground(Color.BLACK);
			getContentPane().add(pnPhong);
			pnPhong.setLayout(null);

			JLabel lbPhong = new JLabel(p.getTenPhong());
			lbPhong.setFont(new Font("Tahoma", Font.BOLD, 18));
			lbPhong.setBounds(70, 10, 100, 50);
			pnPhong.add(lbPhong);

		}
	}

	public JMenuBar taoMenu() {
		JMenuBar mb = new JMenuBar();
		mb.setBackground(Color.WHITE);
		mnHeThong = new JMenu("Hệ Thống");
		mnHeThong.setBackground(Color.WHITE);
		mnHeThong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnQuanLy = new JMenu("Quản Lý");
		mnQuanLy.setBackground(Color.WHITE);
		mnQuanLy.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnHoTro = new JMenu("Hỗ Trợ");
		mnHoTro.setBackground(Color.WHITE);
		mnHoTro.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		itTrangChu = new JMenuItem("Trang Chủ");
		itTrangChu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		itDangXuat = new JMenuItem("Đăng Xuất");
		itDangXuat.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		itThoat = new JMenuItem("Thoát");
		itThoat.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		itPhong = new JMenuItem("Phòng");
		itPhong.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		itKhachHang = new JMenuItem("Khách Hàng");
		itKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		itHoaDon = new JMenuItem("Hóa Đơn");
		itHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		itDichvu = new JMenuItem("Dịch vụ");
		itDichvu.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		itTaiKhoan = new JMenuItem("Tài Khoản");
		itTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		itHuongDan = new JMenuItem("Hướng Dẫn");
		itHuongDan.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		itGioiThieu = new JMenuItem("Giới Thiệu");
		itGioiThieu.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		mnHeThong.setIcon(new ImageIcon("./icon/hotel.png"));
		mnQuanLy.setIcon(new ImageIcon("./icon/checklist.png"));
		mnHoTro.setIcon(new ImageIcon("./icon/idea.png"));

		itTrangChu.setIcon(new ImageIcon("./icon/log-out.png"));
		itDangXuat.setIcon(new ImageIcon("./icon/log-out.png"));
		itThoat.setIcon(new ImageIcon("./icon/turn-off.png"));

		itPhong.setIcon(new ImageIcon("./icon/interior-design.png"));
		itKhachHang.setIcon(new ImageIcon("./icon/people.png"));
		itHoaDon.setIcon(new ImageIcon("./icon/bill.png"));
		itDichvu.setIcon(new ImageIcon("./icon/eating.png"));

		itTaiKhoan.setIcon(new ImageIcon("./icon/account.png"));

		itHuongDan.setIcon(new ImageIcon("./icon/idea.png"));
		itGioiThieu.setIcon(new ImageIcon("./icon/idea.png"));

		itRefresh = new JMenuItem("Làm mới trang chủ");
		itRefresh.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		itRefresh.setIcon(new ImageIcon("./icon/refresh.png"));

		mb.add(mnHeThong);
		mb.add(mnQuanLy);
		mb.add(mnHoTro);

		mnHeThong.add(itDangXuat);
		mnHeThong.add(itThoat);

		mnQuanLy.add(itPhong);
		mnQuanLy.add(itKhachHang);
		mnQuanLy.add(itHoaDon);
		mnQuanLy.add(itDichvu);
		mnQuanLy.add(itTaiKhoan);
		mnQuanLy.add(itRefresh);

		mnHoTro.add(itHuongDan);
		mnHoTro.add(itGioiThieu);

		itPhong.addActionListener(e -> {
			Phong phong;
			try {
				phong = new Phong();
				phong.setVisible(true);

			} catch (RemoteException | MalformedURLException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		itKhachHang.addActionListener(e -> {
			KhachHang khachHang = new KhachHang();
			khachHang.setVisible(true);
		});
		itDichvu.addActionListener(e -> {
			DichVu dichVu;
			try {
				dichVu = new DichVu();
				dichVu.setVisible(true);

			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		itThoat.addActionListener(this);
		itTaiKhoan.addActionListener(e -> {
			TaiKhoan taiKhoan;
			try {
				taiKhoan = new TaiKhoan();
				taiKhoan.setVisible(true);

			} catch (MalformedURLException | RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		itHoaDon.addActionListener(e -> {
			HoaDon hoaDon;
			try {
				try {
					hoaDon = new HoaDon();
					hoaDon.setVisible(true);

				} catch (MalformedURLException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		itRefresh.addActionListener(e -> {

			try {
				phong = phongFacade.getAll();
				thongKePhong(phong);
				dispose();
				TrangChu trangChu = new TrangChu();
				trangChu.setVisible(true);
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

		});

		itDangXuat.addActionListener(e -> {
			dispose();
			DangNhap dangNhap;
			try {
				dangNhap = new DangNhap();
				dangNhap.setVisible(true);

			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		return mb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
//		if (o.equals(itLinhKien)) {
//			UI_LinhKien frm=new UI_LinhKien();
//			frm.setVisible(true);
//		}else
//		if (o.equals(itKhachHang)) {
//			UI_KhachHang frm=new UI_KhachHang();
//			frm.setVisible(true);
//		}else
//		if (o.equals(itLoaiLinhKien)) {
//				UI_LoaiLinhKien frm=new UI_LoaiLinhKien();
//				frm.setVisible(true);
//		}
//		else
//		if (o.equals(itNhaCungCap)) {
//					UI_NhaCungCap frm=new UI_NhaCungCap();
//					frm.setVisible(true);
//		}
//		else
		if (o.equals(itThoat)) {
			System.exit(0);
		}
	}
}
