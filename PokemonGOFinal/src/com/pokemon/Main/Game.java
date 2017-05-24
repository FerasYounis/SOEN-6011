package com.pokemon.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author
 *
 */
public class Game extends JFrame {
	public static int WIDTH = 1400;
	public static int HEIGHT = 800;
	public static long FPS = 60;
	public static int CARD_W = 80, CARD_H = 111; // 卡牌图片尺寸
	public static final int HAND_SIZE = 7; // 初始手牌数
	public static final int BOARD_SIZE = 5; // bench宠物数
	Image offSetImage = null;

	public static enum State {
		GAME, GAMEOVER, MENU;
	}

	public static State state;
	private static MouseManager mouseManager;
	public static BufferedImage cardImg, cardback, menuBackground, gameBackground;
	public static BufferedImage wIcon, pIcon, lIcon, fIcon;
	public static Menu menu;
	public static GameInterface gameInterface;
	public static ObjectHandler objectHandler;

	public void lanuchGame() {
		this.setTitle("Pokemon TCG"); // 设置主界面窗体
		this.setBounds(0, 0, WIDTH, HEIGHT + 30);
		this.setVisible(true);
		this.setResizable(false);
		// this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mouseManager = new MouseManager(); // 鼠标事件
		this.addMouseListener(mouseManager);
		this.addMouseMotionListener(mouseManager);
		this.addMouseWheelListener(mouseManager);

		// 载入Font
		try {
			InputStream font = getClass().getResourceAsStream("/DorFont01.ttf");
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			InputStream font = getClass().getResourceAsStream("/DorFont02.ttf");
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			InputStream font = getClass().getResourceAsStream("/DorFont03.ttf");
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, font));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 加载图片
		ImageLoader loader = new ImageLoader();
		cardback = loader.load("/cardback.jpg");

		wIcon = loader.load("/wIcon.png");
		pIcon = loader.load("/pIcon.png");
		lIcon = loader.load("/lIcon.png");
		fIcon = loader.load("/fIcon.png");

		menuBackground = loader.load("/menuBackground.png");
		gameBackground = loader.load("/background.jpg");

		objectHandler = new ObjectHandler();
		state = State.MENU;
		menu = new Menu();
	
		new Thread(new GameThread()).start();
	}

	public void update() {
		if (state == State.MENU && menu != null) {
			menu.update();
		} else if (state == State.GAME) {
			gameInterface.update();
		}
	}

	@Override
	public void paint(Graphics g2d) {
		if (null == offSetImage) {
			offSetImage = this.createImage(WIDTH, HEIGHT + 30);
		}
		Graphics g = offSetImage.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT + 30);

		if (state == State.MENU && menu != null) {
			menu.draw(g);

		} else if (state == State.GAME && gameInterface != null) {
			gameInterface.draw(g);
		}

		g.dispose();
		g2d.drawImage(offSetImage, 0, 0, null);
	}

	public static Rectangle getMouseRect() {
		return mouseManager.getMouseRect();
	}

	public static MouseManager getMouseManager() {
		return mouseManager;
	}

	class GameThread implements Runnable {
		@Override
		public void run() {
			long startTime = System.nanoTime();
			while (true) {
				long now = System.nanoTime();
				update();
				if (now - startTime > 1000000000 / FPS) {
					repaint();
					startTime = now;
				}
			}
		}
	}

	public static void main(String[] args) {
		new Game().lanuchGame();
	}
}
