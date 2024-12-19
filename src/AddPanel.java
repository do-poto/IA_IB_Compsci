import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class AddPanel {
	public static JFrame AddPanelWindow;
	private JButton AddButton, CancelButton;
	private JLabel Name, Time;
	public static JLabel Information;
	public static JTextField AddName;
	public static JFormattedTextField AddTime;
	private Container MainContainer;
	private GroupLayout groupLayout;
	private NumberFormat TimeNumberFormat;
	private String HourFormat = "00:00";

	public AddPanel() {
		// Adding frame
		AddPanelWindow = new JFrame("Add reminder");
		
		// Font
		Font f1 = new Font(Font.SANS_SERIF , Font.PLAIN, 20);
		
		// Adding buttons
		AddButton = new JButton("Add");
		AddButton.addActionListener((ActionEvent event) -> {
			DataManager.AddData();
		});
		CancelButton = new JButton("Cancel");
		CancelButton.addActionListener((ActionEvent event) -> {
			AddPanelWindow.dispose();
		});

		// Adding labels
		Name = new JLabel("Write the name of the med:");
		Time = new JLabel("Write the time at which the med is taken:");
		Information = new JLabel(DataManager.Message);

		// Adding text fields
		AddName = new JTextField();
		AddName.setEditable(true);
		// Removing text
		AddName.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
				if(AddName.getText().equals("Set name") == true){
					AddName.setText("");
				}
			}
			public void focusLost(FocusEvent e){
				if(AddName.getText().equals("") == true) {
					AddName.setText("Set name");
				}
			}
		});

		// Adding a formatted text field
		AddTime = new JFormattedTextField(TimeNumberFormat);
		AddTime.setValue(new String(HourFormat));
		AddTime.setEditable(true);
		// Removing text
		AddTime.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e){
				if(AddTime.getText().equals("00:00") == true){
					AddTime.setValue(new String());
				}
			}
			public void focusLost(FocusEvent e){
				if(AddTime.getText().equals("") == true){
					AddTime.setValue(new String(HourFormat));
				}
			}
		});

		// Setting out the layout
		MainContainer = AddPanelWindow.getContentPane();
		groupLayout = new GroupLayout(MainContainer);
		MainContainer.setLayout(groupLayout);

		// Groups
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(CancelButton))
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(Name)
						.addComponent(AddName)
						.addComponent(Time)
						.addComponent(AddTime)
						.addComponent(Information))
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(AddButton)));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addComponent(Name)
				.addComponent(AddName, 20, 50, 50)
				.addComponent(Time)
				.addComponent(AddTime, 20, 50, 50)
				.addComponent(Information)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 100, 400)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(CancelButton)
						.addComponent(AddButton)));
		// Font set
		AddButton.setFont(f1);
		CancelButton.setFont(f1);
		AddName.setFont(f1);
		AddTime.setFont(f1);
		Name.setFont(f1);
		Time.setFont(f1);
		
		// Window properties
		AddPanelWindow.pack();
		AddPanelWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		AddPanelWindow.setSize(600, 300);
		AddPanelWindow.setVisible(true);
	}

}
