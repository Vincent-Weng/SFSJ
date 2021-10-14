package com.cosgame.sfsj.play;

import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.cosgame.sfsj.common.GameDeck;

public class Game {

  private CardRank dominantRank; // pre-set for this game
  private CardSuit dominantSuit; // determined during dealing
  private final GameDeck gameDeck = new GameDeck(4);


}
