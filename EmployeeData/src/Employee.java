import java.awt.EventQueue;

import java.sql.*;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

public class Employee {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	DefaultTableModel model = new DefaultTableModel();
	
	
	/**
	 * Launch the application.
	 */
	public void updateTable() 
	{
		conn = EmployeeData.ConnectDB();
		if(conn !=null)
		{	
			String sql ="Select EmpID,NiNumber,Firstname,Surname,Gender,DOB,Age,Salary";
		
		try
		{
			pst = conn.prepareStatement(sql);	
			rs = pst.executeQuery();
			Object[] columnData = new Object[8];
			
			while(rs.next()) {
				
				columnData [0] = rs.getString("EmpID");
				columnData [1] = rs.getString("NiNumber");
				columnData [2] = rs.getString("Firstname");
				columnData [3] = rs.getString("Surname");
				columnData [4] = rs.getString("Gender");
				columnData [5] = rs.getString("DOB");
				columnData [6] = rs.getString("Age");
				columnData [7] = rs.getString("Salary");
				
				model.addRow(columnData);
				
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		}
	}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Employee() {
		initialize();
		
		conn = EmployeeData.ConnectDB();
		Object col [] = {"EmpID","NiNumber","Firstname","Surname","Gender","DOB","Age","Salary"};
		model.setColumnIdentifiers(col);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(76, 139, 167, 28);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btna = new JButton("Add New");
		btna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO employee(EmpID,NiNumber,Firstname,Surname,Gender,DOB,Age,Salary)VALUES(?,?,?,?,?,?,?,?)";
				try
				{
					pst = conn.prepareStatement(sql);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_3.getText());
					pst.setString(5, textField_4.getText());
					pst.setString(6, textField_5.getText());
					pst.setString(7, textField_6.getText());
					pst.setString(8, textField_7.getText());
					
					pst.execute();
					rs.close();
				}
				catch(Exception ev)
				{
					JOptionPane.showConfirmDialog(null,"System Update Completed");
				}
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.addRow(new Object[]{
					
					
					textField.getText(),
					textField_1.getText(),
					textField_2.getText(),
					textField_3.getText(),
					textField_4.getText(),
					textField_5.getText(),
					textField_6.getText(),
					textField_7.getText(),
					
				});
				if (table.getSelectedRow() == -1) {
					if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null,"Membership Update confirmed","Employee Database System",
								JOptionPane.OK_OPTION);
					}
				}
				
			}
		});
		btna.setBounds(610, 671, 224, 69);
		frame.getContentPane().add(btna);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setBounds(290, 138, 205, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(577, 129, 782, 467);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EmpID", "NiNumber", "Firstname", "Surname", "Gender", "DOB", "Age", "Salary"
			}
		));
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		
		JButton btnre = new JButton("Reset");
		btnre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_4.setText(null);
				textField_5.setText(null);
				textField_6.setText(null);
				textField_7.setText(null);
			}
		});
		btnre.setBounds(874, 671, 224, 69);
		frame.getContentPane().add(btnre);
		
		JButton btnexit = new JButton("Exit");
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame =new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame,"Confirm if you want to exit","Employee Database System",
							JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnexit.setBounds(1135, 671, 224, 69);
		frame.getContentPane().add(btnexit);
		
		JLabel lblNewLabel_1 = new JLabel("NiNumber");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(76, 195, 178, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Firstname");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(76, 245, 152, 28);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Surname");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(76, 299, 419, 28);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(76, 358, 419, 28);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("DOB");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(76, 414, 178, 28);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Age");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_6.setBounds(76, 476, 169, 28);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Salary");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_7.setBounds(76, 533, 146, 28);
		frame.getContentPane().add(lblNewLabel_7);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(290, 194, 205, 31);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(290, 244, 205, 31);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(290, 298, 205, 31);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_4.setColumns(10);
		textField_4.setBounds(290, 357, 205, 31);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_5.setColumns(10);
		textField_5.setBounds(290, 411, 205, 31);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_6.setColumns(10);
		textField_6.setBounds(290, 475, 205, 31);
		frame.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField_7.setColumns(10);
		textField_7.setBounds(290, 532, 205, 31);
		frame.getContentPane().add(textField_7);
		
		JLabel lblNewLabel_8 = new JLabel("Employee Database System");
		lblNewLabel_8.setFont(new Font("MS UI Gothic", Font.BOLD, 30));
		lblNewLabel_8.setBounds(525, 10, 454, 69);
		frame.getContentPane().add(lblNewLabel_8);
	}
}
