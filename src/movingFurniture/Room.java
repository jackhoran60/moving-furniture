package movingFurniture;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Room extends JPanel {
	
	private int width;
	private int height;
	private final Color CHAIR_COLOR = new Color(150,75,0);
	private final Color TABLE_COLOR = new Color(53,101,77);
	private ArrayList<Furniture> furniture = new ArrayList<Furniture>();
	
	public Room(int width, int height) {
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		
		Timer timer = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveFurniture();
				repaint();
			}
        });
        timer.start();
	}
	
	public ArrayList<Furniture> getFurniture(){
		return furniture;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Furniture s : furniture) {
			Location loc = s.getLocation();
			Graphics2D g2 = (Graphics2D) g;
			if(s instanceof Table) {
				g2.setColor(TABLE_COLOR);
				g2.fillRect(loc.getX(), loc.getY(), s.getWidth(), s.getLength());
			}
			else if(s instanceof Chair) {
				g2.setColor(CHAIR_COLOR);
				g2.fillOval(loc.getX(), loc.getY(), ((Chair) s).getRadius(), ((Chair) s).getRadius());
			}
		}
	}
	public void moveFurniture() {
		for(Furniture f : furniture) {
				Location loc = f.getLocation();
				if(Math.random() > .5) {
					loc.setX(loc.getX() + (int) (5 * Math.random()));
				}
				else {
					loc.setY(loc.getY() + (int) (7 * Math.random()));
				}
			
		}
	}
	
	public void addChair(int radius, int x, int y) {
		Furniture chair = new Chair(radius, x, y);
		addFurniture(chair);
	}
	public void addChair(int radius, Location location) {
		Furniture chair = new Chair(radius, location);
		addFurniture(chair);
	}
	public void addTable(int width, int length, int x, int y) {
		Furniture table = new Table(width, length, x, y);
		addFurniture(table);
	}
	public void addTable(int width, int length, Location location) {
		Furniture table = new Table(width, length, location);
		addFurniture(table);
	}
	

	public void addFurniture(Furniture furniture) {
		this.furniture.add(furniture);
	}
}
