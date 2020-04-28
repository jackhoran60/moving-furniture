package movingFurniture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScenarioController {
	public final Scenario scenario;
	public final ScenarioView sv;
	public final double stepTime; //the amount of time to take one step (in ms) (e.g. stepSize = 10 = 100 steps/second)
	public ScenarioController(Scenario scenario, ScenarioView sv, double stepTime) {
		this.scenario = scenario;
		this.sv = sv;
		this.stepTime = stepTime;
		sv.repaint();
	}
	public static ScenarioController facGen(int facGen) throws InterruptedException {
		if(facGen == 0) {
			ScenarioView sv = new ScenarioView("",1,1,new HashMap<Integer, MovingFView>());
			double stepTime = 0.1;
			Scenario scenario = Scenario.gen(facGen);
			return new ScenarioController(scenario,sv,stepTime);
		}
		return null;
	}
	public void step() {
		for(int i = 0; i < scenario.fobjects.size(); i++) {
			move(i);
		}
		sv.repaint();
	}
	public boolean setVelocity(int id, Velocity velocity) {
		if(velocity.magnitude <= scenario.fobjects.get(id).maxVelocity.magnitude && velocity.magnitude >= 0) {
			scenario.fobjects.get(id).setVelocity(velocity);
			return true;
		}
		return false;
	}
	private void move(int id) {
		//should this logic go in MovingFObject itself?
		MovingFObject fo = scenario.fobjects.get(id);
		double distTravelled = fo.getVelocity().magnitude * stepTime / 1000;
		double dx = Math.cos(fo.getVelocity().direction) * distTravelled;
		double dy = Math.sin(fo.getVelocity().direction) * distTravelled;
		Location newLoc = new Location(fo.getLocation().x + dx, fo.getLocation().y + dy);
		fo.move(newLoc);
	}
	public int getSize() {
		return scenario.fobjects.size();
	}
	
	
//	public Scenario getScenario() {
//		return scenario;
//	}
//	public double getMaxVelocity() {
//		return scenario.maxVelocity;
//	}
	
//	public boolean moveAtVelToLoc(int id, double velocity, Location location) {
//		//moves object with id in map id at velocity velocity to location location
//		//returns true if velocity < maxVelocity
//		if(velocity > scenario.maxVelocity)
//			return false;
//		MovingFObject t = (MovingFObject) scenario.getSpaceTime().get(0).get(id);
//		double deltaX = location.getX() - t.getX();
//		double deltaY = location.getY() - t.getY();
//		if(deltaX == 0 && deltaY == 0)
//			return true;
//		int frameNum = 0;
//		boolean xInPos = false;
//		boolean yInPos = false;
//		while (!xInPos || !yInPos) {
//			if(!scenario.hasNextFrame(frameNum)) {
//				scenario.addFrame();
//			}
//			frameNum++;
//			MovingFObject temp = (MovingFObject) scenario.getSpaceTime().get(frameNum).get(id);
//			double sum = Math.abs(deltaX) + Math.abs(deltaY);
//			double moveX = velocity * deltaX / sum;
//			double moveY = velocity * deltaY / sum;
//			if(Math.abs(deltaX) <= Math.abs(moveX)) {
//				temp.setX(location.getX());
//				xInPos = true;
//			}
//			else {
//				temp.setX(moveX + temp.getX());
//			}
//			if(Math.abs(deltaY) <= Math.abs(moveY)) {
//				temp.setY(location.getY());
//				yInPos = true;
//			}
//			else {
//				temp.setY(moveY + temp.getY());
//			}
//			deltaY = location.getY() - temp.getY();
//			deltaX = location.getX() - temp.getX();
//		}
//		return true;
//	}
//	public boolean moveAtVelAtTimeToLoc(int id, double velocity, int firstFrameNum, Location location) {
//		//moves object with id in map at velocity to location starting at frame firstFrameNum
//		//returns true if velocity < maxVelocity
//		if(velocity > scenario.getMaxVelocity())
//			return false;
//		MovingFObject t = (MovingFObject) scenario.getSpaceTime().get(firstFrameNum).get(id);
//		double deltaX = location.getX() - t.getX();
//		double deltaY = location.getY() - t.getY();
//		if(deltaX == 0 && deltaY == 0)
//			return true;
//		int frameNum = firstFrameNum;
//		boolean xInPos = false;
//		boolean yInPos = false;
//		Location tempLoc = t.getLocation();
//		while (!xInPos || !yInPos) {
//			if(!scenario.hasNextFrame(frameNum)) {
//				scenario.addFrame();
//			}
//			frameNum++;
//			MovingFObject temp = (MovingFObject) scenario.getSpaceTime().get(frameNum).get(id);
//			temp.setLocation(new Location(tempLoc.getX(),tempLoc.getY()));
//			tempLoc = temp.getLocation();
//			double sum = Math.abs(deltaX) + Math.abs(deltaY);
//			double moveX = velocity * deltaX / sum;
//			double moveY = velocity * deltaY / sum;
//			System.out.println(temp.getX());
//			if(Math.abs(deltaX) <= Math.abs(moveX)) {
//				temp.setX(location.getX());
//				xInPos = true;
//			}
//			else {
//				temp.setX(moveX + temp.getX());
//				if(id==1) {
//					System.out.println(temp.getX());
//				}
//			}
//			if(Math.abs(deltaY) <= Math.abs(moveY)) {
//				temp.setY(location.getY());
//				yInPos = true;
//			}
//			else {
//				temp.setY(moveY + temp.getY());
//			}
//			deltaY = location.getY() - temp.getY();
//			deltaX = location.getX() - temp.getX();
//		}
//		return true;
//	}
//
//	public ArrayList<Location> traceLinearMotion(int id, double velocity, Location location){
//		//this method returns an arraylist of all the locations the object will take moving to location in parameter
//		//the index in the arraylist represents the frame the obj will be in a given location (assuming it begins at frame 0)
//		if(velocity > scenario.getMaxVelocity())
//			return null;
//		MovingFObject t = (MovingFObject) scenario.getSpaceTime().get(0).get(id);
//		Location tl = t.getLocation();
//		double deltaX = location.getX() - tl.getX();
//		double deltaY = location.getY() - tl.getY();
//		if(deltaX == 0 && deltaY == 0)
//			return null;
//		boolean xInPos = false;
//		boolean yInPos = false;
//		ArrayList<Location> trace = new ArrayList<Location>();
//		trace.add(tl);
//		
//		while (!xInPos || !yInPos) {
//
////			Location nextLoc = new Location(tl.getX(),tl.getY());
////			tl = temp.getLocation();
//			double sum = Math.abs(deltaX) + Math.abs(deltaY);
//			double moveX = velocity * deltaX / sum;
//			double moveY = velocity * deltaY / sum;
//			Location newloc = new Location(trace.get(trace.size()-1).getX(),trace.get(trace.size()-1).getY());
//			if(Math.abs(deltaX) <= Math.abs(moveX)) {
//				newloc.setX(location.getX());
//				xInPos = true;
//			}
//			else {
//				newloc.setX(newloc.getX() + moveX);
//			}
//			if(Math.abs(deltaY) <= Math.abs(moveY)) {
//				newloc.setY(location.getY());
//				yInPos = true;
//			}
//			else {
//				newloc.setY(newloc.getY() + moveY);
//			}
//			trace.add(newloc);
//			deltaY = location.getY() - newloc.getY();
//			deltaX = location.getX() - newloc.getX();
//		}
//		return trace;
//	}
}
