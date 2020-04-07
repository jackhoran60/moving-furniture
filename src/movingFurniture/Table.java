package movingFurniture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Table extends MovingFObject {
	public Table(double width, Start start, Goal goal) {
		super(width, width, start, goal);
		setColor(new Color(53,101,77));
	}
	public Table(double width, double length, Start start, Goal goal) {
		super(width, length, start, goal);
		setColor(new Color(53,101,77));
	}
}
