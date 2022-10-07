package main;

import java.awt.Graphics;

import javax.swing.JPanel;

public class SimPanel extends JPanel {

	private int xDelta = 100, yDelta = 100;
	private int xDir = 1, yDir = 1;
	
	// Class constructor
	public SimPanel() {
	
	}
	
	// JComponent paint method
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateOval();
		g.fillOval(xDelta, yDelta, 20, 20);
	}

	// Temp oval update method for test paint
	private void updateOval() {

		xDelta += xDir;
		if(xDelta > 1450 || xDelta < 0) {
			xDir *= -1;
		}
		
		yDelta += yDir;
		if(yDelta > 900 || yDelta < 0) {
			yDir *= -1;
		}
	}
}
