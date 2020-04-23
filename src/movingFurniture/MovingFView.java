package movingFurniture;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class MovingFView implements PropertyChangeListener, Viewable{
	private Location location;
	public final double w;
	public final double h;
	public final PropertyChangeSupport pcs;
	public MovingFView(Location location, double w, double h) {
		this.location = location;
		this.w = w;
		this.h = h;
		pcs = new PropertyChangeSupport(this);
	}
	public Color getColor() {
		return Color.black;
	}
	public Shape getShape() {
		return new Rectangle2D.Double();
	}
	public Location getLocation() {
		return location;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Location newLoc = (Location) evt.getNewValue();
		this.location = newLoc;
	}
	
	public String toString() {
		return location.toString() + " w: " + w + "; h: " + h;
	}
}
