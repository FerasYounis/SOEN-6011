package com.pokemon.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.pokemon.Card.Card;
import com.pokemon.Card.CardFactory;
import com.pokemon.Card.Pokemon;

public class Player extends GameObject {

	protected ArrayList<Card> deck, hand, bench, graveyard, prize;
	protected Pokemon poke;
	private CardFactory cf;

	public Player() {
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		bench = new ArrayList<Card>();
		poke = null;
		graveyard = new ArrayList<Card>();
		prize = new ArrayList<Card>();
		cf = new CardFactory();
		setDeck();

		for (int i = 0; i < 7; i++) {

			hand.add(drawOneCard());
		}

		// hand.add(cf.createCard("Dodrio", Card.Type.pokemon, 1));
		// hand.add(cf.createCard("Doduo", Card.Type.pokemon, 1));
		// hand.add(cf.createCard("Dugtrio", Card.Type.pokemon,1));
		// hand.add(cf.createCard("Energy Switch", Card.Type.trainer,1));
		// hand.add(cf.createCard("Espurr", Card.Type.pokemon,1));
		// hand.add(cf.createCard("Fighting", Card.Type.energy, 1));
		// hand.add(cf.createCard("Fighting", Card.Type.energy, 1));
		// hand.add(cf.createCard("Fighting", Card.Type.energy, 1));
		// hand.add(cf.createCard("Fighting", Card.Type.energy, 1));

		// initial hand loc
		for (int i = 0; i < hand.size(); i++) {
			hand.get(i).setX(500 + 90 * i);
			hand.get(i).setY(685);
		}
	}

	public void update() {
		if (!Game.getMouseManager().LDragging) {

			for (int i = 0; i < hand.size(); i++) {
				hand.get(i).setX(500 + 90 * i);
				hand.get(i).setY(685);
			}

			if (poke != null) {
				poke.setX(GameInterface.playerPoke.x);
				poke.setY(GameInterface.playerPoke.y);
			}

			for (int i = 0; i < bench.size(); i++) {
				bench.get(i).setX(GameInterface.playerBench[i].x);
				bench.get(i).setY(GameInterface.playerBench[i].y);
			}

		}

	}

	public ArrayList<Card> getDeck() {

		return deck;

	}

	public void setDeck() {
		List<String> list = new ArrayList<String>();
		try {
			String encoding = "utf-8";
			File file = new File("deck1.txt");
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;

				while ((lineTxt = bufferedReader.readLine()) != null) {
					// Card card = cf.createCard(lineTxt, CardType.Pokemon, 1);
					// Card card = cf.createCard(name, type, level, deck, HP,
					// ability1, attackHit1, ability2, attackHit2);
					// deck.add(card);
				}
				bufferedReader.close();
				read.close();
			} else {
				System.out.println("not find file");
			}
		} catch (Exception e) {
			System.out.println("error reading the contents of the file");
			e.printStackTrace();
		}
		// for (String string : list) {
		// System.out.println(string);
		// }

	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public ArrayList<Card> getBench() {
		return bench;
	}

	public void setBench(ArrayList<Card> bench) {
		this.bench = bench;
	}

	public Pokemon getPoke() {
		return poke;
	}

	public void setPoke(Pokemon poke) {
		this.poke = poke;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}

	public void setGraveyard(ArrayList<Card> graveyard) {
		this.graveyard = graveyard;
	}

	public Card drawOneCard() {

		Card card = deck.get(deck.size() - 1);
		deck.remove(deck.size() - 1);
		return card;
	}

}
