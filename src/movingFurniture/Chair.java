package movingFurniture;

import java.awt.Graphics;

public class Chair extends MovingObject{
	
	public Chair (int width, Location location) {
		super(width, location);
	}
	public Chair(int width, int length, Location location) {
		super(width, length, location);
	}
	public Chair(int width, Location location, Goal goal) {
		super(width, location, goal);
	}
	public Chair(int width, int length, Location location, Goal goal) {
		super(width, length, location, goal);
	}
	
}
