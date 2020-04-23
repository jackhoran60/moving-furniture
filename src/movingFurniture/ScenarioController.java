package movingFurniture;

import java.util.HashMap;
import java.util.Map;

public class ScenarioController {
	public final Scenario scenario;
	public final ScenarioView sv;
	public final double maxVelocity;
	public static void main(String[] args) throws InterruptedException {
		ScenarioController sc = new ScenarioController();
		Map<Integer, MovingFObject> map = sc.getFObjects();
		while (true) {
			for(int i = 0; i < map.size(); i++) {
				MovingFObject temp = map.get(i);
				Location get = temp.getLocation();
				Location move = new Location(get.x+0.5,get.y+0.5);
				sc.move(i, move);
				sc.sv.repaint();
				//System.out.println(sc.sv.initPos.get(0));
				Thread.sleep(20);
			}
		}
	}
	public ScenarioController() throws InterruptedException {
		//this is playground scenariocontroller
		maxVelocity = 1;
		Start start = new Start(100, 100);
		Goal goal = new Goal(500, 700);
		Table table = new Table(100, 100, 1, start, goal);
		TableView tv = new TableView(table.getLocation(),table.width,table.length);
		table.addListener(tv);
		Map<Integer, MovingFObject> map = new HashMap<Integer,MovingFObject>();
		map.put(0, table);
		scenario = new Scenario(1000,1000,1,map,"playground");
		Map<Integer, MovingFView> mapview = new HashMap<Integer, MovingFView>();
		mapview.put(0,tv);
		
		sv = new ScenarioView("playground", 1000,1000, mapview);
		sv.repaint();
	}
	public ScenarioController(int facGen, double maxVelocity) throws InterruptedException {
		this.maxVelocity = maxVelocity;
		sv = new ScenarioView("",1,1,new HashMap<Integer, MovingFView>());
		scenario = Scenario.gen(facGen, maxVelocity);
		
	}
	public static ScenarioController facGen(int facGen, double maxVelocity) throws InterruptedException {
		return new ScenarioController(facGen, maxVelocity);
	}
	public boolean move(int id, Location newLoc) {
		if(id >= scenario.count())
			return false;
		MovingFObject fo = scenario.fobjects.get(id);
		if(fo.canMoveTo(newLoc)) {
			Location oldLoc = fo.getLocation();
			fo.move(newLoc);
			return true;
		}
		return false;
		
	}
//	public Scenario getScenario() {
//		return scenario;
//	}
//	public double getMaxVelocity() {
//		return scenario.maxVelocity;
//	}
	public Map<Integer, MovingFObject> getFObjects() {
		return scenario.fobjects;
	}
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
