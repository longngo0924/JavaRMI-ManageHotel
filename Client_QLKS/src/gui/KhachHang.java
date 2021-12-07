package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.KhachHangFacade;
import entity.KhachHangEntity;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;

public class KhachHang extends JFrame implements MouseListener, ActionListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	private static KhachHangFacade khachHangFacade;

	private static List<KhachHangEntity> lstKhachHang;

	private static KhachHang gui;

	private JLabel lblMaKhachHang, lblTenKhachHang, lblSoDienThoai, lblDiaChi, lblGioiTinh, lblCmnd, lblTimKiemTen,
			lblTimKiemDC, lblTimKiemSDT;
	private JTextField txtMaKhachHang, txtTenKhachHang, txtSoDienThoai, txtDiaChi, txtCmnd, txtTimKiemTen, txtTimKiemDC,
			txtTimKiemSDT;

	private JButton btnThem, btnSua, btnLuu, btnXoaRong, btnTimKiem, btnThoat;

	private JPanel pnlNorth, pnlCenter;
	private JRadioButton radNam, radNu;

	public JComboBox<String> cboMoTa3, cboMoTa4;

	private DefaultTableModel dataModel;
	private JTable table;

	public String maLoai, maNCC;
	public int hanhdong;

//	private DanhSachKhachHang dskh=new DanhSachKhachHang();

	private JLabel lblEmail;

	private JTextField txtEmail;

	private String typeButtonSelected;

	public KhachHang() {
		setTitle("Quản lý khách hàng");
		setSize(1200, 650);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		maLoai = null;
		maNCC = null;
		hanhdong = 0;

		buildUI();

	}

	private void buildUI() {
		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager == null) {
			System.getProperty("java.security.policy", "rmi/policy.policy");
		}
		try {
			khachHangFacade = (KhachHangFacade) Naming.lookup("rmi://LONG-NGO:8080/khachHangFacade");
		} catch (MalformedURLException | RemoteException | NotBoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		LoadData();

		getContentPane().setBackground(Color.WHITE);
		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		pnlNorth.setPreferredSize(new Dimension(0, 180));
		pnlNorth.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		pnlNorth.setLayout(null); // Absolute layout
		pnlNorth.setBackground(Color.WHITE);

		pnlNorth.add(lblMaKhachHang = new JLabel("Mã khách hàng: "));
		pnlNorth.add(lblTenKhachHang = new JLabel("Tên khách hàng: "));
		pnlNorth.add(lblSoDienThoai = new JLabel("Số điện thoại: "));
		pnlNorth.add(lblDiaChi = new JLabel("Địa chỉ: "));
		pnlNorth.add(lblCmnd = new JLabel("CMND: "));
		pnlNorth.add(lblGioiTinh = new JLabel("Giới tính: "));
		pnlNorth.add(lblEmail = new JLabel("Email: "));

		pnlNorth.add(txtMaKhachHang = new JTextField());
		pnlNorth.add(txtTenKhachHang = new JTextField());
		pnlNorth.add(txtSoDienThoai = new JTextField());
		pnlNorth.add(txtDiaChi = new JTextField());
		pnlNorth.add(txtCmnd = new JTextField());
		pnlNorth.add(txtEmail = new JTextField());
		radNam = new JRadioButton("Nam");
		radNam.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		radNu = new JRadioButton("Nữ");
		radNu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		radNam.setPreferredSize(new Dimension(90, 30));
		radNu.setPreferredSize(new Dimension(90, 30));
		ButtonGroup bg = new ButtonGroup();
		bg.add(radNam);
		bg.add(radNu);
		pnlNorth.add(radNam);
		pnlNorth.add(radNu);

		pnlNorth.add(lblTimKiemTen = new JLabel("Theo tên: "));
		pnlNorth.add(lblTimKiemDC = new JLabel("Theo Mã KH: "));
		pnlNorth.add(lblTimKiemSDT = new JLabel("Theo SĐT: "));

		pnlNorth.add(txtTimKiemTen = new JTextField());
		pnlNorth.add(txtTimKiemDC = new JTextField());
		pnlNorth.add(txtTimKiemSDT = new JTextField());
		pnlNorth.add(btnTimKiem = new JButton("Tìm kiếm"));

		int w1 = 200, w2 = 300, h = 20;
		lblMaKhachHang.setBounds(20, 20, w1, h);
		lblMaKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtMaKhachHang.setBounds(120, 20, w2, h);
		txtMaKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtMaKhachHang.setBackground(Color.WHITE);

		lblTenKhachHang.setBounds(20, 45, w1, h);
		lblTenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTenKhachHang.setBounds(120, 45, w2, h);
		txtTenKhachHang.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblSoDienThoai.setBounds(500, 45, w1, h);
		lblSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtSoDienThoai.setBounds(580, 45, 130, h);
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblEmail.setBounds(500, 20, w1, h);
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtEmail.setBounds(580, 20, w1, h);
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblDiaChi.setBounds(20, 70, w1, h);
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtDiaChi.setBounds(120, 70, w2, h);
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCmnd.setBounds(500, 70, w1, h);
		lblCmnd.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtCmnd.setBounds(580, 70, 130, h);
		txtCmnd.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblGioiTinh.setBounds(20, 95, w1, h);
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		radNam.setBounds(115, 95, 70, h);
		radNam.setBackground(Color.white);
		radNu.setBounds(200, 95, 70, h);
		radNu.setBackground(Color.white);

		lblTimKiemTen.setBounds(350, 140, w1, h);
		lblTimKiemTen.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTimKiemTen.setBounds(430, 140, 200, h);
		txtTimKiemTen.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblTimKiemDC.setBounds(20, 140, w1, h);
		lblTimKiemDC.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTimKiemDC.setBounds(95, 140, 200, h);
		txtTimKiemDC.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblTimKiemSDT.setBounds(680, 140, w1, h);
		lblTimKiemSDT.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTimKiemSDT.setBounds(750, 140, 200, h);
		txtTimKiemSDT.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		btnTimKiem.setBounds(1040, 140, 100, h);
		btnTimKiem.setPreferredSize(new Dimension(90, 35));
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		// Center
		add(pnlCenter = new JPanel(), BorderLayout.CENTER);
		pnlCenter.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		// pnlCenter.setLayout(null);
		pnlCenter.setPreferredSize(new Dimension(0, 180));
		pnlCenter.setBackground(Color.white);

		pnlCenter.add(btnThem = new JButton("Thêm"));
		btnThem.setPreferredSize(new Dimension(90, 35));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		pnlCenter.add(btnSua = new JButton("Sửa"));
		btnSua.setPreferredSize(new Dimension(90, 35));
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		pnlCenter.add(btnXoaRong = new JButton("Xóa rỗng"));
		btnXoaRong.setPreferredSize(new Dimension(90, 35));
		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		pnlCenter.add(btnLuu = new JButton("Lưu"));
		btnLuu.setPreferredSize(new Dimension(90, 35));
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		pnlCenter.add(btnThoat = new JButton("Hủy"));
		btnThoat.setPreferredSize(new Dimension(90, 35));
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		// South
		String[] headers = { "Mã khách hàng", "Tên khách hàng", "Giới tính", "Số điện thoại", "CMND", "Địa chỉ" };
		dataModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll;
		add(scroll = new JScrollPane(table = new JTable(dataModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
		table.setRowHeight(20);
		scroll.setPreferredSize(new Dimension(0, 350));
		table.setBackground(Color.WHITE);

		// table.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		table.getTableHeader().setBackground(Color.WHITE);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.setBackground(Color.white);

		TableColumn column = null;
		column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(10);
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(100);
		column = table.getColumnModel().getColumn(2);
		column.setPreferredWidth(10);
		column = table.getColumnModel().getColumn(3);
		column.setPreferredWidth(10);
		column = table.getColumnModel().getColumn(4);
		column.setPreferredWidth(10);
		column = table.getColumnModel().getColumn(5);
		column.setPreferredWidth(300);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		table.getTableHeader().setReorderingAllowed(false);
//		table.getTableHeader().setResizingAllowed(false);
		Border border = BorderFactory.createLineBorder(Color.black);
		table.setBorder(border);
		table.getTableHeader().setBorder(border);
		loadTableData();

		txtMaKhachHang.setEditable(false);
		EnableTextField(false);

		btnLuu.setEnabled(false);
		btnThoat.setEnabled(false);
		btnXoaRong.setEnabled(false);
		btnSua.setEnabled(false);

		typeButtonSelected = "";

		btnThem.addActionListener((e) -> {
			EmptyTextField();
			EnableTextField(true);
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoaRong.setEnabled(true);
			btnLuu.setEnabled(true);
			btnThoat.setEnabled(true);

			typeButtonSelected = "add";
		});

		btnSua.addActionListener((e) -> {
			EnableTextField(true);
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			btnXoaRong.setEnabled(true);
			btnLuu.setEnabled(true);
			btnThoat.setEnabled(true);

			typeButtonSelected = "modify";
		});

		btnLuu.addActionListener((e) -> {

			if (typeButtonSelected.equals("add")) {
				boolean rs = CheckData();
				KhachHangEntity newCustomer = new KhachHangEntity();
				if (rs) {
					int lastIndex = lstKhachHang.size() + 1;
					newCustomer.setMaKhachHang("KH" + lastIndex);
					newCustomer.setTenKhachHang(txtTenKhachHang.getText());
					newCustomer.setCmnd(txtCmnd.getText());
					newCustomer.setDiaChi(txtDiaChi.getText());
					newCustomer.setSodienthoai(txtSoDienThoai.getText());
					newCustomer.setEmail(txtEmail.getText());
					if (radNam.isSelected()) {
						newCustomer.setGioiTinh("Nam");
					} else if (radNu.isSelected()) {
						newCustomer.setGioiTinh("Nu");
					}
					try {
						if (khachHangFacade.themKhachHang(newCustomer)) {
							LoadData();
							loadTableData();
							String row[] = { newCustomer.getMaKhachHang(), newCustomer.getTenKhachHang(),
									newCustomer.getGioiTinh(), newCustomer.getSodienthoai(), newCustomer.getEmail(),
									newCustomer.getDiaChi() };
							dataModel.addRow(row);
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(getContentPane(), "Lỗi kết nối server, vui lòng kiểm tra lại");
					}
					EmptyTextField();
					EnableTextField(false);
					btnThem.setEnabled(true);
					btnXoaRong.setEnabled(false);
					btnLuu.setEnabled(false);
					btnThoat.setEnabled(false);
					typeButtonSelected = "";
					return;
				}
				;
			}

			if (typeButtonSelected.equals("modify")) {
				KhachHangEntity Customer = new KhachHangEntity();
				if (CheckData()) {
					Customer.setMaKhachHang(txtMaKhachHang.getText());
					Customer.setTenKhachHang(txtTenKhachHang.getText());
					Customer.setDiaChi(txtDiaChi.getText());
					Customer.setEmail(txtEmail.getText());
					Customer.setCmnd(txtCmnd.getText());
					Customer.setSodienthoai(txtSoDienThoai.getText());

					if (radNam.isSelected())
						Customer.setGioiTinh("Nam");
					else
						Customer.setGioiTinh("Nữ");

					try {
						if (khachHangFacade.capNhatKhachHang(Customer)) {
							JOptionPane.showMessageDialog(getContentPane(), "Cập nhật thành công !");
							LoadData();
							loadTableData();
						}

					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(getContentPane(), "Lỗi kết nối server, vui lòng kiểm tra lại");
					}

					EmptyTextField();
					EnableTextField(false);
					btnThem.setEnabled(true);
					btnXoaRong.setEnabled(false);
					btnLuu.setEnabled(false);
					btnThoat.setEnabled(false);
					typeButtonSelected = "";
				}

			}
			loadTableData();

		});
		btnThoat.addActionListener((e) -> {
			EmptyTextField();
			EnableTextField(false);
			btnThem.setEnabled(true);
			btnXoaRong.setEnabled(false);
			btnLuu.setEnabled(false);
			btnThoat.setEnabled(false);
			typeButtonSelected = "";
		});
		btnXoaRong.addActionListener((e) -> {
			EmptyTextField();
		});
		btnTimKiem.addActionListener((e) -> {
			if (!txtTimKiemDC.getText().equals("")) {
				KhachHangEntity khachHangEntity = new KhachHangEntity();
				try {
					khachHangEntity = khachHangFacade.findById(txtTimKiemDC.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (khachHangEntity != null) {
					dataModel.setRowCount(0);
					dataModel.addRow(new Object[] { khachHangEntity.getMaKhachHang(), khachHangEntity.getTenKhachHang(),
							khachHangEntity.getGioiTinh(), khachHangEntity.getSodienthoai(), khachHangEntity.getCmnd(),
							khachHangEntity.getDiaChi() });
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Không tìm thấy");
					loadTableData();
				}
				return;
			}
			if (!txtTimKiemTen.getText().equals("")) {
				List<KhachHangEntity> lstTmp = new ArrayList<KhachHangEntity>();
				try {
					lstTmp = khachHangFacade.findByName(txtTimKiemTen.getText());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				if (lstTmp.size() != 0) {
					dataModel.setRowCount(0);
					for (KhachHangEntity khachHangEntity : lstTmp) {
						dataModel.addRow(
								new Object[] { khachHangEntity.getMaKhachHang(), khachHangEntity.getTenKhachHang(),
										khachHangEntity.getGioiTinh(), khachHangEntity.getSodienthoai(),
										khachHangEntity.getCmnd(), khachHangEntity.getDiaChi() });
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Không tìm thấy");
					loadTableData();
				}
				return;
			}
			if (!txtTimKiemSDT.getText().equals("")) {
				KhachHangEntity khachHangEntity = new KhachHangEntity();
				try {
					khachHangEntity = khachHangFacade.findByPhone(txtTimKiemSDT.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (khachHangEntity != null) {
					dataModel.setRowCount(0);
					dataModel.addRow(new Object[] { khachHangEntity.getMaKhachHang(), khachHangEntity.getTenKhachHang(),
							khachHangEntity.getGioiTinh(), khachHangEntity.getSodienthoai(), khachHangEntity.getCmnd(),
							khachHangEntity.getDiaChi() });
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Không tìm thấy");
					loadTableData();
				}
				return;
			}
		});
	}

	private void EmptyTextField() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtDiaChi.setText("");
		txtEmail.setText("");
		txtSoDienThoai.setText("");
		txtCmnd.setText("");
	}

	private void EnableTextField(boolean tmp) {
		txtTenKhachHang.setEditable(tmp);
		txtDiaChi.setEditable(tmp);
		txtEmail.setEditable(tmp);
		txtSoDienThoai.setEditable(tmp);
		txtCmnd.setEditable(tmp);
		radNam.setEnabled(tmp);
		radNu.setEnabled(tmp);
	}

	private boolean CheckData() {

		if (txtTenKhachHang.getText().equals("")) {
			JOptionPane.showMessageDialog(getContentPane(), "Vui lòng nhập tên khách hàng");
			return false;
		}
		if (txtDiaChi.getText().equals("")) {
			JOptionPane.showMessageDialog(getContentPane(), "Vui lòng nhập địa chỉ khách hàng");
			return false;
		}
		if (radNam.isSelected() == false && radNu.isSelected() == false) {
			JOptionPane.showMessageDialog(getContentPane(), "Vui lòng chọn giới tính khách hàng");
			return false;
		}
		if (txtEmail.getText().equals("")) {
			JOptionPane.showMessageDialog(getContentPane(), "Vui lòng nhập E-mail khách hàng");
			return false;
		}
		if (txtSoDienThoai.getText().equals("")) {
			JOptionPane.showMessageDialog(getContentPane(), "Vui lòng nhập số diện thoại khách hàng");
			return false;
		}
		if (txtCmnd.getText().equals("")) {
			JOptionPane.showMessageDialog(getContentPane(), "Vui lòng nhập số CMND khách hàng");
			return false;
		}

		return true;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public void editTextField() {
		txtTenKhachHang.setEditable(true);
		txtSoDienThoai.setEditable(true);
		txtDiaChi.setEditable(true);

	}

	public void falseEditTextField() {
		txtTenKhachHang.setEditable(false);
		txtSoDienThoai.setEditable(false);
		txtDiaChi.setEditable(false);

	}

	public void xoaRongTextField() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtSoDienThoai.setText("");
		txtDiaChi.setText("");

	}

	private void loadTableData() {
		dataModel.removeTableModelListener(table);
		dataModel.setRowCount(0);
		for (KhachHangEntity khachHangEntity : lstKhachHang) {
			dataModel.addRow(new Object[] { khachHangEntity.getMaKhachHang(), khachHangEntity.getTenKhachHang(),
					khachHangEntity.getGioiTinh(), khachHangEntity.getSodienthoai(), khachHangEntity.getCmnd(),
					khachHangEntity.getDiaChi() });
		}
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				KhachHangEntity khachHangEntity = new KhachHangEntity();
				try {
					khachHangEntity = khachHangFacade.findById(table.getValueAt(table.getSelectedRow(), 0).toString());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (khachHangEntity != null) {
					txtMaKhachHang.setText(khachHangEntity.getMaKhachHang());
					txtTenKhachHang.setText(khachHangEntity.getTenKhachHang());
					txtDiaChi.setText(khachHangEntity.getDiaChi());
					txtEmail.setText(khachHangEntity.getEmail());
					txtSoDienThoai.setText(khachHangEntity.getSodienthoai());
					txtCmnd.setText(khachHangEntity.getCmnd());

					if (khachHangEntity.getGioiTinh().equals("Nam"))
						radNam.setSelected(true);
					else
						radNu.setSelected(true);
					;
					btnSua.setEnabled(true);
				}

			}
		});
		;
	}

	private static void LoadData() {
		try {
			lstKhachHang = khachHangFacade.getAll();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(gui.getContentPane(), "Lỗi kết nối server, vui lòng kiểm tra lại");
		}
	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		gui = new KhachHang();

		gui.setVisible(true);
	}
}
