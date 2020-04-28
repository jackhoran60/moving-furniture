package movingFurniture;

import java.awt.Color;
import java.awt.Graphics;

public class Chair extends MovingFObject{
	public Chair(double diameter, Velocity initVelocity, Velocity maxVelocity, Start start, Goal goal) {
		super(diameter, diameter, initVelocity, maxVelocity, start, goal);
	}
	
	
}
