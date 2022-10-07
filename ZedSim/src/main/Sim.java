package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class Sim implements ActionListener, Runnable {

	private SimFrame simFrame;
	private SimPanel simPanel;
	private SidePanel sidePanel;
	private Thread simThread;
	private final int FPS_TARGET = 120;
	
	JButton spawnButton;
	JButton displayButton;	// print zed info to console, for now
	
	ArrayList<Zed> zeds = new ArrayList<Zed>();

	// Class constructor
	public Sim() {
		simPanel = new SimPanel();
		sidePanel = new SidePanel();
		simFrame = new SimFrame(simPanel, sidePanel);
		
		spawnButton = new JButton("Spawn");
		displayButton = new JButton("Display");
		
		spawnButton.setBounds(45, 10, 80, 50);
		spawnButton.addActionListener(this);
		
		displayButton.setBounds(45, 70, 80, 50);
		displayButton.addActionListener(this);
		
		sidePanel.add(spawnButton);
		sidePanel.add(displayButton);
		
		startSimLoop();
	}
	
	// Spawn new zed by adding to ArrayList
	public void newZed() {
		zeds.add(new Zed(zeds.size()+1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == spawnButton) {
			newZed();
			System.out.println("Zed generated");
		}
		
		if(e.getSource() == displayButton) {
			for(Zed z : zeds) {
				z.printZed();
			}
		}
	}

	// Start the simulation loop in Runnable Thread
	private void startSimLoop() {
		
		simThread = new Thread(this);
		simThread.start();
	}
	
	// Simulation loop code
	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_TARGET;
		long lastFrame = System.nanoTime();
		long now = System.nanoTime();
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		
		while(true) {
			now = System.nanoTime();
			
			if(System.nanoTime() - lastFrame >= timePerFrame) {				
				simPanel.repaint();
				lastFrame = now;
				frames++;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
	}
}
