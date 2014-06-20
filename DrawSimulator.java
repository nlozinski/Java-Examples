package Project2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


class DrawSimulator implements ActionListener, MouseListener, ChangeListener, ItemListener {
	public static int slow=500;
	public static int medium=100;
	public static int fast=25;
	JPanel buttonP = new JPanel();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int xCoordinate=e.getX();
		int yCoordinate=e.getY();
	
		if (!ZombieSim.world.getWalls()[xCoordinate/ZombieSim.dotSize][yCoordinate/ZombieSim.dotSize]){
			ZombieSim.world.addZombie(xCoordinate, yCoordinate);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void stateChanged(ChangeEvent e) {
	    JSlider source = (JSlider)e.getSource();
	    if (!source.getValueIsAdjusting()) {
	    	ZombieSim.sleepieTime=(int)source.getValue();
	    }
	    }

	public DrawSimulator (int MAX_X, int MAX_Y, int dotSize){
		CheckBoxListener myListener = null;
		City.dp = new DotPanel(MAX_X, MAX_Y, dotSize);
		City.dp.setSize(MAX_X*dotSize, MAX_Y*dotSize);
		JFrame frame = new JFrame("Nolan's Zombies");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JSlider simulationSpeed = new JSlider(JSlider.HORIZONTAL, slow, medium, fast);
		City.dp.addMouseListener(this);
		
		
		JCheckBox stopZombies=new JCheckBox("Stop Zombies");
		JCheckBox stopHumans=new JCheckBox("Stop Humans");
	
		stopZombies.addItemListener(myListener);
		stopHumans.addItemListener(myListener);
		Container CPane = City.dp;
		buttonP.setBackground(Color.gray);
		buttonP.add(stopHumans);
		buttonP.add(stopZombies);
		CPane.add(buttonP, BorderLayout.SOUTH);
		
		
		//buttonP.add(simulationSpeed);
 
		/* Create and set the size of the panel */
		
 
		/* Add the panel to the frame */
		Container cPane = frame.getContentPane();
		cPane.add(City.dp);
		// pack() will resize the frame to fit the panel--YOU MUST CALL THIS
		frame.pack();
 
		// Initialize the DotPanel---you cannot do this until after calling pack!!!
		City.dp.init();
		frame.setVisible(true);
		

	}


	class CheckBoxListener implements ItemListener {
       
	public void itemStateChanged(ItemEvent e) {
		Object source=e.getSource();
		if (source=="stopZombies"){
			ZombieSim.StopZombies=true;
			System.out.println (ZombieSim.StopZombies);
		}
		if (source=="stopHumans"){
			ZombieSim.StopHumans=true;
		}
	}

	


	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
