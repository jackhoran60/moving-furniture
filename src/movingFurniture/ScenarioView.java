package movingFurniture;

import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;

public class ScenarioView{
	public final String name;
	public final int FRAMETIME = 20;
	public final int width;
	public final int height;
	private Map<Integer, MovingFView> views;
	public final ScenarioJPanel sjp;
	public ScenarioView(String name, int width, int height, Map<Integer, MovingFView> initPos) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.views = initPos;
		JFrame frame = new JFrame(name);
		this.sjp = new ScenarioJPanel(width, height, initPos);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		frame.add(sjp);
		frame.setSize(width+100, height+100);
		frame.setVisible(true);
	}
	public void repaint() {
		sjp.repaint();
	}
	public Map<Integer, MovingFView> getViews(){
		return views;
	}
	
}
