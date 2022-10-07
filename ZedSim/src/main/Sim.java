package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class Sim implements ActionListener {

	private SimFrame simFrame;
	private SimPanel simPanel;
	private SidePanel sidePanel;
	JButton spawnButton;
	JButton displayButton;	// print zed info to console, for now
	
	ArrayList<Zed> zeds = new ArrayList<Zed>();

	public Sim() {
		
		simPanel = new SimPanel();
		sidePanel = new SidePanel();
		simFrame = new SimFrame(simPanel, sidePanel);
		
		spawnButton = new JButton("Spawn");
		displayButton = new JButton("Display");
		
		spawnButton.setBounds(10, 10, 80, 50);
		spawnButton.addActionListener(this);
		
		displayButton.setBounds(10, 70, 80, 50);
		displayButton.addActionListener(this);
		
		sidePanel.add(spawnButton);
		sidePanel.add(displayButton);
		
	}
	
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

}
