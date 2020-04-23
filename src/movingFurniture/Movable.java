package movingFurniture;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

public class Movable {
	
	private Location location;
	public final PropertyChangeSupport pcs;
	
	public Movable() {
		this(new Location(0,0));
	}
	public Location getLocation() {
		return location;
	}
	public Movable(Location init) {
		this.location = init;
		this.pcs = new PropertyChangeSupport(this);
	}
	
	public void addListener(PropertyChangeListener pl) {
		this.pcs.addPropertyChangeListener(pl);
	}
	
	public void move(Location newloc) {
		Location old = this.location;
		this.location = newloc;
		this.pcs.firePropertyChange("location", old, newloc);
	}
	
	// playing around, need to move to testing
	

}
