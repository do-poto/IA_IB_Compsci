import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class MainWindow extends Thread{
	public static JFrame Window;
	private JMenuBar MenuBar;
	private JMenu Menu, Help;
	private JMenuItem Add, Delete, Refresh, Open;
	private Container MainContainer;
	private JPanel MedsPanel;
	private GroupLayout groupLayout;
	private JButton ConfirmButton;
	private JCheckBox ConfirmBox;
	public static Alarm Alarm = new Alarm();
	
	public MainWindow() {
		// Adding frame
		Window = new JFrame("Main Window");
		
		// Font
		Font f1 = new Font(Font.SANS_SERIF , Font.PLAIN, 20);
		Font f2 = new Font(Font.SANS_SERIF , Font.PLAIN, 17);
		
		// Menu related items
		MenuBar = new JMenuBar();
		Menu = new JMenu("Main");
		Help = new JMenu("Help");

		Add = new JMenuItem("Add");
		Refresh = new JMenuItem("Refresh");
		Delete = new JMenuItem("Delete");
		Open = new JMenuItem("Open");

		// Menu items
		Menu.add(Add);
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddPanel();
			}
		});

		Menu.add(Delete);
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DeletePanel();
				DataManager.ComboBoxDisplay();
			}
		});
		Menu.add(Refresh);
		Refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Window.dispose();
				RunProgram.main(null);
			}
		});

		Help.add(Open);
		Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HelpWindow();
			}
		});

		// Menu
		MenuBar.add(Menu);
		MenuBar.add(Help);

		// Confirmation
		ConfirmBox = new JCheckBox("I have taken all the meds", false);
		ConfirmButton = new JButton("Confirm");
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ConfirmBox.isSelected() == true) {
					new ConfirmWindow();
				} else {
					JOptionPane.showMessageDialog(Window,
							"Medication has not been confirmed. Please check the given pill and/or check the confirmation checkbox.");
				}
			}
		});

		// Populating the main panel
		try (Connection conn = DriverManager.getConnection(DataManager.DB_URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT MedTime, MedName FROM meds");) {
			MedsPanel = new JPanel();
			MedsPanel.setLayout(new GridLayout(0, 1));
			while (rs.next()) {
				String MedsName = rs.getString("MedName");
				String MedsTime = rs.getString("MedTime");
				JLabel label = new JLabel(MedsName + ' ' + MedsTime);
				label.setFont(f1);
				MedsPanel.add(label);
			}
		} 
		catch (SQLException e) {
		}
		
		// Group layout
		MainContainer = Window.getContentPane();
		groupLayout = new GroupLayout(MainContainer);
		MainContainer.setLayout(groupLayout);

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(ConfirmBox)
						.addComponent(MedsPanel))
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(ConfirmButton)));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addComponent(MedsPanel)
				.addGroup(groupLayout
				.createParallelGroup(Alignment.BASELINE)
				.addComponent(ConfirmButton)
				.addComponent(ConfirmBox)));
		
		// Font set up
		ConfirmButton.setFont(f1);
		ConfirmBox.setFont(f1);
		MainContainer.setFont(f1);
		
		Menu.setFont(f2);
		Help.setFont(f2);
		Open.setFont(f2);
		Add.setFont(f2);
		Delete.setFont(f2);
		Refresh.setFont(f2);
		
		// Window properties
		Window.pack();
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Window.setJMenuBar(MenuBar);
		Window.setSize(600, 600);
		Window.setVisible(true);

	}
	
	@Override
	public void run(){}
}