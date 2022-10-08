package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Sim implements ActionListener, Runnable {

	private SimFrame simFrame;
	private SimPanel simPanel;
	private SidePanel sidePanel;
	private Thread simThread;
	private final int FPS_TARGET = 120;
	
	JButton spawnButton;
	JButton displayButton;	// print zed info to console, for now
	JLabel fpsLabel;
	
	ArrayList<Zed> zeds = new ArrayList<Zed>();

	// Class constructor
	public Sim() {
		simPanel = new SimPanel(this);
		sidePanel = new SidePanel();
		simFrame = new SimFrame(simPanel, sidePanel);
		
		fpsLabel = new JLabel("");
		spawnButton = new JButton("Spawn");
		displayButton = new JButton("Display");
		
		sidePanel.setLayout(null);
		
		fpsLabel.setFont(new Font("",Font.BOLD,14));
		fpsLabel.setBackground(Color.black);
		fpsLabel.setBounds(85, 0, 80, 20);
		
		spawnButton.setBounds(45, 30, 80, 40);
		spawnButton.addActionListener(this);
		
		displayButton.setBounds(45, 80, 80, 40);
		displayButton.addActionListener(this);
		
		sidePanel.add(fpsLabel);
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
	
	// Update movement
	public void update() {
		for(Zed z : zeds) {
			z.update();
		}
	}
	
	// Render entities
	public void render(Graphics g) {
		for(Zed z : zeds) {
			z.draw(g);
		}
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
				update();
				simPanel.repaint();
				lastFrame = now;
				frames++;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				//System.out.println("FPS: " + frames);
				fpsLabel.setText("FPS: " + String.valueOf(frames));
				frames = 0;
			}
		}
	}
}
