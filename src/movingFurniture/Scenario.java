package movingFurniture;

import java.util.LinkedHashMap;
import java.util.Map;

public class Scenario{
	public final int width;
	public final int height;
	public final Map<Integer, MovingFObject> fobjects;
	public final String description;
	public Scenario(int width, int height, Map<Integer, MovingFObject> fobjects, String description) {
		this.width = width;
		this.height = height;
		this.fobjects = fobjects;
		this.description = description;
	}
	public static Scenario gen(int genKey) {
		//TODO: move facGen to controller
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
			return new Scenario(width, height, initPos, description);
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
			return new Scenario(width, height, initPos, description);
		}
		return null;
	}
	public int count() {
		return fobjects.size();
	}
	public String toString() {
		return description;
	}
}
