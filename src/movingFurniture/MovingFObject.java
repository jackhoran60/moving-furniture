package movingFurniture;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class MovingObject {
	private Location location;
	private int width;
	private int length;
	private Goal goal;
	
	public MovingObject(int width, Location location) {
		this.width = width;
		this.length = width;
		this.location = location;
	}
	public MovingObject(int width, int length, Location location) {
		this.width = width;
		this.length = length;
		this.location = location;
	}
	public MovingObject(int width, Location location, Goal goal) {
		this.width = width;
		this.length = width;
		this.location = location;
		this.goal = goal;
	}
	public MovingObject(int width, int length, Location location, Goal goal) {
		this.width = width;
		this.length = length;
		this.location = location;
		this.goal = goal;
	}
	public void setGoal(Goal goal) {
		this.goal = goal;
	}
	public Goal getGoal() {
		return goal;
	}
	public int getX() {
		return location.getX();
	}
	public int getY() {
		return location.getY();
	}
	public void setX(int x) {
		this.location.setX(x);
	}
	public void setY(int y) {
		this.location.setY(y);
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int distanceBetweenObjects(MovingObject object) {
		double xDist = Math.abs(location.getX() - object.location.getX()) - (width / 2) - (object.getWidth() / 2);
		double yDist = Math.abs(location.getX() - object.location.getX()) - (width / 2) - (object.getWidth() / 2);
		int dist = (int) (Math.pow(xDist, 2) + Math.pow(yDist, 2));
		return dist;
	}
	public boolean isTouchingObject(MovingObject object) {
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
	public String directionToObject(MovingObject object) {
		//return closest direction between object
		//returns "Left", "Right", "Up", or "Down"
		String horizontal = "Left";
		String vertical = "Up"; 
		double xLen = object.getLocation().getX() - location.getX();
		if(xLen > 0) {
			horizontal = "Right";
		}
		double yLen = object.getLocation().getY() - location.getY();
		if(yLen > 0) {
			vertical = "Down";
		}
		if(Math.abs(xLen) > Math.abs(yLen)) {
			return vertical;
		}
		return horizontal;
	}
	public int distanceFromEdge(Location location) {
		double xDist = (Math.abs(location.getX()-this.location.getX()) - width) / 2;
		double yDist = (Math.abs(location.getY()-this.location.getY()) - width) / 2;
		int dist = (int) (Math.pow(xDist, 2) + Math.pow(yDist, 2));
		return dist;
	}
	public boolean isTouchingLocation(Location location) {
		if(Math.abs(location.getX() - this.location.getX()) < width / 2) {
			return false;	
		}
		if(Math.abs(location.getY() - this.location.getY()) < length / 2) {
			return false;	
		}
		return true;
	}
	public boolean moveLeft(Room room) {
		if(location.getX()-this.width/2 > 0) {
			ArrayList<MovingObject> o = room.getObjects();
			location.setX(location.getX()-1);
			for(int i = 0; i < room.getObjects().size(); i++) {
				MovingObject mo = room.getObjects().get(i);
				if(isTouchingObject(mo)) {
					location.setX(location.getX()+1);
					return false;
				}
			}
			return true;
		}
		return false;
	}
	public boolean moveRight(Room room) {
		if(location.getX()+this.width/2 < room.getWidth()) {
			ArrayList<MovingObject> o = room.getObjects();
			location.setX(location.getX()+1);
			for(int i = 0; i < o.size(); i++) {
				MovingObject mo = o.get(i);
				if(isTouchingObject(mo)) {
					location.setX(location.getX()-1);
					return false;
				}
			}
			return true;
		}
		return false;
	}
	public boolean moveUp(Room room) {
		if(location.getY()-this.length/2 > 0) {
			ArrayList<MovingObject> o = room.getObjects();
			location.setY(location.getY()-1);
			for(int i = 0; i < o.size(); i++) {
				MovingObject mo = o.get(i);
				if(isTouchingObject(mo)) {
					location.setY(location.getY()+1);
					return false;
				}
			}
			return true;
		}
		return false;
	}
	public boolean moveDown(Room room) {
		if(location.getY()+this.length/2 < room.getHeight()) {
			ArrayList<MovingObject> o = room.getObjects();
			location.setY(location.getY()+1);
			for(int i = 0; i < o.size(); i++) {
				MovingObject mo = o.get(i);
				if(isTouchingObject(mo)) {
					location.setY(location.getY()-1);
					return false;
				}
			}
			return true;
		}
		return false;
	}
	public int getWidth() {
		return width;
	}
	public int getLength() {
		return length;
	}
	public String toString() {
		return "Current: X: " + location.getX() + ", Y: " + location.getY() + " Goal: X: " + goal.getX() + ", Y: " + goal.getY();
	}
}
