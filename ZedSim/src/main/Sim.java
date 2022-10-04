package main;

public class Sim {

	private SimFrame simFrame;
	private SimPanel simPanel;
	private SidePanel sidePanel;
	
	public Sim() {
		
		simPanel = new SimPanel();
		sidePanel = new SidePanel();
		simFrame = new SimFrame(simPanel, sidePanel);
		
	}

}
