package com.pokemon.Main;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.pokemon.Card.Card;


public class Animation {
	
	//private static SpriteSheet ss; 
	private static BufferedImage img;
	
	private int speed, frames;
	private int index = 0;
	private int count = 0;
	
	private BufferedImage[] images;
	private long timer, now, lastTime;

	public Animation(int speed, BufferedImage... args) {
		this.frames = args.length;
		this.images = args;
		this.speed = speed;
		index = 0;
		timer = 0;
		now = 0;
		count = 0;
		lastTime = System.currentTimeMillis();
		
	}
	public void runAnimation(){
		now = System.currentTimeMillis();
		timer += now - lastTime;
		lastTime = now;
		if (timer >= speed) {
			index ++;
			count++;
			timer = 0;
			if(count > frames)
				count=0;
			
			if (index >= images.length) {
				index = 0;
			}
		}
		
	}
	
	public void runOnceAnimation(){
		if(count >= frames){
			index = 0;
			timer = 0;
			count = 0;
			return;
		}
		now = System.currentTimeMillis();
		timer += now - lastTime;
		lastTime = now;
		if (timer >= speed) {
			index ++;
			count++;
			timer = 0;
		}
		
	}
	
	public void drawAnimation(Graphics g, int x, int y, int width, int height){
		g.drawImage(images[index], x, y, width, height, null);
	}
	
	public void drawAnimation(Graphics g, int x, int y, int width, int height, float alpha){
		Graphics2D g2D = (Graphics2D) g;
		g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha/100));
		g2D.drawImage(images[index], x, y, width, height, null);
		g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	
	public static void drawCard(Graphics g, Card c, int x, int y){
		g.drawImage(c.getCardImage(), x, y, null);
	}
	
	public static void drawCard(Graphics g, Card c, int x, int y, double angle){
		g.drawImage(RotateImage.rotate(c.getCardImage(), Math.toRadians(angle)), x, y, null);
	}
	
	public static void drawCardBack(Graphics g, int x, int y){
		g.drawImage(Game.cardback, x, y, Game.CARD_W, Game.CARD_H, null);
	}
	
	public static void drawCardBack(Graphics g, int x, int y, double angle){
		BufferedImage img = RotateImage.rotate(Game.cardback, Math.toRadians(angle));
		g.drawImage(img, x, y, img.getWidth(), img.getHeight(), null);
	}
	
//	
//	public static void entity(Graphics g, int x, int y, int w, int h, int col, int row, int tilesize, BufferedImage spriteImg, int multiplier){
//		ss = new SpriteSheet(spriteImg);
//		img = ss.grabImage(col, row, w, h, tilesize);
//		g.drawImage(img, x, y, w*multiplier, h*multiplier, null);
//	}
//	
	
	
	public void setCount(int count){
		this.count = count;
	}
	public int getCount(){
		return count;
	}
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
	public int getFrames() {
		return frames;
	}
	
}
