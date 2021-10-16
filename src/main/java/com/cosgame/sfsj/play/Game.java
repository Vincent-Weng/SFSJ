package com.cosgame.sfsj.play;

import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.cosgame.sfsj.common.GameDeck;

public class Game {

  private final GameDeck gameDeck = new GameDeck(4);
  private CardRank dominantRank; // pre-set for this game
  private CardSuit dominantSuit; // determined during dealing


}
