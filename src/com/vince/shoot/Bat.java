package com.vince.shoot;

import java.util.Random;

public class Bat extends FlyingObject implements Enemy {
	private int xSpeed;
	private int ySpeed=1;
	private int awardType;
	private int eLife=50;
	public Bat(){
		this.image=ShootGame.bat;
		width=image.getWidth();
		height=image.getHeight();
		y=-height;
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-width);
		//x=100;
		//y=200;
		awardType=rand.nextInt(2);
		setXSpeed((int)(Math.random()));
	}
	
	public void setXSpeed(int x){
		if(x==0){
			x--;
		}
		xSpeed=x;
	}
	
	public int getScore() {
		return 0;
	}
	
	
	public int getType(){
		return awardType;
	}
	
	public void step() {
		x+=xSpeed;
		y+=ySpeed;
		if(x>ShootGame.WIDTH){
			xSpeed=-1;
		}
		if(x<0){
			xSpeed=1;
		}
	}
	public void subBLife(){
		eLife--;
	}
	public int getELife() {
		return eLife;
	}

	void setELife() {
		 eLife-=1;
	}
}
