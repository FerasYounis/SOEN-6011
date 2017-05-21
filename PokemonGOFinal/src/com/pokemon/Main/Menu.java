package com.pokemon.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Menu {
	private Button start, quit;

	public Menu() {
		start = new Button(Game.WIDTH / 2, 600, 70, "START", Color.WHITE, new Color(49, 156, 12),
				new Font("DorFont03", Font.BOLD, 56));
		quit = new Button(Game.WIDTH / 2, 700, 70, "QUIT", Color.WHITE, new Color(49, 156, 12),
				new Font("DorFont03", Font.BOLD, 56));
	}
	
	public void start(){
		Game.gameInterface = new GameInterface();
		Game.state = Game.State.GAME;
		
		
	}

	public void update() {
		start.update();
		quit.update();

		if (start.isPressed()) {
			start();
			start.setPressed(false);
		}
		if (quit.isPressed()) {
			quit.setPressed(false);
			System.exit(0);
		}
	}

	public void draw(Graphics g) {
		g.drawImage(Game.menuBackground, 0, 0, Game.WIDTH, Game.HEIGHT + 2, null);
		start.draw(g);
		quit.draw(g);
	}

}
