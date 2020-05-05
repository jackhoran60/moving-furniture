package movingFurniture;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		
		ScenarioController sc = ScenarioController.facGen(1);
		
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
		
		Runnable changeDirection = new Runnable() {
			public void run() { sc.setVelocity(0, new Velocity(100, (Math.random()*2*Math.PI)));
								sc.setVelocity(1, new Velocity(100, (Math.random()*2*Math.PI))); }
		};

		scheduler.scheduleWithFixedDelay(changeDirection,4,4,TimeUnit.SECONDS);
	}
}
