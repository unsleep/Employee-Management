
import java.sql.*;

import javax.swing.*;

public class EmployeeData {
	public static Connection ConnectDB()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection(":jdbc:sqlite:");
					JOptionPane.showMessageDialog(null,"Connection Made");
					return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showConfirmDialog(null,"Connection Made");
			return null;
		}
	}
}
