package movingFurniture;

public class Location {
	private double x;
	private double y;
	Location(double x, double y){
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getDistance(Location otherLoc) {
		double xDist = Math.pow(otherLoc.getX()-x, 2);
		double yDist = Math.pow(otherLoc.getY()-y, 2);
		double dist = Math.sqrt(xDist+yDist);
		return dist;
	}
}
