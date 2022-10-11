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
	private final int FPS_TARGET = 120;	// frames per second
	private final int UPS_TARGET = 200;	// updates per second
	
	JButton spawnButton;
	JButton displayButton;	// print zed info to console, for now
	JButton spawn100Button;
	JLabel fpsLabel;
	JLabel upsLabel;
	
	ArrayList<Zed> zeds = new ArrayList<Zed>();

	// Class constructor
	public Sim() {
		simPanel = new SimPanel(this);
		sidePanel = new SidePanel();
		simFrame = new SimFrame(simPanel, sidePanel);
		
		spawnButton = new JButton("Spawn");
		spawn100Button = new JButton("Spawn 100");
		displayButton = new JButton("Display");
		fpsLabel = new JLabel("");
		upsLabel = new JLabel("");
		
		sidePanel.setLayout(null);
		
		fpsLabel.setFont(new Font("",Font.BOLD,14));
		fpsLabel.setBackground(Color.black);
		fpsLabel.setBounds(85, 0, 80, 20);
		
		upsLabel.setFont(new Font("",Font.BOLD,14));
		upsLabel.setBackground(Color.black);
		upsLabel.setBounds(85, 20, 80, 20);
		
		spawnButton.setBounds(35, 50, 100, 40);
		spawnButton.addActionListener(this);
		
		spawn100Button.setBounds(35, 100, 100, 40);
		spawn100Button.addActionListener(this);
		
		displayButton.setBounds(35, 150, 100, 40);
		displayButton.addActionListener(this);
		
		sidePanel.add(fpsLabel);
		sidePanel.add(upsLabel);
		sidePanel.add(spawnButton);
		sidePanel.add(spawn100Button);
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
		
		if(e.getSource() == spawn100Button) {
			for(int i = 0; i < 100; i++) {
				newZed();
			}
			System.out.println("100 Zeds generated");
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
		double timePerUpdate = 1000000000.0 / UPS_TARGET;
		
		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(true) {
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			
			if(deltaF >= 1) {
				simPanel.repaint();
				frames++;
				deltaF--;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				fpsLabel.setText("FPS: " + String.valueOf(frames));
				upsLabel.setText("UPS: " + String.valueOf(updates));
				frames = 0;
				updates = 0;
			}
		}
	}
}
