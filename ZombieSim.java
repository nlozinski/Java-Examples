package Project2;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class ZombieSim {

	/* These are used to set the size of the window */
	public static final int MAX_X = 200;
	public static final int MAX_Y = 125;
	public static final int dotSize= 4;
	public static int sleepieTime=30;
	public static boolean StopZombies=false;
	public static boolean StopHumans=false;
	static City world = new City(MAX_X,MAX_Y,80, 400);

	public static void main(String[] args) {
			DrawSimulator simulation=new DrawSimulator (MAX_X, MAX_Y, dotSize);
	 
			while (true) {
				City.dp.clear(DotPanel.BLACK);
				world.update();
				world.draw();
				
				
			}
		}

}
