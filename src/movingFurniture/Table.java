package movingFurniture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Table extends MovingObject {
	
	public Table (int width, Location location) {
		super(width, location);
	}
	public Table (int width, int length, Location location) {
		super(width, length, location);
	}
	public Table(int width, Location location, Goal goal) {
		super(width, location, goal);
	}
	public Table(int width, int length, Location location, Goal goal) {
		super(width, length, location, goal);
	}
}
