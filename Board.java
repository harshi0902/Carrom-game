import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Board implements Drawable{
	ArrayList<Tile> tiles = new ArrayList<Tile>();
	private final int carromDiameter = 10;
	private int center = 290;
	
	public Board() { //red = 0, white = 1, black = 2
		//red
		tiles.add(new Carrom(0, center, center));
		
		//white/tan
		tiles.add(new Carrom( 1, center, center-(carromDiameter*4))); //top
		tiles.add(new Carrom( 1, center-(carromDiameter*4), center-(carromDiameter*2))); //second row
		tiles.add(new Carrom( 1, center+(carromDiameter*4), center-(carromDiameter*2)));
		tiles.add(new Carrom( 1, center-(carromDiameter*2), center-(carromDiameter*1))); //third row
		tiles.add(new Carrom( 1, center+(carromDiameter*2), center-(carromDiameter*1)));
		tiles.add(new Carrom( 1, center-(carromDiameter*4), center+(carromDiameter*2))); //fourth row
		tiles.add(new Carrom( 1, center, center+(carromDiameter*2)));
		tiles.add(new Carrom( 1, center+(carromDiameter*4), center+(carromDiameter*2)));
		tiles.add(new Carrom( 1, center, center+(carromDiameter*4))); //bottom
		
		//black
		tiles.add(new Carrom( 2, center-(carromDiameter*2), center-(carromDiameter*3))); //first row
		tiles.add(new Carrom( 2, center+(carromDiameter*2), center-(carromDiameter*3)));
		tiles.add(new Carrom( 2, center, center-(carromDiameter*2))); //second row
		tiles.add(new Carrom( 2, center-(carromDiameter*4), center)); //third row
		tiles.add(new Carrom( 2, center+(carromDiameter*4), center));
		tiles.add(new Carrom( 2, center-(carromDiameter*2), center+carromDiameter)); //third row
		tiles.add(new Carrom( 2, center+(carromDiameter*2), center+carromDiameter));
		tiles.add(new Carrom( 2, center-(carromDiameter*2), center+(carromDiameter*3))); // third row
		tiles.add(new Carrom( 2, center+(carromDiameter*2), center+(carromDiameter*3)));

		//fourth row
		tiles.add(new Striker(center, 480));
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
		
		for(Tile t: tiles) {
			t.draw(g);
		}
	}
	
	public ArrayList<Tile> getTiles(){
		return tiles;
	}
	public int strikerIndex() {
		for(int i = 0; i < tiles.size(); i++) {
			if(tiles.get(i) instanceof Striker)
				return i;
		}
		return -1;
	}

}
