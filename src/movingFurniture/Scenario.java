package movingFurniture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Scenario {
	//a scenario is basically how we'll handle moving objects virtually
	private int defaultScenarioNum;
	private int width;
	private int height;
	private ArrayList<Map<Integer, MovingFObject>> spacetime;
	private Map<Integer, MovingFObject> initPos;
	private String description;
	private double maxVelocity;
	
	public Scenario(int width, int height, Map<Integer, MovingFObject> initPos, String description) {
		defaultScenarioNum = -1;
		spacetime = new ArrayList<Map<Integer, MovingFObject>>();
		spacetime.add(initPos);
		this.width = width;
		this.height = height;
		this.initPos = initPos;
		this.description = description;
		this.maxVelocity = 1;
	}
	public double getMaxVelocity() {
		return maxVelocity;
	}
	public void setMaxVelocity(double maxVelocity) {
		this.maxVelocity = maxVelocity;
	}
	public static Scenario gen(int s) {
		int width = 1000;
		int height = 1000;
		String description = "";
		if(s==0) {
			Start t0s = new Start(200,500);
			Goal t0g = new Goal(800,500);
			Table t0 = new Table(50,t0s,t0g);
			Map<Integer, MovingFObject> initPos= new LinkedHashMap<Integer, MovingFObject>();
			initPos.put(0, t0);
			description = "A single table must move towards its goal.";
			return new Scenario(width, height, initPos, description);
		}
		else if(s==1) {
			Start t0s = new Start(200,500);
			Goal t0g = new Goal(800,500);
			Start c0s = new Start(800,500);
			Goal c0g = new Goal(200,500);
			Table t0 = new Table(50,t0s,t0g);
			Chair c0 = new Chair(50,c0s,c0g);
			Map<Integer, MovingFObject> initPos= new LinkedHashMap<Integer, MovingFObject>();
			initPos.put(0, t0);
			initPos.put(1, c0);
			description = "A table and chair must swap start and goal positions.";
			return new Scenario(width, height, initPos, description);
		}
		return null;
	}
	
	public boolean checkSolution() {
		boolean correct = true;
		int len = spacetime.size();
		Map<Integer, MovingFObject> endPos = spacetime.get(len-1);
		for(Integer I : endPos.keySet()) {
			MovingFObject f = endPos.get(I);
			if(f.getX() != f.getGoal().getX()) {
				System.out.println("FObject " + I + ": X: " + f.getX() + " Goal X: " + f.getGoal().getX());
				correct = false;
			}
			if(f.getY() != f.getGoal().getY()) {
				System.out.println("FObject " + I + ": Y: " + f.getY() + " Goal Y: " + f.getGoal().getY());
				correct = false;
			}
		}
		return correct;
	}
	public boolean frameExists(int frameNum) {
		//returns true if frameNum exists
		if(frameNum < spacetime.size())
			return true;
		return false;
	}
	public boolean hasNextFrame(int frameNum) {
		//see if there's a frame past this frame
		if(frameNum < spacetime.size() - 1) {
			return true;
		}
		return false;
	}
	public void addFrame() {
		int len = spacetime.size();
		Map<Integer, MovingFObject> endPos = spacetime.get(len-1);
		Map<Integer, MovingFObject> newEndPos = new LinkedHashMap<Integer, MovingFObject>();
		for(int i = 0; i < endPos.size(); i++) {
			MovingFObject newTemp = endPos.get(i);
			newEndPos.put(i, newTemp.duplicate());
		}
		//TODO: THIS MIGHT CAUSE ISSUES
		//TODO: MAKE SURE THIS IS NOT A SHALLOW COPY
		spacetime.add(newEndPos);
	}
	public void addFrames(int numOfFrames) {
		if(numOfFrames < 1)
			return;
		else {
			for(int i = 0; i <= numOfFrames; i++) {
				addFrame();
			}
		}
	}
	public ArrayList<Map<Integer, MovingFObject>> getSpaceTime() {
		return spacetime;
	}
	public int getSize(){
		return spacetime.size();
	}
	public Map<Integer, MovingFObject> getInitPos() {
		return initPos;
	}
	public void setInitPos(LinkedHashMap<Integer, MovingFObject> initPos) {
		this.initPos = initPos;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String toString() {
		return description;
	}
}
