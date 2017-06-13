package com.pokemon.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import com.pokemon.Card.Card;

public class ShowDiscard {
	private ArrayList<Card> playerDiscards;
	private ArrayList<Card> enemyDiscards;
	private Button exit;

	public ShowDiscard() {
		this.playerDiscards = new ArrayList<Card>();
		this.enemyDiscards = new ArrayList<Card>();
		exit = new Button(Game.WIDTH - 100, Game.HEIGHT - 100, 50, "Exit", Color.WHITE,
				new Color(49, 156, 12));

	}

	public void update() {
		exit.update();
//		Collections.copy(this.playerDiscards, ObjectHandler.getPlayer().getGraveyard());
//		Collections.copy(this.enemyDiscards, ObjectHandler.getEnemy().getGraveyard());
		this.playerDiscards = (ArrayList<Card>)ObjectHandler.getPlayer().getGraveyard().clone();
		this.enemyDiscards = (ArrayList<Card>)ObjectHandler.getEnemy().getGraveyard().clone();
		
		
		if (exit.isPressed()) {
			Game.state = Game.State.GAME;
			exit.setPressed(false);
		}
		
		

		int x = 100;
		int y = 100;
		for (int i = 0; i < this.playerDiscards.size(); i++) {
			this.playerDiscards.get(i).setX(x);
			this.playerDiscards.get(i).setY(y);

			x += 100;
			if (x >= 1300) {
				x = 100;
				y += 120;
			}

		}
		
		
		x = 100;
		y = 500;
		for (int i = 0; i < this.enemyDiscards.size(); i++) {
			this.enemyDiscards.get(i).setX(x);
			this.enemyDiscards.get(i).setY(y);

			x += 100;
			if (x >= 1300) {
				x = 100;
				y += 120;
			}

		}
		
		
		

	}

	public void draw(Graphics g) {
	//	g.drawImage(Game.gameBackground, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		exit.draw(g);
		g.setColor(Color.BLACK);
		g.setFont(new Font("DorFont03", Font.PLAIN, 27));
		g.drawString("Player's Discards", 50, 70);
		g.drawString("AI's Discards", 50, 470);
		for (int i = 0; i < this.playerDiscards.size(); i++)
			this.playerDiscards.get(i).draw(g, this.playerDiscards.get(i).x, 
					this.playerDiscards.get(i).y, false, true);
		for (int i = 0; i < this.enemyDiscards.size(); i++)
			this.enemyDiscards.get(i).draw(g, this.enemyDiscards.get(i).x, 
					this.enemyDiscards.get(i).y, false, true);
	}

}
