import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class HelpWindow {
	public static JFrame HelpWindow;
	private JTextArea Area1, Area2, Area3, Area4, Area5, Area6;
	private String Text1, Text2, Text3, Text4, Text5, Text6;
	private JButton Next, Previous;
	private JPanel ButtonPanel, Panel1, Panel2, Panel3, Panel4, Panel5, Panel6;
	private CardLayout layout;
	private Container MainContainer;

	public HelpWindow() {
		HelpWindow = new JFrame("Help");

		// Cardlayout components
		layout = new CardLayout();
		MainContainer = new JPanel();

		// ButtonPanel components
		ButtonPanel = new JPanel();

		Next = new JButton("Next");
		Next.addActionListener((ActionEvent e) -> {
			layout.next(MainContainer);
		});

		Previous = new JButton("Previous");
		Previous.addActionListener((ActionEvent e) -> {
			layout.previous(MainContainer);
		});

		ButtonPanel.add(Previous);
		ButtonPanel.add(Next);

		// Welcome panel
		Panel1 = new JPanel();
		Area1 = new JTextArea();
		Text1 ="Welcome to the Help section! \nHere you can learn about the basic features of this program.";
		TutorialPanels.CreatePanel(Panel1, Area1, Text1, 250, 100);
		
		// Information about main panel
		Panel2 = new JPanel();
		Area2 = new JTextArea();
		Text2 = "Main window\n" +
		"From the main window you can firstly see the list of medication that is to be taken. If nothing is visable that means the current medication list is empty.\n" +
		"From this window you can also access the adition, deletion and confirmation of the taken medication.";
		TutorialPanels.CreatePanel(Panel2, Area2, Text2);

		// Adding panel 
		Panel3 = new JPanel();
		Area3 = new JTextArea();
		Text3 = "To add a reminder follow the steps below:\n" +
		"1. Click Menu>Add.\n" + 
		"2. Write the name of the medication in the corresponding box.\n" + 
		"3. Write the hour in the format from of HH:mm in range between 00:00 to 23:59, in the correspoding box.\n" +
		"4. Click the 'Add' button.\n" +
		"5. Close the window via 'Cancel' button or via 'X' in the top right corner of this window.\n" +
		"6. Click Menu>Refresh to see the new results.";
		TutorialPanels.CreatePanel(Panel3, Area3, Text3, 200, 250);

		// Deleting panel
		Panel4 = new JPanel();
		Area4 = new JTextArea();
		Text4 = "To delete a reminder follow the steps below:\n " + 
		"1. Click Menu>Delete.\n" +
		"2. Choose a reminder you would like to delte from the list.\n" +
		"3. Click the 'Delete' button.\n" + 
		"4. Close the window via 'Cancel' button or via 'X' in the top right corner of this window.\n" + 
		"6. Click Menu>Refresh to see the new results.";
		TutorialPanels.CreatePanel(Panel4, Area4, Text4,200, 200);
		
		// Confirmation panel
		Panel5 = new JPanel();
		Area5 = new JTextArea();
		Text5 = "To confirm that all the medication is taken make sure that checkbox on the left of the main menu is checked.\n" +
		"Then click the 'Confirm' button on the right of the screen.\n" +
		"If the checkbox is not checked the program assumes all of the medication is not taken. Please ensure the medication is checked and check the box\n" +
		"You will now preceed to the password panel";
		TutorialPanels.CreatePanel(Panel5, Area5, Text5, 200, 200);
		
		// Password panel
		Panel6 = new JPanel();
		Area6 = new JTextArea();
		Text6 = "To ensure the safety of the user the a third party will be now asked to input the password.\n" +
		"Enter the passowrd into the corresponding box.\n" +
		"If the passowrd is correct a message will appear and the program will close.\n" +
		"If the passowrd is incorrect and the third party is not able to provide one please contact the administration.";
		TutorialPanels.CreatePanel(Panel6, Area6, Text6, 200, 200);


		// Card layout panels
		MainContainer.setLayout(layout);
		MainContainer.add(Panel1);
		MainContainer.add(Panel2);
		MainContainer.add(Panel3);
		MainContainer.add(Panel4);
		MainContainer.add(Panel5);
		MainContainer.add(Panel6);
		
		// Frame attributes
		HelpWindow.add(MainContainer, BorderLayout.NORTH);
		HelpWindow.add(ButtonPanel, BorderLayout.SOUTH);
		HelpWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		HelpWindow.setSize(400, 400);
		HelpWindow.setVisible(true);
	}
}
