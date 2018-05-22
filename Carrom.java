import java.awt.Color;
import java.awt.Graphics;

public class Carrom extends Tile{
	private int color; //red, white, black
	private int pointVal;
	private final int carromDiameter = 20;
	private Color c;
	
	public Carrom(int color, int posX, int posY) {
		super(posX, posY, 0, 0);
		if(color == 0) {
			pointVal = 25;
			c = new Color(245, 90, 90);
		}
		else if(color == 1) {
			pointVal = 10;
			c = new Color(240, 215, 180);
		}
		else {
			pointVal = 5;
			c = new Color(0, 0, 0);
		}
	}

	public int getColor() {
		return color;
	}

	public int getPointVal() {
		return pointVal;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(super.getX(), super.getY(), carromDiameter, carromDiameter); //position
	}
}
