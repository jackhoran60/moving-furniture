package movingFurniture;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JFrame;

public class ScenarioView {
	private String name;
	private int frametime;
	private int width;
	private int height;
	private ArrayList<LinkedHashMap> spacetime;
	
	public ScenarioView(String name, int width, int height, ArrayList<LinkedHashMap> spacetime) {
		this.frametime = 20;
		this.name = name;
		this.width = width;
		this.height = height;
		this.spacetime = spacetime;
	}
	public ScenarioView(String name, int width, int height, ArrayList<LinkedHashMap> spacetime, int frametime) {
		this.frametime = frametime;
		this.name = name;
		this.width = width;
		this.height = height;
		this.spacetime = spacetime;
	}
	
	public void animate() throws InterruptedException {
		JFrame frame = new JFrame(name);
		ScenarioJPanel sjp = new ScenarioJPanel(width, height);
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		frame.add(sjp);
		frame.setSize(width+100, height+100);
		frame.setVisible(true);
		//for a single animation
		animate(sjp);
		//for constant animations
		//animateLoop(sjp);
	}
	private void animate(ScenarioJPanel sjp) throws InterruptedException {
		sjp.drawFrame(spacetime.get(0));
		Thread.sleep(frametime * 50);
		for(LinkedHashMap frame : spacetime) {
			sjp.drawFrame(frame);
			Thread.sleep(frametime);
		}
	}
	private void animateLoop(ScenarioJPanel sjp) throws InterruptedException {
		while(true) {
			sjp.drawFrame(spacetime.get(0));
			Thread.sleep(frametime * 50);
			for(LinkedHashMap frame : spacetime) {
				sjp.drawFrame(frame);
				Thread.sleep(frametime);
			}
			Thread.sleep(frametime * 50);
		}
	}
	
	public int getFrametime() {
		return frametime;
	}
	public void setFrametime(int frametime) {
		this.frametime = frametime;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public ArrayList<LinkedHashMap> getSpacetime() {
		return spacetime;
	}
	public void setSpacetime(ArrayList<LinkedHashMap> spacetime) {
		this.spacetime = spacetime;
	}
	
}
