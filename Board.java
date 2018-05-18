import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Board implements Drawable{
	ArrayList<Tile> tiles = new ArrayList<Tile>();
	private final int carromRadius = 0;
	private final int strikerRadius = 0;
	
	public Board() { //red = 0, white = 1, black = 2
		tiles.add(new Carrom(1,0,0)); //first row
		tiles.add(new Carrom(1,0,0));
		tiles.add(new Carrom(1,0,0));
		
		
		
		
		tiles.add(new Carrom(1, 300 - 2*carromRadius, 300)); //third row
		tiles.add(new Carrom(2, 300 - carromRadius, 300));
		tiles.add(new Carrom(0, 300, 300));
		tiles.add(new Carrom(1, 300 + carromRadius, 300));
		tiles.add(new Carrom(1, 300 + carromRadius*2, 300));
		//fourth row
		
	}
	
	@Override
	public void draw(Graphics g) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResource("carrom_board.png")); //name of file of picture of board
		} catch (IOException e) {
			System.out.println("bleh");
		}
		g.drawImage(img, 0, 0, 600, 600, null);
	}
	
	public int strikerIndex() {
		for(int i = 0; i < tiles.size(); i++) {
			if(tiles.get(i) instanceof Striker)
				return i;
		}
		return -1;
	}

}
