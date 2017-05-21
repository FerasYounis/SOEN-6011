package com.pokemon.Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Button extends GameObject {

	private String text;
	private Font bFont;
	private Color txtColor, backColor;
	private boolean notActive, mouseOver, pressed;
	private boolean firstR;
	private boolean buffB, endTurnB;

	public Button(int x, int y, int h, String text, Color tcolor, Color bcolor) {
		super();
		this.text = text;
		this.bFont = new Font("DorFont03", Font.PLAIN, 24);
		this.txtColor = tcolor;
		this.backColor = bcolor;

		this.endTurnB = true;
		this.w = 200;
		this.h = h;
		this.x = x;
		this.y = y;
		this.text = text;
		firstR = true;
	}

	public Button(int x, int y, int h, String text, Color tcolor, Color bcolor, Font f) {
		super();
		this.text = text;
		this.bFont = f;
		this.txtColor = tcolor;
		this.backColor = bcolor;

		this.endTurnB = true;
		this.w = 200;
		this.h = h;
		this.x = x;
		this.y = y;
		this.text = text;
		firstR = true;
	}

	public Button(int x, int y, Color c) {
		super();
		this.x = x;
		this.y = y;
		this.text = "+";
		this.backColor = c;
		this.txtColor = Color.WHITE;
		this.bFont = new Font("DorFont02", Font.BOLD, 56);
		this.buffB = true;
		this.w = 30;
		this.h = 30;
		firstR = false;
	}

	public void update() {
		if (!notActive && Game.getMouseRect().intersects(getRect())) {
			mouseOver = true;
			if (Game.getMouseManager().LPressed) {
				pressed = true;
				Game.getMouseManager().LPressed = false;
			}
		} else
			mouseOver = false;

	}

	public void draw(Graphics g) {
		g.setFont(bFont);
		if (firstR) {
			this.w = g.getFontMetrics().stringWidth(text) + 10;
			// this.h = g.getFontMetrics().getHeight();
			this.x -= w / 2;
			firstR = false;
		}
		if (notActive) {
			if (buffB) {
				g.setColor(new Color(45, 72, 144));
				Graphics2D g2 = (Graphics2D) g;
				Stroke oldStroke = g2.getStroke();
				g2.setStroke(new BasicStroke(3));
				g2.drawRect(x, y, w, h);
				g2.setStroke(oldStroke);

			} else {
				g.setColor(backColor.darker().darker());
				g.fillRect(x, y, w, h);
				g.setColor(txtColor.darker().darker());
				g.drawString(text, x + 5, y + h - 15);
			}
		} else if (mouseOver) {
			g.setColor(backColor.brighter());
			g.fillRect(x, y, w, h);
			g.setColor(txtColor.darker());
			if (!buffB)
				g.drawString(text, x + 5, y + h - 15);
			else
				g.drawString(text, x + 5, y + h - 3);
		} else {
			g.setColor(backColor);
			g.fillRect(x, y, w, h);
			g.setColor(txtColor);
			if (!buffB)
				g.drawString(text, x + 5, y + h - 15);
			else
				g.drawString(text, x + 5, y + h - 3);
		}
		if (endTurnB) {
			g.setColor(new Color(200, 200, 200));
			Graphics2D g2 = (Graphics2D) g;
			Stroke oldStroke = g2.getStroke();
			g2.setStroke(new BasicStroke(4));
			g2.drawRect(x, y, w, h);
			g2.setStroke(oldStroke);
		}
	}

	public boolean isNotActive() {
		return notActive;
	}

	public void setNotActive(boolean notActive) {
		this.notActive = notActive;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
