package movingFurniture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

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
		if(facGen == 1) {
			Start start = new Start(300, 300);
			Start start2 = new Start(500, 500);
			Goal goal = new Goal(500, 700);
			Table table = new Table(100, 100,new Velocity(100,1), new Velocity(250,0), start, goal);
			Table table2 = new Table(100, 100,new Velocity(100,Math.PI + 0.4), new Velocity(250,0), start2, goal);
			TableView tv = new TableView(table.getLocation(),table.width,table.length);
			TableView tv2 = new TableView(table2.getLocation(),table2.width,table2.length);
			table.addListener(tv);
			table2.addListener(tv2);
			Map<Integer, MovingFObject> map = new HashMap<Integer,MovingFObject>();
			map.put(0, table);
			map.put(1, table2);
			Scenario scenario = new Scenario(800,800,map,"playground");
			Map<Integer, MovingFView> mapview = new HashMap<Integer, MovingFView>();
			mapview.put(0,tv);
			mapview.put(1, tv2);
			ScenarioView sv = new ScenarioView("playground", 800,800, mapview);
			double stepTime = 10;
			ScenarioController sc = new ScenarioController(scenario,sv,stepTime);
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(2);
			Runnable updater = new Runnable() {
				public void run() { sc.step();
									sv.repaint(); }
			};
			ScheduledFuture scheduleHandle = scheduler.scheduleWithFixedDelay(updater,(long)sc.stepTime,(long)sc.stepTime,TimeUnit.MILLISECONDS);
			return sc;
		}
		return null;
	}
	public void step() {
		for(int i = 0; i < scenario.fobjects.size(); i++) {
			move(i);
		}
	}
	public boolean setVelocity(int id, Velocity velocity) {
		if(velocity.magnitude <= scenario.fobjects.get(id).maxVelocity.magnitude && velocity.magnitude >= 0) {
			scenario.fobjects.get(id).setVelocity(velocity);
			return true;
		}
		return false;
	}
	private void move(int id) {
		//moves an object according to its velocity
		MovingFObject fo = scenario.fobjects.get(id);
		double distTravelled = fo.getVelocity().magnitude * stepTime / 1000;
		double dx = Math.cos(fo.getVelocity().direction) * distTravelled;
		double dy = Math.sin(fo.getVelocity().direction) * distTravelled;
		
		Location newLoc = new Location(fo.getLocation().x + dx, fo.getLocation().y + dy);
		fo.move(newLoc);
		
		fixWallColl(id);
		fixObjColl(id);
	}
	private void fixWallColl(int id) {
		//if an object collides with a wall while moving, this method will prevent overlap
		MovingFObject fo = scenario.fobjects.get(id);
		if(Collision.touchingWall(fo, scenario.width, scenario.height)) {
			double newX = fo.getLocation().x;
			double newY = fo.getLocation().y;
			if(Collision.getWallDist(fo, 0, scenario.width, scenario.height) < 0) {
				newX += Collision.getWallDist(fo, 0, scenario.width, scenario.height);
			}
			if(Collision.getWallDist(fo, 1, scenario.width, scenario.height) < 0) {
				newY += Collision.getWallDist(fo, 1, scenario.width, scenario.height);
			}
			if(Collision.getWallDist(fo, 2, scenario.width, scenario.height) < 0) {
				newX -= Collision.getWallDist(fo, 2, scenario.width, scenario.height);
			}
			if(Collision.getWallDist(fo, 3, scenario.width, scenario.height) < 0) {
				newY -= Collision.getWallDist(fo, 3, scenario.width, scenario.height);
			}
			fo.move(new Location(newX,newY));
		}
	}
	private void fixObjColl(int id) {
		//if an object collides with another object while moving, this method will prevent overlap
		MovingFObject fo = scenario.fobjects.get(id);
		for(int i = 0; i < scenario.fobjects.size(); i++) {
			if(Collision.touching(fo, scenario.fobjects.get(i))) {
				
				double xback = Collision.getXDist(fo, scenario.fobjects.get(i));
				
				double yback = Collision.getYDist(fo, scenario.fobjects.get(i));
				double newX = fo.getLocation().x;
				double newY = fo.getLocation().y;
				if(xback > yback) {
					newX = (fo.getLocation().x < scenario.fobjects.get(i).getLocation().x) ? newX + xback : newX - xback;
				}
				else {
					newY = (fo.getLocation().y < scenario.fobjects.get(i).getLocation().y) ? newY + yback : newY - yback;
				}
				
				fo.move(new Location(newX,newY));
				
			}
		}
	}
	public int getSize() {
		return scenario.fobjects.size();
	}
}
