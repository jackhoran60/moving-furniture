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
	private ArrayList<MovingFObject> objects = new ArrayList<MovingFObject>();
	
	
	public Room(int width, int height) {
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		
		Timer timer = new Timer(UPDATE_INTERVAL, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//where to call something every tick
				repaint();
			}
        });
        timer.start();
	}
	
	public ArrayList<MovingFObject> getObjects(){
		return objects;
	}
	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		
//		for(int i = 0; i < objects.size(); i++) {
//			MovingFObject s = objects.get(i);
//			Location loc = s.getLocation();
//			Graphics2D g2 = (Graphics2D) g;
//			if(s instanceof Table) {
//				g2.setColor(TABLE_COLOR);
//				g2.fillRect(loc.getX() - (s.getWidth()/2), loc.getY() - (s.getLength()/2), s.getWidth(), s.getLength());
//			}
//			else if(s instanceof Chair) {
//				g2.setColor(CHAIR_COLOR);
//				g2.fillOval(loc.getX() - (s.getWidth()/2), loc.getY() - (s.getLength()/2), ((Chair) s).getWidth(), ((Chair) s).getWidth());
//			}
//		}
//	}
	
//	public boolean objectStanding(MovingFObject object) {
//		//return true if object isn't touching other objects or the wall
//		for(int i = 0; i < objects.size(); i++) {
//			MovingFObject o = objects.get(i);
//			if(object.isTouchingObject(o)) {
//				return false;
//			}
//				
//				
//		}
//		if(object.getX() + (object.getWidth()/2) > width)
//			return false;
//		if(object.getX() - (object.getWidth()/2) < 0)
//			return false;
//		if(object.getY() + (object.getLength()/2) > height)
//			return false;
//		if(object.getY() - (object.getLength()/2) < 0)
//			return false;
//		
//		return true;
//	}
//	public void randomlyAddTable(int width, int length) {
//		int tempX = (int) (Math.random() * (this.width - width)) + (width/2);
//		int tempY = (int) (Math.random() * (height - length)) + (length/2);
//		Location location = new Location(tempX,tempY);
//		Table table = new Table(width,length,location);
//		randomlyAddObject(table);
//	}
//	public void randomlyAddChair(int diameter) {
//		int tempX = (int) (Math.random() * (this.width - diameter)) + (diameter/2);
//		int tempY = (int) (Math.random() * (height - diameter)) + (diameter/2);
//		Location location = new Location(tempX,tempY);
//		Chair chair = new Chair(diameter,location);
//		randomlyAddObject(chair);
//	}
//	public void randomlyAddTable(int width, int length, Goal goal) {
//		int tempX = (int) (Math.random() * (this.width - width)) + (width/2);
//		int tempY = (int) (Math.random() * (height - length)) + (length/2);
//		Location location = new Location(tempX,tempY);
//		Table table = new Table(width,length,location,goal);
//		randomlyAddObject(table);
//	}
//	public void randomlyAddChair(int diameter, Goal goal) {
//		int tempX = (int) (Math.random() * (this.width - diameter)) + (diameter/2);
//		int tempY = (int) (Math.random() * (height - diameter)) + (diameter/2);
//		Location location = new Location(tempX,tempY);
//		Chair chair = new Chair(diameter,location,goal);
//		randomlyAddObject(chair);
//	}
//	public void randomlyAddObject(MovingObject object) {
//		int tWidth = object.getWidth();
//		int tLength = object.getLength();
//		int tempX = (int) (Math.random() * (this.width - tWidth)) + (tWidth/2);
//		int tempY = (int) (Math.random() * (height - tLength)) + (tLength/2);
//		object.setX(tempX);
//		object.setY(tempY);
//		addObject(object);
//		while(!objectStanding(object)) {
//			tempX = (int) (Math.random() * (this.width - tWidth)) + (tWidth/2);
//			tempY = (int) (Math.random() * (height - tLength)) + (tLength/2);
//			object.setX(tempX);
//			object.setY(tempY);
//		}
//		
//	}
	

	public void addObject(MovingFObject object) {
		this.objects.add(object);
	}
}
