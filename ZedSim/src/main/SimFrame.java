package main;

import java.awt.*;
import javax.swing.*;

public class SimFrame extends JFrame {

	JFrame frame;
	
	// Class constructor
	public SimFrame(SimPanel simPanel, SidePanel sidePanel) {
	
		frame = new JFrame();
		
		frame.setSize(1600,900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		simPanel.setPreferredSize(new Dimension(1450,900));
		simPanel.setBackground(Color.green);
		frame.add(simPanel,BorderLayout.WEST);
		sidePanel.setBackground(Color.gray);
		sidePanel.setPreferredSize(new Dimension(150,900));
		frame.add(sidePanel,BorderLayout.EAST);
		
		frame.setVisible(true);
	}
}
