package com.pokemon.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.pokemon.Abilities.Abilities;
import com.pokemon.Card.Card;
import com.pokemon.Card.Energy;
import com.pokemon.Card.Pokemon;
import com.pokemon.Card.Trainer;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Strategies.StatusAI;
import com.pokemon.Strategies.Strategy;

public class GameInterface {
	public static CardSpot[] playerBench, enemyBench; //
	public static CardSpot playerPoke, enemyPoke, playerDeck, enemyDeck, playerPrize, enemyPrize, playerDiscard,
			enemyDiscard; //
	private Button endTurn, retreat, showDiscard;
	private int selected = -1, mouseOver = -1; // 0-6 player hand; 11 player
												// pokemon; 20-24 player bench;
												// 31 AI pokemon; 40-44 AI bench
	private Player player;
	private Enemy enemy;
	public static int turn;
	public static boolean playerTurn;
	private Card movingCard = null;
	private Strategy AIStrategy;
	private String choiceRange;
	private Abilities choiceAbilities;
	// private boolean drawnCard;

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
		playerPrize = new CardSpot(450 + 1 * space + Game.CARD_W * 1, Game.HEIGHT / 2 + hSpace / 2 + 10);
		enemyPrize = new CardSpot(450 + 1 * space + Game.CARD_W * 1, Game.HEIGHT / 2 - hSpace / 2 - Game.CARD_H + 10);
		playerDiscard = new CardSpot(300 + 1 * space + Game.CARD_W * 1, Game.HEIGHT / 2 + hSpace / 2 + 10);
		enemyDiscard = new CardSpot(300 + 1 * space + Game.CARD_W * 1, Game.HEIGHT / 2 - hSpace / 2 - Game.CARD_H + 10);

