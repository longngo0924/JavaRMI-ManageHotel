package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import dao.CTHDFacade;
import dao.DichVuFacade;
import dao.HoaDonFacade;
import dao.KhachHangFacade;
import dao.NhanVienFacade;
import dao.PhongFacade;
import entity.CT_HoaDonEntity;
import entity.DichVuEntity;
import entity.HoaDonEntity;
import entity.KhachHangEntity;
import entity.PhongEntity;

public class HoaDon extends JFrame implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2765674155650958239L;
	private static PhongFacade phongFacade;
	private static List<PhongEntity> phong;
	private static List<HoaDonEntity> hoadon;
	private static KhachHangFacade khachHangFacade;
	private static NhanVienFacade nhanVienFacade;
	private static DichVuFacade dichVuFacade;
	private static CTHDFacade cthdFacade;
	private static HoaDonFacade hoadonFacade;
	private Container bTong;
	private Box bTren;
	private Box bGiua;
	private Box bDuoi;
	private Box bThongTin;
	private JLabel lblMaHoaDon;
	private JLabel lblNgayDen;
	private JLabel lblPhong;
	private JLabel lblGiaPhong;
	private JTextField txtMaHoaDon;
	private JTextField txtTenPhong;
	private JTextField txtLoaiPhong;
	private JTextField txtGia;
	private Box bChucNang;
	private JButton btnThem;
	private JButton btnGiaHan;
	private JButton btnLuu;
	private JButton btnHuy;
	private JLabel lblThongBao;
	private JTextField txtThongBao;
	private Component verticalStrut;
	private JTextField txtTinhTrang;
	private JButton btnTimKiem;
	private JLabel lblTinhTrang;
	private JTextField txtDiaChiTK;
	private JTextField txtSoDienThoaiTK;
	private Component horizontalStrut_3;
	private Component horizontalStrut_4;
	private Component horizontalStrut_5;
	private Component verticalStrut_1;
	private DefaultTableModel dataModel;
	private JTable table;
	private JLabel lblNgayDi;
	private JLabel lblNgayLap;
	private JButton btnTamTinh;
	private Component horizontalStrut_6;
	private Component verticalStrut_2;
	private JLabel lblKhachHang;
	private JTextField txtKhachHang;
	private JButton btnTKKh;
	private KhachHangEntity khOld;
	private List<DichVuEntity> dichvu;
	private JButton btnThemDv;
	private JComboBox cb;
	private Vector<String> p1;
	private JTextField txtSoLuongDV;

	public HoaDon() throws RemoteException, MalformedURLException, NotBoundException {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Quản lý phòng");
		setSize(1280, 720);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
//		hanhdong = 0;
		buildUI();
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	private void buildUI() throws RemoteException, MalformedURLException, NotBoundException {
		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager == null) {
			System.getProperty("java.security.policy", "rmi/policy.policy");
		}
		phongFacade = (PhongFacade) Naming.lookup("rmi://LONG-NGO:8080/phongFacade");
		khachHangFacade = (KhachHangFacade) Naming.lookup("rmi://LONG-NGO:8080/khachHangFacade");
		nhanVienFacade = (NhanVienFacade) Naming.lookup("rmi://LONG-NGO:8080/nhanVienFacade");
		dichVuFacade = (DichVuFacade) Naming.lookup("rmi://LONG-NGO:8080/dichVuFacade");
		cthdFacade = (CTHDFacade) Naming.lookup("rmi://LONG-NGO:8080/cthdFacade");
		hoadonFacade = (HoaDonFacade) Naming.lookup("rmi://LONG-NGO:8080/hoaDonFacade");

		phong = phongFacade.getAll();
		phong.forEach(v -> System.out.println(v));
		hoadon = hoadonFacade.layDsHoaDon();
		dichvu = dichVuFacade.getAll();

		// TODO Auto-generated method stub

		getContentPane().add(bTong = Box.createVerticalBox());

		bTong.add(Box.createVerticalStrut(10));
		bTong.add(bTren = Box.createHorizontalBox());
		bTong.add(Box.createVerticalStrut(20));
		bTong.add(bGiua = Box.createHorizontalBox());
		bTong.add(Box.createVerticalStrut(20));
		bTong.add(bDuoi = Box.createVerticalBox());

		// thong tin phòng
		bThongTin = Box.createVerticalBox();
		bThongTin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		bTren.add(bThongTin);
		bThongTin.setBackground(Color.WHITE);
		bThongTin.setBorder(
				new TitledBorder(null, "Thông tin hóa đơn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		// label and text field
		lblMaHoaDon = new JLabel("Mã hóa đơn: ");
		lblMaHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMaHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		lblNgayDen = new JLabel("Ngày đến: ");
		lblNgayDen.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgayDen.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		lblNgayDi = new JLabel("Ngày đi: ");
		lblNgayDi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgayDi.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		lblNgayLap = new JLabel("Ngày lập: ");
		lblNgayLap.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgayLap.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		lblPhong = new JLabel("Phòng: ");
		lblPhong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblGiaPhong = new JLabel("Giá: ");
		lblGiaPhong.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGiaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		lblGiaPhong.setPreferredSize(lblNgayDen.getPreferredSize());
		lblPhong.setPreferredSize(lblMaHoaDon.getPreferredSize());

		txtMaHoaDon = new JTextField();
		txtMaHoaDon.setColumns(7);
		txtMaHoaDon.setBackground(Color.WHITE);
		txtMaHoaDon.setEditable(false);
		txtMaHoaDon.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		UtilDateModel ngayden = new UtilDateModel();
		UtilDateModel ngaydi = new UtilDateModel();

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		JDatePanelImpl datePanelNgayDen = new JDatePanelImpl(ngayden, p);
		JDatePickerImpl datePickerNgayDen = new JDatePickerImpl(datePanelNgayDen, new DateLabelFormatter());

		JDatePanelImpl datePanelNgayDi = new JDatePanelImpl(ngaydi, p);
		JDatePickerImpl datePickerNgayDi = new JDatePickerImpl(datePanelNgayDi, new DateLabelFormatter());

		p1 = new Vector<String>();
		for (PhongEntity e : phong) {
			if (e.getTinhTrang().equals("TRONG")) {
				p1.add(e.getTenPhong());
			}
		}
		cb = new JComboBox(p1);
		cb.setBounds(300, 50, 300, 20);
		cb.setPrototypeDisplayValue("======================");

		Vector<String> dv = new Vector<String>();
		for (DichVuEntity d : dichvu) {
			dv.add(d.getTenDichVu());
		}
		JComboBox cbDichVu = new JComboBox(dv);
		cbDichVu.setBounds(350, 50, 80, 20);
		cbDichVu.setPrototypeDisplayValue("=========");

		txtGia = new JTextField();
		txtGia.setColumns(7);
		txtGia.setBackground(Color.WHITE);
		txtGia.setEditable(false);
		txtGia.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// add vao
		Box b01, b02;
		b01 = Box.createHorizontalBox();
		b02 = Box.createHorizontalBox();
		bThongTin.add(b01);
		bThongTin.add(Box.createVerticalStrut(5));
		bThongTin.add(b02);
		bThongTin.add(Box.createVerticalStrut(5));

		b01.add(lblMaHoaDon);
		b01.add(txtMaHoaDon);

		b02.add(lblNgayDen);
		b02.add(datePickerNgayDen);

		b02.add(lblNgayDi);
		b02.add(datePickerNgayDi);

		b01.add(lblPhong);
		b01.add(cb);
		b02.add(lblGiaPhong);
		b02.add(txtGia);

		// chuc nang - button
		bChucNang = Box.createHorizontalBox();
		bGiua.add(bChucNang);
		btnThem = new JButton("Thêm hóa đơn");
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		btnGiaHan = new JButton("Gia hạn hóa đơn");
		btnGiaHan.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		btnTamTinh = new JButton("Tạm tính");
		btnTamTinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnTamTinh.setEnabled(false);

		btnLuu = new JButton("Lưu");
		btnLuu.setEnabled(false);
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		btnHuy = new JButton("Hủy");
		btnHuy.setEnabled(false);
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		btnThemDv = new JButton("Thêm dịch vụ");
		btnThemDv.setEnabled(false);
		btnThemDv.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		txtSoLuongDV = new JTextField();
		txtSoLuongDV.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		bChucNang.add(btnThem);
		bChucNang.add(Box.createHorizontalStrut(10));
		bChucNang.add(btnGiaHan);
		bChucNang.add(Box.createHorizontalStrut(10));
		bChucNang.add(btnLuu);
		bChucNang.add(Box.createHorizontalStrut(10));
		bChucNang.add(btnTamTinh);
		bChucNang.add(Box.createHorizontalStrut(10));
		bChucNang.add(btnHuy);
		bChucNang.add(Box.createHorizontalStrut(10));
		bChucNang.add(cbDichVu);

		bChucNang.add(Box.createHorizontalStrut(10));
		bChucNang.add(txtSoLuongDV);

		bChucNang.add(Box.createHorizontalStrut(10));
		bChucNang.add(btnThemDv);

		bChucNang.add(Box.createHorizontalStrut(120));
		// bduoi
		// tim kiem
		Box btk = Box.createVerticalBox();
		btk.setBorder(new TitledBorder("Tìm Kiếm"));
		Box b11;

		Box btkKh = Box.createVerticalBox();
		btkKh.setBorder(new TitledBorder("Tìm kiếm khách hàng"));
		bDuoi.add(btkKh);
//		bDuoi.add(btk);

		verticalStrut = Box.createVerticalStrut(20);
		btk.add(verticalStrut);
		b11 = Box.createHorizontalBox();

		Box b12;
		b12 = Box.createHorizontalBox();

		btk.add(b11);
		btkKh.add(b12);

		txtTinhTrang = new JTextField();
		txtTinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		lblTinhTrang = new JLabel("Tình trạng:   ");
		lblTinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		lblKhachHang = new JLabel("Nhập CMND khách hàng:   ");
		lblKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lblGiaTK = new JLabel("Giá:   ");
		lblGiaTK.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lblLoai = new JLabel("Loại phòng:   ");
		lblLoai.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		txtDiaChiTK = new JTextField();
		txtDiaChiTK.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		txtSoDienThoaiTK = new JTextField();
		txtSoDienThoaiTK.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		txtKhachHang = new JTextField();
		txtKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 15));

//		b11.add(lblTinhTrang);
//		b11.add(txtTinhTrang);
//
//		horizontalStrut_3 = Box.createHorizontalStrut(10);
//		b11.add(horizontalStrut_3);
//		b11.add(lblGiaTK);
//		b11.add(txtDiaChiTK);
//
//		horizontalStrut_4 = Box.createHorizontalStrut(10);
//		b11.add(horizontalStrut_4);
//		b11.add(lblLoai);
//		b11.add(txtSoDienThoaiTK);
//
//		horizontalStrut_5 = Box.createHorizontalStrut(10);
//		b11.add(horizontalStrut_5);
//		b11.add(btnTimKiem);

		verticalStrut_1 = Box.createVerticalStrut(20);
		btk.add(verticalStrut_1);

		btnTKKh = new JButton("Tìm kiếm khách hàng");

		b12.add(lblKhachHang);
		b12.add(txtKhachHang);
		b12.add(btnTKKh);

		verticalStrut_2 = Box.createVerticalStrut(20);
		b12.add(verticalStrut_2);

		horizontalStrut_6 = Box.createHorizontalStrut(10);
		b12.add(horizontalStrut_6);

		btk.setMaximumSize(new Dimension(1600, 100));
		btkKh.setMaximumSize(new Dimension(1600, 100));

		// table
		Box bds = Box.createVerticalBox();
		bDuoi.add(bds);
		bds.setBorder(new TitledBorder("Danh sách hóa đơn"));

		String[] headers = { "Mã hóa đơn", "Ngày lập", "Ngày đến", "Ngày đi", "Tiền phòng", "Trạng thái" };
		dataModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll;
		bds.add(scroll = new JScrollPane(table = new JTable(dataModel)));
		for (HoaDonEntity hd : hoadon) {
			String mahd = hd.getMaHoaDon();
			String ngayLap = hd.getNgayLap().toString();
			String ngayDen = hd.getNgayLap().toString();
			String ngayDi = hd.getNgayLap().toString();
			String tongtien = String.valueOf(hd.getThanhToan());
			String trangthai;
			if (hd.isTrangThai()) {
				trangthai = "Đã thanh toán";
			} else {
				trangthai = "Chưa thanh toán";
			}
			String row[] = { mahd, ngayLap, ngayDen, ngayDi, tongtien, trangthai };
			dataModel.addRow(row);
		}
		table.setRowHeight(50);
		table.addMouseListener(this);
		table.setDefaultEditor(Object.class, null);
		btnThem.addActionListener((e -> {
			btnLuu.setEnabled(true);
			btnHuy.setEnabled(true);
			btnTamTinh.setEnabled(true);
			btnThem.setEnabled(false);
			btnGiaHan.setEnabled(false);
			txtMaHoaDon.setEditable(true);

		}));
		btnTKKh.addActionListener(e -> {
			String cmnd = txtKhachHang.getText();
			try {
				khOld = khachHangFacade.findByCMND(cmnd);
				if (khOld != null) {
					JOptionPane.showMessageDialog(null, "Tên khách hàng: " + khOld.getTenKhachHang() + "\n Số CMND: "
							+ khOld.getCmnd() + "\n Số điện thoại: " + khOld.getSodienthoai());
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy, thêm mới khách hàng để tiếp tục ");
					KhachHang khachHang = new KhachHang();
					khachHang.setVisible(true);

				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnGiaHan.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "Chức năng đang phát triển");
		});
		btnTamTinh.addActionListener((e) -> {
			Date from = new Date(ngayden.getYear(), ngayden.getMonth(), ngayden.getDay());
			Date to = new Date(ngaydi.getYear(), ngaydi.getMonth(), ngaydi.getDay());
			long time = to.getTime() - from.getTime();
			double tamtinh = 0;
			Object rs = cb.getSelectedItem();
			for (PhongEntity pe : phong) {
				if (rs.equals(pe.getTenPhong())) {
					tamtinh = Math.ceil(pe.getGiaPhong() * time / 84000000);
				}
			}
			txtGia.setText(String.valueOf(tamtinh));

		});
		btnLuu.addActionListener((e) -> {
			if (khOld == null) {
				JOptionPane.showMessageDialog(null, "Chưa chọn thông tin khách hàng");
				return;
			}
			HoaDonEntity hd = new HoaDonEntity();

			Date from = new Date(ngayden.getYear() - 1900, ngayden.getMonth(), ngayden.getDay());
			Date to = new Date(ngaydi.getYear() - 1900, ngaydi.getMonth(), ngaydi.getDay());
			long time = to.getTime() - from.getTime();
			double tamtinh = 0;
			Object rs = cb.getSelectedItem();
			for (PhongEntity pe : phong) {
				if (rs.equals(pe.getTenPhong())) {
					tamtinh = Math.ceil(pe.getGiaPhong() * time / 84000000);
					hd.setPhong(pe);
					try {
						phongFacade.capNhatTinhTrang(pe.getMaPhong(), "DA THUE");
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			txtGia.setText(String.valueOf(tamtinh));

			hd.setMaHoaDon(txtMaHoaDon.getText());
			hd.setNgayDen(from);
			hd.setNgayDi(to);
			hd.setNgayLap(new Date());
			hd.setSoLuong(1);
			hd.setThanhToan(tamtinh);
			hd.setTrangThai(false);

			try {
				hd.setKhachHang(khOld);
				Main main = new Main();
				hd.setNhanVien(main.nv);

				hoadonFacade.themHoaDon(hd);
				String row[] = { hd.getMaHoaDon(), hd.getNgayLap().toString(), hd.getNgayDen().toString(),
						hd.getNgayDi().toString(), String.valueOf(hd.getThanhToan()), "Chưa thanh toán" };
				dataModel.addRow(row);
				JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btnHuy.addActionListener(e -> {
			btnThem.setEnabled(true);
			btnGiaHan.setEnabled(true);
			btnHuy.setEnabled(false);
			btnLuu.setEnabled(false);
			btnTamTinh.setEnabled(false);
		});
		btnTimKiem.addActionListener(this);

		btnThemDv.addActionListener(e -> {
			HoaDonEntity hd;
			try {
				hd = hoadonFacade.timHoaDon(txtMaHoaDon.getText());
				int id = cthdFacade.getSizeCTHD();
				int iddv = cbDichVu.getSelectedIndex() + 1;
				CT_HoaDonEntity cthd = new CT_HoaDonEntity();
				DichVuEntity dv1 = new DichVuEntity();
				dv1.setMaDichVu(String.valueOf(iddv));
				cthd.setMaCTHD(String.valueOf(id + 1));
				cthd.setDichvu(dv1);
				cthd.setHoadon(hd);
				cthd.setSoluong(Integer.parseInt(txtSoLuongDV.getText()));
				cthdFacade.themCTHD(cthd);
				txtSoLuongDV.setText("");
				JOptionPane.showMessageDialog(null, "Thêm dịch vụ thành công");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		String mahd = table.getValueAt(row, 0).toString();
		CTHD cthd;
		try {
			HoaDonEntity hd = hoadonFacade.timHoaDon(mahd);
			if (!hd.isTrangThai()) {
				btnThemDv.setEnabled(true);
			}
			double tamtinh = hoadonFacade.tinhTongTien(mahd);
			cthd = new CTHD(hd.getMaHoaDon());
			cthd.setVisible(true);
			txtMaHoaDon.setText(hd.getMaHoaDon());
			cb.setSelectedIndex(Integer.parseInt(hd.getMaHoaDon()));
			txtGia.setText(String.valueOf(tamtinh));

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

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("serial")
	public class DateLabelFormatter extends AbstractFormatter {

		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		HoaDon fm = new HoaDon();
		fm.setVisible(true);
	}

}