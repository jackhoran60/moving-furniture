package movingFurniture;

public class Velocity {
	public final double magnitude;
	public final double direction;
	// magnitude is pixels / second
	// direction is in degrees with east = 0
	public Velocity (double magnitude, double direction) {
		this.magnitude = magnitude;
		this.direction = direction;
		System.out.println("new velocity!");
		System.out.println(this.toString());
	}
	public String toString() {
		return "Magnitude: " + magnitude + "pix/s; Direction: " + direction + " radians";
	}
	
}
