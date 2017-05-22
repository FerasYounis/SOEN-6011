package com.pokemon.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.pokemon.Card.Card;
import com.pokemon.Card.Energy;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;

public class GameInterface extends JFrame {
	public static CardSpot[] playerBench, enemyBench; //
	public static CardSpot playerPoke, enemyPoke, playerDeck, enemyDeck, playerPrize, enemyPrize; //
	private Button endTurn, drawCard, retreat;
	private int selected = -1, mouseOver = -1; // 0-6 hand; 11 pokemon; 20-24
												// bench;

	private Player player;
	private Enemy enemy;
	private int turn;
	private Card movingCard = null;

	public GameInterface() {
		playerBench = new CardSpot[Game.BOARD_SIZE];
		enemyBench = new CardSpot[Game.BOARD_SIZE];

		int space = 25, hSpace = 20;
		for (int i = 0; i < playerBench.length; i++) {
			playerBench[i] = new CardSpot(550 + i * space + Game.CARD_W * i, 550);
		}
		for (int i = 0; i < enemyBench.length; i++) {
			enemyBench[i] = new CardSpot(550 + i * space + Game.CARD_W * i, 160);
		}
		playerPoke = new CardSpot(550 + 2 * space + Game.CARD_W * 2, Game.HEIGHT / 2 + hSpace / 2 + 10);
		enemyPoke = new CardSpot(550 + 2 * space + Game.CARD_W * 2, Game.HEIGHT / 2 - hSpace / 2 - Game.CARD_H + 10);
		playerDeck = new CardSpot(550 + 5 * space + Game.CARD_W * 5, Game.HEIGHT / 2 + hSpace / 2 + 10);
		enemyDeck = new CardSpot(550 + 5 * space + Game.CARD_W * 5, Game.HEIGHT / 2 - hSpace / 2 - Game.CARD_H + 10);
		playerPrize = new CardSpot(300 + 1 * space + Game.CARD_W * 1, Game.HEIGHT / 2 + hSpace / 2 + 10);
		enemyPrize = new CardSpot(300 + 1 * space + Game.CARD_W * 1, Game.HEIGHT / 2 - hSpace / 2 - Game.CARD_H + 10);

		endTurn = new Button(Game.WIDTH - 72, Game.HEIGHT / 2 - 25, 50, "End Turn", Color.WHITE,
				new Color(49, 156, 12));
		drawCard = new Button(Game.WIDTH - 72, Game.HEIGHT / 2 + 70, 50, "Draw Card", Color.WHITE,
				new Color(49, 156, 12));
		retreat = new Button(Game.WIDTH - 72, Game.HEIGHT / 2 + 70 + 95, 50, "Retreat", Color.WHITE,
				new Color(49, 156, 12));

		player = ObjectHandler.getPlayer();
		enemy = ObjectHandler.getEnemy();

	}

	public void update() {
		if (movingCard == null) {
			endTurn.update();
			drawCard.update();
			retreat.update();
			player.update();
			enemy.update();

			mouseOver = -1;

			if (retreat.isPressed()) {
				retreat();
				retreat.setPressed(false);
			}

			checkMouse();
			selectPoke();

		} else {
			// when the dragging mouse is released
			if (!Game.getMouseManager().LPressed) {
				boolean flag = false;
				if (Math.abs(movingCard.x - player.getPoke().x) < 40
						&& Math.abs(movingCard.y - player.getPoke().y) < 40) {
					player.getPoke().addEnergy((Energy) movingCard);
					player.getHand().remove(selected);
					flag = true;
				}
				if (!flag) {
					for (int i = 0; i < player.getBench().size(); i++) {
						if (Math.abs(movingCard.x - player.getBench().get(i).x) < 40
								&& Math.abs(movingCard.y - player.getBench().get(i).y) < 40) {
							Pokemon p = (Pokemon) player.getBench().get(i);
							p.addEnergy((Energy) movingCard);
							player.getHand().remove(selected);
							flag = true;
							break;
						}
					}
				}
				if (!flag) {
					movingCard.setX(500 + 90 * selected);
					movingCard.setY(685);
				}

				movingCard = null;
				Game.getMouseManager().LPressed = false;
				selected = -1;

			}
		}

		// select energy card
		if ((selected > -1 && selected < 10) && player.getPoke() != null
				&& player.getHand().get(selected).getCardType().equals(CardType.Engergy)) {

			if (Game.getMouseManager().LDragging) {
				movingCard = player.getHand().get(selected);
				movingCard.setDragging(true);
				movingCard.setX(Game.getMouseManager().MX - 40);
				movingCard.setY(Game.getMouseManager().MY - 128 / 2);
			}

		}

		System.out.println(mouseOver + "---(" + selected + ")" + "---" + player.getDeck().size());
	}

