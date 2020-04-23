package movingFurniture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

// make this extend Movable
// get rid of color and move that to visualizer
public class Table extends MovingFObject {
	public Table(double width, double length, double maxVelocity, Start start, Goal goal) {
		super(width, length, maxVelocity, start, goal);
	}
}
