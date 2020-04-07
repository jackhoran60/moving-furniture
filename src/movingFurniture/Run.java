package movingFurniture;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.*;

public class Run extends Canvas{
	public static void main(String[] args) throws InterruptedException {
//		conferenceRoom();
		Scenario scen = new Scenario(1);
		ScenarioView scenView = new ScenarioView("Sample scen", scen.getWidth(), scen.getHeight(),scen.getSpaceTime());
		scenView.animate();
	}
	
//	public static void conferenceRoom() {
//		JFrame frame = new JFrame("Conference Room");
//		JPanel panel = new JPanel();
//		Room room = new Room(1000, 1000);
//		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
//		frame.add(room);
//		
//		frame.setSize(1100, 1100);
//		frame.setVisible(true);
//		//table: 200x50
//		//chair: 40x40
//		//defining goals
//		
//		ArrayList<Location> tableGoals = new ArrayList<Location>();
//		tableGoals.add(new Location(185,125));
//		tableGoals.add(new Location(395,125));
//		tableGoals.add(new Location(605,125));
//		tableGoals.add(new Location(815,125));
////		tableGoals.add(new Location(185,875));
////		tableGoals.add(new Location(395,875));
////		tableGoals.add(new Location(605,875));
////		tableGoals.add(new Location(815,875));
//		
//		ArrayList<Location> chairGoals = new ArrayList<Location>();
//		chairGoals.add(new Location(135,50));
//		chairGoals.add(new Location(235,50));
//		chairGoals.add(new Location(345,50));
//		chairGoals.add(new Location(445,50));
//		chairGoals.add(new Location(555,50));
//		chairGoals.add(new Location(655,50));
//		chairGoals.add(new Location(765,50));
//		chairGoals.add(new Location(865,50));
////		chairGoals.add(new Location(135,950));
////		chairGoals.add(new Location(235,950));
////		chairGoals.add(new Location(345,950));
////		chairGoals.add(new Location(445,950));
////		chairGoals.add(new Location(555,950));
////		chairGoals.add(new Location(655,950));
////		chairGoals.add(new Location(765,950));
////		chairGoals.add(new Location(865,950));
//		
//		//randomly place furniture
//		
////		for(int i = 0; i < 4; i++) {
////			Location location = tableGoals.get(i);
////			Goal goal = new Goal(location);
////			room.randomlyAddTable(200, 50, goal);
////		}
////		for(int i = 0; i < 8; i++) {
////			Location location = chairGoals.get(i);
////			Goal goal = new Goal(location);
////			room.randomlyAddChair(40, goal);
////		}
////		Location loc1 = new Location(20,40);
////		Location loc2 = new Location(200,40);
////		Location loc3= new Location(50,200);
////		Table myTable = new Table(100, 100, loc1);
////		Table table2 = new Table(200, 20, loc2);
////		Chair myChair = new Chair(30, loc3);
////		room.addObject(myTable);
////		room.addObject(table2);
////		room.addObject(myChair);
//		
//		
//	}

}