	private void selectPoke() {
		// select player's pokemon
		if ((selected > -1 && selected < 10) && player.getPoke() == null
				&& player.getHand().get(selected).getCardCategory() == CardCategory.Basic) {
			player.setPoke((Pokemon) player.getHand().get(selected));
			player.getHand().remove(selected);
			Game.getMouseManager().LPressed = false;
			selected = -1;
		}

		// select player's bench
		if ((selected > -1 && selected < 10) && player.getPoke() != null
				&& player.getHand().get(selected).getCardCategory() == CardCategory.Basic
				&& player.getBench().size() <= 5) {
			player.getBench().add(player.getHand().get(selected));
			player.getHand().remove(selected);
			Game.getMouseManager().LPressed = false;
			selected = -1;
		}

	}

	private void checkMouse() {

		// check mouse loc & hand
		for (int i = 0; i < player.getHand().size(); i++) {
			if (player.getHand().get(i).getRect().intersects(Game.getMouseRect())) {
				mouseOver = i;
				if (Game.getMouseManager().LPressed) {
					selected = mouseOver;
				}
				break;
			}
		}

		// check mouse loc & pokemon
		if (player.getPoke() != null && player.getPoke().getRect().intersects(Game.getMouseRect())) {
			// mouseOver = 11;
			if (Game.getMouseManager().LPressed) {
				selected = 11;
			}
		}

		// check mouse loc & bench
		for (int i = 0; i < player.getBench().size(); i++) {
			if (player.getBench().get(i).getRect().intersects(Game.getMouseRect())) {
				// mouseOver = i + 20;
				if (Game.getMouseManager().LPressed) {
					selected = i + 20;
				}
				break;
			}
		}

	}

	private void retreat() {

		if (selected >= 20 && selected <= 24 && player.getPoke() != null) {
			Pokemon p = player.getPoke();
			player.setPoke((Pokemon) player.getBench().get(selected - 20));
			player.getBench().set(selected - 20, p);
			selected = -1;
		}

	}

	public void draw(Graphics g) {

		g.drawImage(Game.gameBackground, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		for (int i = 0; i < playerBench.length; i++) {
			playerBench[i].draw(g);
		}
		for (int i = 0; i < enemyBench.length; i++) {
			enemyBench[i].draw(g);
		}
		playerPoke.draw(g);
		enemyPoke.draw(g);
		playerDeck.draw(g);
		enemyDeck.draw(g);
		playerPrize.draw(g);
		enemyPrize.draw(g);
		endTurn.draw(g);
		retreat.draw(g);
		drawCard.draw(g);

		// draw player's prize card
		if (player.getPrize().size() != 0) {
			player.getPrize().get(player.getPrize().size() - 1).draw(g, playerPrize.x, playerPrize.y, false, false);
		}

		// draw player's deck card
		if (player.getDeck().size() != 0) {
			player.getDeck().get(player.getDeck().size() - 1).draw(g, playerDeck.x, playerDeck.y, false, false);
		}

		// indicate player's deck number
		if (Game.getMouseRect().intersects(new Rectangle(playerDeck.x, playerDeck.y, Game.CARD_W, Game.CARD_H))) {
			g.setColor(Color.black);
			g.drawString("Player's Deck: " + player.getDeck().size(), playerDeck.x - 50, playerDeck.y - 20);
		}

		// indicate player's prize number
		if (Game.getMouseRect().intersects(new Rectangle(playerPrize.x, playerPrize.y, Game.CARD_W, Game.CARD_H))) {
			g.setColor(Color.black);
			g.drawString("Player's Prize: " + player.getPrize().size(), playerPrize.x - 50, playerPrize.y - 20);
		}

		// draw player's bench
		if (player.getBench().size() != 0) {
			for (int i = 0; i < player.getBench().size(); i++) {
				player.getBench().get(i).draw(g, playerBench[i].x, playerBench[i].y, false, true);

				if (selected != -1 && 20 <= selected)
					player.getBench().get(selected - 20).draw(g, 100, 175, true, true);
				else if (mouseOver != -1 && 11 == mouseOver)
					player.getBench().get(i).draw(g, 100, 175, true, true);
			}
		}

		// draw enemy's hand
		for (int i = 0; i < enemy.hand.size(); i++)
			enemy.hand.get(i).draw(g, enemy.hand.get(i).x, enemy.hand.get(i).y, true, false);
		// draw player's hand
		for (int i = 0; i < player.hand.size(); i++) {
			if (i != selected)
				player.hand.get(i).draw(g, player.hand.get(i).x, player.hand.get(i).y);
			if (mouseOver != -1 && i == mouseOver && !Game.getMouseManager().LDragging)
				player.getHand().get(mouseOver).draw(g, player.hand.get(i).x, player.hand.get(i).y - 350, true, true);
		}
		if (selected != -1 && selected < player.getHand().size())
			player.hand.get(selected).draw(g, player.hand.get(selected).x, player.hand.get(selected).y);

		// draw player's pokemon
		if (player.getPoke() != null) {
			player.getPoke().draw(g, playerPoke.x, playerPoke.y, false, true);
			if (selected != -1 && 11 == selected)
				player.getPoke().draw(g, 100, 175, true, true);
			else if (mouseOver != -1 && 11 == mouseOver)
				player.getPoke().draw(g, 100, 175, true, true);
		}

	}
}
