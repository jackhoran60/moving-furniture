package movingFurniture;

import java.awt.Graphics;

public class Chair extends Furniture{
	private Location location;
	private int radius;
	
	Chair(int radius, Location location){
		this.radius = radius;
		this.location = location;
	}
	Chair(int radius, int x, int y){
		this.radius = radius;
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int distanceFromEdge(Location location) {
		int dist = (int) (this.location.getDistance(location) - radius);
		return dist;
	}

	public boolean isTouchingLocation(Location location) {
		if(distanceFromEdge(location) >= 0) {
			return false;
		}
		return true;
	}
	public void draw(Graphics g) {
		
	}
	public int getWidth() {
		return 0;
	}
	public int getLength() {
		return 0;
	}

}
