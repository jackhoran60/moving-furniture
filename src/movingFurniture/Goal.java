package movingFurniture;

public class Goal {
	private Location location;
	
	public Goal(Location location) {
		this.location = location;
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
	public String toString() {
		return "X: " + location.getX() + ", Y: " + location.getY();
	}
}
