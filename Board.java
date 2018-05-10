import java.awt.Graphics;
import java.util.ArrayList;

public class Board implements Drawable{
	ArrayList<Tile> tiles = new ArrayList<Tile>();
	private final int carromRadius = 0;
	private final int strikerRadius = 0;
	
	public Board() { //red = 0, white = 1, black = 2
		tiles.add(new Carrom(1,))
		
		tiles.add(new Carrom(1, 300 - 2*carromRadius, 300));
		tiles.add(new Carrom(2, 300 - carromRadius, 300));
		tiles.add(new Striker(300, 300));
		tiles.add(new Carrom(1, 300 + carromRadius, 300));
		tiles.add(new Carrom(1, 300 + carromRadius*2, 300));
		
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
	}
	
	public int strikerIndex() {
		for(int i = 0; i < tiles.size(); i++) {
			if(tiles.get(i) instanceof Striker)
				return i;
		}
		return -1;
	}

}
