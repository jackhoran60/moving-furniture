package movingFurniture;

import java.awt.Color;
import java.awt.Graphics;

public class Chair extends MovingFObject{
	public Chair(double diameter, Start start, Goal goal) {
		super(diameter, start, goal);
		setColor(new Color(150,75,0));
		setShape("Circle");
	}
	
}
