import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Striker extends Tile{
	private final int strikerDiameter = 23;
	private boolean isClicked;
	private int centerX;
	private int centerY;
	private boolean isHighlighted;
	private AffineTransform strikerTrans = new AffineTransform();
	private BufferedImage img = null;
	
	public Striker(int posX, int posY) {
		super(posX, posY, 0, 0);
		isClicked = false;
		isHighlighted = false;
		try {
		    img = ImageIO.read(getClass().getResource("line.png")); //name of file of picture of board
		} catch (IOException e) {
			System.out.println("bleh");
		}
	}
	
	public int getCenterX() {
		centerX = super.getX() + (strikerDiameter/2);
		return centerX;
	}
	
	public int getCenterY() {
		centerY = super.getY() + (strikerDiameter/2);
		return centerY;
	}
	
	public boolean isClicked() {
		return isClicked;
	}
	
	public int getDiameter() {
		return strikerDiameter;
	}
	
	public void draw(Graphics g) {
		if(!isHighlighted) {
			g.setColor(new Color(245, 245, 245)); //white
			g.fillOval(super.getX(), super.getY(), strikerDiameter, strikerDiameter);//position + size
		}
		
		else {
			g.setColor(new Color(250, 250, 10));
			g.drawOval(super.getX(), super.getY(), strikerDiameter, strikerDiameter);
			strikerTrans.setToTranslation(centerX, centerY);
			strikerTrans.scale(0.05, 0.05);
			System.out.println("center x: " + centerX + " center y: " + centerY);
			strikerTrans.rotate(-super.getDir());
			System.out.println("dir: "+ -super.getDir());
			Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(img, strikerTrans, null);
		}
	}

	public void highlight() {
		isHighlighted = true;
	}

	public void unHighlight() {
		isHighlighted = false;
	}

	public void incrementAngle(double d) {
		double angle = super.getDir();
		super.setDir(angle += d);
	}

}
