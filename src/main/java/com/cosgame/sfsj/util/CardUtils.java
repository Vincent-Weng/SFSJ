package com.cosgame.sfsj.util;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import java.util.Random;

public class CardUtils {
  private static final Random rand = new Random();

  public static Card cardOfRank(int rank) {
    if (rank < 1 || rank > 13) {
      throw new IllegalArgumentException("Illegal rank integer. Must be in [1, 13]");
    }
    return Card.of(CardRank.values()[rank], CardSuit.values()[rand.nextInt(4)]);
  }

  public static Card cardOfRank(CardRank rank) {
    return Card.of(rank, CardSuit.values()[rand.nextInt(4)]);
  }

  public static Card cardOfSuit(CardSuit suit) {
    return Card.of(CardRank.values()[rand.nextInt(14) + 1], suit);
  }
}
