import java.awt.Color;
import java.awt.Graphics;

public class Striker extends Tile{
	private final int strikerDiameter = 23;
	private boolean isClicked;
	private int centerX;
	private int centerY;
	private boolean isHighlighted;
	
	public Striker(int posX, int posY) {
		super(posX, posY, 0, 0);
		isClicked = false;
		isHighlighted = false;
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
			g.setColor(new Color(245, 245, 245)); //white
			g.fillOval(super.getX(), super.getY(), strikerDiameter, strikerDiameter);//position + size
			g.setColor(new Color(250, 250, 10));
			g.drawOval(super.getX(), super.getY(), strikerDiameter, strikerDiameter);
		}
	}

	public void highlight() {
		isHighlighted = true;
	}

	public void unHighlight() {
		isHighlighted = false;
	}

}
