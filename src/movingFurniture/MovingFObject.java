package movingFurniture;

public abstract class MovingFObject extends Movable implements HasDimensionality, HasLocation {
	public final Location start;
	public final double width;
	public final double length;
	private Velocity velocity;
	public final Velocity maxVelocity;
	public final Location goal;
	public MovingFObject(double width, double length, Velocity initVel, Velocity maxVelocity, Location start, Location goal) {
		super(start);
		this.width = width;
		this.length = length;
		this.maxVelocity = maxVelocity;
		this.start = start;
		this.goal = goal;
		this.velocity = initVel;
	}
	public double getWidth() {
		return this.width;
	}
	public double getLength() {
		return this.length;
	}
	
	public Velocity getVelocity() {
		return velocity;
	}
	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	public String toString() {
		return "Current: X: " + getLocation().x + ", Y: " + getLocation().y + " Goal: X: " + goal.x + ", Y: " + goal.y;
	}
}
