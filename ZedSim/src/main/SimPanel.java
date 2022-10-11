package main;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SimPanel extends JPanel {

	private Sim sim;
	private Image bgImg;
	
	// Class constructor
	public SimPanel(Sim sim) {
		this.sim = sim;
		
		bgImg = new ImageIcon("grass1600.png").getImage();
	}
	
	// JComponent paint method
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(bgImg, 0, 0, null);
		
		sim.render(g);
	}
}
