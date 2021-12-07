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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.DichVuFacade;
import entity.DichVuEntity;

public class DichVu extends JFrame implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4441468256750994203L;
	private JButton btnThem, btnSua, btnLuu, btnXoaRong, btnThoat;
	private JButton btnTimKiem;
	private DefaultTableModel dataModel;
	private JTable table;

	private JPanel pnlNorth, pnlCenter;
	private JLabel lblMaDichVu, lblTenDichVu, lblGiaDichVu;
	private JTextField txtMaDichVu, txtTenDichVu, txtGiaDichVu;
	private JLabel lblTimKiemMaDV, lblTimKiemTenDV, lblTimKiemGiaDV;
	private JTextField txtTimKiemMaDV, txtTimKiemTenDV, txtTimKiemGiaDV;
	private DichVuFacade dichVuFacade;
	private List<DichVuEntity> dichvu;
	private int hanhdong = 0;
	private int row;

	public DichVu() throws MalformedURLException, RemoteException, NotBoundException {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Quản lí dịch vụ");
		setSize(1200, 650);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		buildUI();
	}

	private void buildUI() throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager == null) {
			System.getProperty("java.security.policy", "rmi/policy.policy");
		}
		dichVuFacade = (DichVuFacade) Naming.lookup("rmi://LONG-NGO:8080/dichVuFacade");
		dichvu = dichVuFacade.getAll();

		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		pnlNorth.setPreferredSize(new Dimension(0, 180));
		pnlNorth.setBorder(BorderFactory.createTitledBorder("Thông tin dịch vụ"));
		pnlNorth.setLayout(null); // Absolute layout
		pnlNorth.setBackground(Color.WHITE);

		pnlNorth.add(lblMaDichVu = new JLabel("Mã dịch vụ: "));
		pnlNorth.add(lblTenDichVu = new JLabel("Tên dịch vụ: "));
		pnlNorth.add(lblGiaDichVu = new JLabel("Giá dịch vụ: "));

		pnlNorth.add(txtMaDichVu = new JTextField());
		pnlNorth.add(txtTenDichVu = new JTextField());
		pnlNorth.add(txtGiaDichVu = new JTextField());

		pnlNorth.add(lblTimKiemMaDV = new JLabel("Theo mã: "));
		pnlNorth.add(lblTimKiemTenDV = new JLabel("Theo địa chỉ: "));
		pnlNorth.add(lblGiaDichVu = new JLabel("Giá dịch vụ: "));

		pnlNorth.add(txtTimKiemMaDV = new JTextField());
		pnlNorth.add(txtTimKiemTenDV = new JTextField());
		pnlNorth.add(txtTimKiemGiaDV = new JTextField());
		pnlNorth.add(btnTimKiem = new JButton("Tìm kiếm"));

		pnlNorth.add(lblTimKiemMaDV = new JLabel("Theo mã : "));
		pnlNorth.add(lblTimKiemTenDV = new JLabel("Theo tên: "));
		pnlNorth.add(lblTimKiemGiaDV = new JLabel("Theo giá: "));

		int w1 = 200, w2 = 300, h = 20;
		lblMaDichVu.setBounds(20, 20, w1, h);
		lblMaDichVu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtMaDichVu.setBounds(120, 20, w2, h);
		txtMaDichVu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtMaDichVu.setBackground(Color.WHITE);

		lblTenDichVu.setBounds(20, 60, w1, h);
		lblTenDichVu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTenDichVu.setBounds(120, 60, w2, h);
		txtTenDichVu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblGiaDichVu.setBounds(500, 60, w1, h);
		lblGiaDichVu.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtGiaDichVu.setBounds(580, 60, 130, h);
		txtGiaDichVu.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblTimKiemMaDV.setBounds(20, 140, w1, h);
		lblTimKiemMaDV.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTimKiemMaDV.setBounds(80, 140, 200, h);
		txtTimKiemMaDV.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblTimKiemTenDV.setBounds(350, 140, w1, h);
		lblTimKiemTenDV.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTimKiemTenDV.setBounds(430, 140, 200, h);
		txtTimKiemTenDV.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		lblTimKiemGiaDV.setBounds(680, 140, w1, h);
		lblTimKiemGiaDV.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtTimKiemGiaDV.setBounds(750, 140, 200, h);
		txtTimKiemGiaDV.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		btnTimKiem.setBounds(1040, 140, 100, h);
		btnTimKiem.setPreferredSize(new Dimension(90, 35));
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		// Center
		add(pnlCenter = new JPanel(), BorderLayout.CENTER);
		pnlCenter.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		// pnlCenter.setLayout(null);
		pnlCenter.setPreferredSize(new Dimension(0, 180));
		pnlCenter.setBackground(Color.white);

		pnlCenter.add(btnThem = new JButton("Thêm dịch vụ"));
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
		String[] headers = { "Mã dịch vụ", "Tên dịch vụ", "Giá dịch vụ" };
		dataModel = new DefaultTableModel(headers, 0);
		JScrollPane scroll;
		add(scroll = new JScrollPane(table = new JTable(dataModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách các dịch vụ"));
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
		Border border = BorderFactory.createLineBorder(Color.black);
		table.setBorder(border);
		table.getTableHeader().setBorder(border);
		for (DichVuEntity d : dichvu) {
			dataModel.addRow(new Object[] { d.getMaDichVu(), d.getTenDichVu(), String.valueOf(d.getGiaDichVu()) });

		}
		table.addMouseListener(this);

		changeStatusTextfield(false);
		btnThem.addActionListener(e -> {
			changeStatusTextfield(true);
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
		});

		btnSua.addActionListener(e -> {
			hanhdong = 1;
			btnThem.setEnabled(false);
			btnSua.setEnabled(false);
			changeStatusTextfield(true);
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
			for (DichVuEntity d : dichvu) {
				dataModel.addRow(new Object[] { d.getMaDichVu(), d.getTenDichVu(), String.valueOf(d.getGiaDichVu()) });

			}

		});

		btnLuu.addActionListener(e -> {
			String ma = txtMaDichVu.getText();
			String ten = txtTenDichVu.getText();
			double gia = Double.valueOf(txtGiaDichVu.getText());
			if (hanhdong == 0) {
				try {
					boolean rs = dichVuFacade.themDichVu(new DichVuEntity(ma, ten, gia));
					if (rs) {
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						String row[] = { ma, ten, String.valueOf(gia) };
						dataModel.addRow(row);
						changeStatusTextfield(false);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					boolean rs = dichVuFacade.capNhatDichVu(new DichVuEntity(ma, ten, gia));
					if (rs) {
						JOptionPane.showMessageDialog(null, "Thêm thành công");
						row = table.getSelectedRow();
						table.setValueAt(ma, row, 0);
						table.setValueAt(ten, row, 1);
						table.setValueAt(String.valueOf(gia), row, 2);

					}
					table.clearSelection();
					changeStatusTextfield(false);

				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		txtMaDichVu.setText(table.getValueAt(row, 0).toString());
		txtTenDichVu.setText(table.getValueAt(row, 1).toString());
		txtGiaDichVu.setText(table.getValueAt(row, 2).toString());

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
		txtMaDichVu.setEditable(status);
		txtGiaDichVu.setEditable(status);
		txtTenDichVu.setEditable(status);
	}

	public void resetTextfield() {
		txtMaDichVu.setText("");
		txtGiaDichVu.setText("");
		txtTenDichVu.setText("");
	}

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		DichVu dichVu = new DichVu();
		dichVu.setVisible(true);
	}

}
