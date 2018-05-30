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
import java.io.PrintStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.INPUT_STREAM;
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
	private static Timer t = new Timer(1, null);
	ArrayList<Tile> tiles = board.getTiles();
	private static JPanel panel = new CarromBoardPanel();
	private static Striker s = (Striker) board.getTiles().get(board.strikerIndex());
	private static boolean isStrikerSelected = false;
	private static boolean isHit = false;

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
		panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "shoot");
		panel.getActionMap().put("shoot", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isStrikerSelected) {
					t.start();
					s.unHighlight();
					hit(s);
					s.setSpeed(20);
				}
				frame.repaint();
			}
		});

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Exception e) {
			e.printStackTrace();
		}

		JButton button = new JButton("RULES"); // creating first button
		panel.add(button);
		button.addActionListener(new Action1());
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.requestFocusInWindow();
			}

		});

		JButton button2 = new JButton("RESET");
		panel.add(button2);
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.requestFocusInWindow();
				board = new Board();
				frame.repaint();
			}

		});

	}

	public CarromBoardPanel() {
		this.setPreferredSize(new Dimension(this.SIZE_PANEL, SIZE_PANEL));

		t.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int x = arg0.getX();
				int y = arg0.getY();
				double distance = Math.hypot(s.getCenterX() - x, s.getCenterY() - y);
				double radius = (s.getDiameter() / (double) 2);
				if (distance <= radius) {
					s.highlight();
				}

				else if (distance > radius) {
					s.unHighlight();
				}

				isStrikerSelected = true;

				frame.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// System.out.println("You just entered!! "+arg0);

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// System.out.println("You just exited!! "+arg0);

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				panel.requestFocusInWindow();
				System.out.println("You just clicked: " + arg0);

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

		});
	}

	public static class Action1 implements ActionListener { // for the first
															// button
		public void actionPerformed(ActionEvent e) {
			JFrame frame2 = new JFrame("RULES PAGE"); // name for the frame
			frame2.setVisible(true);
			frame2.setSize(600, 600);
			JLabel label = new JLabel("Points distribution: \r\n" + "White coins = 10 pts each, \r\n"
					+ "Black coins = 5pts each, \r\n" + "1 Red coin = 25 pts\r\n"
					+ "If you hit the red then you have to hit a white / black immediately after it\r\n" + "");
			label.setText("<html>Points distribution: \r\n" + " White = 10 pts each, \r\n" + " Black = 5pts each, \r\n"
					+ "1 Red = 25 pts\r\n" + "<br>" + "<br>"
					+ "If you hit the red then you have to hit a white / black immediately after it\r\n" + "<br>"
					+ "<br>" + "Player must place Striker on his/ her baseline of the board\r\n" + "<br>" + "<br>"
					+ "There are NO half reds, which means you can not place the Striker in between the red circles\r\n"
					+ "<br>" + "<br>"
					+ "If you click the Striker once it will hit the coins in the direction you point it at" + "<br>"
					+ "<br>" + "At the end of the game, whoever has the most points wins!\r\n" + "<br>" + "<br>"
					+ "You have 20 seconds for your turn\r\n" + "<br>" + "<br>"
					+ "Press the Left Arrow Key to move the arrow to the Left" + "<br>" + "<br>"
					+ "Press the Right Arrow Key to move the arrow to the Right" + "<br>" + "<br>"
					+ "Press the Enter Key to shoot the Striker" + "</html>");
			label.setFont(new Font("Times New Roman", Font.BOLD, 14));

			frame2.getContentPane().setBackground(Color.CYAN);
			Icon imgIcon = new ImageIcon(this.getClass().getResource("rules.gif"));
			label.setIcon(imgIcon);
			label.setBounds(668, 43, 46, 14); // for example, you can use your
												// own values
			frame2.getContentPane().add(label);
		}

	}

	public void tick() {

		// for (int i = 0; i < tiles.size(); i++) {
		// for (int j = i + 1; j < tiles.size(); j++) {
		// if (collision(tiles.get(i), tiles.get(j))) { // if these two
		//
		// Tile one = tiles.get(i);
		// Tile two = tiles.get(j);
		// int difY = Math.abs(two.getCenterY() - one.getCenterY());
		//
		// double angle = Math.asin((difY) / (one.getRadius() +
		// two.getRadius()));
		// double a = two.getRadius() * Math.cos(angle);
		// double b = two.getRadius() * Math.sin(angle);
		//
		// double contactX = (double) two.getCenterX() + a;
		// double contactY = (double) two.getCenterY() + b;
		//
		// double tanSlope = -((double) two.getX() - contactX) / ((double)
		// two.getY() - contactY);
		// double angleSlope = Math.atan(tanSlope);
		//
		// double difAngleOne = one.getDir() - angleSlope;
		// double difAngleTwo = two.getDir() - angleSlope;
		// one.setDir(Math.PI - difAngleOne);
		// hit(one);
		// one.setSpeed(15);
		// one.setDir(Math.PI - difAngleTwo);
		// hit(two);
		// two.setSpeed(15);
		// }
		// }
		// }

		if (s.getX() >= 523 && s.getY() <= 70 || s.getX() <= 75 && s.getY() <= 70 || s.getX() >= 523 && s.getY() >= 517
				|| s.getX() <= 75 && s.getY() >= 517) {
			s.scored();
			score++;
			t.stop();
		}

		if (s.getY() <= 36) { // if striker hits upper wall
			double roundDir = s.getDir() * 10;
			roundDir = Math.round(roundDir);
			roundDir /= 10;

			double verPi = Math.PI / 2;
			verPi = verPi * 10;
			verPi = Math.round(verPi);
			verPi = verPi / 10;

			if (roundDir == verPi) {
				System.out.println("90");
				s.setDir((Math.PI * 3) / 2);

			}

			else {
				s.setDir((2 * Math.PI) - s.getDir());
			}

			s.setTime(0);
			hit(s);
		}

		else if (s.getY() >= 540) { // hits lower wall

			double roundDir = s.getDir() * 10;
			roundDir = Math.round(roundDir);
			roundDir /= 10;

			double verPi = 3 * Math.PI / 2;
			verPi = verPi * 10;
			verPi = Math.round(verPi);
			verPi = verPi / 10;

			if (roundDir == verPi) {
				s.setDir(Math.PI / 2);
			} else {
				s.setDir(2 * Math.PI - s.getDir());
			}
			s.setTime(0);
			hit(s);
			System.out.println(s.getDir());

		}

		else if (s.getX() <= 36) { // hits left wall
			double roundDir = s.getDir() * 10;
			roundDir = Math.round(roundDir);
			roundDir /= 10;

			double horPi = Math.PI;
			horPi = horPi * 10;
			horPi = Math.round(horPi);
			horPi = horPi / 10;

			if (roundDir == horPi) {
				s.setDir(0);
			} else {
				s.setDir((Math.PI) - s.getDir());
			}
			s.setTime(0);
			hit(s);

		} else if (s.getX() >= 545) { // hits right wall
			double roundDir = s.getDir() * 10;
			roundDir = Math.round(roundDir);
			roundDir /= 10;

			double horPi = 0.0;

			if (roundDir == horPi) {
				s.setDir(Math.PI);
			} else {
				s.setDir((Math.PI) - s.getDir());
			}

			s.setTime(0);
			hit(s);
			for(Integer[] in : s.getPath()){
				System.out.print("(" + in[0] + ", " + in[1] + ")" + "    ");
			}
		}
		for (Tile ti : tiles) {

			if (ti.getPath().size() > (ti.getTime() + (int) ti.getSpeed())) {
				ti.move(ti.getPath().get(ti.getTime() + (int) ti.getSpeed())[0],
						ti.getPath().get(ti.getTime() + (int) ti.getSpeed())[1]);
				ti.setTime(ti.getTime() + (int) ti.getSpeed());
				if (ti.getSpeed() - 0.1 > 0) {
					ti.setSpeed(ti.getSpeed() - 0.1);
				}

			}
		}

		System.out.println(s.getDir());
		frame.repaint();

	}

	public boolean collision(Tile t, Tile t2) { // is a tile touching another
												// tile
		double distance = Math.hypot(t.getCenterX() - t2.getCenterX(), t.getCenterY() - t2.getCenterY());

		distance *= 10;
		distance = Math.round(distance);
		distance /= 10;
		double thisRadius = t.getRadius();
		double otherRadius = t2.getRadius();
		if (distance <= otherRadius + thisRadius) {
			return true;
		}
		return false;
	}

	public static void hit(Tile mTile) {
		System.out.println("called hit");
		if (mTile.getSpeed() > 1) {
			mTile.setSpeed(mTile.getSpeed() - 1);
		}
		mTile.clearPath();

		double dir = s.getDir();
		int x = mTile.getX();
		int y = mTile.getY();
		double ver = dir * 100;
		ver = Math.round(ver);
		ver = ver / 100;

		double verPi = Math.PI / 2;
		verPi = verPi * 100;
		verPi = Math.round(verPi);
		verPi = verPi / 100;

		double verPi2 = 3 * Math.PI / 2;
		verPi2 = verPi2 * 100;
		verPi2 = Math.round(verPi2);
		verPi2 = verPi2 / 100;

		if (ver == verPi) {
			int ct = 0;
			System.out.println("its the one");
			for (int i = y; i >= 0; i--) {
				Integer[] coord = new Integer[2];
				coord[0] = new Integer(x);
				Integer proY = new Integer(y - ct);
				coord[1] = proY;
				mTile.setPath(ct, coord);
				ct++;
			}
		}

		else if (ver == verPi2) {
			int ct = 0;

			for (int i = y; i < 566; i++) {
				Integer[] coord = new Integer[2];
				coord[0] = new Integer(x);
				Integer proY = new Integer(y + ct);
				coord[1] = proY;
				mTile.setPath(ct, coord);
				ct++;
			}
		}

		else if ((dir >= 0 && dir < Math.PI / 2) || (dir > Math.PI * 3 / 2 && dir < Math.PI * 2)) {
			int count = 565;
			int ct = 0;
			for (int i = x; i < count; i++) {
				Integer[] coord = new Integer[2];
				coord[0] = new Integer(i);
				Integer proY;
				proY = new Integer(y - (int) (ct * Math.tan(dir)));
				coord[1] = proY;

				mTile.setPath(ct, coord);
				ct++;
			}

		}

		else if ((dir > Math.PI / 2 && dir <= Math.PI) || (dir > Math.PI && dir < Math.PI * 3 / 2)) {
			int count = 0;
			int ct = 0;
			for (int i = x; i > count; i--) {
				Integer[] coord = new Integer[2];
				coord[0] = new Integer(i);
				Integer proY = new Integer(y - (int) (ct * Math.tan(Math.PI - dir)));
				coord[1] = proY;
				mTile.setPath(ct, coord);
				ct++;
			}
		}


	}

	public void paintComponent(Graphics g) {
		board.draw(g);
		g.setColor(Color.white);
		g.setFont(new Font("Montserrat", Font.PLAIN, 20));
		g.drawString("Score: " + score, 400, 30);
	}

}
