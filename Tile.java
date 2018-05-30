import java.awt.Graphics;
import java.util.ArrayList;

public class Tile implements Drawable {
	private int x;
	private int y;
	private double speed;
	private double dir;
	private int centerX;
	private int centerY;
	private int time = 0;
	private double radius;
	private boolean scored;
	private ArrayList<Integer[]> path = new ArrayList<Integer[]>();

	public Tile(int xpos, int ypos, int speed, double dir) {
		this.x = xpos;
		this.y = ypos;
		this.speed = speed;
		this.dir = Math.toRadians(dir);
		this.scored = false;

	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int timeSet) {
		this.time = timeSet;
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
		
		return (int) (this.x+radius);
	}

	public int getCenterY() {
		return (int) (this.y+radius);
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

	public void setPath(int idx, Integer[] yVal) {
		this.path.add(idx, yVal);
	}


	public void setRadius(double rad){
		this.radius = rad;
	}
	
	public double getRadius(){
		return this.radius;
	}
	
	public void clearPath(){
		this.path = new ArrayList<Integer[]>();
	}
	

	@Override
	public void draw(Graphics g) {
		if(!scored)
			g.drawOval(x, y, 40, 40);
	}

	public void scored() {
		scored = true;
	}

}
