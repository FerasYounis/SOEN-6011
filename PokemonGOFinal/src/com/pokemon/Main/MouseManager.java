package com.pokemon.Main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {
	public boolean RPressed = false;
	public boolean RClicked = false;
	public boolean LClicked = false;
	public boolean LPressed = false;
	public boolean MClicked = false;
	public boolean LDragging = false;
	public int scroll;
	public int MX, MY;

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			LPressed = true;
			MX = e.getX();
			MY = e.getY();
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			RPressed = true;
			MX = e.getX();
			MY = e.getY();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			LPressed = false;
			LClicked = false;
			LDragging = false;
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			RPressed = false;
			RClicked = false;
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			MClicked = false;
			RClicked = false;
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			LClicked = true;
			MX = e.getX();
			MY = e.getY();
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			MClicked = true;
			MX = e.getX();
			MY = e.getY();
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			RClicked = true;
			MX = e.getX();
			MY = e.getY();
		}
	}

	public void mouseMoved(MouseEvent e) {
		MX = e.getX();
		MY = e.getY();
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		LDragging = true;
		MX = e.getX();
		MY = e.getY();
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll = e.getWheelRotation();
	}

	public Rectangle getMouseRect() {
		return new Rectangle(MX, MY, 3, 3);
	}
}
