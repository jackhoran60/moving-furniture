package movingFurniture;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedHashMap;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ScenarioJPanel extends JPanel {
	private int width;
	private int height;
	LinkedHashMap<Integer, MovingFObject> frame;
	public ScenarioJPanel(int width, int height) {
		this.width = width;
		this.height = height;
		this.setSize(width, height);
	}
	public void drawFrame(LinkedHashMap<Integer, MovingFObject> frame) {
		this.frame = frame;
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Supported Shapes: Rectangle, Circle
		Graphics2D g2 = (Graphics2D) g;
		for(Integer I : frame.keySet()) {
			MovingFObject temp = frame.get(I);
			if(temp.getShape().equals("Rectangle")) {
				Rectangle2D rect = new Rectangle2D.Double(temp.getX() - (temp.getWidth()/2), temp.getY() - (temp.getLength()/2), temp.getWidth(), temp.getLength());
				g2.setColor(temp.getColor());
				g2.fill(rect);
			}
			else if(temp.getShape().equals("Circle")) {
				Ellipse2D circ = new Ellipse2D.Double(temp.getX() - (temp.getWidth()/2), temp.getY() - (temp.getLength()/2), temp.getWidth(), temp.getLength());
				g2.setColor(temp.getColor());
				g2.fill(circ);
			}
		}
		//TODO
	}
}
