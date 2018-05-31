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
	private int level = 1;
	
	public Board() { 
		generateNewTile();
		tiles.add(new Striker(center, 480));
	}
	
	public void generateNewTile() {
		int x = (int)((Math.random() * 503) + 36);
		int y = (int)((Math.random() * 503) + 36);
		tiles.add(new Carrom(2, x, y));
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
		//for drawing the level thingy
		g.setColor(new Color(240, 240, 240));
		g.fillRect(5, 5, 60, 20);
		g.setColor(new Color(0, 0 ,0));
		g.drawString("Level: " + level, 10, 20);
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
	
	public void reset() {
		
	}

}
