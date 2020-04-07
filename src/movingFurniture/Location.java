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
	public String toString() {
		return "X: " + x + " Y: " + y;
	}
}
