package com.cosgame.sfsj.common;

import java.util.Arrays;
import java.util.List;

public class Hand {

  private final List<Card> cards;
  private Integer points;

  public Hand(List<Card> cards) {
    this.cards = cards;
  }

  public Hand(Card... cards) {
    this.cards = Arrays.asList(cards);
  }

  public synchronized int getPoints() {
    if (points != null) {
      return points;
    }
    points = 0;
    for (Card c : cards) {
      switch (c.getRank()) {
        case TEN:
        case KING:
          points += 10;
          break;
        case FIVE:
          points += 5;
      }
    }
    return points;
  }
}
