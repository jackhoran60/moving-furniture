package movingFurniture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.util.ArrayList;

public abstract class MovingFObject {
	private Color color;
	private String Shape;
	private Location location;
	private Start start;
	private double width;
	private double length;
	private Goal goal;
	public MovingFObject(MovingFObject toDupe) {
		//deep copy
		width = toDupe.getWidth();
		length = toDupe.getLength();
		location = new Location(toDupe.getX(), toDupe.getY());
		start = new Start(toDupe.getStart().getX(),toDupe.getStart().getY());
		goal = new Goal(toDupe.getGoal().getX(),toDupe.getGoal().getY());
		color = Color.BLACK;
		Shape = "Rectangle";
	}
	public MovingFObject(double diameter, Start start, Goal goal) {
		width = diameter;
		length = diameter;
		location = new Location(start.getX(), start.getY());
		this.start = start;
		this.goal = goal;
		color = Color.BLACK;
		Shape = "Rectangle";
	}
	public MovingFObject(double width, double length, Start start, Goal goal) {
		this.width = width;
		this.length = length;
		location = new Location(start.getX(), start.getY());
		this.start = start;
		this.goal = goal;
		color = Color.BLACK;
		Shape = "Rectangle";
	}
	public abstract MovingFObject duplicate();
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getShape() {
		return Shape;
	}
	public void setShape(String shape) {
		Shape = shape;
	}
	public Start getStart() {
		return start;
	}
	public void setStart(Start start) {
		this.start = start;
	}
	public Goal getGoal() {
		return goal;
	}
	public void setGoal(Goal goal) {
		this.goal = goal;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getX() {
		return location.getX();
	}
	public double getY() {
		return location.getY();
	}
	public void setX(double x) {
		this.location.setX(x);
	}
	public void setY(double y) {
		this.location.setY(y);
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int distanceBetweenObjects(MovingFObject object) {
		//returns the distance between this object and another in parameter
		double xDist = Math.abs(location.getX() - object.location.getX()) - (width / 2) - (object.getWidth() / 2);
		double yDist = Math.abs(location.getX() - object.location.getX()) - (width / 2) - (object.getWidth() / 2);
		int dist = (int) (Math.pow(xDist, 2) + Math.pow(yDist, 2));
		return dist;
	}
	public boolean isTouchingObject(MovingFObject object) {
		//returns true if this object is touching object in parameter
		if(this.equals(object))
			return false;
		double xDist = Math.abs(location.getX() - object.location.getX()) - (width / 2) - (object.getWidth() / 2);
		if(xDist <= 0 && Math.abs(object.location.getY() - location.getY()) < length / 2 + object.length / 2)
			return true;
		double yDist = Math.abs(location.getY() - object.location.getY()) - (length / 2) - (object.getLength() / 2);
		if(yDist <= 0 && Math.abs(object.location.getX() - location.getX()) < width / 2 + object.width / 2)
			return true;
		return false;
	}
	public boolean locationTouchingLocation(int locType, MovingFObject object, int objLocType) {
		//ask professor cytron about
		//this method checks whether a particular type of this object's location (start, current location, or goal) is touching a particular location of the other obj in parameter
		//0 represents Start, 1 represents Location, and 2 represents Goal
		//I want to instead pass static references to Start, Location, and Goal
		if(this.equals(object))
			return false;
		
		Location temp;
		Location objTemp;
		if(locType == 0)
			temp = start;
		else if(locType == 1)
			temp = location;
		else
			temp = goal;
		if(objLocType == 0)
			objTemp = object.getStart();
		else if(objLocType == 1)
			objTemp = object.getLocation();
		else
			objTemp = object.getGoal();
		
		double xDist = Math.abs(temp.getX() - objTemp.getX()) - (width / 2) - (object.getWidth() / 2);
		if(xDist <= 0 && Math.abs(temp.getY() - objTemp.getY()) < length / 2 + object.length / 2)
			return true;
		double yDist = Math.abs(temp.getY() - objTemp.getY()) - (length / 2) - (object.getLength() / 2);
		if(yDist <= 0 && Math.abs(objTemp.getX() - temp.getX()) < width / 2 + object.width / 2)
			return true;
		return false;
		
	}
//	public String directionToObject(MovingObject object) {
//		//return closest direction between object
//		//returns "Left", "Right", "Up", or "Down"
//		String horizontal = "Left";
//		String vertical = "Up"; 
//		double xLen = object.getLocation().getX() - location.getX();
//		if(xLen > 0) {
//			horizontal = "Right";
//		}
//		double yLen = object.getLocation().getY() - location.getY();
//		if(yLen > 0) {
//			vertical = "Down";
//		}
//		if(Math.abs(xLen) > Math.abs(yLen)) {
//			return vertical;
//		}
//		return horizontal;
//	}
//	public int distanceFromEdge(Location location) {
//		double xDist = (Math.abs(location.getX()-this.location.getX()) - width) / 2;
//		double yDist = (Math.abs(location.getY()-this.location.getY()) - width) / 2;
//		int dist = (int) (Math.pow(xDist, 2) + Math.pow(yDist, 2));
//		return dist;
//	}
//	public boolean isTouchingLocation(Location location) {
//		if(Math.abs(location.getX() - this.location.getX()) < width / 2) {
//			return false;	
//		}
//		if(Math.abs(location.getY() - this.location.getY()) < length / 2) {
//			return false;	
//		}
//		return true;
//	}
//	public boolean moveLeft(Room room) {
//		if(location.getX()-this.width/2 > 0) {
//			ArrayList<MovingObject> o = room.getObjects();
//			location.setX(location.getX()-1);
//			for(int i = 0; i < room.getObjects().size(); i++) {
//				MovingObject mo = room.getObjects().get(i);
//				if(isTouchingObject(mo)) {
//					location.setX(location.getX()+1);
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
//	public boolean moveRight(Room room) {
//		if(location.getX()+this.width/2 < room.getWidth()) {
//			ArrayList<MovingObject> o = room.getObjects();
//			location.setX(location.getX()+1);
//			for(int i = 0; i < o.size(); i++) {
//				MovingObject mo = o.get(i);
//				if(isTouchingObject(mo)) {
//					location.setX(location.getX()-1);
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
//	public boolean moveUp(Room room) {
//		if(location.getY()-this.length/2 > 0) {
//			ArrayList<MovingObject> o = room.getObjects();
//			location.setY(location.getY()-1);
//			for(int i = 0; i < o.size(); i++) {
//				MovingObject mo = o.get(i);
//				if(isTouchingObject(mo)) {
//					location.setY(location.getY()+1);
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
//	public boolean moveDown(Room room) {
//		if(location.getY()+this.length/2 < room.getHeight()) {
//			ArrayList<MovingObject> o = room.getObjects();
//			location.setY(location.getY()+1);
//			for(int i = 0; i < o.size(); i++) {
//				MovingObject mo = o.get(i);
//				if(isTouchingObject(mo)) {
//					location.setY(location.getY()-1);
//					return false;
//				}
//			}
//			return true;
//		}
//		return false;
//	}
	public double getWidth() {
		return width;
	}
	public double getLength() {
		return length;
	}
	public String toString() {
		return "Current: X: " + location.getX() + ", Y: " + location.getY() + " Goal: X: " + goal.getX() + ", Y: " + goal.getY();
	}
}
