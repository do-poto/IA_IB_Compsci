import java.sql.*;

import javax.swing.JOptionPane;

public class DataManager {
	public static final String DB_URL = "jdbc:sqlite:medsdata.db";
	private static final String ADD_QUERY = "SELECT MedName, MedTime FROM meds";
	private static final String COMBO_QUERY = "SELECT MedName FROM meds ORDER BY MedId";
	private static final String DELETE_QUERY = "SELECT MedName, MedTime FROM meds";
	public static String Message; 
	private static String MedName, MedTime, MYSQLString;
	
	// Add new data to the db
	public static void AddData() {
		try (
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(ADD_QUERY);) {
			MedName = AddPanel.AddName.getText();
			MedTime = AddPanel.AddTime.getText();
			MYSQLString = "INSERT INTO meds(MedName, MedTime) VALUES('" + MedName + "','" + MedTime + "')";
			stmt.executeUpdate(MYSQLString);
			JOptionPane.showMessageDialog(AddPanel.AddPanelWindow, "Succesfully added: " + MedName);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Delete data from db
	public static void DeleteData() {
		try (
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(DELETE_QUERY);) {
			MedName = (String) DeletePanel.ChooseName.getSelectedItem();
			MYSQLString = "DELETE FROM meds WHERE MedName= '" + MedName + "'";
			stmt.executeUpdate(MYSQLString);
			JOptionPane.showMessageDialog(DeletePanel.DeletePanelWindow, "Succesfully deleted: " + MedName);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	// Displaying data in the combobox of DeletePanel
	public static void ComboBoxDisplay() {
		try {
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(COMBO_QUERY);
			while (rs.next()) {
				String name = rs.getString("MedName");
				DeletePanel.ChooseName.addItem(name);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
