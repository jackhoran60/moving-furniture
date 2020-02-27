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
	//TODO add inGoodStanding to movement, add intermediate move all objects to sides before fixing
	private int width;
	private int height;
	private final Color CHAIR_COLOR = new Color(150,75,0);
	private final Color TABLE_COLOR = new Color(53,101,77);
	private final int UPDATE_INTERVAL = 20;
	private boolean hitConferenceRoomPos = false;
	private boolean chairsInPos = false;
	private ArrayList<MovingObject> objects = new ArrayList<MovingObject>();
	
	
	public Room(int width, int height) {
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		
		Timer timer = new Timer(UPDATE_INTERVAL, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!hitConferenceRoomPos)
					moveObjectsToSides();
				else
					moveObjectsToGoal();
				repaint();
			}
        });
        timer.start();
	}
	
	public ArrayList<MovingObject> getObjects(){
		return objects;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < objects.size(); i++) {
			MovingObject s = objects.get(i);
			Location loc = s.getLocation();
			Graphics2D g2 = (Graphics2D) g;
			if(s instanceof Table) {
				g2.setColor(TABLE_COLOR);
				g2.fillRect(loc.getX() - (s.getWidth()/2), loc.getY() - (s.getLength()/2), s.getWidth(), s.getLength());
			}
			else if(s instanceof Chair) {
				g2.setColor(CHAIR_COLOR);
				g2.fillOval(loc.getX() - (s.getWidth()/2), loc.getY() - (s.getLength()/2), ((Chair) s).getWidth(), ((Chair) s).getWidth());
			}
		}
	}
	public boolean conferenceRoomInPos() {
		if(objects.size() == 0)
			return false;
		for(int i = 0; i < objects.size(); i++) {
			MovingObject mo = objects.get(i);
			if(mo instanceof Chair) {
				if(mo.getX() + (mo.getWidth()) < width - 1) {
					return false;
				}
			}
			if(mo instanceof Table) {
				if(mo.getX() - (mo.getWidth()/2) > 0) {
					return false;
				}
			}
		}
		return true;
	}
	public void moveChairsToGoal() {
		for(int i = 0; i < objects.size(); i++) {
			MovingObject f = objects.get(i);
			Location loc = f.getLocation();
			Goal goal = f.getGoal();
			if(f instanceof Chair) {
				if(goal.getX() > loc.getX()) {
					f.moveRight(this);
				}
				else if(goal.getX() < loc.getX()){
					f.moveLeft(this);
				}
				else if(goal.getY() > loc.getY()) {
					f.moveDown(this);
				}
				else if(goal.getY() < loc.getY()){
					f.moveUp(this);
				}
			}
		}
		for(int i = 0; i < objects.size(); i++) {
			MovingObject f = objects.get(i);
			Location loc = f.getLocation();
			Goal goal = f.getGoal();
			if(f instanceof Chair) {
				if(loc.getX() != goal.getX())
					return;
				if(loc.getY() != goal.getY())
					return;
			}
		}
		chairsInPos = true;
	}
	public void moveTablesToGoal() {
		for(int i = 0; i < objects.size(); i++) {
			MovingObject f = objects.get(i);
			Location loc = f.getLocation();
			Goal goal = f.getGoal();
			if(f instanceof Table) {
				if(goal.getX() > loc.getX()) {
					f.moveRight(this);
				}
				else if(goal.getX() < loc.getX()){
					f.moveLeft(this);
				}
				else if(goal.getY() > loc.getY()) {
					f.moveDown(this);
				}
				else if(goal.getY() < loc.getY()){
					f.moveUp(this);
				}
			}
		}
		for(int i = 0; i < objects.size(); i++) {
			MovingObject f = objects.get(i);
			Location loc = f.getLocation();
			Goal goal = f.getGoal();
			if(f instanceof Table) {
				if(loc.getX() != goal.getX())
					return;
				if(loc.getY() != goal.getY())
					return;
			}
		}
	}
	public void moveObjectsToGoal() {
		if(!chairsInPos) {
			moveChairsToGoal();
		}
		else {
			moveTablesToGoal();
		}
		
	}
	public void moveObjectsToSides() {
		if(!conferenceRoomInPos()) {
			for(int i = 0; i < objects.size(); i++) {
				MovingObject m = objects.get(i);
				if(m instanceof Chair) {
					if(m.getX() + (m.getWidth()) < width - 1) {
						if(!m.moveRight(this)) {
							if(!m.moveUp(this)) {
								m.moveDown(this);
							}
						}
					}
				}
				else {
					if(m.getX() - (m.getWidth()/2) > 0) {
						if(!m.moveLeft(this)) {
							if(!m.moveDown(this)) {
								m.moveUp(this);
							}
						}
					}
					
				}
			}
		}
		else {
			hitConferenceRoomPos = true;
			System.out.println("did we get here yet");
		}
	}
	public boolean objectStanding(MovingObject object) {
		//return true if object isn't touching other objects or the wall
		for(int i = 0; i < objects.size(); i++) {
			MovingObject o = objects.get(i);
			if(object.isTouchingObject(o)) {
				return false;
			}
				
				
		}
		if(object.getX() + (object.getWidth()/2) > width)
			return false;
		if(object.getX() - (object.getWidth()/2) < 0)
			return false;
		if(object.getY() + (object.getLength()/2) > height)
			return false;
		if(object.getY() - (object.getLength()/2) < 0)
			return false;
		
		return true;
	}
	public void randomlyAddTable(int width, int length) {
		int tempX = (int) (Math.random() * (this.width - width)) + (width/2);
		int tempY = (int) (Math.random() * (height - length)) + (length/2);
		Location location = new Location(tempX,tempY);
		Table table = new Table(width,length,location);
		randomlyAddObject(table);
	}
	public void randomlyAddChair(int diameter) {
		int tempX = (int) (Math.random() * (this.width - diameter)) + (diameter/2);
		int tempY = (int) (Math.random() * (height - diameter)) + (diameter/2);
		Location location = new Location(tempX,tempY);
		Chair chair = new Chair(diameter,location);
		randomlyAddObject(chair);
	}
	public void randomlyAddTable(int width, int length, Goal goal) {
		int tempX = (int) (Math.random() * (this.width - width)) + (width/2);
		int tempY = (int) (Math.random() * (height - length)) + (length/2);
		Location location = new Location(tempX,tempY);
		Table table = new Table(width,length,location,goal);
		randomlyAddObject(table);
	}
	public void randomlyAddChair(int diameter, Goal goal) {
		int tempX = (int) (Math.random() * (this.width - diameter)) + (diameter/2);
		int tempY = (int) (Math.random() * (height - diameter)) + (diameter/2);
		Location location = new Location(tempX,tempY);
		Chair chair = new Chair(diameter,location,goal);
		randomlyAddObject(chair);
	}
	public void randomlyAddObject(MovingObject object) {
		int tWidth = object.getWidth();
		int tLength = object.getLength();
		int tempX = (int) (Math.random() * (this.width - tWidth)) + (tWidth/2);
		int tempY = (int) (Math.random() * (height - tLength)) + (tLength/2);
		object.setX(tempX);
		object.setY(tempY);
		addObject(object);
		while(!objectStanding(object)) {
			tempX = (int) (Math.random() * (this.width - tWidth)) + (tWidth/2);
			tempY = (int) (Math.random() * (height - tLength)) + (tLength/2);
			object.setX(tempX);
			object.setY(tempY);
		}
		
	}
	public void addChair(int radius, int x, int y) {
		Location loc = new Location(x,y);
		MovingObject chair = new Chair(radius, loc);
		addObject(chair);
	}
	public void addChair(int radius, Location location) {
		MovingObject chair = new Chair(radius, location);
		addObject(chair);
	}
	public void addTable(int width, int length, int x, int y) {
		Location loc = new Location(x,y);
		MovingObject table = new Table(width, length, loc);
		addObject(table);
	}
	public void addTable(int width, int length, Location location) {
		MovingObject table = new Table(width, length, location);
		addObject(table);
	}
	

	public void addObject(MovingObject object) {
		this.objects.add(object);
	}
}
