package main;

import java.awt.*;
import java.util.*;

public class Zed {

	Random random;
	int name;
	float hp;
	float hunger;
	float xPos;
	float yPos;
	float xVel = 0.01f;
	float yVel = 0.01f;
	int initSpeed = 1;
	final int UNIT_DIAMETER = 10;
	
	// Class constructor
	Zed(int name) {		
		random = new Random();
		
		this.name = name;
		this.hp = random.nextFloat(55,85);
		this.hunger = random.nextFloat(0.995f,1.000f);
		
		// set starting position x,y
		this.xPos = random.nextInt(1400);
		this.yPos = random.nextInt(850);
		
		// set starting direction x,y
		if(random.nextBoolean()==true)
			this.xVel *= random.nextFloat(10);
		else
			this.xVel *= -random.nextFloat(10);
		if(random.nextBoolean()==true)
			this.yVel *= random.nextFloat(10);
		else
			this.yVel *= -random.nextFloat(10);
	}
	
	// Display individual Zed info on console
	public void printZed() {
		System.out.println(this.name + "\tHP: " + this.hp + "\tx: " + this.xPos + "\ty: " + this.yPos);
	}
	
	public void update() {
		updateZedPos();
		updateHP();
	}
	
	public void draw(Graphics g) {
		// if dead zed, color to brown
		if(this.hp <= 0) {
			g.setColor(new Color(139,69,19));
		}
		else {
			g.setColor(Color.black);
		}
		g.fillOval((int)xPos, (int)yPos, UNIT_DIAMETER, UNIT_DIAMETER);
	}
	
	private void updateZedPos() {
		// if dead zed, no movement
		if(this.hp <= 0) {
			xPos += 0;
			yPos += 0;
		}
		else {
			xPos += xVel * initSpeed;
			if(xPos > 1450 - UNIT_DIAMETER || xPos < 0) {
				xVel *= -1;
			}
			
			yPos += yVel * initSpeed;
			if(yPos > 860 - UNIT_DIAMETER || yPos < 0) {
				yVel *= -1;
			}
		}
	}
	
	private void updateHP() {
		if(this.hp > 0) {
			this.hp *= this.hunger;
		}
	}
}