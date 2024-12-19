import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ConfirmWindow {
	public static JFrame ConfirmationWindow;
	private Container MainContainer;
	private GroupLayout groupLayout;
	private JLabel ConfirmLabel;
	public static JTextField EnterPin;
	private JButton ConfirmButton;

	public ConfirmWindow() {
		// Frame
		ConfirmationWindow = new JFrame("Confirmation Window");
		
		// Font
		Font f1 = new Font(Font.SANS_SERIF , Font.PLAIN, 20);

		// Label
		ConfirmLabel = new JLabel("Please enter the password to authenticate:");

		// Enter pin
		EnterPin = new JTextField("Enter password here");
		EnterPin.setEditable(true);

		// GroupLayout
		MainContainer = ConfirmationWindow.getContentPane();
		groupLayout = new GroupLayout(MainContainer);
		MainContainer.setLayout(groupLayout);

		// Button
		ConfirmButton = new JButton("Confrim");
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				FileReader.ReadFile();
			}
		});

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup()
				.addComponent(ConfirmLabel)
				.addComponent(EnterPin)
				.addComponent(ConfirmButton)));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addComponent(ConfirmLabel)
				.addComponent(EnterPin, 20, 50, 50)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 100, 400)
				.addComponent(ConfirmButton));
		// Font set up
		ConfirmLabel.setFont(f1);
		EnterPin.setFont(f1);
		ConfirmButton.setFont(f1);
		
		// Window properties
		ConfirmationWindow.pack();
		ConfirmationWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ConfirmationWindow.setSize(500, 300);
		ConfirmationWindow.setVisible(true);

	}
}
