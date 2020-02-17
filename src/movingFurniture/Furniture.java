package movingFurniture;

public abstract class Furniture {
	private Location location;
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public abstract double distanceFromEdge(Location location);
	public abstract boolean isTouchingLocation(Location location);
}
