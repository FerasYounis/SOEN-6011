package com.pokemon.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import com.pokemon.Card.Card;
import com.pokemon.Card.CardFactory;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;

public class DataReader2 {
	BufferedReader deckReader;
	BufferedReader cardReader;
	BufferedReader abilityReader;
	CardFactory cf;

	ArrayList<Card> deck;
	int[] deckNum;
	String[] cardData;
	String[] abilityData;
	int deckNumber;

	public DataReader2(String url) {
		deck = new ArrayList<Card>();
		deckNum = new int[60];
		cardData = new String[100];
		abilityData = new String[100];
		deckNumber = Integer.parseInt(url.substring(url.length() - 5, url.length() - 4));
		loadDeck(url);
		loadCard();
		loadAbility();
		cf = new CardFactory();

		for (int i = 0; i < deckNum.length; i++) {
			if (deckNum[i] != 0) {
				String cardLine = cardData[deckNum[i] - 1];
				System.out.println(i + ": " + cardLine);

				if (cardLine.contains("basic") || cardLine.contains("stage-one")) {
					Card pokemon = null;
					String[] twoSec = cardLine.split(":attacks:");
					String[] threeSec = twoSec[0].split(":retreat:");

					String basicData = threeSec[0];
					String retreatCost = null;
					if (threeSec.length > 1) {
						String[] retreat = threeSec[1].split(":");
						retreatCost = retreat[2];

					}
					String abilityData = twoSec[1];

					if (cardLine.contains("basic")) {
						String[] datas = basicData.split(":");

						String name = datas[0].replaceAll(" ", "");
						String attr = datas[5];
						int Hp = Integer.parseInt(datas[6]);
						if ("water".equals(attr)) {
							pokemon = cf.createCard(name, CardType.Pokemon, CardCategory.Basic,
									deckNumber, null, Hp, null, retreatCost,
									CardCategory.Water);
						}
						if ("colorless".equals(attr)) {
							pokemon = cf.createCard(name, CardType.Pokemon, CardCategory.Basic,
									deckNumber, null, Hp, null, retreatCost,
									CardCategory.Colorless);
						}
						if ("fight".equals(attr)) {
							pokemon = cf.createCard(name, CardType.Pokemon, CardCategory.Basic,
									deckNumber, null, Hp, null, retreatCost,
									CardCategory.Fighting);
						}
						if ("lightning".equals(attr)) {
							pokemon = cf.createCard(name, CardType.Pokemon, CardCategory.Basic,
									deckNumber, null, Hp, null, retreatCost,
									CardCategory.Lightning);
						}
						if ("psychic".equals(attr)) {
							pokemon = cf.createCard(name, CardType.Pokemon, CardCategory.Basic,
									deckNumber, null, Hp, null, retreatCost,
									CardCategory.Psychic);
						}

					} else if (cardLine.contains("stage-one")) {
						String[] datas = basicData.split(":");

						String name = datas[0].replaceAll(" ", "");
						String basicName = datas[4].replaceAll(" ", "");
						String attr = datas[6];
						int Hp = Integer.parseInt(datas[7]);

						if ("water".equals(attr)) {
							pokemon = cf.createCard(name, CardType.Pokemon, CardCategory.StageOne,
									deckNumber, basicName, Hp, null, retreatCost,
									CardCategory.Water);
						}
						if ("colorless".equals(attr)) {
							pokemon = cf.createCard(name, CardType.Pokemon, CardCategory.StageOne,
									deckNumber, basicName, Hp, null, retreatCost,
									CardCategory.Colorless);
						}
						if ("fight".equals(attr)) {
							pokemon = cf.createCard(name, CardType.Pokemon, CardCategory.StageOne,
									deckNumber, basicName, Hp, null, retreatCost,
									CardCategory.Fighting);
						}
						if ("lightning".equals(attr)) {
							pokemon = cf.createCard(name, CardType.Pokemon, CardCategory.StageOne,
									deckNumber, basicName, Hp, null, retreatCost,
									CardCategory.Lightning);
						}
						if ("psychic".equals(attr)) {
							pokemon = cf.createCard(name, CardType.Pokemon, CardCategory.StageOne,
									deckNumber, basicName, Hp, null, retreatCost,
									CardCategory.Psychic);
						}

					}
					deck.add(pokemon);
				}

				else if (cardLine.contains("trainer")) {
					Card trainer = null;

					String[] datas = cardLine.split(":");
					String name = datas[0].replaceAll(" ", "");
					String catagory = datas[3];
					int despriction = Integer.parseInt(datas[4]);
				
					if("supporter".equals(catagory)){
						trainer = cf.createCard(name, CardType.Trainer, CardCategory.Supporter, deckNumber, null);
					}
					if("item".equals(catagory)){
						trainer = cf.createCard(name, CardType.Trainer, CardCategory.Item, deckNumber, null);
					}
					if("stadium".equals(catagory)){
						trainer = cf.createCard(name, CardType.Trainer, CardCategory.Stadium, deckNumber, null);
					}
					
					deck.add(trainer);
					
					
					
					
					
					
				} else if (cardLine.contains("energy")) {
					String[] datas = cardLine.split(":");
					Card card = null;
					if ("Water".equals(datas[0])) {
						card = cf.createCard(datas[0], CardType.Engergy, CardCategory.Water,
								deckNumber);
					}
					if ("Fight".equals(datas[0])) {
						card = cf.createCard("Fighting", CardType.Engergy, CardCategory.Fighting,
								deckNumber);
					}
					if ("Lightning".equals(datas[0])) {
						card = cf.createCard(datas[0], CardType.Engergy, CardCategory.Lightning,
								deckNumber);
					}
					if ("Psychic".equals(datas[0])) {
						card = cf.createCard(datas[0], CardType.Engergy, CardCategory.Psychic,
								deckNumber);
					}
					deck.add(card);
				}
			}
		}

	}

	public void loadDeck(String url) {
		deckReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(url)));
		String lineTxt = null;
		try {
			int i = 0;
			while ((lineTxt = deckReader.readLine()) != null) {
				deckNum[i++] = Integer.parseInt(lineTxt);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				deckReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadCard() {
		cardReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/cards.txt")));
		String lineTxt = null;
		try {
			int i = 0;
			while ((lineTxt = cardReader.readLine()) != null) {
				cardData[i++] = lineTxt;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				cardReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadAbility() {
		abilityReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/abilities.txt")));
		String lineTxt = null;
		try {
			int i = 0;
			while ((lineTxt = abilityReader.readLine()) != null) {
				abilityData[i++] = lineTxt;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				abilityReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
