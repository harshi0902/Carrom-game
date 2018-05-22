import java.awt.Graphics;

public class Tile implements Drawable{
	private int x;
	private int y;
	private double speed;
	private double dir;

	public Tile(int xpos, int ypos, double speed, double dir) {
		this.x = xpos;
		this.y = ypos;
		this.speed = speed;
		this.dir = Math.toRadians(dir);
		
	}

	public double getSpeed() {
		return speed;
	}

	public double getDir() {
		return dir;
	}
	
	public void setDir(double angle) {
		dir = angle;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
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
