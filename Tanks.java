import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Tanks extends GameObject{
	
	private int xloc;
	private double tankAngle;
	private int yloc;
	private int health;
	private double angle;
	private int power;
	private Color c;
	private Image tankpic;
	private AffineTransform Tanktrans;
	private AffineTransform Cannontrans;
	private AffineTransform explosionTrans;
	private boolean isGreen;
	private Image cannonpic;
	private String powa;
	private String ang;
	private boolean takingdamage;
	
	public Tanks(int x, int y, Color color){
		xloc = x;
		yloc = y;
		angle = Math.PI/2;
		power = 25;
		health = 100;
		c = color;
		Tanktrans = new AffineTransform();
		Cannontrans = new AffineTransform();
		if(c.equals(Color.GREEN)){
	    	tankpic = Toolkit.getDefaultToolkit().getImage("/Users/shreyaskallingal/Documents/workspace/TankGame/res/tank-clipart-4.png");
	    	isGreen = true;
	    	cannonpic = Toolkit.getDefaultToolkit().getImage("/Users/shreyaskallingal/Documents/workspace/TankGame/res/greencannon.png");
	    }
	    else{
	    	tankpic = Toolkit.getDefaultToolkit().getImage("/Users/shreyaskallingal/Documents/workspace/TankGame/res/blacktank.png");
	    	isGreen = false;
	    	cannonpic = Toolkit.getDefaultToolkit().getImage("/Users/shreyaskallingal/Documents/workspace/TankGame/res/cannon.png");
	    }
		
	}
	
	@Override
	public void draw(Graphics g) {
		
	
		Graphics2D g2d = (Graphics2D)g;

		Tanktrans.setToTranslation(xloc-20, yloc-20);
		if(!isGreen){
		Tanktrans.scale(-1, 1);
		Tanktrans.setToTranslation(xloc-10, yloc-20);
		}
		

		g2d.drawImage(tankpic, Tanktrans, null);
	 	
		Graphics2D g3d = (Graphics2D)g;
	    
		Cannontrans.setToTranslation(xloc+5, yloc-18);
		
		Cannontrans.rotate(-angle, 0, 5);
		g3d.drawImage(cannonpic, Cannontrans, null);
		
		if(takingdamage){
	
			Image img1 = Toolkit.getDefaultToolkit().getImage("/Users/shreyaskallingal/Documents/workspace/TankGame/res/explosion.png");

			explosionTrans = new AffineTransform();
		    explosionTrans.translate(xloc -60, yloc-20);
		    explosionTrans.scale(0.15, 0.15);
		
			g2d.drawImage(img1, explosionTrans, null);
		  
		}
		
		g.setColor(Color.YELLOW);
		g.drawRect(xloc-50, yloc+20, 100, 15);
		g.setColor(Color.BLUE);
		g.fillRect(xloc-50, yloc+20, health, 15);
		ang= "Health: " + health;
	    g.setColor(Color.BLACK);

		g.drawString(ang, xloc-30, yloc+33);
		
		
		g.setColor(c);
		g.drawRect(xloc-50, yloc+40, 100, 15);
		g.setColor(Color.YELLOW);
		g.fillRect(xloc-50, yloc+40, power*2, 15);
	
		powa = "Power: " + power*2;
	    g.setColor(Color.BLACK);

		g.drawString(powa, xloc-25, yloc+54);
	}
	
	public void move(int x,int y, int nX, int nY){
		xloc = x;
		yloc = y;
		int nextX = nX;
		int nextY = nY;
		
		double xdif = Math.abs(nextX-x);
		double ydif = Math.abs(nextY-y);
		// System.out.println("y dif is" + ydif);
		double tan = Math.tan(xdif/ydif);
		
		double angle1 = Math.atan(tan);
	//	System.out.println("angle is" + Math.toDegrees(angle1));
		
	}
	
	public int getx(){
		return xloc;
	}
	
	public int gety(){
		return yloc;
	}
	
	public int getHealth(){
		return health;
	}
	
	public boolean takeDamage(){
		takingdamage = true;
		if(health - 10 > 0){
			health-=10;
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public double getAngle(){
		return angle;
	}
	
	public int getPower(){
		return power;
	}
	
	public void incrementAngle(double i){
		if(angle+i >= 0 && angle+i <= Math.PI){
			angle += i;
		}
	}
	
	public void stopDamage(){
		takingdamage= false;
	}
	public void incrementPower(int i){
		if(power+i >= 0 && power+i <=50){
			power += i;
		}
	}
}
