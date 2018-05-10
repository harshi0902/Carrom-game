import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class CarromBoardPanel extends JPanel {

	private final int SIZE_PANEL = 600;
	private static JFrame frame = new JFrame("Carrom Board!");
	private Board board = new Board();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new CarromBoardPanel());
		frame.pack();
		frame.setVisible(true);
		
	}
	public CarromBoardPanel() {
		this.setPreferredSize(new Dimension(this.SIZE_PANEL,SIZE_PANEL));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				frame.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				System.out.println("You just entered!! "+arg0);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				System.out.println("You just exited!! "+arg0);
				
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

	public void paintComponent(Graphics g) {
		board.draw(g);
	}
	
	
	
	

}

