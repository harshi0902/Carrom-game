
public class Carrom extends Tile{
	private int color; //red, white, black
	private int pointVal;
	private final int mass = 0;
	private int carromDiameter;
	
	public Carrom(int color, int posX, int posY, int carromRadius) {
		this.color = color;
		carromDiameter = 2*carromRadius;
		if(color == 0)
			pointVal = 25;
		else if(color == 1)
			pointVal = 10;
		else {
			pointVal = 5;
		}
		super(posX, posY);
	}

	public int getColor() {
		return color;
	}

	public int getPointVal() {
		return pointVal;
	}
	
	public void draw(Graphics g) {
		if (color == 0) {
			g.setColor(new Color(245, 90, 90));
			g.fillOval(posX, posY, carromDiameter, carromDiameter);
			g.setColor(new Color(200, 0, 0));
			g.drawOval(posX, posY, carromDiameter, carromDiameter);
		} else if (color == 1) {
			g.setColor(245, 245, 245);
			g.fillOval(posX, posY, carromDiameter, carromDiameter);
			g.setColor(new Color(0, 0, 0));
			g.drawOval(posX, posY, carromDiameter, carromDiameter);
		} else {
			g.setColor(0, 0, 0);
			g.fillOval(posX, posY, carromDiameter, carromDiameter);
		}
	}
			
}
