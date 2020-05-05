package movingFurniture;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ScenarioJPanel extends JPanel {
	public final int width;
	public final int height;
	public final Map<Integer, MovingFView> objviews;
	public ScenarioJPanel(int width, int height, Map<Integer, MovingFView> objviews) {
		this.width = width;
		this.height = height;
		this.objviews = objviews;
		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new GridLayout());
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(Integer i : objviews.keySet()) {
			MovingFView temp = objviews.get(i);
			g2.setColor(temp.getColor());
			g2.fill(temp.getShape());
		}
	}
}
