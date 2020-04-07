package movingFurniture;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Scenario {
	//a scenario is basically a consistent way
	private int defaultScenarioNum;
	private int width;
	private int height;
	private ArrayList<LinkedHashMap> spacetime;
	private LinkedHashMap<Integer, MovingFObject> initPos;
	private String description;
	
	public Scenario(int width, int height, LinkedHashMap<Integer, MovingFObject> initPos, String description) {
		spacetime = new ArrayList<LinkedHashMap>();
		spacetime.add(initPos);
		this.width = width;
		this.height = height;
		this.initPos = initPos;
		this.description = description;
	}
	public Scenario(int defaultScenarioNum) {
		//default scenarios so users can easily replicate various solutions
		if(defaultScenarioNum == 0) 
			scenario0();
		else if(defaultScenarioNum == 1) 
			scenario1();
			
	}
	private void scenario0() {
		width = 1000;
		height = 1000;
		Start t0s = new Start(200,500);
		Goal t0g = new Goal(800,500);
		Table t0 = new Table(50,t0s,t0g);
		initPos= new LinkedHashMap<Integer, MovingFObject>();
		initPos.put(0, t0);
		spacetime = new ArrayList<LinkedHashMap>();
		spacetime.add(initPos);
		description = "A single table must move towards its goal.";
	}
	private void scenario1() {
		width = 1000;
		height = 1000;
		Start t0s = new Start(200,500);
		Goal t0g = new Goal(800,500);
		Start t1s = new Start(800,500);
		Goal t1g = new Goal(200,500);
		Table t0 = new Table(50,t0s,t0g);
		Table t1 = new Table(50,t1s,t1g);
		initPos= new LinkedHashMap<Integer, MovingFObject>();
		initPos.put(0, t0);
		initPos.put(1, t1);
		spacetime = new ArrayList<LinkedHashMap>();
		spacetime.add(initPos);
		description = "Two tables must swap start and goal positions.";
	}
	public boolean checkSolution() {
		boolean correct = true;
		int len = spacetime.size();
		LinkedHashMap<Integer, MovingFObject> endPos = spacetime.get(len-1);
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
	public void addFrame() {
		int len = spacetime.size();
		LinkedHashMap<Integer, MovingFObject> endPos = spacetime.get(len-1);
		LinkedHashMap<Integer, MovingFObject> newEndPos = (LinkedHashMap<Integer, MovingFObject>) endPos.clone();
		spacetime.add(newEndPos);
	}
	public ArrayList<LinkedHashMap> getSpaceTime() {
		return spacetime;
	}
	public int getSize(){
		return spacetime.size();
	}
	public LinkedHashMap<Integer, MovingFObject> getInitPos() {
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
