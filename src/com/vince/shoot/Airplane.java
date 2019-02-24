package com.vince.shoot;
import com.vince.shoot.ShootGame;

public class Airplane extends FlyingObject implements Enemy {
	private int speed=2;
	int eLife=2;
	public Airplane(){
		this.image=ShootGame.Airplane;
		width=image.getWidth();
		height=image.getHeight();
		x=(int)(Math.random()*(ShootGame.WIDTH-width));
		y=-height;
		//x=100;
		//y=100;
	}
	public int getScore() {
		return 5;
	}

	//move
	public void step() {
		y+=speed;
	}
	@Override
	public int getELife() {
		return eLife;
	}
	@Override
	void setELife() {
		eLife-=1;
	}
	
}
