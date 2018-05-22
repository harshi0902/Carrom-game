import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GridBasedGameDriver {

	private JFrame frame = new JFrame("Tank Game.  Destroy your enemy");
	private JPanel panel;
	private List<Drawable> drawables = new ArrayList<Drawable>();
	private Terrain terrain;
	private Tanks tank1;
	private Tanks tank2;
	private Timer timer;
	private Projectile missile;
	private boolean turn = true;
	private Explosion boom;
	private int ExplosionTime;
	private boolean Moving = true;
	private Intro introduction;

	public static void main(String[] args) {
		new GridBasedGameDriver().start();
	}

	private void start() {
		setUpGame();

		int delay = 100;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (Moving) {
					missile.move();
					if (missile.getX() >= 800 || missile.getX() <= 0 || missile.getY() < 0) {
						timer.stop();
					} else if (missile.getX() <= tank2.getx() + 20 && missile.getX() >= tank2.getx() - 20
							&& missile.getY() <= tank2.gety() + 20 && missile.getY() >= tank2.gety() - 20) {
						timer.stop();
						if (!tank2.takeDamage()) {
							gameOver(1);
						}
						System.out.println(tank2.getHealth());
						drawables.remove(missile);
					} else if (missile.getX() <= tank1.getx() + 20 && missile.getX() >= tank1.getx() - 20
							&& missile.getY() <= tank1.gety() + 20 && missile.getY() >= tank1.gety() - 20) {
						timer.stop();
						if (!tank1.takeDamage()) {
							gameOver(2);
						}
						System.out.println(tank1.getHealth());
						drawables.remove(missile);
					} else if (missile.getY() >= terrain.getHeights().get(missile.getX())) {
						boom = new Explosion(missile.getX(), missile.getY(), 40);
						drawables.add(boom);
						drawables.remove(missile);
						damageTanks(boom);
						destroyTerrain(boom);
						ExplosionTime = 0;
						Moving = false;
						tank1.move(tank1.getx(), terrain.getHeights().get(tank1.getx()), tank1.getx() + 1,
								terrain.getHeights().get(tank1.getx() + 1));
						tank2.move(tank2.getx(), terrain.getHeights().get(tank2.getx()), tank2.getx() + 1,
								terrain.getHeights().get(tank2.getx() + 1));
					}
				} else {

					if (ExplosionTime == 10) {
						drawables.remove(boom);
						Moving = true;
						timer.stop();
					} else {
						ExplosionTime++;
					}
				}
				frame.repaint();
			}
		};
		timer = new Timer(delay, taskPerformer);

		this.frame.setBackground(new Color(0, 0, 0));
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "switchTurn");
		panel.getActionMap().put("switchTurn", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				turn = !turn;

			}
		});

		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "slideLeft");
		panel.getActionMap().put("slideLeft", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (currentTank().getx() - 4 >= 0) {
					currentTank().move(currentTank().getx() - 3, terrain.getHeights().get(currentTank().getx() - 3),
							currentTank().getx() - 4, terrain.getHeights().get(currentTank().getx() - 4));
					frame.repaint();
				}
			}
		});

		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "slideRight");
		panel.getActionMap().put("slideRight", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (currentTank().getx() + 4 < panel.getWidth()) {
					currentTank().move(currentTank().getx() + 3, terrain.getHeights().get(currentTank().getx() + 3),
							currentTank().getx() + 4, terrain.getHeights().get(currentTank().getx() + 4));
					frame.repaint();
				}
			}
		});

		panel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "fireShot");
		panel.getActionMap().put("fireShot", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!timer.isRunning()) {
					fire(currentTank());
					frame.repaint();
					turn = !turn;
				}

			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("A"), "decreaseAngle");
		panel.getActionMap().put("decreaseAngle", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentTank().incrementAngle(Math.PI / 72);
				frame.repaint();
			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("D"), "increaseAngle");

		panel.getActionMap().put("increaseAngle", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentTank().incrementAngle(-Math.PI / 72);
				frame.repaint();
			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("UP"), "increasePower");
		panel.getActionMap().put("increasePower", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentTank().incrementPower(1);
				frame.repaint();
			}
		});
		panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "decreasePower");
		panel.getActionMap().put("decreasePower", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				currentTank().incrementPower(-1);
				frame.repaint();
			}
		});
		panel.requestFocusInWindow();
		// panel.addKeyListener(l);

		setUpObjects();
		frame.repaint();

	}

	protected void damageTanks(Explosion b) {
		// TODO Auto-generated method stub
		if (Math.sqrt((tank1.getx() - b.getX()) * (tank1.getx() - b.getX())
				- (tank1.gety() - b.getY()) * (tank1.gety() - b.getY())) <= b.getRadius()) {
			tank1.takeDamage();

		}
		if (Math.sqrt((tank2.getx() - b.getX()) * (tank2.getx() - b.getX())
				- (tank2.gety() - b.getY()) * (tank2.gety() - b.getY())) <= b.getRadius()) {
			tank2.takeDamage();

		}

		// panel.repaint();
	}

	public void destroyTerrain(Explosion b) {
		// TODO Auto-generated method stub
		for (int index = 0; index <= b.getRadius() * 2; index++) {
			if (b.getX() - b.getRadius() + index >= 0 && b.getX() - b.getRadius() + index < 800) {
				if (terrain.getHeights().get(b.getX() - b.getRadius() + index) <= (b.getY() + (int) Math
						.sqrt((b.getRadius() * b.getRadius() - (b.getRadius() - index) * (b.getRadius() - index))))) {
					terrain.setHeight(b.getX() - b.getRadius() + index, b.getY() + (int) Math
							.sqrt((b.getRadius() * b.getRadius() - (b.getRadius() - index) * (b.getRadius() - index))));
				}
			}
		}

	}

	public void fire(Tanks tank) {
		missile = new Projectile(tank.getx(), tank.gety() - 20, (int) (tank.getPower() * Math.cos(tank.getAngle())),
				(int) (tank.getPower() * Math.sin(tank.getAngle())), Math.toDegrees(tank.getAngle()) > 90);
		drawables.add(missile);
		timer.start();

	}

	private void setUpObjects() {
		// TODO Auto-generated method stub

		makeTerrain(panel.getWidth(), panel.getHeight());
		makeTanks();

	}

	private void makeTanks() {
		// TODO Auto-generated method stub
		tank1 = new Tanks(100, terrain.getHeights().get(100), Color.GREEN);
		tank2 = new Tanks(700, terrain.getHeights().get(700), Color.RED);

		drawables.add(tank1);
		drawables.add(tank2);
	}

	private void makeTerrain(int width, int height) {
		// TODO Auto-generated method stub
		terrain = new Terrain(width, height);
		drawables.add(terrain);
	}

	private void startintro() {
		introduction = new Intro();
		drawables.add(introduction);
	}

	private void stopintro() {
		drawables.remove(introduction);
	}

	private Tanks currentTank() {
		if (turn) {
			return tank1;
		} else {
			return tank2;
		}
	}

	protected void drawGame(Graphics g) {
		for (Drawable dr : drawables) {
			dr.draw(g);
		}

	}

	private void setUpGame() {
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawGame(g);
			}

		};
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setBackground(Color.CYAN);
		frame.add(panel);
		frame.pack();

	}

	private void gameOver(int winner) {
		// TODO Auto-generated method stub
		System.out.println("Tank " + winner + " has won!");

		new GridBasedGameDriver().start();
	}

}
