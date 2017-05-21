package com.pokemon.Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class CardSpot extends GameObject {

	private boolean highlight = false; 
	private boolean canAttack = false;
	private boolean attackable = false;
	private boolean movable = false;
	private boolean selected = false;
	private boolean invaded = false;
	private boolean invading = false;
	private boolean invadingAttack = false;

	public CardSpot(int x, int y) {
		this.x = x;
		this.y = y;
		this.w = Game.CARD_W;
		this.h = Game.CARD_H;
	}

	public CardSpot(int x, int y, boolean horizontal) {
		this.x = x;
		this.y = y;
		this.h = Game.CARD_W;
		this.w = Game.CARD_H;
	}

	public void draw(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;
		Stroke oldStroke = g.getStroke();
		g.setStroke(new BasicStroke(3));

		g.setColor(new Color(255, 255, 255, 25));
		g.fillRect(x, y, w, h);
		if (invaded) {
			g.setColor(Color.red);
			g.fillRect(x - 2, y - 2, w + 2, h + 2);
		} else if (invading) {
			g.setColor(Color.red);
			g.fillRect(x - 2, y - 2, w + 2, h + 2);
		} else if (canAttack) {
			g.setColor(new Color(0, 255, 0, 150));
			g.fillRect(x, y, w, h);
		} else if (attackable) {
			g.setColor(new Color(255, 0, 0, 150));
			g.fillRect(x, y, w, h);
		} else if (movable) {
			g.setColor(new Color(130, 77, 22, 150));
			g.fillRect(x, y, w, h);
		} else if (highlight) {
			g.setColor(new Color(0, 0, 255, 150));
			g.fillRect(x, y, w, h);
		} else {
			g.setColor(new Color(45, 72, 144));
			g.drawRect(x, y, w, h);
		}
		if (selected) {
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(x, y, w, h);
		}
		if (invadingAttack) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, w - 2, h - 2);
		}

		g.setStroke(oldStroke);
	}

	public void clear() {
		highlight = false;
		canAttack = false;
		attackable = false;
		movable = false;
		selected = false;
		invaded = false;
		invading = false;
		invadingAttack = false;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	public boolean isCanAttack() {
		return canAttack;
	}

	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}

	public boolean isAttackable() {
		return attackable;
	}

	public void setAttackable(boolean attackable) {
		this.attackable = attackable;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean movable) {
		this.movable = movable;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isInvaded() {
		return invaded;
	}

	public void setInvaded(boolean invaded) {
		this.invaded = invaded;
	}

	public boolean isInvading() {
		return invading;
	}

	public void setInvading(boolean invading) {
		this.invading = invading;
	}

	public boolean isInvadingAttack() {
		return invadingAttack;
	}

	public void setInvadingAttack(boolean invadingAttack) {
		this.invadingAttack = invadingAttack;
	}

}
