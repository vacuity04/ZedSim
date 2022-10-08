package main;

import java.awt.*;
import java.util.*;

public class Zed {

	Random random;
	int name;
	int hp;
	float xPos;
	float yPos;
	float xVel = 0.01f;
	float yVel = 0.01f;
	int initSpeed = 25;
	
	// Class constructor
	Zed(int name) {		
		random = new Random();
		
		this.name = name;
		this.hp = random.nextInt(55,85);
		this.xPos = random.nextInt(1600);
		this.yPos = random.nextInt(900);
	}
	
	// Display individual Zed info on console
	public void printZed() {
		System.out.println(this.name + "\tHP: " + this.hp + "\tx: " + this.xPos + "\ty: " + this.yPos);
	}
	
	public void update() {
		updateZedPos();
	}
	
	public void draw(Graphics g) {
		g.fillOval((int)xPos, (int)yPos,  20,  20);
	}
	
	private void updateZedPos() {
		xPos += xVel * initSpeed;
		if(xPos > 1450 || xPos < 0) {
			xVel *= -1;
		}
		
		yPos += yVel * initSpeed;
		if(yPos > 900 || yPos < 0) {
			yVel *= -1;
		}
	}
}