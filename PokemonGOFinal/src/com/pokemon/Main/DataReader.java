package com.pokemon.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.pokemon.Card.Card;
import com.pokemon.Card.CardFactory;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;

public class DataReader {
	BufferedReader bufferedReader;

	public ArrayList<Card> loadData(String url, int deck) {
		ArrayList<Card> list = new ArrayList<Card>();
		try {
			String encoding = "utf-8";
			File file = new File(url);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				boolean pokemon = false;
				boolean energy = false;
				boolean trainer = false;
				CardFactory cf = new CardFactory();

				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (lineTxt.contains("##Pokémon")) {
						pokemon = true;
						energy = false;
						trainer = false;
					}
					if (lineTxt.contains("##Trainer Cards")) {
						pokemon = false;
						energy = false;
						trainer = true;
					}
					if (lineTxt.contains("##Energy")) {
						pokemon = false;
						energy = true;
						trainer = false;
					}					
					if (lineTxt.contains("Total Cards")) {
						pokemon = false;
						energy = false;
						trainer = false;
					}
					if (pokemon) {
						if (lineTxt.startsWith("* ")) {
							String[] s = lineTxt.split(" ");
							int number = Integer.parseInt(s[1]);
							String name = s[2];
							int stage = Integer.parseInt(s[3]);
							String evolution = s[4];
							int HP = Integer.parseInt(s[5]);
							String ability1 = s[6];
							int attackHit1 = Integer.parseInt(s[7]);
							String ability2 = s[8];
							int attackHit2 = Integer.parseInt(s[9]);

							for (int i = 0; i < number; i++) {
								if (0 == stage) {
									Card card = cf.createCard(name, CardType.Pokemon, CardCategory.Basic, deck, evolution, HP,
											ability1, attackHit1, ability2, attackHit2);
									list.add(card);
								} else if (1 == stage) {
									Card card = cf.createCard(name, CardType.Pokemon, CardCategory.StageOne, deck, evolution, HP,
											ability1, attackHit1, ability2, attackHit2);
									list.add(card);
								}
							}
						}
					} else if (trainer) {
						if (lineTxt.startsWith("* ")) {
							// String[] s = lineTxt.split(" ");
							// int number = Integer.parseInt(s[0]);
							// String name = s[1];
							// int stage = Integer.parseInt(s[2]);
							// String evolution = s[3];
							// int HP = Integer.parseInt(s[4]);
							// String ability1 = s[5];
							// int attackHit1 = Integer.parseInt(s[6]);
							// String ability2 = s[7];
							// int attackHit2 = Integer.parseInt(s[8]);
							//
							// for (int i = 0; i < number; i++) {
							// if (0 == stage) {
							// Card card = cf.createCard(name, CardType.Pokemon,
							// CardCategory.Basic, deck, HP,
							// ability1, attackHit1, ability2, attackHit2);
							// }
							// if (1 == stage) {
							// Card card = cf.createCard(name, CardType.Pokemon,
							// CardCategory.StageOne, deck, HP,
							// ability1, attackHit1, ability2, attackHit2);
							// }
							// }
						}
					} else if (energy) {
						if (lineTxt.startsWith("* ")) {
							String[] s = lineTxt.split(" ");
							int number = Integer.parseInt(s[0]);
							String name = s[1];

							for (int i = 0; i < number; i++) {
								if (name.equals("Water")) {
									Card card = cf.createCard(name, CardType.Engergy, CardCategory.Water, deck);
									list.add(card);
								} else if (name.equals("Fighting")) {
									Card card = cf.createCard(name, CardType.Engergy, CardCategory.Fighting, deck);
									list.add(card);
								} else if (name.equals("Psychic")) {
									Card card = cf.createCard(name, CardType.Engergy, CardCategory.Psychic, deck);
									list.add(card);
								} else if (name.equals("Lightning")) {
									Card card = cf.createCard(name, CardType.Engergy, CardCategory.Lightning, deck);
									list.add(card);
								} else if (name.equals("Colorless")) {
									Card card = cf.createCard(name, CardType.Engergy, CardCategory.Colorless, deck);
									list.add(card);
								}
							}
						}
					}

				}

				
			} else {
				System.out.println("not find file");
			}
		} catch (Exception e) {
			System.out.println("error reading the contents of the file");
			e.printStackTrace();
		} finally {

			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return list;
	}

}
