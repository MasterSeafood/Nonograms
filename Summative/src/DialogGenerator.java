import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;


public class DialogGenerator {
	
	public static void genCustomError(String text, int fontSize) { //Generate a dialog error box with custom text and font size
		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		panel.setLayout(new FlowLayout());

		JLabel label = new JLabel(text);

		label.setFont(new Font("Segoe UI Light", Font.PLAIN, fontSize));

		panel.add(label);

		JOptionPane.showOptionDialog(null, panel, "Uh oh!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null,
				new String[] { "OK" }, "OK");
		
	}
	
	public static void genSuccessfulDownload() { //Generate a dialog box to show a download has been successful

		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		panel.setLayout(new FlowLayout());

		JTextPane label = new JTextPane();
		label.setText("\"puzzle.txt\" has been downloaded and placed in the same directory as the \n"
				+ "JAR file for this program.");

		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));

		panel.add(label);

		JOptionPane.showOptionDialog(null, panel, "Success!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				new String[] { "OK" }, "OK");
		
	}
	

	public static int genSuccessSolve() { //Generate a dialog box to show a successful solve

		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		panel.setLayout(new FlowLayout());
		

		URLGenerator urlGen = new URLGenerator();
		ImageIcon iconCheck = new ImageIcon(urlGen.getURL("nonogramCheck3.png"));

		JLabel label = new JLabel("You solved the puzzle correctly!");

		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));

		panel.add(label);

		return JOptionPane.showOptionDialog(null, panel, "Congratulations!", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, iconCheck, new String[] { "Play new", "Return to menu", "Cancel" },
				"Play new");

	}

	public static int genFailedSolve() { //Generate a dialog box to show a failed solve

		JPanel panel = new JPanel();

		panel.setBackground(Color.WHITE);

		panel.setLayout(new FlowLayout());
		
		URLGenerator urlGen = new URLGenerator();

		ImageIcon iconCheck = new ImageIcon(urlGen.getURL("nonogramCross.png"));

		JLabel label = new JLabel("Your solution is incorrect");

		label.setFont(new Font("Segoe UI Light", Font.PLAIN, 17));

		panel.add(label);

		return JOptionPane.showOptionDialog(null, panel, "Oops!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				iconCheck, new String[] { "Keep trying", "Return to menu", "Cancel" }, "Keep trying");

	}

}
