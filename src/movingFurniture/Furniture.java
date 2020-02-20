package movingFurniture;

import java.awt.Graphics;

public abstract class Furniture {
	private Location location;
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public abstract int distanceFromEdge(Location location);
	public abstract boolean isTouchingLocation(Location location);
	public abstract void draw(Graphics g);
	public abstract int getWidth();
	public abstract int getLength();
}
