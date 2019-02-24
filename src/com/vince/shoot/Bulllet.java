package com.vince.shoot;

public class Bulllet extends FlyingObject {
	private int speed=3;
	public Bulllet(int x,int y){
		this.x=x;
		this.y=y;
		this.image=ShootGame.bullet;
		height=image.getHeight();
		width=image.getWidth();
	}
	public void step() {
		y-=speed;	
	}
	@Override
	int getELife() {
		return 0;
	}
	@Override
	void setELife() {
	}
}