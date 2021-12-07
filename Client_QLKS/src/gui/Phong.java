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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.PhongFacade;
import entity.PhongEntity;

public class Phong extends JFrame implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6430973327355681499L;
	private static PhongFacade phongFacade;
	private static List<PhongEntity> phong;
	private int hanhdong;
	private JRadioButton radCon, radHet;
	private JLabel lblMaPhong, lblTenPhong, lblLoaiPhong, lblGiaPhong, lblTinhTrang;
	private JTextField txtMaPhong, txtTenPhong, txtLoaiPhong, txtGia;
	private JTextField txtTimKiemGiaPhong, txtTimKiemLoaiPhong, txtTimKiemTinhTrang;
	private JButton btnThem, btnSua, btnLuu, btnThoat, btnXoaRong;
	private JLabel lblTimKiemTenPhong, lblTimKiemLoaiPhong, lblTimKiemTinhTrang;
	private JButton btnTimKiem;
	private DefaultTableModel dataModel;
	private JTable table;

	private JPanel pnlNorth, pnlCenter;
	private int row;
	private JTextField txtTinhTrang;

	public Phong() throws RemoteException, MalformedURLException, NotBoundException {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Quản lý phòng");
		setSize(1200, 650);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		hanhdong = 0;
		buildUI();

	}

	private void buildUI() throws RemoteException, MalformedURLException, NotBoundException {
		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager == null) {
			System.getProperty("java.security.policy", "rmi/policy.policy");
		}
		phongFacade = (PhongFacade) Naming.lookup("rmi://LONG-NGO:8080/phongFacade");
		phong = phongFacade.getAll();

		getContentPane().setBackground(Color.WHITE);
		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		pnlNorth.setPreferredSize(new Dimension(0, 180));
		pnlNorth.setBorder(BorderFactory.createTitledBorder("Thông tin phòng"));
		pnlNorth.setLayout(null); // Absolute layout
		pnlNorth.setBackground(Color.WHITE);

		pnlNorth.add(lblMaPhong = new JLabel("Mã phòng: "));
		pnlNorth.add(lblTenPhong = new JLabel("Tên phòng: "));
		pnlNorth.add(lblLoaiPhong = new JLabel("Loại phòng: "));
		pnlNorth.add(lblGiaPhong = new JLabel("Giá phòng: "));
		pnlNorth.add(lblTinhTrang = new JLabel("Tình trạng: "));

		pnlNorth.add(txtMaPhong = new JTextField());
		pnlNorth.add(txtTenPhong = new JTextField());
		pnlNorth.add(txtLoaiPhong = new JTextField());
		pnlNorth.add(txtGia = new JTextField());
		pnlNorth.add(txtTinhTrang = new JTextField());

		radCon = new JRadioButton("Còn phòng");
		radCon.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		radHet = new JRadioButton("Hết phòng");
		radHet.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		radCon.setPreferredSize(new Dimension(90, 30));
		radHet.setPreferredSize(new Dimension(90, 30));
		ButtonGroup bg = new ButtonGroup();
