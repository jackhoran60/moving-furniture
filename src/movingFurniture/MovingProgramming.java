package movingFurniture;

import java.util.ArrayList;
import java.util.Map;

public class MovingProgramming {
	//this class is meant to be where the algorithm or heuristic is to be programmed
	//it should interact with ScenarioController, not Scenario and any of its components
	private ScenarioController sc;
	private ScenarioView sv;
	private boolean[][] graph;
	private boolean[] forComplexMotion;
	public MovingProgramming(int facGen) throws InterruptedException {
		sc = new ScenarioController(facGen);
		masterProg();
		String name = "Scenario " + facGen;
		sv = new ScenarioView(name, sc.getScenario().getWidth(), sc.getScenario().getHeight(), sc.getScenario().getSpaceTime());
		sv.animate();
	}
	public static MovingProgramming generateScenario(int facGen) throws InterruptedException {
		return new MovingProgramming(facGen);
	}
	
	public void masterProg() {
		//this is where highest level movement goes
		moveObjsToGoals();
	}
	public void generateGraph() {
		//progression:
		//	generate -> removeCycles -> loop(return root & planLinearMotion) -> planComplexMotion
		//GRAPH RULES:
		//graph[i][j] = true IF i => j in graph
		//	i => j if i's linear path intersects j's goal
		//	j => i if i's path intersects j's start
		//if i=>j and j=>i we have cycle
		//	remove the obj from graph w/ the highest # of cycles iteratively, these
		//	  will have complex motion
		Map<Integer, MovingFObject> map = sc.getInitPos();
		graph = new boolean[map.size()][map.size()];
		for(int i = 0; i < map.size(); i++) {
			MovingFObject cloned = map.get(i).duplicate();
			ArrayList<Location> locs = sc.traceLinearMotion(i,sc.getMaxVelocity(),cloned.getGoal());
			for(int j = 0; j < map.size(); j++) {
				if(j != i) {
					for(int k = 0; k < locs.size(); k++) {
						cloned.setLocation(locs.get(k));
						if(cloned.locationTouchingLocation(1, map.get(j), 2)) {
							graph[i][j] = true;
						}
						if(cloned.locationTouchingLocation(1, map.get(j), 0)) {
							graph[j][i] = true;
						}
					}
				}
			}
		}
	}
	public int returnGraphRoot() {
		//this method returns a root of the graph, which is to take highest priority in linear planner
		//the root is then removed from the graph
		for(int i = 0; i < sc.getInitPos().size(); i++) {
			for(int j = 0; j < sc.getInitPos().size(); j++) {
				if(graph[i][j]) {
					//means i -> j
					// therefore we need to see if i has a parent
					boolean hasPred = true;
					int pred = -1;
					while(hasPred) {
						for(int k = 0; k < sc.getInitPos().size(); k++) {
							if(graph[k][i]) {
								//k is a parent of i
								pred = k;
							}
						}
						if(pred == -1) {
							//i has no predecessor
							hasPred = false;
						}
						else {
							i = pred;
						}
					}
					//TODO: remove i from graph
					return i;
				}
			}
		}
		return 0;
	}
	public void removeGraphCycles() {
		//TODO: a cycle can be i => j => k => i as well
		//			finding this will be more complicated and this method needs to be rewritten
		Map<Integer, MovingFObject> map = sc.getInitPos();
		int[] cycleCount = new int[map.size()];
		forComplexMotion = new boolean[map.size()];
		//count cycles
		for(int i = 0; i < map.size(); i++) {
			for(int j = 0; j < map.size(); j++) {
				if(i != j) {
					if(graph[i][j] && graph[j][i]) {
						cycleCount[i]++;
						cycleCount[j]++;
					}
				}
			}
		}
		//remove fobjects from linear motion planning
		boolean hasCycles = true;
		while(hasCycles) {
			int max = 0;
			int maxIndex = -1;
			for(int i = 0; i < cycleCount.length; i++) {
				if(cycleCount[i] > max) {
					max = cycleCount[i];
					maxIndex = i;
				}
			}
			if(maxIndex == -1) {
				hasCycles = false;
			}
			else {
				cycleCount[maxIndex] = 0;
				forComplexMotion[maxIndex] = true;
				for(int i = 0; i < cycleCount.length; i++) {
					graph[maxIndex][i] = false;
					graph[i][maxIndex] = false;
				}
				
			}
			
		}
		
	}
	public void moveObjsToGoals() {
		//super basic test to see if all the different classes work in harmony
		Map<Integer, MovingFObject> map = sc.getInitPos();
		for(int i = 0; i < map.size(); i++) {
			MovingFObject temp = map.get(i);
			sc.moveAtVelAtTimeToLoc(i, sc.getMaxVelocity(), 0, (Location)temp.getGoal());
		}
	}
}
