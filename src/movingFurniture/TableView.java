package movingFurniture;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;

public class TableView extends MovingFView implements Viewable{
	private Location location;
	public final double w;
	public final double h;
	public static final Color color = new Color(53,101,77);
	private Shape shape;
	public TableView(Location location, double w, double h) {
		super(location,w,h);
		this.location = location;
		this.w = w;
		this.h = h;
		this.shape = new Rectangle2D.Double(location.x - (w/2), location.y - (h/2),w,h);
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
		this.shape = new Rectangle2D.Double(newLoc.x, newLoc.y,w,h);
	}
	
	
}
