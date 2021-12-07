package gui;

import java.awt.Color;
import java.awt.Font;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CTHDFacade;
import dao.HoaDonFacade;
import dao.PhongFacade;
import entity.CT_HoaDonEntity;
import entity.HoaDonEntity;

public class CTHD extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8227757040277656215L;
	private JTable table;
	private DefaultTableModel dataModel;
	private JTable table_1;
	private CTHDFacade cthdFacade;
	private List<CT_HoaDonEntity> cthd;
	private HoaDonFacade hoaDonFacade;
	private HoaDonEntity hd;
	private PhongFacade phongFacade;

	public CTHD(String mahd) throws RemoteException, MalformedURLException, NotBoundException {
		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager == null) {
			System.getProperty("java.security.policy", "rmi/policy.policy");
		}
		cthdFacade = (CTHDFacade) Naming.lookup("rmi://LONG-NGO:8080/cthdFacade");
		cthd = cthdFacade.findByHoaDon(mahd);
		hoaDonFacade = (HoaDonFacade) Naming.lookup("rmi://LONG-NGO:8080/hoaDonFacade");
		hd = hoaDonFacade.timHoaDon(mahd);
		phongFacade = (PhongFacade) Naming.lookup("rmi://LONG-NGO:8080/phongFacade");

		setTitle("Chi tiết hóa đơn");
		setSize(550, 750);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel lblTitle = new JLabel("HÓA ĐƠN THUÊ PHÒNG");
		lblTitle.setForeground(Color.RED);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(153, 31, 255, 32);
		getContentPane().add(lblTitle);

		JLabel lblMaHoaDon = new JLabel("Mã hóa đơn: " + mahd);
		lblMaHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaHoaDon.setBounds(61, 95, 220, 22);
		getContentPane().add(lblMaHoaDon);

		JLabel lblNgayLapHD = new JLabel("Ngày lập: " + hd.getNgayLap());
		lblNgayLapHD.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgayLapHD.setBounds(308, 95, 220, 22);
		getContentPane().add(lblNgayLapHD);

		JLabel lblNgayDen = new JLabel("Ngày đến: " + hd.getNgayDen());
		lblNgayDen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgayDen.setBounds(61, 157, 220, 22);
		getContentPane().add(lblNgayDen);

		JLabel lblNgayDi = new JLabel("Ngày đi: " + hd.getNgayDi());
		lblNgayDi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgayDi.setBounds(308, 157, 220, 22);
		getContentPane().add(lblNgayDi);

		JLabel lblKhachHang = new JLabel("Khách hàng: " + hd.getKhachHang().getTenKhachHang());
		lblKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKhachHang.setBounds(61, 217, 220, 22);
		getContentPane().add(lblKhachHang);

		double tienPhong = hoaDonFacade.tinhTienPhong(mahd);
		double tongtien = hoaDonFacade.tinhTongTien(mahd);
		JLabel lblTngTin = new JLabel("Tổng tiền: " + tongtien);
		lblTngTin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTngTin.setBounds(61, 500, 220, 22);
		getContentPane().add(lblTngTin);

		JLabel lblDichVu = new JLabel("Dịch vụ");
		lblDichVu.setForeground(Color.RED);
		lblDichVu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDichVu.setBounds(216, 244, 95, 22);
		getContentPane().add(lblDichVu);

		JButton btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThanhToan.setBounds(61, 625, 120, 43);
		getContentPane().add(btnThanhToan);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHuy.setBounds(400, 625, 120, 43);
		getContentPane().add(btnHuy);

		Main main = new Main();
		JLabel lblNhanVienTT = new JLabel("Nhân viên thanh toán: " + main.nv.getTenNhanVien());
		lblNhanVienTT.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhanVienTT.setBounds(61, 554, 187, 22);
		getContentPane().add(lblNhanVienTT);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 279, 423, 139);
		getContentPane().add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		String[] headers = { "Tên dịch vụ", "Giá dịch vụ", "Số lượng" };
		dataModel = new DefaultTableModel(headers, 0);
		table_1.setModel(dataModel);
		for (CT_HoaDonEntity ct : cthd) {
			String row[] = { ct.getDichvu().getTenDichVu(), String.valueOf(ct.getDichvu().getGiaDichVu()),
					String.valueOf(ct.getSoluong()) };
			dataModel.addRow(row);
		}

		JLabel lblTienPhong = new JLabel("Tiền phòng: " + tienPhong);
		lblTienPhong.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTienPhong.setBounds(61, 446, 220, 22);
		getContentPane().add(lblTienPhong);

		if (hd.isTrangThai()) {
			btnThanhToan.setEnabled(false);
		} else {
			btnThanhToan.setEnabled(true);
		}
		btnHuy.addActionListener(e -> {
			dispose();
		});

		btnThanhToan.addActionListener(e -> {
			try {
				boolean rs = hoaDonFacade.thanhToanHoaDon(mahd);
				boolean rsp = phongFacade.capNhatTinhTrang(hd.getPhong().getMaPhong(), "TRONG");
				if (rs && rsp) {
					JOptionPane.showMessageDialog(null, "Thanh toán thành công");
					btnThanhToan.setEnabled(false);
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		CTHD cthd = new CTHD("1");
		cthd.setVisible(true);

	}
}
