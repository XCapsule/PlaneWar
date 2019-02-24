package com.vince.shoot;

import java.awt.image.BufferedImage;

public class Hero extends FlyingObject {
	protected BufferedImage[] images={};
	protected int index=0;
	
	int doubleFire;
	private int life;
	
	public Hero(){
		life=3;
		doubleFire=0;
		this.image=ShootGame.hero0;
		images=new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
		height=(int)(image.getHeight()*0.8);
		width=(int)(image.getWidth()*0.8);
		x=140;
		y=460;
	}

	public void step() {
		if(images.length>0){
			image=images[index++/10%images.length];
		}
	}
	
	public void  moveTo(int x,int y){
		this.x=x-47;
		this.y=y-40;
	}
	
	public void addDoubleFire(){
		doubleFire+=40;
	}
	public void addLife(){
		life++;
	}
	public void subLife(){
		life--;
	}
	
	public int getLife(){
		return life;
	}

	@Override
	int getELife() {
		return 0;
	}

	@Override
	void setELife() {	
	}
}
