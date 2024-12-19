import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JOptionPane;

public class FileReader {
	public static String pin;
	public static Path filePath;
	public static String code;
	// Compare password with authentication attempt 
	public static void ReadFile(){
		try{
		pin = ConfirmWindow.EnterPin.getText();
		filePath = Path.of("Password.txt");
		code = Files.readString(filePath);
		if(pin.equals(code)){
		JOptionPane.showMessageDialog(ConfirmWindow.ConfirmationWindow, "You have succesfully authorized the process.");
		System.exit(0);
		}
		else{
		JOptionPane.showMessageDialog(ConfirmWindow.ConfirmationWindow, "Wrong password, please try again.");
		}
		}
		catch(IOException e){
			System.out.print(e);
		}
	}
}
