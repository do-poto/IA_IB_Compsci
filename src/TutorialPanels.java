import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TutorialPanels {
    public static void CreatePanel(JPanel Panel,JTextArea Area, String Text){
		Area.setText(Text);
		Area.setEditable(false);
		Area.setLineWrap(true);
		Area.setWrapStyleWord(true);
		Area.setPreferredSize(new Dimension(200, 150));
		Panel.add(Area);
    }
    public static void CreatePanel(JPanel Panel,JTextArea Area, String Text, int w, int h){
        Area.setText(Text);
		Area.setEditable(false);
		Area.setLineWrap(true);
		Area.setWrapStyleWord(true);
		Area.setPreferredSize(new Dimension(w, h));
		Panel.add(Area);
    }
}