//		bg.add(radCon);
//		bg.add(radHet);
//		pnlNorth.add(radCon);
//		pnlNorth.add(radHet);

		pnlNorth.add(lblTimKiemTenPhong = new JLabel("Theo giá phòng: "));
		pnlNorth.add(lblTimKiemLoaiPhong = new JLabel("Theo loại phòng: "));
		pnlNorth.add(lblTimKiemTinhTrang = new JLabel("Theo tình trạng: "));

		pnlNorth.add(txtTimKiemGiaPhong = new JTextField());
		pnlNorth.add(txtTimKiemLoaiPhong = new JTextField());
		pnlNorth.add(txtTimKiemTinhTrang = new JTextField());
		pnlNorth.add(btnTimKiem = new JButton("Tìm kiếm"));

		int w1 = 200, w2 = 300, h = 20;
		lblMaPhong.setBounds(20, 20, w1, h);
		lblMaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtMaPhong.setBounds(120, 20, w2, h);
		txtMaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtMaPhong.setBackground(Color.WHITE);

		lblTenPhong.setBounds(20, 45, w1, h);
		lblTenPhong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTenPhong.setBounds(120, 45, w2, h);
		txtTenPhong.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblLoaiPhong.setBounds(500, 45, w1, h);
		lblLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtLoaiPhong.setBounds(580, 45, 200, h);
		txtLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblGiaPhong.setBounds(500, 20, w1, h);
		lblGiaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtGia.setBounds(580, 20, 200, h);
		txtGia.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblTinhTrang.setBounds(20, 70, w1, h);
		lblTinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		radCon.setBounds(115, 70, 100, h);
		radCon.setBackground(Color.white);
		radHet.setBounds(215, 70, 100, h);
		radHet.setBackground(Color.white);

		lblTimKiemTenPhong.setBounds(20, 110, w1, h);
		lblTimKiemTenPhong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTimKiemGiaPhong.setBounds(120, 110, 150, h);
		txtTimKiemGiaPhong.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblTimKiemLoaiPhong.setBounds(350, 110, w1, h);
		lblTimKiemLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTimKiemLoaiPhong.setBounds(470, 110, 150, h);
		txtTimKiemLoaiPhong.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblTimKiemTinhTrang.setBounds(680, 110, w1, h);
		lblTimKiemTinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTimKiemTinhTrang.setBounds(790, 110, 150, h);
		txtTimKiemTinhTrang.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		btnTimKiem.setBounds(1040, 110, 100, h);
		btnTimKiem.setPreferredSize(new Dimension(90, 35));
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		// Center
		add(pnlCenter = new JPanel(), BorderLayout.CENTER);
		pnlCenter.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		// pnlCenter.setLayout(null);
		pnlCenter.setPreferredSize(new Dimension(0, 180));
		pnlCenter.setBackground(Color.white);

		pnlCenter.add(btnThem = new JButton("Thêm phòng"));
		btnThem.setPreferredSize(new Dimension(120, 35));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		pnlCenter.add(btnXoaRong = new JButton("Xóa rỗng"));
		btnXoaRong.setPreferredSize(new Dimension(90, 35));
		btnXoaRong.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		pnlCenter.add(btnSua = new JButton("Sửa thông tin"));
		btnSua.setPreferredSize(new Dimension(120, 35));
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		pnlCenter.add(btnLuu = new JButton("Lưu"));
		btnLuu.setPreferredSize(new Dimension(90, 35));
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		pnlCenter.add(btnThoat = new JButton("Thoát"));
		btnThoat.setPreferredSize(new Dimension(90, 35));
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		// South
		String[] headers = { "Mã phòng", "Tên phòng", "Loại phòng", "Giá phòng", "Tình Trạng" };
		dataModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll;
		add(scroll = new JScrollPane(table = new JTable(dataModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách phòng"));
		table.setRowHeight(20);
		scroll.setPreferredSize(new Dimension(0, 350));
		table.setBackground(Color.WHITE);

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
		Border border = BorderFactory.createLineBorder(Color.black);
		table.setBorder(border);
		table.getTableHeader().setBorder(border);
		for (PhongEntity p : phong) {
			dataModel.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong(), p.getGiaPhong(),
					p.getTinhTrang() });

		}
		table.addMouseListener(this);

		changeStatusTextfield(false);

		btnThem.addActionListener(e -> {
			hanhdong = 1;
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			changeStatusTextfield(true);

		});

		btnLuu.addActionListener(e -> {

			if (hanhdong == 1) {
				PhongEntity phong = new PhongEntity();
				phong.setMaPhong(txtMaPhong.getText());
				phong.setGiaPhong(Double.valueOf(txtGia.getText()));
				phong.setTenPhong(txtTenPhong.getText());
				phong.setLoaiPhong(txtLoaiPhong.getText());
				phong.setTinhTrang("TRONG");

				try {
					phongFacade.themPhong(phong);
					String row[] = { phong.getMaPhong(), phong.getTenPhong(), phong.getLoaiPhong(),
							String.valueOf(phong.getGiaPhong()), phong.getTinhTrang() };
					dataModel.addRow(row);
					changeStatusTextfield(false);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				if (hanhdong == 2) {
					PhongEntity phong = new PhongEntity();
					phong.setMaPhong(txtMaPhong.getText());
					phong.setGiaPhong(Double.valueOf(txtGia.getText()));
					phong.setTenPhong(txtTenPhong.getText());
					phong.setLoaiPhong(txtLoaiPhong.getText());
					try {
						PhongEntity p = phongFacade.findById(phong.getMaPhong());
						phong.setTinhTrang(p.getTinhTrang());
						boolean rs = phongFacade.capNhatPhong(phong);
						if (rs) {
							row = table.getSelectedRow();
							table.setValueAt(txtMaPhong.getText(), row, 0);
							table.setValueAt(txtTenPhong.getText(), row, 1);
							table.setValueAt(txtLoaiPhong.getText(), row, 2);
							table.setValueAt(String.valueOf(phong.getGiaPhong()), row, 3);
							table.setValueAt(phong.getTinhTrang(), row, 4);

						}
						table.clearSelection();

						changeStatusTextfield(false);

					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			btnThem.setEnabled(true);
			btnSua.setEnabled(true);

		});

		btnXoaRong.addActionListener(e -> {
			resetTextfield();
		});

		btnThoat.addActionListener(e -> {
			hanhdong = 0;
			btnThem.setEnabled(true);
			btnSua.setEnabled(true);
			changeStatusTextfield(false);
			table.clearSelection();
			dataModel.setRowCount(0);
			for (PhongEntity p : phong) {
				dataModel.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong(), p.getGiaPhong(),
						p.getTinhTrang() });

			}

		});
		btnSua.addActionListener(e -> {
			hanhdong = 2;
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			changeStatusTextfield(true);
		});

		btnTimKiem.addActionListener(e -> {
			try {
				List<PhongEntity> phongs = phongFacade.timPhong(txtTimKiemLoaiPhong.getText(),
						Double.valueOf(txtTimKiemGiaPhong.getText()), txtTimKiemTinhTrang.getText());

				dataModel.setRowCount(0);
				for (PhongEntity p : phongs) {
					dataModel.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getLoaiPhong(), p.getGiaPhong(),
							p.getTinhTrang() });

				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		row = table.getSelectedRow();
		txtMaPhong.setText(table.getValueAt(row, 0).toString());
		txtTenPhong.setText(table.getValueAt(row, 1).toString());
		txtLoaiPhong.setText(table.getValueAt(row, 2).toString());
		txtGia.setText(table.getValueAt(row, 3).toString());

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

	public void changeStatusTextfield(boolean status) {
		if (!status) {
			resetTextfield();
		}
		txtMaPhong.setEditable(status);
		txtLoaiPhong.setEditable(status);
		txtGia.setEditable(status);
		txtTenPhong.setEditable(status);
	}

	public void resetTextfield() {
		txtMaPhong.setText("");
		txtLoaiPhong.setText("");
		txtGia.setText("");
		txtTenPhong.setText("");
	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		Phong phong = new Phong();
		phong.setVisible(true);
	}
}
