import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.ShapeGraphicAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Striker extends Tile {
	private final int strikerDiameter = 23;
	private boolean isClicked;
	private boolean isHighlighted;
	private AffineTransform strikerTrans = new AffineTransform();
	private BufferedImage img = null;

	public Striker(int posX, int posY) {
		super(posX, posY, 0, 0);
		isClicked = false;
		isHighlighted = false;
		super.setRadius(11.5);
		try {
			img = ImageIO.read(getClass().getResource("line.png")); // name of
																	// file of
																	// picture
																	// of board
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCenterX() {
		super.setCenterX(super.getX() + (strikerDiameter / 2));
		return super.getCenterX();
	}

	public int getCenterY() {
		super.setCenterY(super.getY() + (strikerDiameter / 2));
		return super.getCenterY();
	}

	public boolean isClicked() {
		return isClicked;
	}

	public int getDiameter() {
		return strikerDiameter;
	}

	public void draw(Graphics g) {
		if(!super.getScore()) {
			if (!isHighlighted) {
				g.setColor(new Color(245, 245, 245)); // white
				g.fillOval(super.getX(), super.getY(), strikerDiameter, strikerDiameter);// position
																							// +
																							// size
			}	
			else {
				g.setColor(new Color(250, 250, 10));
				g.drawOval(super.getX(), super.getY(), strikerDiameter, strikerDiameter);
				strikerTrans.setToTranslation(super.getCenterX(), super.getCenterY());
				strikerTrans.scale(0.05, 0.05);
				strikerTrans.rotate(-super.getDir());
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(img, strikerTrans, null);
			}
		}
	}

	public void highlight() {
		isHighlighted = true;
	}

	public void unHighlight() {
		isHighlighted = false;
	}

	public double getRadius() {
		return strikerDiameter/(double)2;
	}
	public void incrementAngle(double d) {
		double angle = super.getDir();
		super.setDir(angle += d);
		if (Math.abs(super.getDir()) >= 2 * Math.PI) {
			super.setDir(super.getDir() % (2 * Math.PI));
		} else if (super.getDir() <= 0) {
			super.setDir(2 * Math.PI + super.getDir());
		}
	}
}
