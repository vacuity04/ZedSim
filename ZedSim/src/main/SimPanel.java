package main;

import java.awt.Graphics;
import javax.swing.JPanel;

public class SimPanel extends JPanel {

	public SimPanel() {
				
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.fillRect(50, 50, 100, 50);
	}
}
