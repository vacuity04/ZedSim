package main;

import java.awt.*;
import javax.swing.*;

public class SimFrame extends JFrame {

	JFrame frame;
	
	public SimFrame(SimPanel simPanel, SidePanel sidePanel) {
	
		frame = new JFrame();
		
		frame.setSize(1600,900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		simPanel.setPreferredSize(new Dimension(1500,900));
		frame.add(simPanel,BorderLayout.WEST);
		sidePanel.setPreferredSize(new Dimension(100,900));
		frame.add(sidePanel,BorderLayout.EAST);
		
		frame.setVisible(true);
	}
}
