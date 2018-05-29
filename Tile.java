import java.awt.Graphics;
import java.util.ArrayList;

public class Tile implements Drawable {
	private int x;
	private int y;
	private double speed;
	private double dir;
	private int centerX;
	private int centerY;
	private ArrayList<Integer[]> path = new ArrayList<Integer[]>();

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

	public void move(int xpos, int ypos) {
		this.x = xpos;
		this.y = ypos;
	}

	public int getX() {
		return this.x;
	}

	public int getCenterX() {
		return this.centerX;
	}

	public int getCenterY() {
		return this.centerY;
	}

	public void setCenterX(int cenX) {
		this.centerX = cenX;
	}

	public void setCenterY(int cenY) {
		this.centerY = cenY;
	}

	public int getY() {
		return this.y;
	}

	public ArrayList<Integer[]> getPath() {
		return this.path;
	}
	
	public void setPath(int idx, Integer[] yVal){
		this.path.add(idx, yVal);
	}

	@Override
	public void draw(Graphics g) {
		g.drawOval(x, y, 40, 40);
	}

}
