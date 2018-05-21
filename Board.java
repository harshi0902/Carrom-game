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
// 		tiles.add(new Carrom(1,0,0)); //first row
// 		tiles.add(new Carrom(1,0,0));
// 		tiles.add(new Carrom(1,0,0));
		
// 		tiles.add(new Carrom(1, 300 - 2*carromRadius, 300)); //third row
// 		tiles.add(new Carrom(2, 300 - carromRadius, 300));
// 		tiles.add(new Carrom(0, 300, 300));
// 		tiles.add(new Carrom(1, 300 + carromRadius, 300));
// 		tiles.add(new Carrom(1, 300 + carromRadius*2, 300));
// 		//fourth row
		
		//red
		tiles.add(new Carrom(0, 300-carromRadius, 300-carromRadius));
		
		//white/tan
		tiles.add(newCarrom( 1, 300-carromRadius, 300-(carromRadius*5))); //top
		tiles.add(newCarrom( 1, 300-(carromRadius*5), 300-(carromRadius*3))); //second row
		tiles.add(newCarrom( 1, 300+(carromRadius*3), 300-(carromRadius*3)));
		tiles.add(newCarrom( 1, 300-(carromRadius*3), 300-(carromRadius*2))); //third row
		tiles.add(newCarrom( 1, 300+carromRadius, 300-(carromRadius*2)));
		tiles.add(newCarrom( 1, 300-(carromRadius*5), 300+carromRadius)); //fourth row
		tiles.add(newCarrom( 1, 300-carromRadius, 300+carromRadius));
		tiles.add(newCarrom( 1, 300+(carromRadius*3), 300+carromRadius));
		tiles.add(newCarrom( 1, 300-carromRadius, 300+(carromRadius*3))); //bottom
		
		//black
		tiles.add(newCarrom( 2, 300-(carromRadius*3), 300-(carromRadius*4))); //first row
		tiles.add(newCarrom( 2, 300+carromRadius, 300-(carromRadius*4)));
		tiles.add(newCarrom( 2, 300-carromRadius, 300-(carromRadius*3))); //second row
		tiles.add(newCarrom( 2, 300-(carromRadius*5), 300-carromRadius)); //third row
		tiles.add(newCarrom( 2, 300+(carromRadius*3), 300-carromRadius));
		tiles.add(newCarrom( 2, 300-(carromRadius*3), 300)); //third row
		tiles.add(newCarrom( 2, 300+carromRadius, 300));
		tiles.add(newCarrom( 2, 300-(carromRadius*3), 300+(carromRadius*2))); // third row
		tiles.add(newCarrom( 2, 300+carromRadius, 300+(carromRadius*2)));
		
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
