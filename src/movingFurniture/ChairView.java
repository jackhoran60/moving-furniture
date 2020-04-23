package movingFurniture;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;

public class ChairView extends MovingFView implements Viewable {
	private Location location;
	public final double d;
	private Shape shape;
	public static final Color color = new Color(150,75,0);
	public ChairView(Location location, double d) {
		super(location,d,d);
		this.location = location;
		this.d = d;
		this.shape = new Ellipse2D.Double(location.x - (d/2), location.y - (d/2),d,d);
	}
	@Override
	public Shape getShape() {
		return this.shape;
	}
	@Override
	public Color getColor() {
		return this.color;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Location newLoc = (Location) evt.getNewValue();
		this.location = newLoc;
		this.shape = new Ellipse2D.Double(location.x - (d/2), location.y - (d/2),d,d);
	}
	
}
