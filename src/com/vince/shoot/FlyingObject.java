package com.vince.shoot;
import java.awt.image.BufferedImage;

public abstract class FlyingObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	protected BufferedImage image;
	public int getX(){
		return x;
	}

	public void setX(int x){
	this.x=x;
	}
	public int getY(){
		return y;
	}
	public void setY(int y){
		this.y=y;
	}
	public int getWidth(){
		return width;
	}
	public void setWidth(int width){
		this.width=width;
	}
	public int height(){
		return height;
	}
	public void setHeight(int height){
		this.height=height;
	}
	public BufferedImage getImage(){
		return image;
	}
	public void getImage(BufferedImage image){
		this.image=image;
	}
	
	
	public boolean shootBy(Bulllet bullet){
		int x=bullet.x;
		int y=bullet.y;
		return this.x<x&&x<this.x+width&&this.y<y&&y<this.y+height;
	}
	
	//碰撞
	public boolean crash(Hero hero){
		int x=hero.x;
		int y=hero.y;
		return this.x<x&&x<this.x+width&&this.y<y&&y<this.y+height;
	}
	
	abstract int getELife();
	abstract void setELife();
	
	public abstract void step();
	int eLife;
}
