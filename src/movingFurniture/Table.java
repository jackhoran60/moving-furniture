package movingFurniture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Table extends Furniture {
	private Location location;
	private int width;
	private int length;
	
	Table(int width, int length, Location location){
		this.width = width;
		this.length = length;
		this.location = location;
	}
	Table(int width, int length, int x, int y){
		this.width = width;
		this.length = length;
		Location location = new Location(x,y);
		this.location = location;
	}
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	public void setLocation(int x, int y) {
		Location location = new Location(x,y);
		this.location = location;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public int distanceFromEdge(Location location) {
		double xDist = (Math.abs(location.getX()-this.location.getX()) - width) / 2;
		double yDist = (Math.abs(location.getY()-this.location.getY()) - width) / 2;
		int dist = (int) (Math.pow(xDist, 2) + Math.pow(yDist, 2));
		return dist;
	}

	@Override
	public boolean isTouchingLocation(Location location) {
		if(Math.abs(location.getX() - this.location.getX()) < width / 2) {
			return false;	
		}
		if(Math.abs(location.getY() - this.location.getY()) < length / 2) {
			return false;	
		}
		return true;
	}
	
	public void draw(Graphics g) {
		int x = location.getX();
		int y = location.getY();
		g.drawRect(x, y, width, length);
		
	}
}
