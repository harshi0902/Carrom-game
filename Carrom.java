
public class Carrom extends Tile{
	private int color; //red, white, black
	private int pointVal;
	private final int mass = 0;
	
	public Carrom(int color, int posX, int posY) {
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
}
