package movingFurniture;

public class Chair extends Furniture{
	private Location location;
	private double radius;
	
	Chair(double radius, Location location){
		this.radius = radius;
		this.location = location;
	}
	Chair(double radius, double x, double y){
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
	public void setLocation(double x, double y) {
		Location location = new Location(x,y);
		this.location = location;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double distanceFromEdge(Location location) {
		double dist = this.location.getDistance(location) - radius;
		return dist;
	}

	public boolean isTouchingLocation(Location location) {
		if(distanceFromEdge(location) >= 0) {
			return false;
		}
		return true;
	}

}
