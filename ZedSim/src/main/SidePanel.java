package main;

import java.awt.Graphics;
import javax.swing.JPanel;

public class SidePanel extends JPanel {

	public SidePanel() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.fillOval(50, 50, 100, 50);
	}
}
