package com.vince.shoot;

import java.awt.Color;

import java.awt.Graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;  

import javax.imageio.ImageIO;
import javax.swing.*;




@SuppressWarnings("serial")
public class ShootGame extends JPanel {
	
	
	private int state;
	private final int START=0;
	private final int RUNNING=1;
	private final int PAUSE=2;
	private final int GAME_OVER=3;
	
	
	private Timer timer;
	private int intervel=1000/100;
	int m;
	int n;
	
	public static int imX=0;
	public static int imY=0;
	public static final int WIDTH=400;
	public static final int HEIGHT=630;
	
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage Airplane;
	public static BufferedImage bat;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	public static BufferedImage bullet;
	
	
	private FlyingObject[] flyings={};
	private Hero hero=new Hero();
	private Bulllet[] bullets={};
	@SuppressWarnings("unused")
	private int score=0;//记分
	
	public ShootGame(){
		flyings=new FlyingObject[2];
		flyings[0]=new Airplane();
		flyings[1]=new Bat();
		
		bullets=new Bulllet[1];
		bullets[0]=new Bulllet(hero.getX()+(int)(0.5*(hero.width)),500);
	}
	
	static{
		try{
			background=ImageIO.read(ShootGame.class.getResource("background.jpeg"));
			start=ImageIO.read(ShootGame.class.getResource("start.png"));
			bullet=ImageIO.read(ShootGame.class.getResource("bullet.png"));
			bat=ImageIO.read(ShootGame.class.getResource("bat.png"));
			Airplane=ImageIO.read(ShootGame.class.getResource("airplane.png"));
			pause=ImageIO.read(ShootGame.class.getResource("pause.png"));
			hero0=ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1=ImageIO.read(ShootGame.class.getResource("hero1.png"));
			gameover=ImageIO.read(ShootGame.class.getResource("gameover.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	int f=0;
	public void paint(Graphics g){
		{
			if (imY>=background.getHeight()){
				imY=0;
				f++;
				System.out.println(imY);
			}
			if(f>3){
				f=f-4;
				//System.out.println(f);
				hero.addDoubleFire();   //火力增加
			}	
		g.drawImage(background, imX, imY,null);//修改！！！！！！
		g.drawImage(background, imX, imY-(background.getHeight()),null);
		}
		g.setColor(Color.white);
		paintFlyingObjects(g);
		paintBulllet(g);
		paintHero(g);
		if(hero.getLife()>=0){
		paintLife(g);
		}
		//加入绘制内容
		if(hero.doubleFire>-40){
			paintDouble(g);
		}
	}
	
	//玩家
	public void paintHero(Graphics g){
		g.drawImage(hero.getImage(),hero.getX(), hero.getY(), null);
	}
	
	
	//子弹
	public void paintBulllet(Graphics g){
		for(int i=0;i<bullets.length;i++){
			Bulllet b=bullets[i];
			g.drawImage(b.getImage(), b.getX(), b.getY(), null);
		}
	}
	
	
	
	//画飞行物
	public void paintFlyingObjects(Graphics g){
		for(int i=0;i<flyings.length;i++){
			FlyingObject f=flyings[i];
			g.drawImage(f.getImage(), f.getX(), f.getY(), null);
		}
	}
	
	
	public static void main(String[] args){
		JFrame frame=new JFrame("飞机大战");
		ShootGame game=new ShootGame();
		frame.add(game);
		frame.setSize(WIDTH,HEIGHT);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		
		game.action();
		
	}
	
	public static FlyingObject nextOne(){
		Random random=new Random();
		int type=random.nextInt(20);
		
		if(type==0){
			
			return new Bat();
		}else{
			return new Airplane();
		}
	}
	int flyEnteredIndex=0;
	//飞行物入场
	public void enterAction(){
		flyEnteredIndex++;
		if(flyEnteredIndex%40==0){
			FlyingObject obj=nextOne();
			flyings=Arrays.copyOf(flyings, flyings.length+1);
			flyings[flyings.length-1]=obj;
		}
	}
	
	//子弹入场
	int bullentIndex=0;
	public void shootBullet(){
		bullentIndex++;
		if(bullentIndex%20==0){
			if(hero.doubleFire>-40){
				hero.doubleFire--;
				bullets=Arrays.copyOf(bullets, bullets.length+4);
				bullets[bullets.length-1]=new Bulllet(m-33,n);
				bullets[bullets.length-2]=new Bulllet(m+33,n);
				bullets[bullets.length-3]=new Bulllet(m+33,n+26);
				bullets[bullets.length-4]=new Bulllet(m-33,n+26);
			}
		bullets=Arrays.copyOf(bullets, bullets.length+1);
		bullets[bullets.length-1]=new Bulllet(m,n-20);}
}
	
	
	public  void stepAction(){
		for(int i=0;i<flyings.length;i++){
			FlyingObject f=flyings[i];
			f.step();
		}
		for(int i=0;i<bullets.length;i++){
			Bulllet b=bullets[i];
			b.step();
		}
		hero.step();
	}
	
	public void action(){		
		MouseAdapter l=new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				if(state==RUNNING){
				 m=e.getX();
				n=e.getY();
				hero.moveTo(m, n);
			}}
			public void mouseEntered(MouseEvent e){
				if(state==PAUSE){
					state=RUNNING;
				}
			}
			public void mouseExited(MouseEvent e){
				if(state!=GAME_OVER){
					state=PAUSE;
				}
			}
			public void mouseClicked(MouseEvent e){
				switch(state){
				case START:
				state=RUNNING;
				break;
				case GAME_OVER:
				flyings=new FlyingObject[0];
				bullets=new Bulllet[0];
				hero=new Hero();
				score=0;
				state=START;
				break;
				}
			}
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		timer=new Timer();
		timer.schedule(new TimerTask(){
			//总控制台
			public void run(){
				enterAction();
				shootBullet();
				stepAction();
				bangAction();
				crashAction();
				checkGameverAction();
				imY+=1;
				repaint();
			}
		},intervel,intervel);
	}
	
	
	//子弹
	public void bangAction(){
		for(int i=0;i<bullets.length;i++){
			Bulllet b=bullets[i];
			int re=bang(b);
			if(re==0){
				b.y=0 ;
			}    //撞击后子弹消失
		}
	}
	
	
	
	public int bang(Bulllet bullet){
		int index=-1;
		for(int i=0;i<flyings.length;i++){
			FlyingObject obj=flyings[i];
			if(obj.shootBy(bullet)){
				
				index=i;
				break;
			}
		}
		if(index!=-1){
			flyings[index].setELife();
			if(flyings[index].getELife()==0){
			@SuppressWarnings("unused")
			FlyingObject one=flyings[index];
				@SuppressWarnings("unused")
				FlyingObject temp=flyings[index];
				flyings[index]=flyings[flyings.length-1];
			
				flyings=Arrays.copyOf(flyings, flyings.length-1);
		}
		return 0;
		}
		return 1;
		}
	
	
	//撞击flyings掉命,死亡程序
	public void crashAction(){
			crashx(hero);	
		}
	
	public void crashx(Hero hero){
		int index=-1;
		for(int i=0;i<flyings.length;i++){
			FlyingObject obj=flyings[i];
			if(obj.crash(hero)){
				index=i;
				break;
			}
		}
		if(index!=-1){
			hero.subLife();
			
		}
	}
	
	public void paintLife(Graphics x){
		Integer s=hero.getLife();
		String q=s.toString();
		x.drawString(q, 16, 30);
	}
	
	
	
	//获得火药
	public void paintDouble(Graphics x){
		String q="获得三倍火力";
		x.drawString(q, 30, 30);
	}
	
	
	public void checkGameverAction(){
		if(hero.getLife()==0){
			state=GAME_OVER;
		}
	}
	
	
	
}
