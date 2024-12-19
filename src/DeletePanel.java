import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class DeletePanel {
	public static JFrame DeletePanelWindow;
	private JButton DeleteButton, CancelButton;
	private JLabel Name;
	public static JComboBox ChooseName;
	private Container MainContainer;
	private GroupLayout groupLayout;

	DeletePanel() {
		// Adding frame
		DeletePanelWindow = new JFrame("Delete reminder");
		// Font
		Font f1 = new Font(Font.SANS_SERIF , Font.PLAIN, 20);
		
		// Adding buttons
		DeleteButton = new JButton("Delete");
		DeleteButton.addActionListener((ActionEvent event) -> {
			DataManager.DeleteData();
		});
		CancelButton = new JButton("Cancel");
		CancelButton.addActionListener((ActionEvent event) -> {
			DeletePanelWindow.dispose();
		});

		// Adding ComboBox
		ChooseName = new JComboBox();

		// Adding label
		Name = new JLabel("Write the name of the medicine reminder you would like to delete:");

		// Layout set up
		MainContainer = DeletePanelWindow.getContentPane();
		groupLayout = new GroupLayout(MainContainer);
		MainContainer.setLayout(groupLayout);

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(CancelButton))
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(Name)
						.addComponent(ChooseName))
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(DeleteButton)));
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
				.addComponent(Name)
				.addComponent(ChooseName, 20, 20, 20)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 100, 400)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(CancelButton)
						.addComponent(DeleteButton)));
		
		DeletePanelWindow.pack();
		DeletePanelWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DeletePanelWindow.setSize(800, 400);
		DeletePanelWindow.setVisible(true);
		
		//Font set up
		Name.setFont(f1);
		ChooseName.setFont(f1);
		CancelButton.setFont(f1);
		DeleteButton.setFont(f1);
	}
}
