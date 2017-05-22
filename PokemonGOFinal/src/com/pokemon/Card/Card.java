package com.pokemon.Card;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Game;
import com.pokemon.Main.GameObject;
import com.pokemon.Main.ImageLoader;
import com.pokemon.Main.ObjectHandler;

public class Card extends GameObject {

	// protected int maxHp, hp, hpLimit, maxAttack, attack, attackLimit, cost,
	// range, maxRange, id;
	// protected String name, description;
	protected BufferedImage cardBack, cardImage;

	protected CardType cardType;
	protected boolean dragging = false;
	private CardCategory cardCategory;

	public Card(String url, CardType type, CardCategory cardCategory) {
		this.cardType = type;
		this.cardCategory = cardCategory;

		this.w = Game.CARD_W;
		this.h = Game.CARD_H;
		// icon = new BufferedImage(Game.CARD_W, Game.CARD_H,
		// BufferedImage.TYPE_INT_ARGB);
		// Graphics g2 = icon.getGraphics();
		// g2.drawImage(cardImage, 0, 0, cardImage.getWidth(),
		// cardImage.getHeight(), null);

		ImageLoader loader = new ImageLoader();
		BufferedImage bi = loader.load(url);
		cardImage = new BufferedImage(245, 342 + 50, BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = cardImage.getGraphics();
		g2.drawImage(bi, 0, 0, 245, 342, null);

		cardBack = new BufferedImage(Game.CARD_W, Game.CARD_H, BufferedImage.TYPE_INT_ARGB);
		Graphics g2d = cardBack.getGraphics();
		g2d.drawImage(Game.cardback, 0, 0, Game.CARD_W, Game.CARD_H, null);

		ObjectHandler.addCard(this);
	}

	public void update() {

	}

	public void draw(Graphics g, int x, int y) {
		draw(g, x, y, 0);
	}

	public void draw(Graphics g, int x, int y, double angle) {
		// this.x = x;
		// this.y = y;
		// Animation.drawCard(g, this, x, y, angle);
		// this.w = cardImage.getWidth();
		// this.h = cardImage.getHeight();

		g.drawImage(cardImage, x, y, 80, 128, null);

		g.setColor(Color.WHITE);
		g.setFont(new Font("DorFont02", Font.PLAIN, 32));

	}

	public void draw(Graphics g, int x, int y, boolean enlarge, boolean front) {
		if (front == true) {
			if (enlarge == true)
				g.drawImage(cardImage, x, y, 245, 342 + 50, null);
			else
				g.drawImage(cardImage, x, y, 80, 128, null);
		} else
			g.drawImage(cardBack, x, y, 80, 111, null);
	}

	public BufferedImage getCardBack() {
		return cardBack;
	}

	public void setCardBack(BufferedImage cardImage) {
		this.cardBack = cardImage;
	}

	public BufferedImage getCardImage() {
		return cardImage;
	}

	public void setCardImage(BufferedImage cardImage) {
		this.cardImage = cardImage;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public void setCardCategory(CardCategory cardCategory) {
		this.cardCategory = cardCategory;
	}

	public boolean getDragging() {
		return dragging;
	}

	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}

	public CardCategory getCardCategory() {
		return cardCategory;
	}

}
