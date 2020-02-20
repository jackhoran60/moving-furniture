package movingFurniture;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Run extends Canvas{
	public int x = 20;
	public int y = 20;
	
	
	public static void main(String[] args) {
		new Run();
		
		
	}
	public Run() {
		
		JFrame frame = new JFrame("Moving Furniture");
		JPanel panel = new JPanel();
		Room room = new Room(800, 800);
		
		panel.setMinimumSize( room.getSize() );
		frame.setMinimumSize(room.getSize());
		frame.pack();
		
		
		
		
		Table myTable = new Table(20, 40, 20, 40);
		
		room.addFurniture(myTable);
		room.addTable(200, 20, 50, 100);
		room.addChair(30, 200, 30);
		
		
		//just following tutorial here
		//how do I do OOP with graphics?
		//i.e. should main() be in Room / do I need a Run class?
		
		frame.add(room);
		frame.setVisible(true);
		
		
		
		
	}
	public void moveObjects() {
		
	}
	
	
	
}
