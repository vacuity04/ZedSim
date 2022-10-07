package main;

import java.awt.*;
import java.util.*;

public class Zed {

	Random random;
	int name;
	int hp;
	int xPos;
	int yPos;
	int xVel;
	int yVel;
	int initSpeed = 10;
	
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
	
	public void draw(Graphics g) {
		g.fillOval(xPos, yPos,  10,  10);
	}
}