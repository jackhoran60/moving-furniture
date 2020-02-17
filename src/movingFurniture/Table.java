package movingFurniture;

public class Table extends Furniture{
	private Location location;
	private double width;
	private double length;
	
	Table(double width, double length, Location location){
		this.width = width;
		this.length = length;
		this.location = location;
	}
	Table(double width, double length, double x, double y){
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
	public void setLocation(double x, double y) {
		Location location = new Location(x,y);
		this.location = location;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	@Override
	public double distanceFromEdge(Location location) {
		double xDist = (Math.abs(location.getX()-this.location.getX()) - width) / 2;
		double yDist = (Math.abs(location.getY()-this.location.getY()) - width) / 2;
		double dist = Math.pow(xDist, 2) + Math.pow(yDist, 2);
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

}
