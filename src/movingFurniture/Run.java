package movingFurniture;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Run extends Canvas{
	public static void main(String[] args) {
		JFrame frame = new JFrame("Moving Furniture");
		JPanel panel = new JPanel();
		Room room = new Room(800, 800);
		
		panel.setMinimumSize( room.getSize() );
		frame.setMinimumSize(room.getSize());
		frame.pack();
		Table myTable = new Table(100, 60, 20, 40);
		Table table2 = new Table(200, 20, 50, 160);
		Chair myChair = new Chair(30, 200, 30);
		room.addFurniture(myTable);
		room.addFurniture(table2);
		room.addFurniture(myChair);
		
		frame.add(room);
		frame.setVisible(true);
		
	}
}
