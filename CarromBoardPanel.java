import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;

public class CarromBoardPanel extends JPanel {

	private final int SIZE_PANEL = 600;
	private static Board board = new Board();
	private int score = 0;
	private static JFrame frame = new JFrame("Carrom Board!");
	private Timer t  = new Timer(100, null);
	private static JPanel panel = new CarromBoardPanel();
	private static Striker s = (Striker) board.getTiles().get(board.strikerIndex());
	
	public static void main(String[] args) {
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setResizable(false);

		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "decreaseAngle");
		panel.getActionMap().put("decreaseAngle", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				s.incrementAngle(Math.PI / 72);
				frame.repaint();
			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "increaseAngle");

		panel.getActionMap().put("increaseAngle", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				s.incrementAngle(-Math.PI / 72);
				frame.repaint();
			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "shoot");
		panel.getActionMap().put("shoot", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("dir to shoot: " + (s.getDir()%(Math.PI * 2)));
				//s.shoot(s.getDir()%(Math.PI * 2));
				frame.repaint();
			}
		});
		

			
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		JButton button = new JButton("RULES"); // creating first button
		panel.add(button);
		button.addActionListener(new Action1());

		JButton button2 = new JButton("RESET"); // creating second button
		panel.add(button2);
		
	}
	public CarromBoardPanel() {
		this.setPreferredSize(new Dimension(this.SIZE_PANEL,SIZE_PANEL));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int x = arg0.getX();
				int y = arg0.getY();
				double distance = Math.hypot(s.getCenterX() - x, s.getCenterY() - y);
				double radius = (s.getDiameter()/(double)2);
				if(distance <= radius){
					System.out.println("hi");
					s.highlight();
				}
				
				else if(distance > radius) {
					s.unHighlight();
				}
				
				frame.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				//System.out.println("You just entered!! "+arg0);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				//System.out.println("You just exited!! "+arg0);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
				System.out.println("You just clicked: "+arg0);
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
			
		});
	}
	
	public static class Action1 implements ActionListener { // for the first button
		public void actionPerformed(ActionEvent e) {
			JFrame frame2 = new JFrame("RULES PAGE"); // name for the frame
			frame2.setVisible(true);
			frame2.setSize(600, 600);
			JLabel label = new JLabel("Points distribution: \r\n" + "White coins = 10 pts each, \r\n"
					+ "Black coins = 5pts each, \r\n" + "1 Red coin = 25 pts\r\n"
					+ "If you hit the red then you have to hit a white / black immediately after it\r\n" + "");
			label.setText("<html>Points distribution: \r\n" + " White = 10 pts each, \r\n" + " Black = 5pts each, \r\n"
					+ "1 Red = 25 pts\r\n" +
					"<br>" + "<br>"
					+ "If you hit the red then you have to hit a white / black immediately after it\r\n"+ "<br>" + "<br>" + "Player must place Striker on his/ her baseline of the board\r\n"
					+ "<br>" + "<br>"
					+ "There are NO half reds, which means you can not place the Striker in between the red circles\r\n"
					+"<br>" + "<br>" + "If you click the Striker once it will hit the coins in the direction you point it at"
					+ "<br>" + "<br>"	+ "At the end of the game, whoever has the most points wins!\r\n" + "<br>" + "<br>"+ "You have 20 seconds for your turn\r\n"+ "<br>" + "<br>"+ "Press the Left Arrow Key to move the arrow to the Left"+ 
					 "<br>" + "<br>" + "Press the Right Arrow Key to move the arrow to the Right"+
					 "<br>" + "<br>" + "Press the Enter Key to shoot the Striker"+ 
					"</html>");
			Icon imgIcon = new ImageIcon(this.getClass().getResource("rules.gif"));
			label.setIcon(imgIcon);
			label.setBounds(668, 43, 46, 14); // for example, you can use your own values
			frame2.getContentPane().add(label);
		}

	}
	
	public void paintComponent(Graphics g) {
		board.draw(g);
		g.setColor(Color.white);
		g.setFont(new Font("Montserrat", Font.PLAIN, 20));
		g.drawString("Score: " + score , 400, 30);
	}
	
	
	
	

}

