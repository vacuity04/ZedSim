package main;

import java.awt.Graphics;
import javax.swing.JPanel;

public class SimPanel extends JPanel {

	private Sim sim;
	
	// Class constructor
	public SimPanel(Sim sim) {
		this.sim = sim;
	}
	
	// JComponent paint method
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		sim.render(g);
	}
}
