
public class Striker extends Tile{
	private final int mass = 0;
	private int diameter;
	
	public Striker(int posX, int posY, int strikerRadius) {
		super(posX, posY);
		diameter = 2*strikerRadius;
	}
	
	public void draw(Graphics g) {
		g.setColor(245,245,245);
		g.fillOval(posX, posY, diameter, diameter);

}
