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
		return time;
	}

	public void setTime(int timeSet) {
		time = timeSet;
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

	public void setSpeed(double newSpeed) {
		speed = newSpeed;
	}

	public void move(int xpos, int ypos) {
		x = xpos;
		y = ypos;
	}

	public int getX() {
		return x;
	}

	public int getCenterX() {
		
		return (int) (x+radius);
	}

	public int getCenterY() {
		return (int) (y+radius);
	}

	public void setCenterX(int cenX) {
		centerX = cenX;
	}

	public void setCenterY(int cenY) {
		centerY = cenY;
	}

	public int getY() {
		return y;
	}

	public ArrayList<Integer[]> getPath() {
		return path;
	}

	public void setPath(int idx, Integer[] yVal) {
		path.add(idx, yVal);
	}


	public void setRadius(double rad){
		radius = rad;
	}
	
	public double getRadius(){
		return radius;
	}
	
	public void clearPath(){
		path = new ArrayList<Integer[]>();
	}
	

	@Override
	public void draw(Graphics g) {
		g.drawOval(x, y, 40, 40);
	}

	public boolean getScore() {
		return scored;
	}
	
	public void scored() {
		scored = true;
	}

}
