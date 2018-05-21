import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Board implements Drawable{
	ArrayList<Tile> tiles = new ArrayList<Tile>();
	private final int carromRadius = 10;
	private final int strikerRadius = 10;
	
	public Board() { //red = 0, white = 1, black = 2
// 		//red
				tiles.add(new Carrom(0, center-carromDiameter, center-carromDiameter));
				
				//white/tan
				tiles.add(new Carrom( 1, center-carromDiameter, center-(carromDiameter*5))); //top
				tiles.add(new Carrom( 1, center-(carromDiameter*5), center-(carromDiameter*3))); //second row
				tiles.add(new Carrom( 1, center+(carromDiameter*3), center-(carromDiameter*3)));
				tiles.add(new Carrom( 1, center-(carromDiameter*3), center-(carromDiameter*2))); //third row
				tiles.add(new Carrom( 1, center+carromDiameter, center-(carromDiameter*2)));
				tiles.add(new Carrom( 1, center-(carromDiameter*5), center+carromDiameter)); //fourth row
				tiles.add(new Carrom( 1, center-carromDiameter, center+carromDiameter));
				tiles.add(new Carrom( 1, center+(carromDiameter*3), center+carromDiameter));
				tiles.add(new Carrom( 1, center-carromDiameter, center+(carromDiameter*3))); //bottom
				
				//black
				tiles.add(new Carrom( 2, center-(carromDiameter*3), center-(carromDiameter*4))); //first row
				tiles.add(new Carrom( 2, center+carromDiameter, center-(carromDiameter*4)));
				tiles.add(new Carrom( 2, center-carromDiameter, center-(carromDiameter*3))); //second row
				tiles.add(new Carrom( 2, center-(carromDiameter*5), center-carromDiameter)); //third row
				tiles.add(new Carrom( 2, center+(carromDiameter*3), center-carromDiameter));
				tiles.add(new Carrom( 2, center-(carromDiameter*3), center)); //third row
				tiles.add(new Carrom( 2, center+carromDiameter, center));
				tiles.add(new Carrom( 2, center-(carromDiameter*3), center+(carromDiameter*2))); // third row
				tiles.add(new Carrom( 2, center+carromDiameter, center+(carromDiameter*2)));
				
		//fourth row
		tiles.add(new Striker(center, 470));
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