		endTurn = new Button(Game.WIDTH - 72, Game.HEIGHT / 2 - 25, 50, "End Turn", Color.WHITE,
				new Color(49, 156, 12));
		// drawCard = new Button(Game.WIDTH - 72, Game.HEIGHT / 2 + 70, 50,
		// "Draw Card", Color.WHITE,
		// new Color(49, 156, 12));
		retreat = new Button(Game.WIDTH - 72, Game.HEIGHT / 2 + 70, 50, "Retreat", Color.WHITE, new Color(49, 156, 12));
		showDiscard = new Button(Game.WIDTH - 72, Game.HEIGHT / 2 + 165, 50, "Discards", Color.WHITE,
				new Color(49, 156, 12));
		player = ObjectHandler.getPlayer();
		enemy = ObjectHandler.getEnemy();
		AIStrategy = new StatusAI();
		turn = 1;
		playerTurn = true;
		choiceRange = null;
		choiceAbilities = null;
		// drawnCard = false;

	}

	public void update() {
		checkWin();
		checkLose();
		player.update();
		enemy.update();
		
		if(movingCard == null)
			checkMouse();

		

		if (choiceAbilities == null) {
			if (playerTurn) {
				if (player.getPoke() != null)
					player.getPoke().checkStatus();
				if (movingCard == null) {

					mouseOver = -1;

					endTurn.update();
					retreat.update();
					showDiscard.update();
					
					if (retreat.isPressed()) {
						retreat();
						retreat.setPressed(false);
					}


					if (endTurn.isPressed()) {
						endTurn();
						endTurn.setPressed(false);
					}
					
					if (showDiscard.isPressed()) {
						Game.state = Game.State.SHOWDISCARD;
						showDiscard.setPressed(false);
					}
					
					checkMouse();
					selectPoke();
					checkEvolve();

				} else {
				//	System.out.println("is dragging...");
					movingCard.setX(Game.getMouseManager().MX - 40);
					movingCard.setY(Game.getMouseManager().MY - 128 / 2);
					// when the dragging mouse is released
					if (!Game.getMouseManager().LPressed) {
						boolean flag = false;

						if (player.getPoke() != null && Math.abs(movingCard.x - player.getPoke().x) < 40
								&& Math.abs(movingCard.y - player.getPoke().y) < 40) {
							player.getPoke().addEnergy((Energy) movingCard);
							player.getHand().remove(selected - 60);
							flag = true;
						}
						if (!flag) {
							for (int i = 0; i < player.getBench().size(); i++) {
								if (Math.abs(movingCard.x - player.getBench().get(i).x) < 40
										&& Math.abs(movingCard.y - player.getBench().get(i).y) < 40) {
									Pokemon p = (Pokemon) player.getBench().get(i);
									p.addEnergy((Energy) movingCard);
									player.getHand().remove(selected - 60);
									flag = true;
									break;
								}
							}
						}
						if (!flag) {
							if(player.getHand().size() <= 10){
								movingCard.setX(500 + 90 * (selected - 60));
								movingCard.setY(685);
							}else if(10 < player.getHand().size() && 19 >= player.getHand().size()){
								movingCard.setX(500 + 45 * (selected - 60));
								movingCard.setY(685);
							}
						}
						movingCard = null;
						Game.getMouseManager().LPressed = false;
						selected = -1;

					}
				}

//				 System.out.println(mouseOver + "---(" + selected + ")" +
//				 "---");

				// select energy card
				if ((selected >= 60 && selected - 60 < player.getHand().size())
						&& player.getHand().get(selected - 60).getCardType().equals(CardType.Engergy)) {

					if (Game.getMouseManager().LDragging) {
						movingCard = player.getHand().get(selected - 60);
						movingCard.setDragging(true);

					}
				}

			} else if (!playerTurn) {
				selected = -1;
				if (player.getPoke() != null)
					player.getPoke().turn();
				if (enemy.getPoke() != null)
					enemy.getPoke().checkStatus();
				AIStrategy.turn();
				if (enemy.getPoke() != null)
					enemy.getPoke().turn();
				player.getHand().add(player.drawOneCard());

			}

		} else if (choiceAbilities != null) {
			boolean flag = false;
			mouseOver = -1;
			 System.out.println(this.choiceRange + ": " + selected);
			if (!flag && "choice:opponent-bench".equals(choiceRange)) {
				if (selected >= 40 && selected <= 44) {
					choiceAbilities.turn(selected - 40);
					flag = true;
				}else
					selected = -1;
				if(enemy.getBench().size() == 0){
					flag = true;
				}
			}
			if (!flag && "choice:opponent".equals(choiceRange)) {
				if ((selected >= 40 && selected <= 44) || selected == 31) {
					choiceAbilities.turn(selected - 40);
					flag = true;
				}else
					selected = -1;
				if(enemy.getBench().size() == 0 && enemy.getPoke() == null){
					flag = true;
				}

			}
			if (!flag && "choice:bench".equals(choiceRange)) {
				if (selected >= 20 && selected <= 24) {
					choiceAbilities.turn(selected - 20);
					flag = true;
				}else
					selected = -1;
				if(player.getBench().size() == 0){
					flag = true;
				}
			}
			
			if (flag) {
				this.choiceAbilities = null;
				this.choiceRange = null;
				selected = -1;

			}

		}
	}

	public int getMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(int mouseOver) {
		this.mouseOver = mouseOver;
	}

	public static int getTurn() {
		return turn;
	}

	public static void setTurn(int turn) {
		GameInterface.turn = turn;
	}

	public static boolean isPlayerTurn() {
		return playerTurn;
	}

	public static void setPlayerTurn(boolean playerTurn) {
		GameInterface.playerTurn = playerTurn;
	}

	public Card getMovingCard() {
		return movingCard;
	}

	public void setMovingCard(Card movingCard) {
		this.movingCard = movingCard;
	}

	private void checkLose() {
		if (enemy.prize.size() == 0 || player.deck.size() == 0) {
			JOptionPane.showMessageDialog(null, "Sorry! You LOSE the game!!!");
			System.exit(0);
		}
	}

	private void checkWin() {
		if (player.prize.size() == 0 || enemy.deck.size() == 0) {
			JOptionPane.showMessageDialog(null, "Congratulations! You WIN!!!");
			System.exit(0);
		}
	}

	private void checkEvolve() {
		boolean flag = false;
		if (selected >= 60 && selected - 60 < player.getHand().size()
				&& player.getHand().get(selected - 60).getCardType() == CardType.Pokemon) {
			Pokemon p = (Pokemon) player.getHand().get(selected - 60);
			if (player.getPoke() != null && p.evolve(player.getPoke())) {
				int hitPoint = player.getPoke().getHP() - player.getPoke().getCurrentHP();
				ArrayList<Energy> list = player.getPoke().getEnergys();
				player.setPoke(p);
				player.getPoke().setCurrentHP(p.getHP() - hitPoint);
				player.getPoke().setEnergys(list);
				player.getPoke().setStatus(Pokemon.Status.normal);
				player.getPoke().setAttackable(true);
				player.getPoke().setRetreatable(true);
				player.getHand().remove(selected - 60);
				flag = true;
			}

			for (int i = 0; i < player.getBench().size(); i++) {
				if (!flag) {
					Pokemon pp = (Pokemon) player.getBench().get(i);
					if (p.evolve(pp)) {
						int hitPoint = pp.getHP() - pp.getCurrentHP();
						ArrayList<Energy> list = pp.getEnergys();
						player.getBench().set(i, p);
						Pokemon ppp = (Pokemon) player.getBench().get(i);
						ppp.setEnergys(list);
						ppp.setCurrentHP(p.getHP() - hitPoint);
						ppp.setStatus(Pokemon.Status.normal);
						ppp.setAttackable(true);
						ppp.setRetreatable(true);
						player.getHand().remove(selected - 60);
						flag = true;
					}
				}
			}

		}
	}

	private void endTurn() {
		playerTurn = false;
		// drawnCard = false;
	}

	// private void drawCard() {
	// if (!drawnCard) {
	// player.getHand().add(player.drawOneCard());
	// }
	// drawnCard = true;
	// }

	private void selectPoke() {
		// select player's pokemon
		if ((selected >= 60 && selected - 60 < player.getHand().size()) && player.getPoke() == null
				&& player.getHand().get(selected - 60).getCardCategory() == CardCategory.Basic) {
			player.setPoke((Pokemon) player.getHand().get(selected - 60));
			player.getHand().remove(selected - 60);
			Game.getMouseManager().LPressed = false;
			selected = -1;
		}

		// select player's bench
		if ((selected >= 60 && selected - 60 < player.getHand().size()) && player.getPoke() != null
				&& player.getHand().get(selected - 60).getCardCategory() == CardCategory.Basic
				&& player.getBench().size() < 5) {
			player.getBench().add((Pokemon) player.getHand().get(selected - 60));
			player.getHand().remove(selected - 60);
			Game.getMouseManager().LPressed = false;
			selected = -1;
		}

	}

	private void checkMouse() {
		// check mouse loc & player's hand
		for (int i = 0; i < player.getHand().size(); i++) {
			if (player.getHand().get(i).getRect().intersects(Game.getMouseRect())) {
				mouseOver = i + 60;
				if (Game.getMouseManager().LPressed) {
					selected = mouseOver;
				}
				break;
			}
		}
		// check mouse loc & player's pokemon
		if (player.getPoke() != null && player.getPoke().getRect().intersects(Game.getMouseRect())) {
			if (Game.getMouseManager().LPressed) {
				selected = 11;
			}
		}
		// check mouse loc & player's bench
		for (int i = 0; i < player.getBench().size(); i++) {
			if (player.getBench().get(i).getRect().intersects(Game.getMouseRect())) {
				if (Game.getMouseManager().LPressed) {
					selected = i + 20;
				}
				break;
			}
		}
		// check mouse loc & AI's pokemon
		if (enemy.getPoke() != null && enemy.getPoke().getRect().intersects(Game.getMouseRect())) {
			if (Game.getMouseManager().LPressed) {
				selected = 31;
			}
		}
		// check mouse loc & AI's bench
		for (int i = 0; i < enemy.getBench().size(); i++) {
			if (enemy.getBench().get(i).getRect().intersects(Game.getMouseRect())) {
				if (Game.getMouseManager().LPressed) {
					selected = i + 40;
				}
				break;
			}
		}
	}

	private void retreat() {

		if (selected >= 20 && selected <= 24) {

			Pokemon p = player.getPoke();
			if (player.getPoke() == null) {
				player.setPoke((Pokemon) player.getBench().get(selected - 20));
				player.getBench().remove(selected - 20);
			} else {
				if (player.getPoke().isRetreatable() && player.getPoke().getRetreatCost() != null) {
					if (player.getPoke().getEnergys().size() >= Integer.parseInt(player.getPoke().getRetreatCost())) {
						ArrayList<Card> list = player.getPoke()
								.costEnergy(Integer.parseInt(player.getPoke().getRetreatCost()));
						player.getGraveyard().addAll(list);
						System.out.println(player.getGraveyard().size());
						player.setPoke((Pokemon) player.getBench().get(selected - 20));
						player.getBench().set(selected - 20, p);
					}
				}

			}
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
		playerDiscard.draw(g);
		enemyDiscard.draw(g);
		endTurn.draw(g);
		retreat.draw(g);
		showDiscard.draw(g);
		// drawCard.draw(g);
		Font f = g.getFont();
		g.setFont(new Font("DorFont03", Font.PLAIN, 60));
		g.setColor(Color.BLACK);
		g.drawString("TURN: " + GameInterface.turn, 1140, 150);
		g.setFont(f);

		// draw player's pokemon
		if (player.getPoke() != null) {
			player.getPoke().draw(g, playerPoke.x, playerPoke.y, false, true);
			if (selected != -1 && 11 == selected) {
				player.getPoke().draw(g, 100, 175, true, true);
				for (Button b : player.getPoke().getAbility()) {
					b.draw(g);
					b.update();
					if (b.isPressed()) {
						if (player.getPoke().isAttackable() && player.getPoke().attackButton(b)) {
							player.checkKnockout();
							enemy.checkKnockout();
							selected = -1;

							endTurn();
						}
						b.setPressed(false);
					}
				}
			} else if (mouseOver != -1 && 11 == mouseOver) {
				player.getPoke().draw(g, 100, 175, true, true);
			}
		}

		// draw player's trainer card

		if (selected >= 60 && selected < 60 + player.getHand().size()
				&& player.getHand().get(selected - 60).getCardType() == CardType.Trainer) {
			player.getHand().get(selected - 60).draw(g, 100, 175, true, true);
			Trainer t = (Trainer) player.getHand().get(selected - 60);
			Button b = t.getButton();
			b.draw(g);
			b.update();
			if (b.isPressed()) {
				t.getAbility().turn("enemy");
				player.getGraveyard().add(t);
				player.getHand().remove(t);
				b.setPressed(false);
				selected = -1;
			}
		}

		// draw AI's pokemon
		if (enemy.getPoke() != null) {
			enemy.getPoke().draw(g, enemyPoke.x, enemyPoke.y, false, true);
		}

		// draw player's prize card
		if (player.getPrize().size() != 0) {
			player.getPrize().get(0).draw(g, playerPrize.x, playerPrize.y, false, false);
		}

		// draw player's deck card
		if (player.getDeck().size() != 0) {
			player.getDeck().get(0).draw(g, playerDeck.x, playerDeck.y, false, false);
		}

		// draw enemy's prize card
		if (enemy.getPrize().size() != 0) {
			enemy.getPrize().get(0).draw(g, enemyPrize.x, enemyPrize.y, false, false);
		}

		// draw enemy's deck card
		if (enemy.getDeck().size() != 0) {
			enemy.getDeck().get(0).draw(g, enemyDeck.x, enemyDeck.y, false, false);
		}

		// draw player's discard card
		if (player.getGraveyard().size() != 0) {
			player.getGraveyard().get(player.getGraveyard().size() - 1).draw(g, playerDiscard.x, playerDiscard.y, false,
					true);
		}

		// draw enemy's discard card
		if (enemy.getGraveyard().size() != 0) {
			enemy.getGraveyard().get(enemy.getGraveyard().size() - 1).draw(g, enemyDiscard.x, enemyDiscard.y, false,
					true);
		}

		// draw player's bench
		if (player.getBench().size() != 0) {
			for (int i = 0; i < player.getBench().size(); i++) {
				player.getBench().get(i).draw(g, playerBench[i].x, playerBench[i].y, false, true);

				if (selected != -1 && 20 <= selected && selected <= 24)
					player.getBench().get(selected - 20).draw(g, 100, 175, true, true);
				// else if (mouseOver != -1 && 11 == mouseOver)
				// player.getBench().get(i).draw(g, 100, 175, true, true);
			}
		}

		// draw AI's bench
		if (enemy.getBench().size() != 0) {
			for (int i = 0; i < enemy.getBench().size(); i++) {
				enemy.getBench().get(i).draw(g, enemyBench[i].x, enemyBench[i].y, false, true);

				if (selected != -1 && 40 <= selected && selected <= 44)
					enemy.getBench().get(selected - 40).draw(g, 100, 175, true, true);
			}
		}
		// draw AI's active
		if (selected != -1 && 31 == selected) {
			enemy.getPoke().draw(g, 100, 175, true, true);
			for (Button b : enemy.getPoke().getAbility()) {
				b.draw(g);
			}

		}

		// draw enemy's hand
		for (int i = 0; i < enemy.hand.size(); i++)
			enemy.hand.get(i).draw(g, enemy.hand.get(i).x, enemy.hand.get(i).y, true, false);
		// draw player's hand
		for (int i = player.hand.size() - 1; i >= 0 ; i--) {
			//if (i != mouseOver - 60)
				player.hand.get(i).draw(g, player.hand.get(i).x, player.hand.get(i).y);
			if (mouseOver != -1 && i == (mouseOver - 60) && !Game.getMouseManager().LDragging)
				player.getHand().get(i).draw(g, player.hand.get(i).x, player.hand.get(i).y - 350, true, true);
		}

//		// draw player's hand detail
//		if (selected >= 60 && selected - 60  < player.getHand().size())
//			player.hand.get(selected - 60).draw(g, player.hand.get(selected - 60).x, player.hand.get(selected - 60).y);
		for(Card c: player.getHand()){
			if(c == movingCard)
				c.draw(g, c.getX(), c.getY());
		}
		
		
		
		
		if (movingCard == null) {
			// indicate player's fighting pokemon status
			if (player.getPoke() != null && Game.getMouseRect()
					.intersects(new Rectangle(playerPoke.x, playerPoke.y, Game.CARD_W, Game.CARD_H))) {
				g.setColor(Color.black);
				g.setFont(new Font("DorFont03", Font.PLAIN, 26));
				g.drawString("Remaining HP: " + player.getPoke().getCurrentHP(), playerPoke.x - 50, playerPoke.y - 35);
				g.drawString("Status: " + player.getPoke().getStatus(), playerPoke.x - 50, playerPoke.y - 15);

			}

			// indicate AI's fighting pokemon status
			if (enemy.getPoke() != null && Game.getMouseRect()
					.intersects(new Rectangle(enemyPoke.x, enemyPoke.y, Game.CARD_W, Game.CARD_H))) {
				g.setColor(Color.black);
				g.setFont(new Font("DorFont03", Font.PLAIN, 26));
				g.drawString("Remaining HP: " + enemy.getPoke().getCurrentHP(), enemyPoke.x - 50, enemyPoke.y - 35);
				g.drawString("Status: " + enemy.getPoke().getStatus(), enemyPoke.x - 50, enemyPoke.y - 15);

			}

			// indicate player's bench pokemon status
			for (int i = 0; i < player.getBench().size(); i++) {
				if (Game.getMouseRect().intersects(new Rectangle(player.getBench().get(i).x, player.getBench().get(i).y,
						Game.CARD_W, Game.CARD_H))) {
					g.setColor(Color.black);
					g.setFont(new Font("DorFont03", Font.PLAIN, 26));
					Pokemon p = (Pokemon) player.getBench().get(i);
					g.drawString("Remaining HP: " + p.getCurrentHP(), player.getBench().get(i).x - 50,
							player.getBench().get(i).y - 35);
					g.drawString("Status: " + p.getStatus(), player.getBench().get(i).x - 50,
							player.getBench().get(i).y - 15);
				}
			}

			// indicate enemy's bench pokemon status
			for (int i = 0; i < enemy.getBench().size(); i++) {
				if (Game.getMouseRect().intersects(new Rectangle(enemy.getBench().get(i).x, enemy.getBench().get(i).y,
						Game.CARD_W, Game.CARD_H))) {
					g.setColor(Color.black);
					g.setFont(new Font("DorFont03", Font.PLAIN, 26));
					Pokemon p = (Pokemon) enemy.getBench().get(i);
					g.drawString("Remaining HP: " + p.getCurrentHP(), enemy.getBench().get(i).x - 50,
							enemy.getBench().get(i).y - 35);
					g.drawString("Status: " + p.getStatus(), enemy.getBench().get(i).x - 50,
							enemy.getBench().get(i).y - 15);
				}
			}

		}

		// indicate player's deck number
		if (Game.getMouseRect().intersects(new Rectangle(playerDeck.x, playerDeck.y, Game.CARD_W, Game.CARD_H))) {
			g.setColor(Color.black);
			g.setFont(new Font("DorFont03", Font.PLAIN, 24));
			g.drawString("Player's Deck: " + player.getDeck().size(), playerDeck.x - 50, playerDeck.y - 20);
		}

		// indicate player's prize number
		if (Game.getMouseRect().intersects(new Rectangle(playerPrize.x, playerPrize.y, Game.CARD_W, Game.CARD_H))) {
			g.setColor(Color.black);
			g.setFont(new Font("DorFont03", Font.PLAIN, 24));
			g.drawString("Player's Prize: " + player.getPrize().size(), playerPrize.x - 50, playerPrize.y - 20);
		}

		// indicate enemey's deck number
		if (Game.getMouseRect().intersects(new Rectangle(enemyDeck.x, enemyDeck.y, Game.CARD_W, Game.CARD_H))) {
			g.setColor(Color.black);
			g.setFont(new Font("DorFont03", Font.PLAIN, 24));
			g.drawString("AI's Deck: " + enemy.getDeck().size(), enemyDeck.x - 50, enemyDeck.y - 20);
		}

		// indicate enemy's prize number
		if (Game.getMouseRect().intersects(new Rectangle(enemyPrize.x, enemyPrize.y, Game.CARD_W, Game.CARD_H))) {
			g.setColor(Color.black);
			g.setFont(new Font("DorFont03", Font.PLAIN, 24));
			g.drawString("AI's Prize: " + enemy.getPrize().size(), enemyPrize.x - 50, enemyPrize.y - 20);
		}

		// indicate player's discard number
		if (Game.getMouseRect().intersects(new Rectangle(playerDiscard.x, playerDiscard.y, Game.CARD_W, Game.CARD_H))) {
			g.setColor(Color.black);
			g.setFont(new Font("DorFont03", Font.PLAIN, 24));
			g.drawString("Player's Discard: " + player.getGraveyard().size(), playerDiscard.x - 50,
					playerDiscard.y - 20);
		}

		// indicate enemy's discard number
		if (Game.getMouseRect().intersects(new Rectangle(enemyDiscard.x, enemyDiscard.y, Game.CARD_W, Game.CARD_H))) {
			g.setColor(Color.black);
			g.setFont(new Font("DorFont03", Font.PLAIN, 24));
			g.drawString("Enemy's Discard: " + enemy.getGraveyard().size(), enemyDiscard.x - 50, enemyDiscard.y - 20);
		}

	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public String getChoiceRange() {
		return choiceRange;
	}

	public void setChoiceRange(String choiceRange) {
		this.choiceRange = choiceRange;
	}

	public Abilities getChoiceAbilities() {
		return choiceAbilities;
	}

	public void setChoiceAbilities(Abilities choiceAbilities) {
		this.choiceAbilities = choiceAbilities;
	}

}
