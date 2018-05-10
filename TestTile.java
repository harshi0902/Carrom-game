import java.awt.Graphics;

public class TestTile implements Drawable {
	private int x;
	private int y;
	private int size;
	private int mass;

	public TestTile(int sizeIn, int massIn, int xpos, int ypos) {
		this.size = sizeIn;
		this.mass = massIn;
		this.x = xpos;
		this.y = ypos;
		
	}

	public void move(int xpos, int ypos){
		this.x = xpos;
		this.y = ypos;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	@Override
	public void draw(Graphics g) {
		g.drawOval(x, y, 40, 40);
	}

}
