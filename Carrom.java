import java.awt.Color;
import java.awt.Graphics;

public class Carrom extends Tile {
	private int color; // red, white, black
	private int pointVal;
	private final int carromDiameter = 20;
	private Color c;

	public Carrom(int color, int posX, int posY) {

		super(posX, posY, 0, 0);
		super.setRadius(carromDiameter / 2);
		c = new Color((int)(Math.random() * 0x1000000));
	}

	public int getColor() {
		return color;
	}

	public int getPointVal() {
		return pointVal;
	}

	public double getRadius() {
		return carromDiameter / 2;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(super.getX(), super.getY(), carromDiameter, carromDiameter); // position
		g.setColor(Color.BLACK);
		g.drawOval(super.getX(), super.getY(), carromDiameter, carromDiameter); // position
	}
}
