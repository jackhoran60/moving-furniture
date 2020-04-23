package movingFurniture;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Scenario implements PropertyChangeListener {
	public final int width;
	public final int height;
	public final Map<Integer, MovingFObject> fobjects;
	public final String description;
	public final double maxVelocity;
	public final PropertyChangeSupport pcs;
	public Scenario(int width, int height, double maxVelocity, Map<Integer, MovingFObject> fobjects, String description) {
		this.width = width;
		this.height = height;
		this.fobjects = fobjects;
		this.description = description;
		this.maxVelocity = maxVelocity;
		//listens to individual objects??
		for(int i = 0; i < fobjects.size(); i++) {
			fobjects.get(i).addListener(this);
		}
		this.pcs = new PropertyChangeSupport(this);
	}
	public static Scenario gen(int genKey, double maxVelocity) {
		int width = 1000;
		int height = 1000;
		String description = "";
		if(genKey==0) {
			Start t0s = new Start(200,500);
			Goal t0g = new Goal(800,500);
			Table t0 = new Table(50,50, 1, t0s,t0g);
			Map<Integer, MovingFObject> initPos = new LinkedHashMap<Integer, MovingFObject>();
			initPos.put(0, t0);
			description = "A single table must move towards its goal.";
			return new Scenario(width, height, maxVelocity, initPos, description);
		}
		else if(genKey==1) {
			Start t0s = new Start(200,500);
			Goal t0g = new Goal(800,500);
			Start c0s = new Start(800,500);
			Goal c0g = new Goal(200,500);
			Table t0 = new Table(50,50,1,t0s,t0g);
			Chair c0 = new Chair(50,1,c0s,c0g);
			Map<Integer, MovingFObject> initPos= new LinkedHashMap<Integer, MovingFObject>();
			initPos.put(0, t0);
			initPos.put(1, c0);
			description = "A table and chair must swap start and goal positions.";
			return new Scenario(width, height, maxVelocity, initPos, description);
		}
		return null;
	}
	public int count() {
		return fobjects.size();
	}
	public String toString() {
		return description;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//repaint view ????
		
	}
}
