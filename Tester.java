import javax.swing.*;
import java.awt.Color.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Tester extends JPanel {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Home Page");
		frame.setVisible(true);
		frame.setSize(1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.add(panel);

		JButton button = new JButton("RULES"); // creating first button
		panel.add(button);
		button.addActionListener(new Action1());

		JButton button2 = new JButton("RESET"); // creating second button
		panel.add(button2);

		JButton button3 = new JButton("START"); // creating third button
		panel.add(button3);
		button3.addActionListener(new Action3());

		JButton button4 = new JButton("Sound?"); // creating fourth button
		panel.add(button4);

	}

	public static class Action1 implements ActionListener { // for the first button
		public void actionPerformed(ActionEvent e) {
			JFrame frame2 = new JFrame("RULES PAGE"); // name for the frame
			frame2.setVisible(true);
			frame2.setSize(1000, 500);
			JLabel label = new JLabel("Points distribution: \r\n" + "White coins = 10 pts each, \r\n"
					+ "Black coins = 5pts each, \r\n" + "1 Red coin = 25 pts\r\n"
					+ "If you hit the red then you have to hit a white / black immediately after it\r\n" + "");
			JPanel panel = new JPanel();
			label.setText("<html>Points distribution: \r\n" + " White = 10 pts each, \r\n" + " Black = 5pts each, \r\n"
					+ "1 Red = 25 pts\r\n" +
					"<br>" + "<br>"
					+ "If you hit the red then you have to hit a white / black immediately after it\r\n"+ "<br>" + "<br>" + "Player must place Striker on his/ her baseline of the board\r\n"
					+ "<br>" + "<br>"
					+ "There are NO half reds, which means you can not place the Striker in between the red circles\r\n"
					+ "</html>");
			frame2.add(panel);
			panel.add(label);
		}

	}

	public static class Action3 implements ActionListener { // for the first button
		public void actionPerformed(ActionEvent e) {
			JFrame frame3 = new JFrame("GAME"); // name for the frame where we play the game
			frame3.setVisible(true);
			frame3.setSize(1000, 500);
			JLabel label = new JLabel("START GAME!");
			JPanel panel = new JPanel();
			frame3.add(panel);
			panel.add(label);
		}
	}

}
